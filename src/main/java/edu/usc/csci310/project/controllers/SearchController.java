package edu.usc.csci310.project.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import edu.usc.csci310.project.models.Park;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.cdimascio.dotenv.Dotenv;

@SuppressWarnings("ALL")
@RestController
public class SearchController {
    Dotenv dotenv = Dotenv.configure().filename("apikey.env").load();
    String apiKey = dotenv.get("API_KEY");
    String parkApiURL = "https://developer.nps.gov/api/v1/parks";
    String amenityApiURL = "https://developer.nps.gov/api/v1/amenities/parksplaces";
    String activityApiURL = "https://developer.nps.gov/api/v1/activities/parks";

    static ArrayList<Park> parkList = new ArrayList<Park>();
    static int currParkIndex = 0;

    @PostMapping("/getAdditionalInfo")
    public String getAdditionalInfo(@RequestBody String exactParkNameJson) {
        System.out.println("ENTERED GET ADDITIONAL INFO\n\n\n\n\n\n\n\n\n");
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(exactParkNameJson);
            System.out.println("exactParkNameJson: " + exactParkNameJson);
            JsonNode parkNameNode = rootNode.path("parkNames");
            System.out.println("parkNameNode: " + parkNameNode);

            if (parkNameNode.isMissingNode()) {
                return "Error: parkName is missing";
            }

            String parkName = parkNameNode.get(0).asText();
            System.out.println("parkName: " + parkName);
            String queryUrl = parkApiURL + "?q=" + parkName.replace(" ", "%20") + "&api_key=" + apiKey;

            // Perform the HTTP request
            HttpURLConnection conn = (HttpURLConnection) new URL(queryUrl).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", apiKey);
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            System.out.println("Response code: " + responseCode);


            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Parse response
                InputStream response = conn.getInputStream();
                ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length;

                while ((length = response.read(buffer)) != -1) {
                    resultStream.write(buffer, 0, length);
                }

                // convert the byte array to a string
                String ActualResultFR = resultStream.toString("UTF-8");
//                System.out.println("ActualResultFR: " + ActualResultFR);

                ObjectMapper APImapper = new ObjectMapper();

                JsonNode APIroot = APImapper.readTree(ActualResultFR);
                JsonNode dataArr = APIroot.path("data");
//                System.out.println("dataArr: " + dataArr);
                ArrayList<Park> parks = mapper.readValue(dataArr.toString(), new TypeReference<ArrayList<Park>>() {});
                System.out.println("parks: " + parks);
                return mapper.writeValueAsString(parks);
            } else {
                // Handle errors
                System.out.println("HTTP error code: " + responseCode);
                return "HTTP error code: " + responseCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    @PostMapping("/search")
    public String search(@RequestBody String query) {
        System.out.println("Searching for: " + query);
        System.out.println("API KEY: " + apiKey);
        try {
            // find where in the query request it says "type" and "params"
            ObjectMapper mapper = new ObjectMapper();

            JsonNode root = mapper.readTree(query);
            JsonNode type = root.path("type");
            JsonNode theWord = root.path("params").path("query"); // theWord is the search query
            JsonNode activity = root.path("params").path("activity"); // activity is the activity query
            JsonNode amenity = root.path("params").path("amenity"); // amenity is the amenity query
            JsonNode state = root.path("params").path("stateCode"); // state is the state query

            String typeOfSearch = type.asText();
            String searchQuery = theWord.asText();
            String activityQuery = activity.asText();
            String amenityQuery = amenity.asText();
            String stateQuery = state.asText();

            // if there is a space in any of the queries, replace it with %20
            searchQuery = searchQuery.replace(" ", "%20");
            activityQuery = activityQuery.replace(" ", "%20");
            amenityQuery = amenityQuery.replace(" ", "%20");
            stateQuery = stateQuery.replace(" ", "%20");

            String mergedURL = "";

            int bruhSearchType = 0;

            if(typeOfSearch.equals("search")) {
                mergedURL = parkApiURL + "?q=" + searchQuery + "&api_key="+ apiKey;
            }
            if (typeOfSearch.equals("state")) {
                mergedURL = parkApiURL + "?stateCode=" + stateQuery + "&api_key=" + apiKey;
                bruhSearchType = 1;
            }
            if (typeOfSearch.equals("amenity")) {
                mergedURL = amenityApiURL + "?q=" + amenityQuery + "&api_key=" + apiKey;
                 bruhSearchType = 2;
            }
            if (typeOfSearch.equals("activity")) {
                mergedURL = activityApiURL + "?q=" + activityQuery + "&api_key=" + apiKey;
                bruhSearchType = 3;
            }

            System.out.println("mergedURL: " + mergedURL);
            URL url = new URL(mergedURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Authorization", apiKey);
                conn.setRequestProperty("Accept", "application/json");

                int responseCode = conn.getResponseCode();

                if (responseCode == 200) {
                    // Success

                    InputStream response = conn.getInputStream();
                    ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int length;

                    while ((length = response.read(buffer)) != -1) {
                        resultStream.write(buffer, 0, length);
                    }

                    // convert the byte array to a string
                    String ActualResultFR = resultStream.toString("UTF-8");

                    // do a semantic search for the amenity/activity

                    ObjectMapper APImapper = new ObjectMapper();

                    JsonNode APIroot = APImapper.readTree(ActualResultFR);
                    JsonNode dataArr = APIroot.path("data");

                    // if the search type is word or state
                    if((bruhSearchType == 1) || (bruhSearchType == 0))
                    {
                        parkList = APImapper.readValue(dataArr.toString(), new TypeReference<ArrayList<Park>>() {
                        });

                        for(Park park : parkList) {
                            System.out.println(park.getName());
                        }
                    }
                    // if the search type is amenity
                    else if(bruhSearchType == 2)
                    {
                        System.out.println("amenity is: " + amenity.asText().replace("\"", ""));
                        for(JsonNode amenityNode : dataArr)
                        {
                            String amenityName = amenityNode.findPath("name").asText().replace("\"", "");
                            System.out.println("amenityName: " + amenityName);
                            if(amenityName.equals(amenity.asText().replace("\"", "")))
                            {
                                JsonNode parksNode = amenityNode.findPath("parks");
                                parkList = APImapper.readValue(parksNode.toString(), new TypeReference<ArrayList<Park>>() {
                                });
                                System.out.println("Amenity found: " + amenityName);

                                for(Park park : parkList) {
                                    System.out.println(park.getName());
                                }

                                break;
                            }
                        }
                    }
                    // if the search type is activity
                    else if(bruhSearchType == 3)
                    {
                        System.out.println("activity is: " + activity.asText().replace("\"", ""));
                        for(JsonNode activityNode : dataArr)
                        {
                            String activityName = activityNode.findPath("name").asText().replace("\"", "");
                            System.out.println("activityName: " + activityName);
                            if(activityName.equals(activity.asText().replace("\"", "")))
                            {
                                JsonNode parksNode = activityNode.findPath("parks");
                                parkList = APImapper.readValue(parksNode.toString(), new TypeReference<ArrayList<Park>>() {
                                });
                                System.out.println("Activity found: " + activityName);

                                for(Park park : parkList) {
                                    System.out.println(park.getName());
                                }

                                break;
                            }
                        }
                    }
                    else
                    {
                        return "Error, invalid search type";
                    }

                    // access one of the parkLists and print out the entire parkList
                    System.out.println("ParkList: " + APImapper.writeValueAsString(parkList.get(0)));

                    return APImapper.writeValueAsString(parkList);

//                    return ActualResultFR.toString();

                } else {
                    // Error
                    return "Error, response code: " + responseCode;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Error, exception: " + e.getMessage();
            }
    }
}

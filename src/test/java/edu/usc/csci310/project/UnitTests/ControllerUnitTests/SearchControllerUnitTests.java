//package edu.usc.csci310.project.ControllerUnitTests;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import edu.usc.csci310.project.controllers.SearchController;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@WebMvcTest(SearchController.class)
//public class SearchControllerUnitTests {
//
//    @Test
//    void testSearch() throws Exception {
//        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
//        JsonNode mockJson = mock(JsonNode.class);
//
//        HttpURLConnection httpURLConnection = mock(HttpURLConnection.class);
//        InputStream is = mock(InputStream.class);
//        when(httpURLConnection.getResponseCode()).thenReturn(200);
//        when(httpURLConnection.getInputStream()).thenReturn(is);
//
//
//        URL mockUrl = mock(URL.class);
//        when(mockUrl.openConnection()).thenReturn(httpURLConnection);
//
//
//        when(new URL("https://developer.nps.gov/api/v1/parks?q=queryText&api_key=test-api-key")).thenReturn(mockUrl);
//
//
//        when (new ObjectMapper()).thenReturn(mockObjectMapper);
//        SearchController searchController = new SearchController();
//
//
//        String response = searchController.search("{\"type\":\"search\",\"params\":{\"query\":\"queryText\"}}");
//
//
//        assertEquals("{\"results\":[]}", response);
//    }
//}

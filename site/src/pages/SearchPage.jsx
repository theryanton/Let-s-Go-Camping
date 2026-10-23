import React, { useState } from 'react';
import '../styles/search.css';
import ParkList from '../components/ParkList';

function SearchPage() {
    const [fetchResponse, setFetchResponse] = useState('');
    const [masterOption, setMasterOption] = useState('search');
    const [searchQuery, setSearchQuery] = useState('');
    const [activity, setActivity] = useState('');
    const [amenity, setAmenity] = useState('');
    const [stateCode, setStateCode] = useState('');  // Add state code to state
    const [searchResults, setSearchResults] = useState([]);
    const [message, setMessage] = useState('');

    const [returnedContent, setReturnedContent] = useState('');

    const handleSearch = () => {

        const scrollY = window.scrollY;

        let params = {
            query: searchQuery,
            activity: activity,
            amenity: amenity,
            stateCode: stateCode
        };

        setSearchResults([]); // clear search results
        setReturnedContent(''); // clear returned content

        if (masterOption === 'search') {
            delete params.activity;
            delete params.amenity;
            delete params.stateCode;
        } else if (masterOption === 'activity') {
            delete params.query;
            delete params.amenity;
            delete params.stateCode;
        } else if (masterOption === 'amenity') {
            delete params.query;
            delete params.activity;
            delete params.stateCode;
        } else if (masterOption === 'state') {
            delete params.query;
            delete params.activity;
            delete params.amenity;
        }

        fetch("/search", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            // send what kind of param it is + the actual param
            body: JSON.stringify({ type: masterOption, params: params })
        })
        .then(response => response.json())
        .then(data => {
            // print out the response to the page
            setReturnedContent(JSON.stringify(data, null, 2));

            window.scrollTo(0, scrollY);
        })
        .catch(error => {
            setMessage("Error: " + error.message);
        });
    };

    return (
        <div className ="search-section">
          <h1 className="title-header">Let's Go Camping!</h1>
            <div className="search-container">
                <h2 className="search-header">Search</h2>
                <select value={masterOption} onChange={e => setMasterOption(e.target.value)}>
                   <option value="search">Search</option>
                   <option value="activity">Filter by Activity</option>
                   <option value="amenity">Filter by Amenity</option>
                   <option value="state">Filter by State</option>
                </select>
               {masterOption === 'search' && (
                   <input
                       className="search-bar"
                       type="text"
                       placeholder="Search"
                       value={searchQuery}
                       onChange={e => setSearchQuery(e.target.value)}
                   />
               )}
               {masterOption === 'activity' && (
                        <select value={activity} onChange={e => {setMasterOption('activity'); setActivity(e.target.value);}}>
                        <option value="">Select Activity</option>
                        <option value="Arts and Culture">Arts and Culture</option>
                        <option value="Astronomy">Astronomy</option>
                        <option value="Auto and ATV">Auto and ATV</option>
                        <option value="Biking">Biking</option>
                        <option value="Boating">Boating</option>
                        <option value="Camping">Camping</option>
                        <option value="Canyoneering">Canyoneering</option>
                        <option value="Caving">Caving</option>
                        <option value="Climbing">Climbing</option>
                        <option value="Compass and GPS">Compass and GPS</option>
                        <option value="Dog Sledding">Dog Sledding</option>
                        <option value="Fishing">Fishing</option>
                        <option value="Flying">Flying</option>
                        <option value="Food">Food</option>
                        <option value="Golfing">Golfing</option>
                        <option value="Guided Tours">Guided Tours</option>
                        <option value="Hands-On">Hands-On</option>
                        <option value="Hiking">Hiking</option>
                        <option value="Horse Trekking">Horse Trekking</option>
                        <option value="Hunting and Gathering">Hunting and Gathering</option>
                        <option value="Ice Skating">Ice Skating</option>
                        <option value="Junior Ranger Program">Junior Ranger Program</option>
                        <option value="Living History">Living History</option>
                        <option value="Museum Exhibits">Museum Exhibits</option>
                        <option value="Paddling">Paddling</option>
                        <option value="Park Film">Park Film</option>
                        <option value="Playground">Playground</option>
                        <option value="SCUBA Diving">SCUBA Diving</option>
                        <option value="Shopping">Shopping</option>
                        <option value="Skiing">Skiing</option>
                        <option value="Snorkeling">Snorkeling</option>
                        <option value="Snow Play">Snow Play</option>
                        <option value="Snowmobiling">Snowmobiling</option>
                        <option value="Snowshoeing">Snowshoeing</option>
                        <option value="Surfing">Surfing</option>
                        <option value="Swimming">Swimming</option>
                        <option value="Team Sports">Team Sports</option>
                        <option value="Tubing">Tubing</option>
                        <option value="Water Skiing">Water Skiing</option>
                        <option value="Wildlife Watching">Wildlife Watching</option>
                   </select>
               )}
               {masterOption === 'amenity' && (
                   <select value={amenity} onChange={e => {setMasterOption('amenity'); setAmenity(e.target.value);}}>
                        <option value="">Select Amenity</option>
                        <option value="ATM/Cash Machine">ATM/Cash Machine</option>
                        <option value="Accessible Rooms">Accessible Rooms</option>
                        <option value="Accessible Sites">Accessible Sites</option>
                        <option value="Airboat Launch">Airboat Launch</option>
                        <option value="Amphitheater">Amphitheater</option>
                        <option value="Animal-Safe Food Storage">Animal-Safe Food Storage</option>
                        <option value="Aquatic Invasive Species Inspection">Aquatic Invasive Species Inspection</option>
                        <option value="Assistive Listening Systems">Assistive Listening Systems</option>
                        <option value="Assistive Listening Systems - T-Coil Compatible">Assistive Listening Systems - T-Coil Compatible</option>
                        <option value="Audio Description">Audio Description</option>
                        <option value="Audio Description - Live">Audio Description - Live</option>
                        <option value="Automated Entrance">Automated Entrance</option>
                        <option value="Automated External Defibrillator (AED)">Automated External Defibrillator (AED)</option>
                        <option value="Baby Changing Station">Baby Changing Station</option>
                        <option value="Backcountry Permits">Backcountry Permits</option>
                        <option value="Beach/Water Access">Beach/Water Access</option>
                        <option value="Bear Canister Purchase/Rental">Bear Canister Purchase/Rental</option>
                        <option value="Bear Spray Purchase/Rental">Bear Spray Purchase/Rental</option>
                        <option value="Benches/Seating">Benches/Seating</option>
                        <option value="Bicycle - Rack">Bicycle - Rack</option>
                        <option value="Bicycle - Rental">Bicycle - Rental</option>
                        <option value="Bicycle - Repair">Bicycle - Repair</option>
                        <option value="Bicycle - Sharing Station">Bicycle - Sharing Station</option>
                        <option value="Boat Ramp">Boat Ramp</option>
                        <option value="Boat Rental">Boat Rental</option>
                        <option value="Braille">Braille</option>
                        <option value="Bus/Shuttle Stop">Bus/Shuttle Stop</option>
                        <option value="Canoe/Kayak/Small Boat Launch">Canoe/Kayak/Small Boat Launch</option>
                        <option value="Captioned Media">Captioned Media</option>
                        <option value="Cellular Signal">Cellular Signal</option>
                        <option value="Dock/Pier">Dock/Pier</option>
                        <option value="Electric Car Charging Station">Electric Car Charging Station</option>
                        <option value="Electrical Hookup - Boat/RV">Electrical Hookup - Boat/RV</option>
                        <option value="Electrical Outlet/Cell Phone Charging">Electrical Outlet/Cell Phone Charging</option>
                        <option value="Elevator">Elevator</option>
                        <option value="Entrance Passes for Sale">Entrance Passes for Sale</option>
                        <option value="Ferry - Passenger">Ferry - Passenger</option>
                        <option value="Ferry - Vehicle">Ferry - Vehicle</option>
                        <option value="Fire Extinguisher">Fire Extinguisher</option>
                        <option value="Fire Pit">Fire Pit</option>
                        <option value="Firewood For Sale/Available">Firewood For Sale/Available</option>
                        <option value="First Aid Kit Available">First Aid Kit Available</option>
                        <option value="First Aid/Medical Care Available">First Aid/Medical Care Available</option>
                        <option value="Fish-Cleaning Station">Fish-Cleaning Station</option>
                        <option value="Fishing Licenses Issued">Fishing Licenses Issued</option>
                        <option value="Food/Drink - Bar/Alcohol For Sale">Food/Drink - Bar/Alcohol For Sale</option>
                        <option value="Food/Drink - Cafeteria">Food/Drink - Cafeteria</option>
                        <option value="Food/Drink - Coffee">Food/Drink - Coffee</option>
                        <option value="Food/Drink - Ice Cream">Food/Drink - Ice Cream</option>
                        <option value="Food/Drink - Restaurant/Table Service">Food/Drink - Restaurant/Table Service</option>
                        <option value="Food/Drink - Snacks">Food/Drink - Snacks</option>
                        <option value="Food/Drink - Vending Machine/Self Service">Food/Drink - Vending Machine/Self Service</option>
                        <option value="Fuel (Diesel)">Fuel (Diesel)</option>
                        <option value="Fuel (Unleaded)">Fuel (Unleaded)</option>
                        <option value="Gifts/Souvenirs/Books">Gifts/Souvenirs/Books</option>
                        <option value="Grill">Grill</option>
                        <option value="Groceries/Convenience Items">Groceries/Convenience Items</option>
                        <option value="Historical/Interpretive Information/Exhibits">Historical/Interpretive Information/Exhibits</option>
                        <option value="Hitching Post/Corral">Hitching Post/Corral</option>
                        <option value="Ice">Ice</option>
                        <option value="Information">Information</option>
                        <option value="Information - Maps Available">Information - Maps Available</option>
                        <option value="Information - Park Newspaper Available">Information - Park Newspaper Available</option>
                        <option value="Information - Ranger/Staff Member Present">Information - Ranger/Staff Member Present</option>
                        <option value="Information Kiosk/Bulletin Board">Information Kiosk/Bulletin Board</option>
                        <option value="Internet/WiFi Available">Internet/WiFi Available</option>
                        <option value="Junior Ranger Activity">Junior Ranger Activity</option>
                        <option value="Junior Ranger Booklet Available">Junior Ranger Booklet Available</option>
                        <option value="Kennel">Kennel</option>
                        <option value="Large Print">Large Print</option>
                        <option value="Laundry">Laundry</option>
                        <option value="Lifejacket Rentals">Lifejacket Rentals</option>
                        <option value="Locker/Storage">Locker/Storage</option>
                        <option value="Low-Vision Access">Low-Vision Access</option>
                        <option value="Mechanic">Mechanic</option>
                        <option value="Open Captioning">Open Captioning</option>
                        <option value="Parking - Auto">Parking - Auto</option>
                        <option value="Parking - Boat Trailer">Parking - Boat Trailer</option>
                        <option value="Parking - Bus/RV">Parking - Bus/RV</option>
                        <option value="Permits Issued">Permits Issued</option>
                        <option value="Pet Boarding">Pet Boarding</option>
                        <option value="Pet Waste Disposal">Pet Waste Disposal</option>
                        <option value="Pets Allowed">Pets Allowed</option>
                        <option value="Picnic Shelter/Pavilion">Picnic Shelter/Pavilion</option>
                        <option value="Picnic Table">Picnic Table</option>
                        <option value="Playground">Playground</option>
                        <option value="Primitive Campsites">Primitive Campsites</option>
                        <option value="Propane For Sale">Propane For Sale</option>
                        <option value="Propane Recycling">Propane Recycling</option>
                        <option value="Public Transit">Public Transit</option>
                        <option value="Recreational Field">Recreational Field</option>
                        <option value="Recycling">Recycling</option>
                        <option value="Restroom">Restroom</option>
                        <option value="Restroom - Accessible">Restroom - Accessible</option>
                        <option value="Restroom - Family">Restroom - Family</option>
                        <option value="Restroom - Seasonal">Restroom - Seasonal</option>
                        <option value="Scenic View/Photo Spot">Scenic View/Photo Spot</option>
                        <option value="Sea Plane Launch">Sea Plane Launch</option>
                        <option value="Sewage Dump Station - Boat/RV">Sewage Dump Station - Boat/RV</option>
                        <option value="Sewer Hookups - Boat/RV">Sewer Hookups - Boat/RV</option>
                        <option value="Shelter/Cabin">Shelter/Cabin</option>
                        <option value="Showers">Showers</option>
                        <option value="Sign Language Interpreter">Sign Language Interpreter</option>
                        <option value="Ski Rental">Ski Rental</option>
                        <option value="Supplies - Camping/Outdoor">Supplies - Camping/Outdoor</option>
                        <option value="Supplies - Fishing/Boating">Supplies - Fishing/Boating</option>
                        <option value="Supplies - General">Supplies - General</option>
                        <option value="Swimming Pool">Swimming Pool</option>
                        <option value="Tactile Exhibit">Tactile Exhibit</option>
                        <option value="Telephone">Telephone</option>
                        <option value="Tent Campsites">Tent Campsites</option>
                        <option value="Text Telephone/TTY">Text Telephone/TTY</option>
                        <option value="Theater/Auditorium">Theater/Auditorium</option>
                        <option value="Ticket Sales">Ticket Sales</option>
                        <option value="Toilet - Flush">Toilet - Flush</option>
                        <option value="Toilet - Vault/Composting">Toilet - Vault/Composting</option>
                        <option value="Trailhead">Trailhead</option>
                        <option value="Trailhead/Hiker Register">Trailhead/Hiker Register</option>
                        <option value="Trash Dumpster">Trash Dumpster</option>
                        <option value="Trash/Litter Receptacles">Trash/Litter Receptacles</option>
                        <option value="Volume-Controlled Telephone">Volume-Controlled Telephone</option>
                        <option value="Water - Bottle-Filling Station">Water - Bottle-Filling Station</option>
                        <option value="Water - Drinking/Potable">Water - Drinking/Potable</option>
                        <option value="Water - Hookup - Boat/RV">Water - Hookup - Boat/RV</option>
                        <option value="Water - Non-Potable">Water - Non-Potable</option>
                        <option value="Wheelchair Accessible">Wheelchair Accessible</option>
                        <option value="Wheelchairs Available">Wheelchairs Available</option>
                   </select>
               )}
               {masterOption === 'state' && (
                   <select value={stateCode} onChange={e => {setMasterOption('state'); setStateCode(e.target.value);}}>
                       <option value="">Select State</option>
                       <option value="AL">Alabama</option>
                       <option value="AK">Alaska</option>
                       <option value="AZ">Arizona</option>
                       <option value="AR">Arkansas</option>
                       <option value="CA">California</option>
                       <option value="CO">Colorado</option>
                       <option value="CT">Connecticut</option>
                       <option value="DE">Delaware</option>
                       <option value="FL">Florida</option>
                       <option value="GA">Georgia</option>
                       <option value="HI">Hawaii</option>
                       <option value="ID">Idaho</option>
                       <option value="IL">Illinois</option>
                       <option value="IN">Indiana</option>
                       <option value="IA">Iowa</option>
                       <option value="KS">Kansas</option>
                       <option value="KY">Kentucky</option>
                       <option value="LA">Louisiana</option>
                       <option value="ME">Maine</option>
                       <option value="MD">Maryland</option>
                       <option value="MA">Massachusetts</option>
                       <option value="MI">Michigan</option>
                       <option value="MN">Minnesota</option>
                       <option value="MS">Mississippi</option>
                       <option value="MO">Missouri</option>
                       <option value="MT">Montana</option>
                       <option value="NE">Nebraska</option>
                       <option value="NV">Nevada</option>
                       <option value="NH">New Hampshire</option>
                       <option value="NJ">New Jersey</option>
                       <option value="NM">New Mexico</option>
                       <option value="NY">New York</option>
                       <option value="NC">North Carolina</option>
                       <option value="ND">North Dakota</option>
                       <option value="OH">Ohio</option>
                       <option value="OK">Oklahoma</option>
                       <option value="OR">Oregon</option>
                       <option value="PA">Pennsylvania</option>
                       <option value="RI">Rhode Island</option>
                       <option value="SC">South Carolina</option>
                       <option value="SD">South Dakota</option>
                       <option value="TN">Tennessee</option>
                       <option value="TX">Texas</option>
                       <option value="UT">Utah</option>
                       <option value="VT">Vermont</option>
                       <option value="VA">Virginia</option>
                       <option value="WA">Washington</option>
                       <option value="WV">West Virginia</option>
                       <option value="WI">Wisconsin</option>
                       <option value="WY">Wyoming</option>
                   </select>
                   )}

               <button className="search-btn" onClick={handleSearch}>Search</button>
               {message && <div id="searchMessage">{message}</div>}
               {searchResults.length > 0 && (
                   <div className="search-results">
                       <h2>Search Results:</h2>
                       <ul>
                           {searchResults.map((result, index) => (
                               <li key={index}>{result}</li>
                           ))}
                       </ul>
                   </div>
               )}
               {/* Display returned content */}
                  <div className="returned-content">
                      <h2>Returned Content:</h2>
                      {returnedContent && <ParkList parks={JSON.parse(returnedContent)} />}
                  </div>
               </div>
           </div>
       );
   }

export default SearchPage;
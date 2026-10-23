import React, {useEffect, useState} from 'react';
import axios from 'axios';
import '../components/Helpers.js';
import { useNavigate } from 'react-router-dom'
import {getCookie} from "../components/Helpers";
import ParkList from "../components/ParkList";

function Favorites() {
    const [favoritesExists, setFavoritesExists] = useState("");
    const [username, setUsername] = useState("");
    const [searchResult, setSearchResult] = useState("");
    const [returnedContent, setReturnedContent] = useState('');

    const session_id = getCookie("user_id");

    const navigate = useNavigate();

    useEffect(() => {
        async function getFavorites() {

            console.log("finding favorites…");

            if (!session_id) {

                setFavoritesExists("You are not logged in!");
                console.error("No user_id found.");
                return;

            }
            console.log("session_id successfully found with value " + session_id.toString());

            try {
                const response = await axios.post('/favorites', { user_id: parseInt(session_id) });
                console.log(response.data);
                if (response.status === 204) {
                    setFavoritesExists("Your favorites list is empty. Add some parks!");
                } else if (response.data) {
                    setReturnedContent(JSON.stringify(response.data, null, 2));
                    setFavoritesExists("");
                }
            } catch (e) {
                console.error(e);
                setFavoritesExists("Couldn't get favorites list.");
            }

        }
        getFavorites().catch(error => (console.error("Couldn't get favorites. Womp womp.")));
    },[session_id]);

    const handleSearch = async () => {
        try {

            if (session_id === null) {
                console.log("User not logged in!");
                return;
            }

            const response = await axios.get(
                `/find-user?username=${encodeURIComponent(username)}&user_id=${encodeURIComponent(session_id)}`
            );

            console.log(response.data);
            if (response.status === 204) {
                setFavoritesExists("Compare list empty...");
            } else if (response.data) {
                setReturnedContent(JSON.stringify(response.data, null, 2));
                setFavoritesExists("");
            }

        } catch (error) {
            console.error(error.message);
            if (error.response.status === 404) {
                setSearchResult("User not found.");
            }
            else {
                setSearchResult("Unable to complete search: " + error.response.data.message);
            }
        }
    };

    return(
        <div className="favorites-section">
            <div className="compare-favorites-container">
                <h1>Compare Favorites</h1>
                <input
                    type="text"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    placeholder="Enter username"
                />
                <button onClick={handleSearch}>Search</button>
                {searchResult && <div>{searchResult}</div>}
            </div>
            <div className="favorites-container">
                <h1>My Favorites</h1>
                {favoritesExists && <p>{favoritesExists}</p>}
            </div>
            <div className="favorites-list">
                {returnedContent ? (
                    <ParkList parks={JSON.parse(returnedContent)} />
                ) : (
                    <p>No favorites to display.</p>
                )}
            </div>
        </div>
    );

}

export default Favorites;
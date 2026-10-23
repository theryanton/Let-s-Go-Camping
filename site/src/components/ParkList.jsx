import React, { useState, useEffect } from 'react';

const ParkList = ({ parks }) => {
  const [selectedParkName, setSelectedParkName] = useState(null);
  const [updatedParks, setUpdatedParks] = useState(parks);
  const [displayLimit, setDisplayLimit] = useState(10); // Initial display limit set to 10

  const handleDisplayMore = () => {
    // Increase display limit by 10, or up to the length of the parks array
    setDisplayLimit(prevLimit => Math.min(prevLimit + 10, parks.length));
  };

  useEffect(() => {
    setUpdatedParks(parks);  // Update the internal state when parks prop changes
  }, [parks]);  // Dependency array includes parks to react to changes

  // Function to fetch additional info for a specific park
  const fetchAdditionalInfo = async (parkName) => {
    try {
      const response = await fetch('/getAdditionalInfo', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ parkNames: [parkName] })
      });
      const data = await response.json();
      if (data && data.length > 0) {
        const newParksData = updatedParks.map(park => {
          if (park.name === parkName) {
            // Merge the updated info into the current park data
            return { ...park, ...data.find(info => info.name === parkName) };
          }
          return park;
        });
        setUpdatedParks(newParksData);
      }
    } catch (error) {
      console.error('Failed to fetch additional park info:', error);
    }
  };

  const toggleParkDetails = (parkName) => {
    const park = updatedParks.find(p => p.name === parkName);
    // Check if the park's detail is null and fetch data if necessary
    if (!park.description || !park.activities || !park.states) {
      fetchAdditionalInfo(parkName);
    }

    if (selectedParkName === parkName) {
      setSelectedParkName(null);  // If the same name is clicked, toggle it off
    } else {
      setSelectedParkName(parkName);  // Otherwise, set it to the new name
    }
  };

    return (
      <div>
        {parks.slice(0, displayLimit).map((park) => (
          <div key={park.id} style={{ marginBottom: '20px', border: '1px solid black', padding: '10px' }}>
            <div style={{ cursor: 'pointer' }} onClick={() => toggleParkDetails(park.name)}>
              {park.name}
            </div>
            {selectedParkName === park.name && (
              <div>
                <p>Description: {park.description ?? 'Loading description...'}</p>
                <p>Latitude: {park.latitude ?? 'Loading latitude...'}</p>
                <p>Longitude: {park.longitude ?? 'Loading longitude...'}</p>
                <p>Activities: {park.activities ? park.activities.map(activity => activity.name).join(', ') : 'Loading activities...'}</p>
                <p>States: {park.states ?? 'Loading states...'}</p>
                <p>Directions: <a href={park.directionsUrl ?? '#'}>Click here for directions</a></p>
              </div>
            )}
          </div>
        ))}
        {displayLimit < parks.length && (
          <button onClick={handleDisplayMore}>Display 10 More</button>
        )}
      </div>
    );
  };


export default ParkList;

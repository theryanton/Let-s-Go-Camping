import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import SearchPage from './pages/SearchPage';
import UserRegistration from './pages/UserRegistration';
import Login from './pages/Login';
import Favorites from './pages/favorites.jsx';
import NavBar from './pages/NavBar';

function App() {
  return (
    <div>
      <NavBar /> {/* Render NavBar component */}
      <Routes>
        <Route path="*" element={<Navigate replace to="/Login" />} />
        <Route path="/SearchPage" element={<SearchPage />} />
        <Route path="/UserRegistration" element={<UserRegistration />} />
        <Route path="/Login" element={<Login />} />
        <Route path="/Favorites" element={<Favorites />} />
      </Routes>
    </div>
  );
}

export default App;
import React from 'react';
import SearchBar from './SearchBar';
import './App.css';
import fleximage from './flex-image.jpeg';

function App() {
    return (
        <div className="two-columns App">
            <img className="flex-image" src={fleximage}></img>
            <div >
                <p className="app-paragraph">
                This search bar allows you to insert and retrieve elements.
                </p>
                <SearchBar />
            </div>
        </div>
    );
}

export default App;
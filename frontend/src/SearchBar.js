import React, { useState } from 'react';
import ApiService from './ApiService';

const SearchBar = () => {
    const [query, setQuery] = useState('');
    const [successMessage, setSuccessMessage] = useState('');
    const [searchResult, setSearchResult] = useState('');

    const handleSearch = async () => {
        try {
            const result = await ApiService.get(`search?query=${query}`);
            setSearchResult(result.word);
            setSuccessMessage('Word found!');
        } catch (error) {
            console.error('Search failed:', error.message);
            setSearchResult('');
            setSuccessMessage(`Word "${query}" not found.`);
        }
    };

    const handleInsert = async () => {
        try {
            await ApiService.post('add-word', { word: query });
            setSuccessMessage('Word inserted successfully');
            setSearchResult('');
        } catch (error) {
            console.error('Insert failed:', error.message);
            setSuccessMessage('');
            setSearchResult('');
        }
    };

    return (
        <div>
            <input
                type="text"
                placeholder="Type here..."
                value={query}
                onChange={(e) => setQuery(e.target.value)}
            />
            <button onClick={handleSearch}>Search</button>
            <button onClick={handleInsert}>Insert</button>
            {successMessage && <p>{successMessage}</p>}
            {searchResult && <p>Search Result: {searchResult}</p>}
        </div>
    );
};

export default SearchBar;

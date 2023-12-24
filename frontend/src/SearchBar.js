import React, { useState } from 'react';
import ApiService from './ApiService';

const SearchBar = () => {
    const [query, setQuery] = useState('');

    const handleSearch = async () => {
        try {
            const result = await ApiService.get(`search?query=${query}`);
            console.log('Search Result:', result);
        } catch (error) {
            console.error('Search failed:', error.message);
        }
    };

    const handleInsert = async () => {
        try {
            await ApiService.post('add-word', { word: query });
            console.log('Word inserted successfully');
        } catch (error) {
            console.error('Insert failed:', error.message);
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
        </div>
    );
};

export default SearchBar;
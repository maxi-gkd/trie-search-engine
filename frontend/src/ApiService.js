const BASE_URL = 'http://localhost:8080';

const handleResponse = async (response) => {
    if (!response.ok) {
        const error = await response.json();
        throw new Error(error.message || 'Something went wrong');
    }
    return response.ok;
};

const ApiService = {
    get: async (endpoint) => {
        const response = await fetch(`${BASE_URL}/${endpoint}`);
        return handleResponse(response);
    },

    post: async (endpoint, data) => {
        const response = await fetch(`${BASE_URL}/${endpoint}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        });
        return handleResponse(response);
    },

    handleResponse: handleResponse,
};

export default ApiService;
import axios from "axios";

const baseUrl = "http://localhost:5000";

export const login = async (params) => {
    const response = await request(axios.post, baseUrl + "/login", params);
    return response;
};

export const signup = async (params) => {
    console.log(params);
    const response = await request(axios.post, baseUrl + "/signup", params);
    return response;
};

export const logout = async () => {
    await request(axios.post, baseUrl + "/logout");
    sessionStorage.clear();
};

const request = async (method, url, params) => {
    try {
        var response;
        if (params) {
            response = await method(url, params, { withCredentials: true });
        } else {
            response = await method(url, { withCredentials: true });
        }

        return {
            status: response.status,
            data: response.data,
        };
    } catch (error) {
        if (error.response) {
            return {
                status: error.response.status,
                data: error.response.data,
            };
        } else {
            return {
                status: 503,
                data: null,
            };
        }
    }
};

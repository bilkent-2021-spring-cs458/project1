import axios from "axios";
import qs from "querystring";

const baseUrl = "http://localhost:8080";

export const login = async (params) => {
    const response = await request(
        axios.post,
        baseUrl + "/login",
        qs.stringify(params)
    );
    return response;
};

export const signup = async (params) => {
    const response = await request(
        axios.post,
        baseUrl + "/signup",
        qs.stringify(params)
    );
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
                data: null,
            };
        } else {
            return {
                status: 503,
                data: null,
            };
        }
    }
};

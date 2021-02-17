import { post } from "./utilities";

const baseUrl = "http://3.140.185.156:5000";

export const getUserDetails = async () => {
    const response = await post(baseUrl);
    return response;
};

export const signin = async (params) => {
    const response = await post(baseUrl + "/login", params);
    sessionStorage.setItem("isSignedIn", true);
    return response;
};

export const signup = async (params) => {
    const response = await post(baseUrl + "/signup", params);
    sessionStorage.setItem("isSignedIn", true);
    return response;
};

export const signout = async () => {
    sessionStorage.clear();
    await post(baseUrl + "/logout");
};

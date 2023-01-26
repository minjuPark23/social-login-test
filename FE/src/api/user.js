import { apiInstance } from "./index.js";

const api = apiInstance();

/* 회원정보 */
async function fetchUser(success, fail){
    api.defaults.headers["Authorization"] = sessionStorage.getItem("token");
    console.log("user.js의 fetchUser 실행");

    await api.get("/user/me")
        .then(success)
        .catch(fail);
}

export {
    fetchUser,
};
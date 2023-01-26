import Vue from "vue"
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate"

// sessionStorage 설정

Vue.use(Vuex);

import userStore from "./modules/userStore";

const store = new Vuex.Store({
    modules: {
        userStore,
    },
    plugins:[
        createPersistedState({
            // 새로고침 하더라도 userStore의 값은 세션 스토리지에 저장하겠다며..
            storage: sessionStorage,
            paths: ["userStore"],
        }),
    ],
});

export default store;
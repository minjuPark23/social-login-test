// import router from "@/router/index.js"

import {
    fetchUser
} from "@/api/user.js"
 
const userStore = {
    namespaced: true,
    state: {
        /** 컴포넌트 간 공유할 데이터
        $store.state.{변수명}로 접근 가능*/
        // userNo,
        // userName,
        // userEmail,
        user: null,
    },
    getters: {
        /** 각 컴포넌트에서 Vuex의 데이터 접근시 중복된 코드를 반복호출하게 할 수 있다.
        부모, 자식 컴포넌트에 중복되어 작성될 경우
        state의 변경을 각 컴포넌트가아닌 Vuex에서 실행하도록 한다. (접근)
        각 컴포넌트에서는 수행 로직을 호출하기만
        $store.getters.{함수명}
        computed에서 부른다.*/
        userLogin(state) {
            console.log("state.user: ", state.user);
            return state.user;
        },
    },
    mutations:{
        /** vuex의 데이터 즉, state 값을 변경한다.
        getter와의 차이
            - 인자를 받을 수 있음
            - methods에서 등록
        actions와의 차이
            - 동기적 로직(순차적인 로직만 선언)
        -> 직접 state에 접근해 변경하는것이 아님 == setters*/
        SET_USER: (state, user) => {
            state.user = user;
        },
        USER_LOGOUT: (state) => {
            state.user = null;
        }

    },
    actions: {
        /** mutations와의 차이
            - 비동기적 로직
        서버와의 통신같이 결과를 받아올 타이밍이 예측되지 않은 로직은 Actions*/
        login(){
            // 소셜로그인 루트
        },
        join(){
            // 입력받은 이메일이 이미 가입되었는지 확인하는 로직 필요
        },
        getUserInfo({ commit }){
            console.log("fetchUser 실행");
            fetchUser(
                (resp) => {
                    const {data} = resp;
                    if(resp.status === 200){
                        console.log(data);
                        commit("SET_USER", data);
                    }
                },
                (error) => {
                    console.log(error);
                }
            )
        }
    }
};

export default userStore;
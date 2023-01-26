import Vue from "vue";
import VueRouter from "vue-router";
import MainV from "../views/MainV.vue";
import LoginV from "../views/LoginV.vue";
import LoginRedirect from "../components/LoginRedirect.vue";

Vue.use(VueRouter);

const routes = [
    {
        path: "/",
        component: MainV,
        name: "MainV",
        // redirect: "/recent", // /로 접속하면 /recent로 리다이렉트
    },
    {
        path: "/login",
        component: LoginV,
        name: "Login",
        beforeEnter: (to, from, next) => {
            if(sessionStorage.getItem("token")){
                next("/");
                console.log("토큰 이쪙")
            }else{
                next("");
                console.log("토큰 없쪙")
            }
        }
    },
    {
        path: "/oauth/redirect",
        component: LoginRedirect,
    }
]

const router = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes,
});

// 라우터 네비게이션 가드
// router.beforeEach(function(to, from ,next){
//     // to: 이동할 url
//     // from: 현재 url
//     // next: to에서 지정한 url로 이동하기위해 꼭 호출해야 하는 함수

// })

export default router;
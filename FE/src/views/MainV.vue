<template>
  <div class="container">
    <div class="daou txtL">다우오피스</div>
    <div class="portal txtL">계약고객포털</div>
    <div v-if="isLoggedIn">
      <button class="btn" v-on:click="login">Login</button>
      <button class="btn" v-on:click="join">Join</button>
    </div>
    <div v-else>
      <div>환영합니다. {{ username }}님</div>
      <div>
        <button class="btn" v-on:click="logout">Logout</button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapMutations, mapState } from 'vuex';

const userStore = "userStore";

export default {
  data(){
    return {
    }
  },
  computed: {
    ...mapState (userStore, ['user']),
    ...mapMutations (userStore, ['USER_LOGOUT']),

    isLoggedIn() {
      if(sessionStorage.getItem("token")){
        console.log("isLoggedIn 확인작업");
        return false;
      }
      return true;
    },
    username(){
      if(this.user) return this.user.name;
      return '이름없음';
    },
  },
  methods: {
    login: function (){
      // 로그인 화면으로 이동
      this.$router.push("Login");
    },
    join(){

    },
    logout(){
      // session storage의 token 삭제
      sessionStorage.removeItem('token'); 
      // 새로고침
      this.$router.go(this.$router.currentRoute);
      // userInfo 삭제 
      this.USER_LOGOUT;
      console.log("현 사용자: "+this.user);
    }
  },
}
</script>

<style scoped>

.btn{
  /* Solid Btn */
    box-sizing: border-box;

    /* Auto layout */

    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    padding: 16px 26px;
    gap: 10px;

    /* width: 384px;
    height: 48px; */

    /* Gray/900 */

    background: #111111;
    /* Gray/900 */

    border: 1px solid #111111;
    border-radius: 24px;

    /* Inside auto layout */

    flex: none;
    order: 1;
    align-self: stretch;
    flex-grow: 0;
    margin-top: 50px;

    /* Lebel */


    width: 94px;
    height: 16px;

    font-family: 'Noto Sans KR';
    font-style: normal;
    font-weight: 700;
    font-size: 14px;
    line-height: 16px;
    /* identical to box height, or 114% */

    text-align: center;

    /* Gray/000 */

    color: #FFFFFF;


    /* Inside auto layout */

    flex: none;
    order: 0;
    flex-grow: 0;
}
.portal{
  /* 계약고객포털 */
    width: 320px;
    height: 40px;

    font-family: 'Noto Sans KR';
    font-style: normal;
    font-weight: 700;
    font-size: 36px;
    line-height: 40px;
    /* identical to box height, or 111% */

    letter-spacing: -0.02em;

    /* Gray/900 */

    color: #111111;


    /* Inside auto layout */

    flex: none;
    order: 1;
    align-self: stretch;

}

.daou {
  /* 다우오피스 */
    width: 320px;
    height: 40px;

    font-family: 'Noto Sans KR';
    font-style: normal;
    font-weight: 400;
    font-size: 36px;
    line-height: 40px;
    /* identical to box height, or 111% */

    letter-spacing: -0.02em;

    /* Gray/900 */

    color: #111111;


    /* Inside auto layout */

    flex: none;
    order: 0;
    align-self: stretch;
    flex-grow: 0;
}
</style>
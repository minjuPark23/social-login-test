<template>
  <div class="Fielsset">
    <div>
      <div class="daou txtL">다우오피스</div>
      <div class="portal txtL">계약고객포털</div>  
    </div>
    <div>
      <div class="inputTitle txtL">이메일</div>
      <input class="loginInput" type="text">
    </div>
    <div>
      <div class="inputTitle txtL">비밀번호</div>
      <input class="loginInput" type="text">
    </div>

    <!--소셜 로그인-->
    <div>
      <div>
        <div class="NaverLoginBtn" v-on:click="naver">
          <span class="NaverLogo"></span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return {

    }
  },
  methods:{
    naver(){
      location.href='http://localhost:9285/oauth2/authorization/naver?redirect_uri=http://localhost:8080/oauth/redirect';
    },
  },
}
</script>

<style scoped>

.NaverLogo{
  width: 16.66px;
  height: 18px;

  background: url(@/assets/naverLogo.jpg);

  /* Inside auto layout */

  flex: none;
  order: 0;
  flex-grow: 0;
}

.NaverLoginBtn{
  /* Button/Large */
    /* Auto layout */

    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    padding: 16px;
    gap: 10px;

    width: 20px; 
    height: 20px;

    /* Vendor/NAVER */

    background: #03C75A;
    border-radius: 50px;

    /* Inside auto layout */

    flex: none;
    order: 1;
    flex-grow: 0;
}

.loginInput{
  /* Input Underline */
    box-sizing: border-box;

    /* Auto layout */

    display: flex;
    flex-direction: row;
    align-items: center;
    padding: 10px 8px;
    gap: 8px;

    width: 320px;
    height: 36px;

    /* Gray/000 */

    background: #FFFFFF;
    /* Gray/900 */

    border-bottom: 1px solid #111111;

    /* Inside auto layout */

    flex: none;
    order: 1;
    align-self: stretch;
    flex-grow: 0;
}

.inputTitle {
  /* 이메일 */
    width: 111px;
    height: 17px;

    /* Label/Small */

    font-family: 'Noto Sans KR';
    font-style: normal;
    font-weight: 400;
    font-size: 12px;
    line-height: 17px;
    letter-spacing: -0.02em;

    /* Gray/900 */

    color: #111111;


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


.Fielsset {
    /* Fielsset */
    box-sizing: border-box;

    /* Auto layout */

    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 40px;
    gap: 48px;

    position: absolute;
    width: 400px;
    height: 496px;
    left: 760px;
    top: 195px;

    /* white */

    background: #FFFFFF;
    /* GreyBasic */

    border: 1px solid #CCCCCC;
    /* CardShadow */

    box-shadow: 1px 1px 5px rgba(0, 0, 0, 0.1);
    border-radius: 24px;
}

</style>
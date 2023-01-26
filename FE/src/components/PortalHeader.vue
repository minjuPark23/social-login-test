<template>
    <div class="header">
        <span class="title">고객 포털 서비스</span>
    </div>
</template>

<script>
export default {
    data(){
        return {

        }
    },
}
</script>

<style scoped>
.title {
    width: 111px;
    height: 30px;

    font-family: 'Noto Sans KR';
    font-style: normal;
    font-weight: 700;
    font-size: 16px;
    line-height: 30px;
    /* identical to box height, or 188% */

    display: flex;
    align-items: center;

    /* Gray/000 */

    color: #FFFFFF;


    /* Inside auto layout */

    flex: none;
    order: 0;
    flex-grow: 0;
}

.header {
    display: flex;
    flex-direction: row;
    align-items: center;
    padding: 0px 24px;
    gap: 10px;

    position: absolute;
    width: 1920px;
    height: 56px;
    left: 0px;
    top: 0px;

    /* Gray/900 */

    background: #111111;
}
</style>
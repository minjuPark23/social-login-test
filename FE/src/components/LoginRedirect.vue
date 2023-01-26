<template>
    <div>
    </div>
</template>

<script>
import { mapActions } from 'vuex';

const userStore = "userStore";

export default {
    created: function(){
        const token = this.$route.query.token
        if (token) {
            sessionStorage.setItem("token", token);
            console.log(token);
            this.getUserInfo();
        }
        this.$router.replace("/");
    },
    methods: {
        ...mapActions(userStore, ['getUserInfo'])
    }
}
</script>

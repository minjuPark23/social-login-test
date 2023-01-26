<template>
  <div id="app">
    <PortalHeader></PortalHeader>
    <router-view class="contianer"></router-view>
  </div>
</template>

<script>
import PortalHeader from "./components/PortalHeader.vue";

export default {
  name: 'App',
  components: {
    PortalHeader,
  },
  data: () => ({}),
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.txtL{
  text-align: left;
}

.container {
  margin: 150px;
}
</style>
 
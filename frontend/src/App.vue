<template>
  <Header />
  <router-view />
  <Footer />
</template>

<script>
import Header from './layouts/Header.vue'
import Footer from './layouts/Footer.vue'

export default {
  name: 'App',
  components: {
    Footer,
    Header
  }
}
</script>

<style lang="scss">
  //@import "assets/styles/element-ui-index.css";
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
</style>

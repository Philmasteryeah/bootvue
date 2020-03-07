<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Hello</router-link> |
      <router-link to="/test">Test</router-link> |
      <router-link to="/callservice">Service</router-link> |
      <router-link to="/user">User</router-link> |
      <router-link to="/login">Login</router-link> |
      <router-link to="/protected">Protected</router-link>
    </div>
    <router-view :hellomsg="msg"></router-view>
  </div>
</template>

<script>

export default {
  name: 'app',
  data () {
    return {
      msg: 'Welcome to your Vue.js powered Spring Boot App'
    }
  }
}
</script>

<style lang="scss">
  // Import CoreUI styles
  @import 'assets/scss/style';
</style>

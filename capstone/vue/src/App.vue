<template>
  <div id="app">
    <header>
    <div class="left-links">
      <router-link v-bind:to="{ name: 'home' }">
        <img src="../src/img/Kings and Queens Trading.png">
      </router-link>&nbsp; &nbsp;
      <router-link v-bind:to="{ name: 'collections' }">My Collections</router-link>&nbsp; &nbsp;
      <router-link v-bind:to="{ name: 'mtg-view' }">
        <img src="../src/img/Magic_The_Gathering_logo_PNG2.png">
        </router-link>&nbsp; &nbsp;
      <router-link v-bind:to="{ name: 'pokemon-tcg-view' }">
        <img src="../src/img/pokemon-logo-png-1432.png">
        </router-link>&nbsp;

    </div>
    <div class="right-links">
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">Login</router-link>
    </div>
    </header>
    
    <main class="container">
        <router-view />

    </main>
  </div>
</template>

<script>
export default {
  data() {
    return {
    }
  }
}
</script>

<style scoped>
  .container {
    display: flex;
    padding-top: 0rem;
  }

  header {
    display: flex;
    justify-content: space-between;
    padding: 20px;
    margin: 0;
    border-bottom: solid #0E2431 5px;
    background-color: cornflowerblue;
  }

  header a {
    text-decoration: none;
  }

  header a:hover {
    font-weight: bold;
  }

  img{
    max-block-size: 125px;
  }
</style>

<template>
  <div id="app">
    <header>

    <div class="left-links">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <a href="#">Magic</a>&nbsp;|&nbsp;
      <a href="#">Pokemon</a>&nbsp;
    </div>
    <div class="right-links">
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">Login</router-link>
    </div>

    </header>
    <main class="container">
      <img src="" alt="">
      <section class="center-card" :class="{ flipped: isFlipped }" v-if="$route.path === '/login' || $route.path === '/' || $route.path === '/register'">
        <router-view />
      </section>
    </main>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isFlipped: false,
    }
  }
}
</script>

<style scoped>
  .container {
    display: flex;
  }

  .center-card {
    position: relative;
    display: flex;
    top: 15%;
    left: 20%;
    right: 20%;
    margin: auto;
    padding-right: 15px;
    height: 30rem;
    width: 20rem;
    padding: 5px;
    background-color: cornflowerblue;
    filter: drop-shadow(30px 10px 4px rgb(46, 46, 46, 0.7));
    border-radius: 10px;
    border: solid #0E2431 10px;
    transform-style: preserve-3d;
    transform-origin: center right;
    transition: transform 1s;
}

.flipped {
  transform: translateX(-100%) rotateY(-180deg);
  background-color: red;
}

  img {
    height: 100vh;
    opacity: 50%;
    z-index: -1;
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
</style>

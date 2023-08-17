<template>
  <div id="app">
    <header>
      <div class="left-links">
        <router-link v-bind:to="{ name: 'home' }">
          <div class="translucent-image">
            <img src="../src/img/Kings and Queens Trading.png" />
            <div class="translucent-mask-home"></div>
          </div> </router-link
        >&nbsp; &nbsp;
        <router-link v-bind:to="{ name: 'pokemon-tcg-view' }">
          <img src="../src/img/pokemon-logo-png-1432.png" /> </router-link
        >&nbsp; &nbsp;
        <router-link v-bind:to="{ name: 'mtg-view' }">
          <img
            class="white-border"
            src="../src/img/white-mtg-logo.png"
          /> </router-link
        >&nbsp;
        <router-link v-bind:to="{ name: 'collections' }">
          <div class="translucent-image">
            <img src="../src/img/my collections.png" />
            <div class="translucent-mask-my-collections"></div>
          </div> </router-link
        >&nbsp; &nbsp;
      </div>
      <div class="right-links">
        <div class="logout-button">
          <router-link
            v-bind:to="{ name: 'logout' }"
            v-if="$store.state.token != ''"
          >
            <button>Logout</button>
          </router-link>
          <router-link
            v-bind:to="{ name: 'login' }"
            v-if="$store.state.token == ''"
          >
            <button>Login</button>
          </router-link>
        </div>
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
    return {};
  },
};
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
  border-bottom: solid #0e24313b 5px;
  background-image: url("../src/img/coverbarBackground.png");
  background-repeat: no repeat;
  background-size: cover;
}

header a {
  text-decoration: none;
}

header a:hover {
  font-weight: bold;
}

img {
  max-block-size: 125px;
}

.translucent-image {
  position: relative;
  overflow: hidden;
  border-radius: 20px;
}

.translucent-mask-home{
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: radial-gradient(
    ellipse at top left,
    transparent,
    rgba(59, 31, 92, 0.5),
    transparent
  );
}

.translucent-mask-my-collections{
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: radial-gradient(
    ellipse at top left,
    transparent,
    rgba(124, 43, 124, 0.521),
    transparent
  );
}

.left-links {
  display: flex;
  justify-content: space-between;
  flex-grow: 1;
}

.right-links {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.logout-button button {
  border: solid;
  padding: 12px;
  border-radius: 8px;
  background: linear-gradient(to top, #3f11ac, #ae8eef);
  color: dark purple;
}
.logout-button button:hover {
  border: solid;
  padding: 12px;
  border-radius: 8px;
  background: linear-gradient(to bottom, #3f11ac, #ae8eef);
  color: dark purple;
  cursor: pointer;
}
main {
  background-image: url("img/ElementalBackground.png");
  background-size:cover;
  background-attachment: fixed;
  width: 100vw;
  min-height: 100vh;
  }

</style>

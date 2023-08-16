<template>
  <main>
    <div class="home-card">
      <router-link v-bind:to="{ name: 'mtg-view' }">
     <Card 
     name="Magic The Gathering"
     :picture= 'mtgimage'
     description="Make and share your own collection of Magic The Gathering cards, or view and favorite collections other users have made!"
     />
     </router-link>
  
     <router-link v-bind:to="{ name: 'pokemon-tcg-view' }">
     <Card 
     name="Pokemon TCG"
     :picture= 'karpImage'
     description="Make and share your own collection of Pokemon TCG cards, or view and favorite collections other users have made!"
     />
     </router-link>
    </div>
  </main>
</template>

<script>
import Card from '../components/Card.vue'
export default {
  components: { Card },
  name: "home",
  data() {
    return {
      karpImage: require('../img/karp.png'),
      mtgimage: require('../img/mtg.png')
    };
  }
};
</script>

<style scoped>
html, body {
  width: 100%;
  height: 100%;
}
hr {
  height: 1px;
        background-color: #0E2431;
        border: none;
}
p {
  padding: 5px;
}

.home-card{
  padding: 50px;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  text-decoration: none;
  flex-grow: 1;
}

main {
  background-image: url("../img/ElementalBackground.png");
  background-size: cover;
  background-attachment: fixed;
  width: 100vw;
  height: 100vh;
}

router-link{
  text-decoration: none;
}
</style>
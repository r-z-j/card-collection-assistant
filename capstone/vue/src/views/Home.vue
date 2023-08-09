<template>
  <div class="home-card">
   <Card 
   name="Magic The Gathering"
   :picture= 'mtgimage'
   description="Make and share your own collection of Magic The Gathering cards, or view and favorite collections other users have made!"
   />
   <Card 
   name="Pokemon TCG"
   :picture= 'karpImage'
   description="Make and share your own collection of Pokemon TCG cards, or view and favorite collections other users have made!"
   />
  </div>
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

<style>
hr {
  height: 1px;
        background-color: #0E2431;
        border: none;
}
p {
  padding: 5px;
}

.home-card{
  display: flex;
  flex-grow: 1;
}


</style>
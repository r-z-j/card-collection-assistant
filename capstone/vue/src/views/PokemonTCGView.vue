<template>
    <div class="multiple-pokemon">
        <div>
       <router-link class="router-link" v-bind:to="{ name: 'pokemon-search' }">  
      <Card name="Search Cards" :picture="pokemonCardStackImage" description="Browse all cards"></Card>
       </router-link>&nbsp;
      </div>
        <div>
        <router-link class="router-link" v-bind:to="{ name: 'collections' }" @click="$router.go()">
      <Card name="My Collections" :picture="pokemonUserCollecctionImage" description="View all of your trading card collections"></Card>
      </router-link>&nbsp;
      </div>

    <div>
      <router-link class="router-link" v-bind:to="{ name: 'favorited' }"> 
      <Card name="Favorite Collections" :picture="favPokeImage" description="View all of your favorite trading card collections"></Card>
      </router-link>
    </div>
    <router-link class="router-link"  v-bind:to="{ name: 'all-collections' }"> 
    <div>
      <Card name="All Collections" :picture="allCollectionsImages" description="Browse all collections"></Card>
    </div>
    </router-link>
    </div>
  </template>
  
  <script>
  import Card from '../components/Card.vue'
  export default {
      components: {
          Card
      },
      name: "pokemon",
      data(){
          return {
              pokemonCardStackImage: require('../img/starterChoicePokemon.png'),
              pokemonUserCollecctionImage: require('../img/starterPokemon.png'),
              favPokeImage: require('../img/eeveeImages.png'),
              allCollectionsImages: require('../img/allPokemonCollections.png')
          }
      }
  
  }
  </script>
  
  <style>
  .multiple-pokemon{
  padding: 50px;
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  justify-content: space-around;
  flex-grow: 1;
  text-decoration: none;
  background-image: url("../img/pokemonCardsBackground.png");
  background-size: cover;
  width: 100vw;
  min-height: 100vh;
  }
  
  .router-link{
    text-decoration: none;
  }
  
  </style>
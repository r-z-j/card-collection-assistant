<template>
    <div class="multiple">
    <div> 
       <router-link v-bind:to="{ name: 'card-test-pokemon' }">  
      <Card name="Add Cards to Collections" :picture="pokemonCardStackImage" description="Browse all cards"></Card>
       </router-link>&nbsp;
      
    </div> 
    <div>  
      <Card name="User Collections" :picture="pokemonUserCollecctionImage" description="View all of your trading card collections"></Card>
    </div>
    <div>
      <Card name="Favorited Collections" :picture="favPokeImage" description="View all of your favorited trading card collections"></Card>
    </div>
    <div>
      <Card name="All Collections" :picture="allCollectionsImages" description="Browse all collections"></Card>
    </div> 
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
  .multiple{
      display: flex;
      flex-direction: row;
      flex-grow: 1;
      flex-wrap: wrap;
  }
  
  
  </style>
<template>
    <div class="multiple-pokemon">
        <div >
       <router-link v-bind:to="{ name: 'pokemon-search' }">  
      <Card name="Add Cards to Collections" :picture="pokemonCardStackImage" description="Browse all cards"></Card>
       </router-link>&nbsp;
      </div>
        <div >
        <router-link v-bind:to="{ name: 'collections' }">
      <Card name="User Collections" :picture="pokemonUserCollecctionImage" description="View all of your trading card collections"></Card>
      </router-link>&nbsp;
      </div>

    <div>
      <Card name="Favorited Collections" :picture="favPokeImage" description="View all of your favorited trading card collections"></Card>
    </div>
    <router-link v-bind:to="{ name: 'all-collections' }"> 
    <div >
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
  display: grid;
  grid-template-columns: 1fr 1fr;
  flex-direction: row;
  justify-content: space-around;
  flex-grow: 1;
  text-decoration: none;
  background-image: url("../img/pokemonCardsBackground.png");
  background-size: cover;
  width: 100%;
  height: 100%;
  }
  

  
  </style>
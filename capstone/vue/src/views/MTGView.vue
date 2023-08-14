<template>
<main>
  <div class="multiple">
    <div class="card-format">
    <router-link v-bind:to="{ name: 'mtg-search-view' }">  
    <Card name="Search Cards" picture="" description="Search for cards to add to a collection"></Card>
    </router-link>
    </div>
    <div class="card-format">
    <router-link v-bind:to="{ name: 'collections'}">
    <Card name="My Collections" picture="" description="View all of your trading card collections"></Card>
    </router-link>
    </div>
    <div class="card-format">
    <Card name="Favorited Collections" picture="" description="View all of your favorited trading card collections"></Card>
    </div>
    <div class="card-format">
    <Card name="All Collections" picture="" description="Browse all collections"></Card>
    </div>
  </div>
</main>
  </template>
  
  <script>
  import Card from '../components/Card.vue'
  export default {
      components: {
          Card
      },
  
  }
  </script>
  
  <style>
  .multiple{
  padding: 50px;
  display: grid;
  grid-template-columns: 1fr 1fr;
  flex-direction: row;
  justify-content: space-around;
  flex-grow: 1;
  text-decoration: none;
  width: 100vw;
  height: 100vw;
  }

  main{
  background-image: url('../img/MTG-background-image.png');
  background-size:cover;
  background-attachment: fixed;
  }
  
  </style>
<template>
    <div class="multiple">
      <router-link v-bind:to="{ name: 'mtg-search-view' }">  
      <Card name="Search Cards" picture="" description="Search for cards to add to a collection"></Card>
      </router-link>
      <router-link v-bind:to="{ name: 'collections'}">
      <Card name="My Collections" picture="" description="View all of your trading card collections"></Card>
      </router-link>
      <Card name="Favorited Collections" picture="" description="View all of your favorited trading card collections"></Card>

      <Card name="All Collections" picture="" description="Browse all collections"></Card>
      
    </div>
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
      display: flex;
      flex-direction: row;
      flex-grow: 1;
      flex-wrap: wrap;
  }
  
  
  </style>
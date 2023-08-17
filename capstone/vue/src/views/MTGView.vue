<template>
<main>
  <div class="multiple">
    <div class="card-format">
    <router-link class="router-link" v-bind:to="{ name: 'mtg-search-view' }">  
    <Card name="Search Cards" :picture='searchImage' description="Browse all cards"></Card>
    </router-link>
    </div>
    <div class="card-format">
    <router-link class="router-link" v-bind:to="{ name: 'collections'}">
    <Card name="My Collections" :picture='myCollectionImage' description="View all of your trading card collections"></Card>
    </router-link>
    </div>
    <div class="card-format">
    <router-link class="router-link" v-bind:to="{ name: 'favorited' }"> 
    <Card name="Favorite Collections" :picture='favCollectionsImage' description="View all of your favorite trading card collections"></Card>
    </router-link>
    </div>
    <router-link class="router-link" v-bind:to="{ name: 'all-collections' }"> 
    <div class="card-format">
    <Card name="All Collections" :picture='allCollectionsImage' description="Browse all collections"></Card>
    </div>
    </router-link>
  </div>
</main>
  </template>
  
  <script>
  import Card from '../components/Card.vue'
  export default {
      components: {
          Card
      },
      data() {
    return {
      searchImage: require('/src/img/search-card-mtg.png'),
      myCollectionImage: require('/src/img/mycollections-card.png'),
      favCollectionsImage: require('/src/img/fav-col-img.png'),
      allCollectionsImage: require('/src/img/allcollections-card.png')
    };
  }
  
  }
  </script>
  
  <style scoped>
  .multiple{
  padding: 50px;
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  justify-content: space-around;
  flex-grow: 1;
  text-decoration: none;
  width: 100vw;
  min-height: 100vw;
  }


  main{
  background-image: url('../img/MTG-background-image.png');
  background-size:cover;
  background-attachment: fixed;
  }

  .router-link{
    text-decoration: none;
  }
  
  </style>
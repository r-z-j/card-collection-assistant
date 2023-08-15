<template>
  <div class="collections">
    <link rel="stylesheet"
      href="http://fonts.googleapis.com/css2?family=Simonetta&display=swap"/>
    <center><h1 class="page-header">My Collections</h1>
    <router-link v-bind:to="{ name: 'create-collection' }"> 
            <button class="transparent-button">Create Collection</button>
            </router-link>
    </center>
    <section>
      <div
        class="collection-container"
        v-for="collection in collections"
        v-bind:key="collection.collectionId"
      >
        <div class="tile-container">
          <div class="collection-title">{{ collection.collectionName }}</div>
          <div v-if="collection.gameTypeId === 2">
            <router-link v-bind:to="{ name: 'pokemon-search' }"> 
            <img src="../img/pokemon-cardback.png" />
            </router-link>
          </div>
          <div v-else-if="collection.gameTypeId === 1">
            <router-link v-bind:to="{ name: 'pokemon-search' }"> 
            <img src="../img/magicCardBack.png" />
            </router-link>
          </div>
<button class="transparent-button">add to favorites</button>
        </div>
        
      </div>
    </section>
  </div>
</template>

<script>
import collectionService from "../services/CollectionApiService";

export default {
  name: "collections",
  data() {
    return {
      collections: null,
      favorited: null,
    };
  },

  computed: {
  },

  async created() {
    const res = await this.getCollections();
    this.collections = res.data;
    
   
  },

  methods: {
    getCollections: async () => {
      return collectionService.getMyCollections();
    },
  },
  getFavoritedCollections: async () => {
    return collectionService.getFavoriteCollections();
  },
};
</script>

<style scoped>
.transparent-button {
  background-color: rgba(255, 0, 200, 0.63); 
  color: #fff;
  padding: 10px 20px;
  border: none;
  border-radius: 3px;
  cursor: pointer;
  transition: background-color 0.3s ease, color 0.3s ease;
}

.transparent-button:hover {
  background-color: rgba(0, 123, 255, 0.664); 
}


.collection-title {
  padding: 40px;
  color: seashell;
  justify-content: center;
}
.collections {
  background-image: url("../img/ElementalBackground.png");
  background-size: cover;
  background-position: 55%;
  width: 100vw;
  min-height: 100vw;
}

.tile-container{
  justify-content: center;
  text-align: center;
}

section {
  display: flex;
  flex-direction: row;
  justify-content: center;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  padding: 20px;
  justify-content: space-around;
  max-width: 100%;
  max-height: auto;

}
.collection-container {
  font-size: 20px;
  background-color: rgba(245, 203, 214, 0.514);
  border-radius: 5%;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  margin-bottom: 10px;
  
  
}

img {
  border-radius: 15px;
  width: 270px;
  height: 378px;
}

.page-header {
  padding: 50px;
  color: rgb(241, 239, 234);
  font-family: 'Simonetta', cursive;
  font-style: bold;
  font-size: 75px;
}
</style>
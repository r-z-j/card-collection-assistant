<template>
  <div class="collections">
      <link rel="stylesheet"
      href="http://fonts.googleapis.com/css2?family=Simonetta&display=swap"/>
    <center><h1 class="page-header">Collections</h1></center>

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
    // myCollections() {
    //   return this.$store.state.collections;
    // },
  },

  async created() {
    const res = await this.getCollections();
    this.collections = res.data;
    // const res = await collectionService.getMyCollections();
    // console.log(res.data);
    // this.collections = res.data;
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
.collection-title {
  padding: 40px;
  color: seashell;
  justify-content: center;
}
.collections {
  display: block;
  align-content: center;
  flex-grow: 1;
  background-image: url("../img/ElementalBackground.png");
  background-size: cover;
  background-position: 55%;
  width: 100vw;
  height: 100vw;
}

.tile-container{
  justify-content: center;
  text-align: center;
}

section {
  display: flex;
  flex-direction: row;
  justify-content: center;
}
.collection-container {
  display: inline-block;
  padding: 20px;
  justify-content: center;
}

img {
  border-radius: 15px;
  width: 270px;
  height: 378px;
}

.page-header {
  padding: 50px;
  color: rgb(248, 243, 238);
  font-family: 'Simonetta', cursive;
  font-style: bold;
  font-size: 75px;
  
}
</style>
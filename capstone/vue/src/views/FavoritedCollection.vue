<template>
  <div class="favorited-collection">
    <link
      rel="stylesheet"
      href="http://fonts.googleapis.com/css2?family=Simonetta&display=swap"
    />
    <center><h1 class="page-header">Favorited</h1></center>

    <section>
      <div
        class="favorited-container"
        v-for="collection in favorited"
        v-bind:key="collection.collectionId"
      >
        <div class="tile-container">
          <div class="collection-title">{{ collection.collectionName }}</div>
          <div v-if="collection.gameTypeId === 2">
            <router-link v-bind:to="{ name: 'ptcg-collection-cards', params: {id: collection.collectionId} }">
              <img src="../img/pokemon-cardback.png" />
            </router-link>
          </div>
          <div v-else-if="collection.gameTypeId === 1">
            <router-link v-bind:to="{ name: 'mtg-collection-cards', params: { id: collection.collectionId } }">
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
  name: "favorited",
  data() {
    return {
      favorited: null,
    };
  },

  async created() {
    const res = await this.getFavoritedCollections();
    this.favorited = res.data;
    console.log(res.data)
 
  },

  methods: {
    getFavoritedCollections: async () => {
      return collectionService.getFavoriteCollections();
    },
  },
};
</script>

<style scoped>
.collection-title {
  padding: 40px;
  color: seashell;
  justify-content: center;
}
.favorited-collection {
  display: block;
  align-content: center;
  flex-grow: 1;
  background-image: url("../img/ElementalBackground.png");
  background-size: cover;
  background-position: 55%;
  width: 100vw;
  min-height: 100vw;
}

.tile-container {
  justify-content: center;
  text-align: center;
  background-color: rgba(48, 48, 94, 0.678);
  border-radius: 10px;
}

section {
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  justify-content: center;
}
.favorited-container {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
  padding: 15px;
  
}

img {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  border-radius: 15px;
  min-width: 270px;
  height: 378px;
  
}

.page-header {
  padding: 50px;
  color: rgb(248, 243, 238);
  font-family: "Simonetta", cursive;
  font-style: bold;
  font-size: 75px;
}

</style>
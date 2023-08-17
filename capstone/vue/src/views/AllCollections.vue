<template>
  <div class="collections">
    <link
      rel="stylesheet"
      href="http://fonts.googleapis.com/css2?family=Simonetta&display=swap"
    />
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
            <router-link
              v-bind:to="{
                name: 'ptcg-collection-cards',
                params: { id: collection.collectionId },
              }"
            >
              <img src="../img/pokemon-cardback.png" />
            </router-link>
          </div>
          <div v-if="collection.gameTypeId === 1">
            <router-link
              v-bind:to="{
                name: 'mtg-collection-cards',
                params: { id: collection.collectionId },
              }"
            >
              <img src="../img/magicCardBack.png" />
            </router-link>
          </div>
          <div v-if="$store.state.token.length > 0">

          <button
          v-if="!favorited.filter(e => e.collectionId === collection.collectionId).length > 0"
          @click="addToFavorites(collection.collectionId)"
          >Add to Favorites</button>
          <button
          v-else
          @click="removeFromFavorites(collection.collectionId)"
          >Remove from Favorites</button>

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
      collections: [],
      favorited: [],
    };
  },

  async created() {
    try {
      const favs = await this.getFavoritedCollections();
      this.favorited = favs;
      const res = await this.getCollections();
      this.collections = res; 
    } catch (e) {
      console.log(e);
    }
  },

  methods: {
    getCollections: async () => {
      try {
      const magicRes = await collectionService.getMagicCollections();
      const pokeRes = await collectionService.getPokemonCollections();
      const res = [...magicRes.data, ...pokeRes.data];
      return res;
      } catch (e) {
        console.log(e)
      }
    },
    getFavoritedCollections: async () => {
      try {
        const favRes = await collectionService.getFavoriteCollections();
        return favRes.data;
      } catch (e) {
        console.log("here")
      }
    },

    async addToFavorites(id) {
      await collectionService.addCollectionToFavorites(id);
      this.$router.go();
    },
    async removeFromFavorites(id) {
      await collectionService.removeCollectionToFavorites(id);
      this.$router.go();
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
.collections {
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
.collection-container {
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

button {
  margin-top: 10px;
  padding: 10px 20px;
  font-size: 16px;
  background-color: #3d1eafe5;
  color: white;
  border: 2px solid #260cbdd5;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}
button:hover {
  background-color: #98049d;
}
</style>
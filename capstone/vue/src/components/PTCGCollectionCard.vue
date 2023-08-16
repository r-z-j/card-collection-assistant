<template>
  <div class="card-list" v-if="isLoaded">
    <div
      v-for="card in cardListResponse"
      :key="card.id"
      class="poke-card"
    > 
      <div class="card-image">
        <img :src="getImage(card.cardId)" alt="Card Image" />
      </div>
    </div>
     <router-link v-bind:to="{ name: 'pokemon-search' }" class="back-to-search">
      <AddCardCard></AddCardCard>
    </router-link>
      <router-link v-bind:to="{ name: 'UpdateCollection' }" class="back-to-search">
      <UpdateCollectionCard></UpdateCollectionCard>
      </router-link>
  </div>
    <div class="pokeball" v-else>
      <img src="../img/pokeball.gif" alt="">
    </div>
    
</template>
  
<script>
import collectionApiService from "../services/CollectionApiService";
import pokeService from "../services/PokemonService.js";
import AddCardCard from "../components/AddCardCard.vue"
import UpdateCollectionCard from './UpdateCollectionCard.vue';

export default {
  name: "poke-card",
  props: ["pokeCardName"],
  components: {AddCardCard, UpdateCollectionCard},

  data() {
    return {
      cardListResponse: [],
      imageUris: new Map(),
      collectionID: this.$route.params.id,
      isLoaded: false,
    };
  },

  created() {
    this.getCollectionFromID(); 
  },

  methods: {
    async getCollectionFromID() {
      try {
        const response = await collectionApiService.getCollectionById(
          this.collectionID.toString()
        );
        this.cardListResponse = response.data.cardList;
        for(const card of this.cardListResponse) {
          const res = await pokeService.getSingleCardById(card.cardApiId);
          this.imageUris.set(card.cardId, res.data.data.images.large);
        }
        console.log(this.imageUris)
        this.isLoaded = true;
      } catch (error) {
        console.error("Error fetching collection:", error);
      }
    },
    getImage(id) {
      console.log(this.imageUris.get(id));
      return this.imageUris.get(id);
    }
  },
};
</script>
  
  <style scoped>
.card-list {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
}
.poke-card {
  position: relative;
  display: flex;

  flex-direction: row;
  border: 2px solid #360164;
  border-radius: 10px;
  padding: 10px;
  margin: 10px;
  text-align: center;
  background-color: rgb(197, 134, 236, 0.65);
}

.flip-button {
  width: 30px;
  height: 30px;
  cursor: pointer;
  background-color: white;
  border-radius: 100px;
}

.flip-button-container {
  position: absolute;
  top: 40px;
  right: -5px;
  z-index: 1;
}

.card-image {
  position: relative;
  min-width: 270px;
  max-width: 270px;
  min-height: 378px;
  max-height: 378px;
  padding-right: 10px;
  perspective: 1000px;
}

.card-image-content {
  position: relative;
  width: 100%;
  height: 100%;
  transform-style: preserve-3d;
  transition: transform 0.3s ease;
  backface-visibility: hidden;
  will-change: transform;
}

.card-face {
  width: 100%;
  height: 100%;
  border-radius: 12px;
  object-fit: cover;
  position: absolute;
  top: 0;
  left: 0;
  transition: opacity 0.3s ease;
}


.card-image {
  width: 270px;
  height: 378px;
  border-radius: 12px;
}

.pokeball {
  position: absolute;
  top: 70vh;
  right: 10vw;
}

button {
  padding: 10px 20px;
  font-size: 16px;
  background-color: #6200ff;
  color: white;
  border: 2px solid #270cbd;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #3e049d;
}
</style>
<template>
  <main>
    <h1 v-if="!isUpdating" class="collection-name">{{ collectionName }}</h1>
    <form v-else v-on:submit.prevent class="transparent-form">
      <div class="field">
        <label for="title">Title</label>
        <input type="text" name="title" v-model="collectionName" />
      </div>
    </form>
    <div class="card-list" v-if="isLoaded">
      <div v-for="card in cardListResponse" :key="card.id" class="poke-card">
        <div class="card-image">
          <button 
        @click="deleteCardById(card.cardApiId)"

        v-if="showDeleteButton()" 
        class="delete-button">
          <img
          src="../img/trashicon.png" class="delete-icon"/>
        </button>
          <img :src="getImage(card.cardId)" alt="Card Image" />
        </div>
      </div>
      <router-link
        v-bind:to="{ name: 'pokemon-search' }"
        class="back-to-search"
      >
        <AddCardCard></AddCardCard>
      </router-link>
      <div @click="isUpdating = !isUpdating">
        
      </div>
    </div>
    <div class="pokeball" v-else>
      <img src="../img/pokeball.gif" alt="" />
    </div>
  </main>
</template>
  
<script>
import collectionApiService from "../services/CollectionApiService";
import pokeService from "../services/PokemonService.js";
import AddCardCard from "../components/AddCardCard.vue";

export default {
  name: "poke-card",
  props: ["pokeCardName"],
  components: { AddCardCard, },

  data() {
    return {
      cardListResponse: [],
      imageUris: new Map(),
      collectionID: this.$route.params.id,
      isLoaded: false,
      collection: {},
      collectionName: "",
      isUpdating: false,
    };
  },

  created() {
    this.getCollectionFromID();
  },

  methods: {

    async deleteCardById(cardId) {
      const confirmed = window.confirm("Are you sure you want to remove this card from this collection?");
      
      if (confirmed) {
        try {
          await collectionApiService.removeCardFromCollection(this.collectionID, cardId);
          this.$router.go()
        } catch (error) {
          console.error("Error deleting card:", error);
        }
      }
    },

    showDeleteButton(){
      return this.collection.authorId === this.$store.state.user.id
    },

    async getCollectionFromID() {
      try {
        const response = await collectionApiService.getCollectionById(
          this.collectionID.toString()
        );
        this.cardListResponse = response.data.cardList;
        this.collection = response.data;
        this.collectionName = response.data.collectionName;
        for (const card of this.cardListResponse) {
          const res = await pokeService.getSingleCardById(card.cardApiId);
          this.imageUris.set(card.cardId, res.data.data.images.large);
        }
        console.log(this.imageUris);
        this.isLoaded = true;
      } catch (error) {
        console.error("Error fetching collection:", error);
      }
    },
    getImage(id) {
      console.log(this.imageUris.get(id));
      return this.imageUris.get(id);
    },
    saveCollectionName() {
      // set collection name
      this.collection.collectionName = this.collectionName;
      collectionApiService.
      this.isUpdating = false;
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

.collection-name {
  text-align: center;
  margin-top: 50px;
}

.pokeball {
  position: absolute;
  top: 70vh;
  right: 10vw;
}

.delete-icon{
  height: 33px;
  width: 27px;
  position: relative;
}

.delete-button{
  position: absolute;
  top: 280px;
  right: -5px;
  z-index: 1;
}

button {
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

a{
  text-decoration: none;
}
</style>
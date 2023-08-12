<template>
  <div>
    <div v-for="card in $store.state.magicCards" 
         v-bind:key="card.id" 
         class="magic-card"
         >
      <div class="card-image" @click="flipCard(card)">
        <img v-if="card.isFlipped && card.backFace" 
             :src="card.backFace.imageUri" 
             alt="Card Back Face" 
        />
        <img v-else-if="card.frontFace"
             :src="card.frontFace.imageUri" 
             alt="Card Front Face" 
        />
        <img v-else
             :src="card.imageUri" 
             alt="Card Image" 
        />
      </div>
      <div class="card-details">
        <h1>{{ card.name }} ({{ card.setName }})</h1>
        <h3>{{ card.oracleText }}</h3>
        <div class="buttons">
          <button>View Collections</button>
          <button>Add To Collection</button>
        </div>
      </div>
    </div>
  </div>
</template>
  
<script>
import scryfallService from '../services/ScryfallService';

export default {
  name: "magic-card",
  props: ["magicCardName"],
  created() {
    this.getMultipleCardsBySearch(this.$store.state.searchQuery);
  },
  methods: {
    getMultipleCardsBySearch(searchQuery){
      scryfallService.getMultipleCardsBySearchName(searchQuery).then(response => {
        this.$store.commit("CLEAR_MAGIC_CARDS")
        this.$store.commit("SET_MAGIC_CARDS_SEARCH", response.data);
      });
    },
    flipCard(card){
      card.isFlipped = !card.isFlipped;
    }
  },
  computed: {
    getSearchQuery(){
      return this.$store.state.searchQuery
    }
  },
  watch: {
    '$store.state.searchQuery'(newValue) {
      this.getMultipleCardsBySearch(newValue);
    }
  }
};
</script>
  
  <style scoped>
.magic-card {
  display: flex;
  flex-direction: row;
  border: 2px solid #a3a2a2;
  border-radius: 10px;
  padding: 10px;
  margin: 10px;
  width: 90vw;
  text-align: center;
  background-color: rgb(197, 134, 236);
}

.card-image {
  min-width: 270px;
  max-width: 270px;
  min-height: 378px;
  max-height: 378px;
  padding-right: 10px;
}

img {
  width: 270px;
  height: 378px;
  border-radius: 10px;
}

.card-details {
  display: flex;
  flex-direction: column;
  flex-grow: 1;
}

.buttons {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  margin-top: auto;
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
  
<template>
  <div>
    <div v-for="card in magicCards" :key="card.id" class="magic-card" :class="{ flipped: card.isFlipped }">
      <div class="card-image">
        <div class="flip-button-container" @click="flipCard(card)">
          <img v-if="card.frontFace && card.backFace" class="flip-button" src="@/img/flip.png" alt="Flip Icon" />
        </div>
        <div class="card-image-content">
          <img v-if="card.isFlipped && card.backFace" :src="card.backFace.imageUri" alt="Card Back Face" class="card-face back-face" />
          <img v-else-if="card.frontFace" :src="card.frontFace.imageUri" alt="Card Front Face" class="card-face front-face" />
          <img v-else :src="card.imageUri" alt="Card Image" />
        </div>
      </div>
      <div class="card-details">
        <h1>{{ card.name }} ({{ card.setName }})</h1>
        <h3>{{ card.oracleText }}</h3>
        <div class="buttons">
          <button>View Collections</button>
           <button @click="openModal(card)">Add To Collections</button>
        </div>
      </div>
      <card-form-modal v-show="showModal" @close="closeModal" :gameType="1" />
    </div>
  </div>
</template>

<script>
import scryfallService from '../services/ScryfallService';
import CardFormModal from './CardFormModal.vue';

export default {
  components: { CardFormModal },
  name: "magic-card",
  props: ["magicCardName"],
  created() {
    this.getMultipleCardsBySearch(this.$store.state.searchQuery);
  },
  data() {
    return {
      showModal: false,
      selectedCard: null,
    };
  },
  methods: {
    async getMultipleCardsBySearch(searchQuery) {
      const response = await scryfallService.getMultipleCardsBySearchName(searchQuery);
      this.$store.commit("CLEAR_MAGIC_CARDS");
      this.$store.commit("SET_MAGIC_CARDS_SEARCH", response.data);
    },
    flipCard(card) {
      card.isFlipped = !card.isFlipped;
    },
    openModal(card) {
      this.selectedCard = card;
      this.showModal = true;
    },
    closeModal() {
      this.selectedCard = null;
      this.showModal = false;
    }
  },
  computed: {
    magicCards() {
      return this.$store.state.magicCards;
    },
  },
  watch: {
    "$store.state.searchQuery"(newValue) {
      this.getMultipleCardsBySearch(newValue);
    },
  },
};
</script>

<style scoped>
.magic-card {
  position: relative;
  display: flex;
  flex-direction: row;
  border: 2px solid #360164;
  border-radius: 10px;
  padding: 10px;
  margin: 10px;
  width: 90vw;
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

.card-face.back-face {
  transform: rotateY(180deg);
}

.magic-card.flipped .flip-button {
  transform: rotateY(180deg);
}

.magic-card.flipped .card-image-content {
  transform: rotateY(180deg);
}

img {
  width: 270px;
  height: 378px;
  border-radius: 12px;
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

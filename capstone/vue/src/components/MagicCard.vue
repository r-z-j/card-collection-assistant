<template>
  <div>
    <div
      v-for="card in magicCards"
      :key="card.id"
      class="magic-card"
      :class="{ flipped: card.isFlipped }"
    >
      <div class="card-image">

        <router-link v-bind:to="{ name: 'add-magic', params: { id: card.id } }">
          <button class="add-button">
            <img src="../img/plus-symbol-button.png" class="add-icon" />
          </button>
        </router-link>


        <div class="flip-button-container" @click="flipCard(card)">
          <img
            v-if="card.frontFace && card.backFace"
            class="flip-button"
            src="@/img/flip.png"
            alt="Flip Icon"
          />
        </div>
        <div class="card-image-content">
          <img
            v-if="card.isFlipped && card.backFace"
            :src="card.backFace.imageUri"
            alt="Card Back Face"
            class="card-face back-face"
          />
          <img
            v-else-if="card.frontFace"
            :src="card.frontFace.imageUri"
            alt="Card Front Face"
            class="card-face front-face"
          />
          <img v-else :src="card.imageUri" alt="Card Image" />
        </div>
      </div>

      <div class="card-details">
        <table>
          <tr>
            <th>Name</th>
            <td>{{ card.name }}</td>
          </tr>
          <tr>
            <th>Cost</th>
            <td>
              {{ card.manaCost }}
            </td>
          </tr>
          <tr>
            <th>Type</th>
            <td>
              {{ card.typeLine }}
            </td>
          </tr>
          <tr>
            <th>Effect</th>
            <td>{{ card.oracleText }}</td>
          </tr>
          <tr v-if="card.power">
            <th>Power</th>
            <td>
              {{ card.power }}
            </td>
          </tr>
          <tr v-if="card.toughness">
            <th>Toughness</th>
            <td>
              {{ card.toughness }}
            </td>
          </tr>
          <tr>
            <th>Set Name</th>
            <td>{{ card.setName }}</td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import scryfallService from "../services/ScryfallService";

export default {
  name: "magic-card",
  props: ["magicCardName"],
  created() {
    this.getMultipleCardsBySearch(this.$store.state.searchQuery);
  },
  methods: {
    async getMultipleCardsBySearch(searchQuery) {
      const response = await scryfallService.getMultipleCardsBySearchName(
        searchQuery
      );
      this.$store.commit("CLEAR_MAGIC_CARDS");
      this.$store.commit("SET_MAGIC_CARDS_SEARCH", response.data);
    },
    flipCard(card) {
      card.isFlipped = !card.isFlipped;
    },
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
  width: 50vw;
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

.add-icon {
  height: 15px;
  width: 15px;
  position: relative;

}

.add-button {
  position: fixed;
  display: flex;
  flex-direction: column;
  align-content: center;
  background-color: #6200ff;
  border: 2px solid #270cbd;
  cursor: pointer;
  border-radius: 6px;
  top: 280px;
  right: -5px;
  z-index: 1;
  padding: 10px 10px;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #3e049d;
}

table {
  border-collapse: collapse;
  width: 100%;
}

th,
td {
  padding: 8px;
  text-align: left;
  border-bottom: 1px solid black;
}

tr:not(:first-child) {
  margin-top: 10px;
}
</style>

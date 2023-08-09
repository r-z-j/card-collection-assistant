<template>
  <div class="magic-card">
    <div class="card-image">
      <img :src="cardImageUrl" alt="Card Image" />
    </div>
    <div class="card-details">
      <h1>{{ card.name }}</h1>
      <h3>{{ card.oracle_text }}</h3>
      <div class="buttons">
        <button>View Collections</button>
        <button>Add To Collection</button>
      </div>
    </div>
  </div>
</template>
  
  <script>
export default {
  props: ["cardName"],
  data() {
    return {
      card: {},
    };
  },
  computed: {
    cardImageUrl() {
      return this.card.image_uris ? this.card.image_uris.normal : "";
    },
  },
  mounted() {
    this.fetchCardInfo();
  },
  methods: {
    async fetchCardInfo() {
      try {
        const response = await fetch(
          `https://api.scryfall.com/cards/named?exact=${this.cardName}`
        );
        const data = await response.json();
        this.card = data;
      } catch (error) {
        console.error("Error fetching card:", error);
      }
    },
  },
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
}

.card-details{
    display: flex;
    flex-direction: column;
    flex-grow: 1;
}

.buttons{
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
  
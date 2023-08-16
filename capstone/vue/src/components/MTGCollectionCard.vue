<template>
  <div class="card-list">
    <div
      v-for="card in currentCollection"
      :key="card.id"
      class="magic-card"
      :class="{ flipped: card.isFlipped }"
    >
      <div class="card-image">
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
    </div>
    <router-link v-bind:to="{ name: 'mtg-search-view' }" class="back-to-search">
      <AddCardCard></AddCardCard>
    </router-link>
  </div>
</template>
  
<script>
import collectionApiService from "../services/CollectionApiService";
import scryfallService from "../services/ScryfallService";
import AddCardCard from '../components/AddCardCard.vue';

export default {
  name: "magic-card",
  props: ["magicCardName"],
  components: {AddCardCard},

  data() {
    return {
      cardListResponse: [],
      currentCollection: [],
      collectionID: this.$route.params.id,
    };
  },

  created() {
    this.getCollectionFromID().then(() => {
      this.getCardsFromCollection();
    });
  },

  methods: {
    async getCollectionFromID() {
      try {
        const response = await collectionApiService.getCollectionById(
          this.collectionID.toString()
        );
        this.cardListResponse = response.data.cardList;
      } catch (error) {
        console.error("Error fetching collection:", error);
      }
    },

    async getCardsFromCollection() {
      try {
        for (const card of this.cardListResponse) {
          const response = await scryfallService.getSingleCardById(
            card.cardApiId
          );
          let singleCardResponse = response.data;
          let mappedCard = {
            id: singleCardResponse.id,
            name: singleCardResponse.name,
            oracleText: singleCardResponse.oracle_text,
            setName: singleCardResponse.set_name,
            isFlipped: false,
            isDualSided:
              singleCardResponse.layout === "transform" ||
              singleCardResponse.layout === "modal_dfc",
          };
          if (
            singleCardResponse.card_faces &&
            singleCardResponse.card_faces.length > 1
          ) {
            mappedCard.frontFace = {
              name: singleCardResponse.card_faces[0].name,
              oracleText: singleCardResponse.card_faces[0].oracle_text,
              imageUri: singleCardResponse.card_faces[0].image_uris.normal,
            };
            mappedCard.backFace = {
              name: singleCardResponse.card_faces[1].name,
              oracleText: singleCardResponse.card_faces[1].oracle_text,
              imageUri: singleCardResponse.card_faces[1].image_uris.normal,
            };
          } else {
            mappedCard.imageUri = singleCardResponse.image_uris.normal;
          }
          this.currentCollection.push(mappedCard);
        }
      } catch (error) {
        console.error("Error fetching cards:", error);
      }
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
};
</script>
  
  <style scoped>
.card-list {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
}
.magic-card {
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

.card-face.back-face {
  transform: rotateY(180deg);
}

.magic-card.flipped .flip-button {
  transform: rotateY(180deg);
}

.magic-card.flipped .card-image-content {
  transform: rotateY(180deg);
}

.back-to-search{
  text-decoration: none;
}

img {
  width: 270px;
  height: 378px;
  border-radius: 12px;
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
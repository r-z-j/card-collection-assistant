<template>
  <div>
    <img v-if="apiCard" :src="apiCard.image_uris.small" alt="" />

    <div v-if="filteredCollections.length < 1">
        <h2>You don't have any collections</h2>

        </div>
        <div v-else>


    <form v-on:submit.prevent>
      <div class="field">
        <label for="title">Title</label>
        <input type="text" name="title" v-model="cardToSave.cardName" />
      </div>
      <div class="field">
        <label for="quantity">Quantity</label>
        <input type="number" name="quantity" v-model="cardToSave.quantity" />
      </div>
      <div class="field">
        <label for="price">Price</label>
        <input type="number" name="price" v-model="cardToSave.userPrice" />
      </div>
      <div class="field">
        <label for="condition">Condition</label>
        <select name="condition" v-model="cardToSave.conditionId">
          <option value="1">Mint</option>
          <option value="2">Near Mint</option>
          <option value="3">Excellent</option>
          <option value="4">Good</option>
          <option value="5">Light Layed</option>
          <option value="6">Played</option>
          <option value="7">Poor</option>
        </select>
      </div>
      <div class="field">
        <label for="collection">Collection</label>
        <select name="pets" id="pet-select" v-model="cardToSave.collectionId">
          <option
            v-for="collection in filteredCollections"
            :value="collection.collectionId"
            :key="collection.collectionId"
          >
            {{ collection.collectionName }}
          </option>
        </select>
      </div>
      <div class="actions">
        <button
          type="submit"
          v-on:click="submitCard(cardToSave, $route.params.id)"
        >
          Add Card
        </button>
      </div>
    </form>
    </div>

  </div>
</template>

<script>
import collectionService from "../services/CollectionApiService";
import scryfallService from '../services/ScryfallService';

export default {
  name: "add-poke",
  data() {
    return {
      collections: null,
      apiCard: null,
      cardToSave: {
        cardApiId: "",
        cardName: "",
        userPrice: 0,
        quantity: 1,
        gameTypeId: 1,
        conditionId: 0,
        collectionId: 0,
      },
    };
  },

  computed: {
    filteredCollections() {
      return this.collections.filter(
        (collection) => collection.gameTypeId === 1
      );
    },
  },

  async created() {
    const collectionRes = await this.getCollections();
    this.collections = collectionRes.data;

    const cardRes = await this.getCardData(
      this.$route.params.id,
    );
    console.log(cardRes.data);
    this.apiCard = cardRes.data;
  },

  methods: {
    getCollections: async () => {
      return collectionService.getMyCollections();
    },

    getCardData: async (id) => {
      const res = await scryfallService.getSingleCardById(id);
      return res;
    },

    submitCard: async (card, apiId) => {
      card.cardApiId = apiId;
      console.log(card);
      collectionService.addCardToCollection(card);
    },
  },
};
</script>
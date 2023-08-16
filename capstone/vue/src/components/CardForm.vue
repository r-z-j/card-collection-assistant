<template>
    <form v-on:submit.prevent class="transparent-form">
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
        <select name="collection" id="collection" v-model="cardToSave.collectionId">
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
          class="btn btn-primary transparent-button"
        >
          Add Card
        </button>
      </div>
    </form>
</template>

<script>
import collectionService from '../services/CollectionApiService';
import scryfallService from '../services/ScryfallService';
import pokemonService from '../services/PokemonService';

export default {
  name: 'CardForm',
  props: {
      gameType: {
          type: Number,
          required: true
      }
  },
  data() {
    return {
      collections: [],
      apiCard: null,
      cardToSave: {
        cardApiId: "",
        cardName: "",
        userPrice: 0,
        quantity: 1,
        gameTypeId: this.gameType,
        conditionId: 0,
        collectionId: 0,
      },
    };
  },

  computed: {
    filteredCollections() {
      return this.collections.filter(
        (collection) => collection.gameTypeId === this.gameType
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

    getMagicCardData: async (id) => {
      const res = await scryfallService.getSingleCardById(id);
      return res;
    },

    getPokemonCardData: async (id) => {
      const res = await pokemonService.getSingleCardById(id);
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

<style scoped>
.transparent-form {
  background-color: rgba(255, 255, 255, 0.1); 
  padding: 20px;
  border-radius: 5px;
  backdrop-filter: blur(10px); 
  border: 1px solid rgba(255, 255, 255, 0.2);
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
  transition: background-color 0.3s ease;
}

.transparent-form:hover {
  background-color: rgba(255, 255, 255, 0.5); 
}

form {
    width: 300px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #f9f9f9;
}

.field {
    margin-bottom: 10px;
}

label {
    display: block;
    font-weight: bold;
    margin-bottom: 5px;
}

input,
select {
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 3px;
}

.transparent-button {
  background-color: rgba(0, 123, 255, 0.3); 
  color: #fff;
  padding: 10px 20px;
  border: none;
  border-radius: 3px;
  cursor: pointer;
  transition: background-color 0.3s ease, color 0.3s ease;
}

.transparent-button:hover {
  background-color: rgba(0, 123, 255, 0.5); 
}

</style>
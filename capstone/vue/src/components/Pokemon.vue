<template>
  <div>
    <div
      v-for="card in $store.state.pokeCards"
      v-bind:key="card.id"
      class="pokemon-card"
    >
      <div class="card-image">
        <img :src="card.imageUri" alt="Card Image" />
      </div>
      <div class="card-details">
        <h3>{{ card.name }}</h3>
        <h3>{{ card.hp }}</h3>

        <div v-for="type in card.types" v-bind:key="type.name">
          <p>{{ type }}</p>
        </div>

        <div v-for="attack in card.attacks" v-bind:key="attack.name">
          <p>{{ attack.name }}</p>
          <p>{{ attack.damage }}</p>
          <p>{{ attack.text }}</p>
        </div>

        <div v-for="weakness in card.weaknesses[0]" v-bind:key="weakness.type">
          <p>{{ weakness }}</p>
        </div>

        <div class="buttons">
          
          <button>
            <router-link v-bind:to="{ name: 'add-poke', params: { id: card.id } }">
                Add To Collections</router-link>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import pokemonService from "../services/PokemonService";

export default {
  name: "pokemon-card",
  props: ["pokeCardName"],

  created() {
    this.getMultipleCardsBySearch(this.$store.state.searchQuery);
  },

  methods: {
    getSingleCardById() {
      pokemonService.getSingleCardById("gym2-2").then((response) => {
        this.$store.commit("SET_POKEMON_CARD", response.data);
      });
    },
    getMultipleCardsBySearch(searchQuery) {
      pokemonService
        .getMultipleCardsBySearchName(searchQuery)
        .then((response) => {
          console.log(response.data);
          this.$store.commit("CLEAR_POKEMON_CARDS");
          this.$store.commit("SET_POKE_CARDS_SEARCH", response.data);
        });
    },
  },

  computed: {
    getSearchQuery() {
      return this.$store.state.searchQueery;
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
.pokemon-card {
  display: flex;
  flex-direction: row;
  border: 2px solid #52525a;
  background-color: rgb(255, 255, 255, 0.65);
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
  background-color: #ff0015e0;
  color: white;
  border: 2px solid #0f0f0f;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}
button:hover {
  background-color: #3e049d;
}
</style>
<template>
  <div>
    <div
      v-for="card in $store.state.pokeCards"
      v-bind:key="card.id"
      class="pokemon-card"
    >
    <div class="card-image">

      <router-link v-bind:to="{ name: 'add-poke', params: { id: card.id } }">
        <button class="add-button">
          <img src="../img/plus-symbol-button.png" class="add-icon" />
          <span class="button-text">Add Card</span>
        </button>
      </router-link>
      
      <img :src="card.imageUri" alt="Card Image" />
      </div>
      <div class="card-details">
        <table>
          <tr>
            <th>Name</th>
            <td>{{ card.name }}</td>
          </tr>
          <tr>
            <th>HP</th>
            <td>{{ card.hp }}</td>
          </tr>
          <tr>
            <th>Type(s)</th>
            <td>
              <div v-for="type in card.types" v-bind:key="type.name">
                <p>{{ type }}</p>
              </div>
            </td>
          </tr>
          <tr>
            <th>Attacks</th>
            <td>
              <div v-for="attack in card.attacks" v-bind:key="attack.name">
                <p>Name: {{ attack.name }}</p>
                <p>Damage: {{ attack.damage }}</p>
                <p>Text: {{ attack.text }}</p>
              </div>
            </td>
          </tr>
          <tr>
            <th>Weaknesses</th>
            <td>
              <div
                v-for="weakness in card.weaknesses[0]"
                v-bind:key="weakness.type"
              >
                <p>{{ weakness }}</p>
              </div>
            </td>
          </tr>
        </table>
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
  position: relative;
  display: flex;
  flex-direction: row;
  border: 2px solid #52525a;
  background-color: rgb(255, 255, 255, 0.65);
  border-radius: 10px;
  padding: 10px;
  margin: 10px;
  width: 50vw;
  text-align: center;
}
.card-image {
  position: relative;
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
.add-icon {
  height: 15px;
  width: 15px;
  position: relative;
  opacity: 1;
}

.add-button:hover .add-icon {
  opacity: 0; 
}
.add-button {
  position: absolute;
  display: flex;
  flex-direction: column;
  align-content: center;
  background-color: #6200ff;
  border: 2px solid #270cbd;
  cursor: pointer;
  border-radius: 6px;
  bottom: 40px;
  right: -5px;
  z-index: 1;
  padding: 10px 10px;
  overflow: hidden;
  transition: background-color 0.3s ease, padding 0.3s ease;
}

.button-text {
  color: white;
  font-size: 14px;
  position: absolute;
  top: 50%;
  left: 70%; 
  transform: translateY(-50%) translateX(-100%); 
  opacity: 0; 
  transition: opacity 0.3s ease; 
  white-space: nowrap; 
}
.add-button:hover {
  background-color: #3e049d;
  padding: 10px 75px;
}

.add-button:hover .button-text {
  opacity: 1; 
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
  border-bottom: 1px solid #ddd;
}

tr:not(:first-child) {
  margin-top: 10px;
}
</style>
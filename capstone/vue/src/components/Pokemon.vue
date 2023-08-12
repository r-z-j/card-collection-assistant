<template>
<div>
  <div v-for="card in $store.state.pokeCards" v-bind:key="card.id" class="pokemon-card">
    <div class="card-image">
      <img :src="card.imageUri" alt="Card Image" />
    </div>
    <div class="card-details">
      <h1>{{ card.name }}</h1>
      <h1>{{ card.hp }}</h1>
      <h1>{{ card.types }}</h1>
      <h1>{{ card.attacks }}</h1>
      <h1>{{ card.weakenesses }}</h1> 
      <div class="buttons">
        <button>View Collections</button>
        <button>Add To Collection</button>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
import pokemonService from  '../services/PokemonService';

export default {
  name: "pokemon-card",  
  props: ["pokeCardName"],
  
  created(){
      this.getMultipleCardsBySearch(this.$store.state.searchQuery);
  },
  
  
  methods: {
   getSingleCardById(){
       pokemonService.getSingleCardById('gym2-2').then(response =>{
           this.$store.commit("SET_POKEMON_CARD", response.data);
       });
   },
   getMultipleCardsBySearch(searchQuery){
       pokemonService.getMultipleCardsBySearchName(searchQuery).then(response =>{
           this.$store.commit("CLEAR_POKEMON_CARDS")
           this.$store.commit('SET_POKE_CARDS_SEARCH', response.data);
       });
   },


  },
  
  computed: {
      getSearchQuery(){
          return this.$store.state.searchQueery
      }
  },
    watch: {
        '$store.state.searchQuery'(newValue){
            this.getMultipleCardsBySearch(newValue);
        }
    }
};
</script>
  
  <style scoped>
.pokemon-card {
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
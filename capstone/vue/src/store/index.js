import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},

    //Searches are stored here
    searchQuery: '',

    //Pokemon Card Objects
    pokeCards: [],
    pokeCard: {
      id: null,
      name: '',
      imageUri: [],
      hp: '',
      types: [0,1],
      attacks: {
       name : '',
       text:'',
       damage: '',
    },
      weaknesses: {
        type: '',
        value: '',
      },
    },
    //Magic Card Objects
    magicCards: [],
    magicCard: {
      id: null,
      name: '',
      imageUri: [],
      oracleText: '',
      setName: '',
      isDualSided: false,
    }
    
  },

  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },

    //POKEMON MUTATIONS
    SET_POKEMON_CARD(state, data){
     state.pokeCard.id = data.data.id;
     state.pokeCard.name = data.data.name;
     state.pokeCard.imageUri = data.data.images;
     state.pokeCard.hp = data.data.hp;
     state.pokeCard.types = data.data.types;
     state.pokeCard.attacks.name = data.data.attacks.name;
     state.pokeCard.weaknesses = data.data.weaknesses;
     state.pokeCard.weaknesses.value = data.data.weaknesses.value;
    },

    SET_POKE_CARDS_SEARCH(state, data){
      state.pokeCards = data.data.map(cardData=>({
    
        id: cardData.id,
        name: cardData.name,
        imageUri: cardData.images.large,
        hp: cardData.hp,
        types: cardData.types,
        attacks: cardData.attacks,
        weaknesses: cardData.weaknesses,

      } ));
    },

    CLEAR_POKEMON_CARDS(state) {
      state.pokeCards = [];
    },


    //MAGIC CARD MUTATIONS
    SET_MAGIC_CARD(state, data) {
      state.magicCard.name = data.name;
      state.magicCard.id = data.id;
      state.magicCard.imageUri = data.image_uris;
      state.magicCard.oracleText = data.oracle_text;
      state.magicCard.setName = data.set_name;
    },
    SET_MAGIC_CARDS_SEARCH(state, data) {
      state.magicCards = data.data.map(cardData => {
        let card = {
          id: cardData.id,
          name: cardData.name,
          oracleText: cardData.oracle_text,
          setName: cardData.set_name,
          isFlipped: false,
          isDualSided: cardData.layout === "transform" || cardData.layout === "modal_dfc",
        };
    
        // Check if the card has multiple faces
        if (cardData.card_faces && cardData.card_faces.length > 1) {
          card.frontFace = {
            name: cardData.card_faces[0].name,
            oracleText: cardData.card_faces[0].oracle_text,
            imageUri: cardData.card_faces[0].image_uris.normal,
          };
          card.backFace = {
            name: cardData.card_faces[1].name,
            oracleText: cardData.card_faces[1].oracle_text,
            imageUri: cardData.card_faces[1].image_uris.normal,
          };
        } else {
          card.imageUri = cardData.image_uris.normal;
        }
    
        return card;
      });
    },

    SET_SEARCH_QUERY(state, newQuery){
      state.searchQuery = newQuery;
    },

    CLEAR_MAGIC_CARDS(state) {
      state.magicCards = [];
    },
  },
  actions: {
    updateSearchQuery({ commit }, newQuery){
      commit("SET_SEARCH_QUERY", newQuery)
    }

  }
})

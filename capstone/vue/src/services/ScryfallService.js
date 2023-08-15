import axios from 'axios';

const http = axios.create({
  baseURL: "https://api.scryfall.com/cards/"
});

export default {

    getSingleCardByName(cardName){
        return http.get(`named?exact=${cardName}`);
    },

    getSingleCardById(id){
        return http.get(id);
    },

    getMultipleCardsBySearchName(searchName){
        return http.get(`search?order=released&q=${searchName}&unique=prints`)
    }
}

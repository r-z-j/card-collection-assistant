import axios from 'axios';

const http = axios.create({
  baseURL: "https://api.scryfall.com/cards/"
});

export default {

    getSingleCardByName(cardName){
        return http.get(`named?exact=${cardName}`);
    },

    getMultipleCardsBySearchName(searchName){
        return http.get(`search?q=name:${searchName}`)
    }

}

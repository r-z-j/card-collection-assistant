import axios from 'axios';

const http = axios.create({
    baseURL: "https://api.pokemontcg.io/v2/"
});

export default{

    getSingleCardById(cardId){
        return http.get(`cards/${cardId}`);
    },

    getMultipleCardsBySearchName(searchName){
        return http.get(`cards?q=name:${searchName}*&page=1&pageSize=10`);
    }

    
}

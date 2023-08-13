
import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:9000/collection/"
});

export default {

    // ***** Main Collections *****
    getMagicCollections() {
        return http.get('magic');
    },

    getPokemonCollections() {
        return http.get('pokemon');
    },

    getMyCollections() {
        return http.get('mine');
    },

    createCollection(collection) {
        return http.post('create', collection);
    },

    getCollectionById(collectionId) {
        return http.get(collectionId);
    },

    deleteCollectionById(collectionId) {
        return http.delete(collectionId);
    },

    //  ***** Favorite Collections *****
    getFavoriteCollections() {
        return http.get('favorite');
    },

    addCollectionToFavorites(collectionId) {
        return http.post(`favorite/${collectionId}`);
    },

    removeCollectionToFavorites(collectionId) {
        return http.delete(`favorite/${collectionId}`);
    },


    //  ***** Cards *****
    addCardToCollection(collectionId, card) {
        return http.post(`${collectionId}/add-card`, card);
    },

    removeCardFromCollection(collectionId, cardId) {
        return http.delete(`${collectionId}/card/${cardId}`);
    },





}
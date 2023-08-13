
import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:9000/collection/"
});

export default {

    getMagicCollections() {
        return http.get('magic');
    },

    getPokemonCollections() {
        return http.get('pokemon');
    },

    getMyCollections() {
        return http.get('mine');
    },

    getFavoriteCollections() {
        return http.get('favorite');
    },

    addCollectionToFavorites(collectionId) {
        return http.post(`favorite/${collectionId}`);
    },

    removeCollectionToFavorites(collectionId) {
        return http.delete(`favorite/${collectionId}`);
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

    addCardToCollection(collectionId, card) {
        return http.post(`${collectionId}/add-card`, card);
    },

    removeCardFromCollection(collectionId, cardId) {
        return http.delete(`${collectionId}/card/${cardId}`);
    },





}
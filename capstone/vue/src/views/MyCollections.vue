<template>
  <div class="collections">
    <h1>Collections</h1>
    <button @click="getCollections">get collections</button>
    
    <div v-if="collections.length>0">

        <div v-for="collection in collections" v-bind:key="collection.collectionId">
            <div v-for="card in collection.cardList" v-bind:key="card.cardId">
                <p>{{ card.cardName }} ${{ card.userPrice }}</p>
            </div>
        </div>

    </div>

  </div>
</template>

<script>
import collectionService from "../services/CollectionApiService";

export default {
  name: "collections",
  data() {
      return {
          collections: []
      }
  },
  methods: {
    async getCollections() {
      try {
        await collectionService.getMyCollections().then(res =>{
            console.log(res);
            this.$store.commit("SET_COLLECTIONS", res);
        })
      } catch (e) {
        console.error("ERROR fetching collections:", e);
      }
    },
  },
};
</script>

<template>
  <div class="collections">
    <h1>Collections</h1>
        <div v-for="collection in collections " v-bind:key="collection.collectionId">
          <h2>{{ collection.collectionName }}</h2>
            <div v-for="card in collection.cardList" v-bind:key="card.cardId">
                <p>{{ card.cardName }} ${{ card.userPrice }}</p>
            </div>
        </div>
  </div>
</template>

<script>
import collectionService from "../services/CollectionApiService"

export default {
  name: "collections",
  data() {
    return {
      collections: null,
    }
  },

  computed: {
    myCollections() {
      return this.$store.state.collections;
    }
  },

  async created() {
    const res = await this.getCollections();
    this.collections = res.data;
    // const res = await collectionService.getMyCollections();
    // console.log(res.data);
    // this.collections = res.data;
  },

  methods: {
    getCollections: async() => {
      return collectionService.getMyCollections();
    }
  },

};
</script>
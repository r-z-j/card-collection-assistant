<template>
  <div class="collections">
    <h1>Collections</h1>
    <button @click="getCollections">get collections</button>
  </div>
</template>

<script>
import collectionService from "../services/CollectionApiService";

export default {
  name: "collections",
  methods: {
    async getCollections() {
      try {
        const res = await collectionService.getMyCollections()
          console.log(res.data);
      } catch (e) {
        console.error("ERROR fetching collections:", e);
      }
    },
  },
};
</script>

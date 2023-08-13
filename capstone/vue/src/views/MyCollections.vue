<template>
  <div class="collections">
    <h1>Collections</h1>
    <!-- <button @click="getCollection">get collections</button> -->
    

        <div v-for="collection in myCollections" v-bind:key="collection.collectionId">
            <div v-for="card in collection.cardList" v-bind:key="card.cardId">
                <p>{{ card.cardName }} ${{ card.userPrice }}</p>
            </div>
        </div>


  </div>
</template>

<script>

export default {
  name: "collections",
  computed: {
    myCollections() {
      return this.$store.state.collections;
    }
  },
};
</script>

<template>
  <div >
      <p>{{ $route.params.id }}</p>


  </div>
</template>

<script>
import collectionService from "../services/CollectionApiService"
// import pokemonService from "../services/PokemonService"

export default {
  name: "add-card",
  data() {
    return {
      collections: null,
      card: null,
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
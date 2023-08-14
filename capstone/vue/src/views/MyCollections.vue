<template>
  <div class="collections">
    <center><h1 class="page-header">Collections</h1></center>
      
        <div class="card" v-for="collection in collections" v-bind:key="collection.collectionId" >
          {{ collection.collectionName }}
          <img src="../img/magicCardBack.png"/>
            <div class="collection-title"></div>
            
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
      favorited: null,
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
    getFavoritedCollections: async() => {
      return collectionService.getFavoriteCollections();
    }

};
</script>

<style scoped>
.collection-title{
  padding: 40px;
 color: seashell;
}
.collections {
  display: block;
  align-content: center;
  flex-grow: 1;
  background-image: url("../img/collectionsBackground.png");
  background-size: cover;
  width: 100vw;
  height:100vw;
  
}

.card{
background-color: rgb(17, 17, 16);
display: flex;




}

.page-header{
  padding: 50px;
}






</style>
<template>
  <div class="collections">
    <link
      rel="stylesheet"
      href="http://fonts.googleapis.com/css2?family=Simonetta&display=swap"
    />
    <center>
      <h1 class="page-header">My Collections</h1>
      <router-link v-bind:to="{ name: 'create-collection' }">
        <button >Create Collection</button>
      </router-link>
    </center>
    

    <section v-if="isLoaded">
      <div
        class="collection-container"
        v-for="collection in collections"
        v-bind:key="collection.collectionId"
      >
        <div class="tile-container">
          <div class="collection-title">{{ collection.collectionName }}</div>
          <div v-if="collection.gameTypeId === 2">
            <router-link
              v-bind:to="{
                name: 'ptcg-collection-cards',
                params: { id: collection.collectionId },
              }"
            >
              <img src="../img/pokemon-cardback.png" />
            </router-link>
          </div>
          <div v-else-if="collection.gameTypeId === 1">
            <router-link
              v-bind:to="{
                name: 'mtg-collection-cards',
                params: { id: collection.collectionId },
              }"
            >
              <img src="../img/magicCardBack.png" />
            </router-link>
          </div>
          <center><button @click="deleteCollectionById(collection.collectionId)" class="delete-button">Delete Collection</button></center>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import collectionService from "../services/CollectionApiService";

export default {
  name: "collections",
  data() {
    return {
      collections: null,
      userId: this.$store.state.user.id
    };
  },
 computed: {
    isLoaded() {
      if (this.collections){
        return true
      }
      return false;
    }
  },
  async created() {
    const res = await this.getCollections();
    this.collections = res.data;
    if (this.collections.length > 0  && this.collections[0].authorId != this.userId){
      this.reloadPage();
    }
  },
  
  methods: {

    async deleteCollectionById(id) {
      const confirmed = window.confirm("Are you sure you want to delete this collection?");
      console.log(id)
      if (confirmed) {
        try {
          await collectionService.deleteCollectionById(id.toString());
          this.$router.go()
        } catch (error) {
          console.error("Error deleting card:", error);
        }
      }
    },

    getCollections: async () => {
      return collectionService.getMyCollections();
    },
    reloadPage() {
        this.$router.go()
      }
    },
};
</script>

<style scoped>
.collection-title {
  padding: 40px;
  color: seashell;
  justify-content: center;
}
.collections {
  display: block;
  align-content: center;
  flex-grow: 1;
  background-image: url("../img/ElementalBackground.png");
  background-size: cover;
  background-position: 55%;
  width: 100vw;
  min-height: 100vw;
}

.tile-container {
  justify-content: center;
  text-align: center;
  background-color: rgba(48, 48, 94, 0.678);
  border-radius: 10px;
}

section {
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  justify-content: center;
}
.collection-container {
  display: inline-block;
  padding: 20px;
  justify-content: center;
}

img {
  border-radius: 15px;
  width: 270px;
  height: 378px;
}

.page-header {
  padding: 50px;
  color: rgb(241, 239, 234);
  font-family: "Simonetta", cursive;
  font-style: bold;
  font-size: 75px;
}

button {
 
  padding: 10px 20px;
  font-size: 16px;
  background-color: #3d1eafe5;
  color: white;
  border: 2px solid #260cbdd5;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-bottom: 50px;
}
button:hover {
  background-color: #98049d;
}

.delete-button{
  margin-top: 10px;
}
</style>
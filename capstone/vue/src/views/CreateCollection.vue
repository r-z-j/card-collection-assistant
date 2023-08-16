<template>
  <form v-on:submit.prevent class="transparent-form">
    <div class="field">
      <label for="title">Title</label>
      <input type="text" name="title" v-model="collection.collectionName" />
    </div>
    <div class="field">
      <label for="gameType">Game</label>
      <select name="gameType" v-model="collection.gameTypeId">
        <option value="1">Magic</option>
        <option value="2">Pokemon</option>
      </select>
    </div>
    <div class="actions">
      <button type="submit" v-on:click="submitCollection(collection)"
     class="btn btn-primary transparent-button" >
        Create Collection
      </button>
    </div>
  </form>
</template>

<script>
import collectionService from "../services/CollectionApiService";

export default {
  name: "create-collection",
  data() {
    return {
      collection: {
        collectionName: "",
        gameTypeId: 0,
        authorId: this.$store.state.user.id,
      },
    };
  },

  methods: {
    submitCollection(collection) {
        collectionService.createCollection(collection)
        this.$router.push("/collections");
    },
  },
};
</script>
<style scoped>
.transparent-form {
  background-color: rgba(255, 255, 255, 0.1); 
  padding: 20px;
  border-radius: 5px;
  backdrop-filter: blur(10px); 
  border: 1px solid rgba(255, 255, 255, 0.2);
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
  transition: background-color 0.3s ease;
}

.transparent-form:hover {
  background-color: rgba(255, 255, 255, 0.5); 
}

form {
    width: 300px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #f9f9f9;
}

.field {
    margin-bottom: 10px;
}

label {
    display: block;
    font-weight: bold;
    margin-bottom: 5px;
}

input,
select {
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 3px;
}

.transparent-button {
  background-color: rgba(0, 123, 255, 0.3); 
  color: #fff;
  padding: 10px 20px;
  border: none;
  border-radius: 3px;
  cursor: pointer;
  transition: background-color 0.3s ease, color 0.3s ease;
}

.transparent-button:hover {
  background-color: rgba(0, 123, 255, 0.5); 
}
</style>
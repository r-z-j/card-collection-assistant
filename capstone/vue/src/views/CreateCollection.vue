
<template>
  <form v-on:submit.prevent>
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
      <button type="submit" v-on:click="submitCollection(collection)">
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
    submitCollection: (collection) => {
        collectionService.createCollection(collection)
    },
  },
};
</script>
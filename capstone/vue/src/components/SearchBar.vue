<template>
  <div class="search-bar">
    <input
      type="text"
      v-model="searchQuery"
      @input="handleInputChange"
      placeholder="Search for cards..."
    />
    <button @click="performSearch()">Search</button>
    
  </div>
</template>

<script>
export default {
  data() {
    return {
      searchQuery: "",
    };
  },
  methods: {
    handleInputChange() {},
    performSearch() {
      this.$store.commit("SET_SEARCH_QUERY", this.searchQuery)
    },
  },
};
</script>

<style scoped>
.search-bar {
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #f5f5f5;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 5px;
  margin: 0 auto;
  max-width: 75%;
}

.search-bar input {
  flex-grow: 1;
  padding: 12px;
  font-size: 16px;
  border: none;
  border-radius: 5px;
}

button {
  padding: 10px 20px;
  font-size: 16px;
  background-color: #6200ff;
  color: white;
  border: 2px solid #270cbd;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}
button:hover {
  background-color: #3e049d;
}
</style>
<template>
  <div class="search-bar">
    <input
      type="text"
      v-model="searchQuery"
      @input="handleInputChange"
      @keypress.enter="performSearch"
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
  background-color: #f5f5f5b7;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 5px;
  margin: 15px auto;
  max-width: 50vw
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
  background-color: #3d1eafe5;
  color: white;
  border: 2px solid #260cbdd5;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}
button:hover {
  background-color: #3e049d;
}
</style>
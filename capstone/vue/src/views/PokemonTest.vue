<template>
  <main>
    <section>
      <search-bar></search-bar>
    </section>
    <div class="centered-container">
      <div class="cards">
        <Pokemon></Pokemon>
      </div>
    </div>
  </main>
</template>

<script>
import Pokemon from "../components/Pokemon.vue";
import SearchBar from "../components/SearchBar.vue";

export default {
  components: {
    Pokemon,
    SearchBar
  },
};
</script>

<style scoped>
.centered-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  flex-grow: 1;
}

.cards {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

main{
  display: flex;
  flex-direction: column;
  justify-content: center;
  flex-grow: 1;
  background-color: rgba(253, 6, 6, 0.925);
}

</style>
<template>
  <main>
    <section>
      <SearchBar></SearchBar>
    </section>
    <div class="centered-container">
      <div class="cards">
        <MagicCard></MagicCard>
      </div>
    </div>
  </main>
</template>

<script>
import MagicCard from "../components/MagicCard.vue";
import SearchBar from "../components/SearchBar.vue";

export default {
  components: {
    MagicCard,
    SearchBar,
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
  background-color: rgb(20, 0, 36);
}
</style>

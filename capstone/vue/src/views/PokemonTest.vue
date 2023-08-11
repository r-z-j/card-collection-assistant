<template>
  <main class="centered-container">
    <div class="cards">
      <Pokemon></Pokemon>
    </div>
  </main>
</template>

<script>
import Pokemon from '../components/Pokemon.vue';

export default {
  components: {
    Pokemon
  }
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

</style>
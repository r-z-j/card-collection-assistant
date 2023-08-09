<template>
  <main class="centered-container">
    <div class="cards">
      <MagicCard cardName="Swiftfoot Boots"></MagicCard>
      <MagicCard cardName="Lightning Greaves"></MagicCard>
      <MagicCard cardName="Winged Boots"></MagicCard>
      <MagicCard cardName="Boots of Speed"></MagicCard>
      <MagicCard cardName="Zephyr Boots"></MagicCard>
      <MagicCard cardName="Trailblazer's Boots"></MagicCard>
      <MagicCard cardName="Boot Nipper"></MagicCard>
    </div>
  </main>
</template>

<script>
import MagicCard from '../components/MagicCard.vue';

export default {
  components: {
    MagicCard
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

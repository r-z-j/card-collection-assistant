<template>
  <main>
    <div></div>
    <PTCGCollectionCard>
    
    </PTCGCollectionCard>
  </main>
</template>

<script>
import PTCGCollectionCard from '../components/PTCGCollectionCard.vue';

export default {
  components: { PTCGCollectionCard },

}
</script>

<style scoped>
main {
  background-image: url('../img/pokeball.png');
  background-size:cover;
  background-attachment: fixed;
  height: 100vh;
  width: 100vw;
  }
</style>
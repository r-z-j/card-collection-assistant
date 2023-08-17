<template>
  <main> 
    <MTGCollectionCard>
    </MTGCollectionCard>

  </main>
</template>

<script>
import MTGCollectionCard from '../components/MTGCollectionCard.vue';

export default {
  components: { MTGCollectionCard },

}
</script>

<style scoped>
main {
  background-image: url('../img/MTG-background-image.png');
  background-size:cover;
  background-attachment: fixed;
  width: 100vw;
  min-height: 100vh;
  }
</style>
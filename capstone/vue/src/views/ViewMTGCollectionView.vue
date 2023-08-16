<template>
  <main>
    <div></div>
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
  height: 100%;
  width: 100%;
  }
</style>
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

<style>
main {
  background-image: url('../img/MTG-background-image.png');
  background-size:cover;
  background-attachment: fixed;
  }
</style>
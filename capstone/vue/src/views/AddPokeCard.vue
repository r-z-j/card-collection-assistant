<template>
  <main>
  <div>
    <CardForm :game-type="2" />
  </div>
  </main>
</template>

<script>
import CardForm from '../components/CardForm.vue'

export default {
  components: {
    CardForm
  }
};

</script>

<style scoped>
main {
  background-image: url('../img/pokeball.png');
  background-size:cover;
  background-attachment: fixed;
  width: 100vw;
  height: 100vh;
  }
</style>
<template>
  <div>
    <CardForm :game-type="1" />
  </div>
</template>

<script>
import CardForm from '../components/CardForm.vue'


export default {
  components: {
    CardForm
  }
};
</script>

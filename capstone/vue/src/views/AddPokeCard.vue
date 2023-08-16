<template>
  <div>
    <h2>Add Pokemon Card</h2>
    <CardForm :game-type="2" />
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

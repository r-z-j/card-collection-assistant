<template>
  <div class="modal">
    <div class="modal-content transparent-form">
      <span class="close" @click="closeModal">&times;</span>
      <card-form :gameType="gameType" />
    </div>
  </div>
</template>

<script>
import CardForm from './CardForm.vue';

export default {
  components: { CardForm },
  name: 'card-form-modal',
  props: {
    gameType: Number,
  },
  methods: {
    closeModal() {
      this.$emit('close');
    },
  },
  created() {
    console.log('CardFormModal - gameType:', this.gameType);
  },
};
</script>

<style scoped>
/* Modal styles */
.modal {
  display: none;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.4);
}

.modal-content {
  background-color: #fff;
  margin: 15% auto;
  padding: 20px;
  border: 1px solid #888;
  width: 80%;
  border-radius: 5px;
}

.close {
  position: absolute;
  top: 0;
  right: 10px;
  font-size: 20px;
  font-weight: bold;
  cursor: pointer;
}
</style>
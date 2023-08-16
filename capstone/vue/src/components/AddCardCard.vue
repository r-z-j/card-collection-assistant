<template>
  <div class="add-card-card">
    <h1>
        ADD MORE CARDS
    </h1>
    <div>
        <img src="../img/plus_sign_icon_.png" class="centered-image"/>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.add-card-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  border: 2px solid #360164;
  border-radius: 10px;
  padding: 10px;
  margin: 10px;
  text-align: center;
  background-color: rgb(197, 134, 236, 0.65);
  position: relative;
  min-width: 295px;
  max-width: 295px;
  min-height: 402px;
  max-height: 402px;
  padding-right: 10px;
  perspective: 1000px;

}

.centered-image {
  height: 125px;
  width: 125px;
  margin-top: 65px;
}
</style>
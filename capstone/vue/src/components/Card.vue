<template>
  <div class="card card-contents">
    <h1> {{ name }}</h1>
    <img v-bind:src="picture" />
    <p> {{ description }}</p>
  </div>
</template>

<script>
export default {
    props: [
        'name',
        'picture',
        'description',
    ]
}
</script>

<style>
  
</style>
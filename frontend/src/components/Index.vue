<template>
  <div class="hello red">
    <h1>{{ msg }}</h1>
  </div>
</template>

<script>
export default {
  name: 'BBP TEST',
  props: {
    msg: String
  }
}
</script>

<style lang="scss">
.red {
  color: $bgColor;
}
</style>

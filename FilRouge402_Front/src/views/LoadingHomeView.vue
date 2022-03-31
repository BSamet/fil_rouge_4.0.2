<template>
  <div class="loadingHome">
    <img src="../assets/loadingHome.svg" />
  </div>
</template>

<script>
export default {
  name: "LoadingHomeView",
};
</script>

<style lang="scss" scoped>
.loadingHome {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100vw;
  height: 90vh;
}
</style>

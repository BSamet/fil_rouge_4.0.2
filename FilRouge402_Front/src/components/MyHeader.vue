<template>
  <header class="header">
    <div class="header__title">
      <h1>Shape that !</h1>
    </div>
    <nav class="header__nav">
      <RouterLink to="/">Home</RouterLink>
      <RouterLink to="/sandbox">Sandbox</RouterLink>
    </nav>
  </header>
</template>

<script>
export default {
  name: "header",
  computed: {
    isLoadingHome() {
      return this.$store.state.loadingHome;
    }
  }
}
</script>

<style lang="scss" scoped>
  .header {
    &__title {
      width: 100vw;
      height: 10vh;
      background-color: #FFD8BE;
      display: flex;
      justify-content: center;
      align-items: center;
      h1 {
        font-size: 2.5rem;
      }
    }

    &__nav {
      background-color: #FFEEDD;
      font-size: 1.5rem;
      width: 100vw;
      display: flex;
      justify-content: space-around;
      align-items: center;
      border: 1px solid #FFD8BE;
      a {
        text-decoration: none;
        color: #181818;
        &:hover {
          color: #9381FF;
        }
      }
    }
  }
</style>
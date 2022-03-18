<template>
  <div class="modal-backdrop">
    <div class="modal">
      <header class="modal-header">
        <slot name="header">
          This is the default title!
        </slot>
      </header>
      <section class="modal-body">
        <slot name="body">
          This is the default body!
        </slot>
      </section>

      <footer class="modal-footer">
        <p @click="close">Close</p>
      </footer>
    </div>
  </div>
</template>

<script>
export default {
  name: "ModalShape",
  methods: {
    close() {
      this.$emit('close');
    },
  }
};
</script>

<style lang="scss" scoped>
  .modal {
    height: 100%;
    display:flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
    &-header {
      text-align: center;
      font-size: 1.2rem;
      height: 5%;
    }
    &-body {
      height: 80%;
      width: 100%;
      font-size: 1.1rem;
      text-align: center;
    }
    &-footer {
      height: 5%;
      font-size: 1.2rem;
      text-align: center;
      p {
        padding: 0 25px 0 25px;
        cursor: pointer;
        box-shadow: 0 0 2px 1px #5b5b5b;
        border-radius: 15px;
        background-color: #FFEEDD;
      }
    }
  }
</style>
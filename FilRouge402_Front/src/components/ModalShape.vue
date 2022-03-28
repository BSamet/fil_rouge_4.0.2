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
        <slot name="footer">
          This is the default footer.
        </slot>
      </footer>
    </div>
  </div>
</template>

<script>
export default {
  name: "ModalShape",
};
</script>

<style lang="scss" scoped>
.modal {
  height: 100%;
  display: flex;
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
  }
}
</style>
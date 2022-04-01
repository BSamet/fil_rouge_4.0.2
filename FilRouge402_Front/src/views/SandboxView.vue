<template>
  <section class="allScene">
    <h1 class="allScene__title">Scene</h1>
    <card-scene></card-scene>
    <div class="allScene__button" @click="showModal">
      <img src="../assets/addShape.svg" />
    </div>

    <!--  Scene forms-->
    <Transition name="slide-fade">
      <div class="allScene--blur" v-if="isModalVisible"></div>
    </Transition>

    <div class="allScene__forms">
      <Transition name="slide-fade">
        <modal-shape v-if="isModalVisible" @close="closeModal">
          <template v-slot:header>
            <h3><u>Add new scene</u></h3>
          </template>

          <template v-slot:body>
            <add-scene></add-scene>
          </template>

          <template v-slot:footer>
            <div class="allScene__forms--footer">
              <p @click="closeModal">Close</p>
            </div>
          </template>
        </modal-shape>
      </Transition>
    </div>
    <!-- ----- -->

  </section>
</template>

<script>
import CardScene from "@/components/scene/CardScene.vue";
import ModalShape from "@/components/ModalShape.vue";
import AddScene from "@/components/scene/AddScene.vue";

export default {
  name: "SandboxView",
  components: { AddScene, CardScene, ModalShape },
  methods: {
    // Modal for update
    showModal() {
      this.$store.state.isModalVisible = true;
    },
    closeModal() {
      this.$store.state.isModalVisible = false;
    },
    //
  },
  computed: {
    isModalVisible() {
      return this.$store.state.isModalVisible;
    },
  },
};
</script>

<style lang="scss" scoped>
.allScene {
  &__title {
    text-align: center;
    font-size: 2.3rem;
    font-weight: bold;
  }

  &__button {
    cursor: pointer;
    position: fixed;
    bottom: 75px;
    right: 7%;

    img {
      height: 75px;
      background: #d5d6aa;
      border-radius: 50%;
    }
  }

  &__forms {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    display: flex;
    justify-content: center;
    align-items: center;
    transition: opacity 0.5s ease;

    > div {
      width: auto;
      height: auto;
      background-color: #d5d6aa;
      box-shadow: 20px 20px 40px 1px #5b5b5b;
      border-radius: 15px;
      padding: 30px 70px 30px 70px;
    }

    &--footer {
      padding-top: 10px;

      p {
        padding: 0 25px 0 25px;
        cursor: pointer;
        box-shadow: 0 0 2px 1px #5b5b5b;
        border-radius: 15px;
        background-color: #ffeedd;
      }
    }
  }

  &--blur {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: #f2f2f2;
    opacity: 0.5;
  }
}

//Forms transition
.slide-fade-enter-active,
.slide-fade-leave-active {
  transition: all 0.3s ease-out;
}

.slide-fade-enter-from,
.slide-fade-leave-to {
  transform: translateX(20px);
  opacity: 0;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>

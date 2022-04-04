<template>
  <transition name="fade" mode="out-in">
    <div v-if="loadingUpdate" class="sceneUpdate__forms">
      <form action="#">
        <div class="sceneUpdate__forms--container">
          <label class="sceneUpdate__forms--label" for="name">Name :</label>
          <input
            v-model="sceneName"
            type="text"
            id="name"
            placeholder="Enter a name"
            required
          />
        </div>
        <div class="sceneUpdate__forms--container">
          <p class="sceneUpdate__forms--submit" @click.prevent="sendUpdate()">
            Update
          </p>
        </div>
      </form>
    </div>
    <div v-else class="sceneUpdate__forms">
      <img src="../../assets/loader.svg" />
    </div>
  </transition>
</template>

<script>
export default {
  name: "UpdateScene",
  props: ["sceneIdUpdate"],
  components: {},
  data() {
    return {
      loadingUpdate: false,
      checked: true,

      sceneId: this.sceneIdUpdate,
      sceneName: "",
    };
  },
  methods: {
    sendUpdate: function () {
      this.loadingUpdate = false;
      const update3dScene = {
        id: this.sceneId,
        name: this.sceneName,
      };

      setTimeout(() => {
        this.$store.dispatch("setUpdateScene3d", update3dScene)
      }, 1000);
    },
  },
  mounted() {
    let myScene = this.$store.getters.getScene3dById(this.sceneIdUpdate);
    this.sceneName = myScene[0].name;
    setTimeout(() => {
      this.loadingUpdate = true;
    }, 1000);
  },
};
</script>

<style lang="scss" scoped>
.sceneUpdate {
  &__forms {
    &--container {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      margin: 8px 0 8px 0;

      input,
      select {
        text-align: center;
        font-size: 1rem;
        border-radius: 15px;
        width: 250px;
        height: 25px;
        border: none;
        transition: all 0.2s linear;

        &:focus,
        &:active {
          outline: none;
          border: 2px solid #d2c072;
        }

        &::-webkit-outer-spin-button,
        &::-webkit-inner-spin-button {
          -webkit-appearance: none;
          margin: 0;
        }
      }
    }

    &--checkbox {
      > div {
        padding: 10px;
        border: 1px solid #ffffff;
        border-radius: 15px 0 0 15px;
        box-shadow: inset 0px -2px 2px 2px #ffffff;
        width: 600px;
        height: 150px;
        overflow-y: scroll;
        overflow-x: hidden;
        display: grid;
        grid-template-columns: repeat(3, 1fr);
        grid-row-gap: 10px;
        align-items: start;
        justify-items: start;
        > div {
          margin: 0 10px 0 10px;
        }
        label {
          font-size: 1rem;
          padding-left: 10px;
        }
      }
    }

    &--label {
      align-self: center;
    }

    &--submit {
      margin-top: 15px;
      padding: 0 25px 0 25px;
      cursor: pointer;
      box-shadow: 0 0 2px 1px #5b5b5b;
      border-radius: 15px;
      background-color: #ffeedd;
    }
  }
}
</style>

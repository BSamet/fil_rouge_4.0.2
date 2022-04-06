<template>
  <transition name="fade" mode="out-in">
    <div v-if="loading" class="shapeForms__forms">
      <form action="#">
        <div class="shapeForms__forms--checkbox">
          <div>
            <div v-for="forms3d of getForms3dBySceneId" :key="forms3d.id">
              <input
                  type="checkbox"
                  :id="forms3d.id"
                  :value="forms3d.forms2D.id"
                  v-model="shapeId"
              />
              <label :for="forms3d.id">{{ forms3d.forms2D.name }}</label>
            </div>
          </div>
        </div>
        <div class="shapeForms__forms--container">
          <p class="shapeForms__forms--submit" @click.prevent="sendPost()">
            Create
          </p>
        </div>
      </form>
    </div>
    <div v-else class="shapeForms__forms">
      <img src="../../assets/loader.svg" />
    </div>
  </transition>
</template>

<script>
export default {
  name: "AddShapeInScene",
  props: ['sceneId'],
  data() {
    return {
      loading: false,
      shapeId: [],
    };
  },
  computed: {
    getForms3dBySceneId() {
      return this.$store.getters.getForms3dBySceneId(0)
    }
  },
  methods: {
    // Post shape in scene
    sendPost: function () {
      this.loading = false;
      const toPost = {
        sceneId: this.sceneId,
        forms3DId: this.shapeId,
      };
      setTimeout(() => {
        this.$store.dispatch("setPostShape3dInScene", toPost);
      }, 1000);
    },
    //
  },
  mounted() {
    this.loading = true;
  },
};
</script>

<style lang="scss" scoped>
.shapeForms {
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
      align-self: self-start;
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
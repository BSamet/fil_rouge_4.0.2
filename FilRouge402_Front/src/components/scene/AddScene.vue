<template>
  <transition name="fade" mode="out-in">
    <div v-if="loading" class="sceneForms__forms">
      <form action="#">
        <div class="sceneForms__forms--container">
          <label class="shapeForms__forms--label" for="name">Name :</label>
          <input
            v-model="name"
            type="text"
            id="name"
            placeholder="Enter a name"
            required
          />
        </div>
        <div class="sceneForms__forms--checkbox">
          <label class="sceneForms__forms--label" for="name"
            >Add shape in scene ?</label
          >
          <div>
            <div v-for="forms3d of forms3dbyscene" :key="forms3d.id">
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
        <div class="sceneForms__forms--container">
          <p class="sceneForms__forms--submit" @click.prevent="sendPost()">
            Create
          </p>
        </div>
      </form>
    </div>
    <div v-else class="sceneForms__forms">
      <img src="../../assets/loader.svg" />
    </div>
  </transition>
</template>

<script>
import axios from "axios";

export default {
  name: "AddScene",
  data() {
    return {
      loading: false,
      name: "",
      shapeId: [],
      shape: this.$store.forms3dbyscene,
    };
  },
  computed: {
    forms3dbyscene() {
      return this.$store.state.forms3dbyscene;
    },
  },
  methods: {
    sendPost: function () {
      this.loading = false;
      const toPost = {
        name: this.name,
        forms3DId: this.shapeId
      }
      axios
        .post(
          "http://localhost:9090/Forms3DComposite/0",
            toPost
        )
        .then((res) => {
          setTimeout(
            () =>
              (this.$store.state.updateComponent =
                !this.$store.state.updateComponent),
            1000
          );
          setTimeout(() => (this.$store.state.isModalVisible = false), 1000);
        });
    },
  },
  async mounted() {
    await this.$store
      .dispatch("getForms3dByScene", 0)
      .then(() => (this.loading = true));
  },
};
</script>

<style lang="scss" scoped>
.sceneForms {
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
        width: 400px;
        height: 150px;
        overflow-y: scroll;
        overflow-x: hidden;
        display: flex;
        justify-content: space-around;
        align-items: center;
        flex-wrap: wrap;
        >div {
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

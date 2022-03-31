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
        <div class="sceneUpdate__forms--checkbox">
          <label class="sceneUpdate__forms--label" for="name"
            >Update shape in scene ?</label
          >
          <div>
            <div
              v-for="forms3dInScene of scene3dbyid.myAll3dForms"
              :key="forms3dInScene.id"
            >
              <input
                type="checkbox"
                :id="forms3dInScene.id"
                :value="forms3dInScene.id"
                v-model="formsToUpdate"
              />
              <label :for="forms3dInScene.id">{{
                forms3dInScene.forms2D.name
              }}</label>
            </div>
            <div v-for="forms3dFree of forms3dbyscene" :key="forms3dFree.id">
              <input
                type="checkbox"
                :id="forms3dFree.id"
                :value="forms3dFree.id"
                v-model="formsToUpdate"
              />
              <label :for="forms3dFree.id">{{
                forms3dFree.forms2D.name
              }}</label>
            </div>
          </div>
        </div>
        <div class="sceneUpdate__forms--container">
          <p class="sceneUpdate__forms--submit" @click.prevent="sendPost()">
            Update
          </p>
        </div>
        {{ scene3dbyid }}
      </form>
    </div>
    <div v-else class="sceneUpdate__forms">
      <img src="../../assets/loader.svg" />
    </div>
  </transition>
</template>

<script>
import axios from "axios";

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
      formsToUpdate: [],
    };
  },
  methods: {
    // sendUpdate: function () {
    //   this.loadingUpdate = false;
    //   const update3dShape = {
    //     id: this.sceneId,
    //     name: this.sceneName,
    //   };
    //   axios.put("http://localhost:9090/Forms3D", update3dShape).then((res) => {
    //     setTimeout(
    //       () =>
    //         (this.$store.state.updateComponent =
    //           !this.$store.state.updateComponent),
    //       1000
    //     );
    //     setTimeout(
    //       () => (this.$store.state.isModalUpdateVisible = false),
    //       1000
    //     );
    //   });
    // },
  },
  computed: {
    scene3dbyid() {
      this.sceneName = this.$store.state.scene3dbyid.name;
      return this.$store.state.scene3dbyid;
    },
    forms3dbyscene() {
      return this.$store.state.forms3dbyscene;
    },
  },
  async mounted() {
    await this.$store.dispatch("getForms3dByScene", 0);
    await this.$store.dispatch("getScene3dById", this.sceneId).then(() => {
      console.log("ligne 111")
      console.dir(this.$store.state.scene3dbyid)
      setTimeout(() => (this.loadingUpdate = true), 1000);
      this.$store.state.scene3dbyid.myAll3dForms.forEach((value, index) => {
        this.formsToUpdate.push(value.id);
      });
    });
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

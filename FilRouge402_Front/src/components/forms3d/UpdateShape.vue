<template>
  <transition name="fade" mode="out-in">
    <div v-if="loadingUpdate" class="shapeForms__forms">
      <form action="#">
        <div class="shapeForms__forms--container">
          <p class="shapeForms__forms--label">Type of shape</p>
          <input type="text" v-model="shapeType" disabled />
        </div>

        <div class="shapeForms__forms--container">
          <p class="shapeForms__forms--label">Select a scene</p>
          <select v-model="sceneId" required>
            <option value="">{{ sceneId }}</option>
            <option>0</option>
            <option v-for="scene of scene3d" :key="scene.id">
              {{ scene.id }}
            </option>
          </select>
        </div>

        <div class="shapeForms__forms--container">
          <label class="shapeForms__forms--label" for="name">Name :</label>
          <input v-model="name" type="text" id="name" required />
        </div>

        <div v-if="shapeType === 'Rectangle'">
          <div class="shapeForms__forms--container">
            <label class="shapeForms__forms--label" for="rLongueur"
              >Length :</label
            >
            <input v-model="longueur" type="number" id="rLongueur" required />
          </div>
          <div class="shapeForms__forms--container">
            <label class="shapeForms__forms--label" for="rLargeur"
              >Width :</label
            >
            <input v-model="largeur" type="number" id="rLargeur" required />
          </div>
          <div class="shapeForms__forms--container">
            <label class="shapeForms__forms--label" for="rDepths"
              >Depths :</label
            >
            <input v-model="depths" type="number" id="rDepths" required />
          </div>
        </div>

        <div v-if="shapeType === 'Circle'">
          <div class="shapeForms__forms--container">
            <label class="shapeForms__forms--label" for="cRayon">Rayon :</label>
            <input v-model="rayon" type="number" id="cRayon" required />
          </div>
          <div class="shapeForms__forms--container">
            <label class="shapeForms__forms--label" for="cDepths"
              >Depths :</label
            >
            <input v-model="depths" type="number" id="cDepths" required />
          </div>
        </div>

        <div v-if="shapeType === 'Triangle'">
          <div class="shapeForms__forms--container">
            <label class="shapeForms__forms--label" for="tHauteur"
              >Height :</label
            >
            <input v-model="hauteur" type="number" id="tHauteur" required />
          </div>
          <div class="shapeForms__forms--container">
            <label class="shapeForms__forms--label" for="tBase">Base :</label>
            <input v-model="base" type="number" id="tBase" required />
          </div>
          <div class="shapeForms__forms--container">
            <label class="shapeForms__forms--label" for="tDepths"
              >Depths :</label
            >
            <input v-model="depths" type="number" id="tDepths" required />
          </div>
        </div>

        <div class="shapeForms__forms--container">
          <p class="shapeForms__forms--submit" @click.prevent="sendUpdate()">
            Update
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
  name: "UpdateShape",
  props: ["shapeIdUpdate"],
  components: {},
  data() {
    return {
      loadingUpdate: false,

      // For Update
      shapeid: this.shapeIdUpdate,
      shapeType: "",
      name: "",
      longueur: "",
      hauteur: "",
      largeur: "",
      base: "",
      rayon: "",
      depths: "",
      sceneId: "",

      sceneIdBeforeChange: "",
    };
  },
  methods: {
    sendUpdate: function () {
      this.loadingUpdate = false;

      const update3dShape = {
        id: this.shapeid,
        type: this.shapeType,
        name: this.name,
        longueur: this.longueur,
        hauteur: this.hauteur,
        largeur: this.largeur,
        base: this.base,
        rayon: this.rayon,
        depths: this.depths,
        sceneId: this.sceneId,
      };

      const resultToSent = {
        data: update3dShape,
        sceneIdBeforeChange: this.sceneIdBeforeChange,
      };

      setTimeout(() => {
        this.$store.dispatch("setUpdateForms3d", resultToSent);
      }, 1000);
    },
  },

  computed: {
    // Get 3d Scene
    scene3d() {
      return this.$store.state.scene3d;
    },
  },
  mounted() {
    let myShape = this.$store.getters.getForms3dById(this.shapeIdUpdate);
    this.shapeType = myShape[0].forms2D.type;
    this.name = myShape[0].forms2D.name;
    this.longueur = myShape[0].forms2D.longueur;
    this.hauteur = myShape[0].forms2D.hauteur;
    this.largeur = myShape[0].forms2D.largeur;
    this.base = myShape[0].forms2D.base;
    this.rayon = myShape[0].forms2D.rayon;
    this.depths = myShape[0].depths;
    this.sceneId = myShape[0].sceneId;
    this.sceneIdBeforeChange = myShape[0].sceneId;

    setTimeout(() => {
      this.loadingUpdate = true;
    }, 1000);
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

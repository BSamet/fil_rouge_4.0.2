<template>
  <div class="scene">
    <div v-for="scene of scene3d" :key="scene.id" class="card">
      <div class="card__update">
        <img
          src="../../assets/update_shape.svg"
          @click="showModalUpdate(scene)"
        />
        <img
          src="../../assets/delete_shape.svg"
          @click="sendDelete(scene.id, scene)"
        />
      </div>
      <div class="card__picture">
        <div class="card__picture--scene">
          <img src="../../assets/backgroundshape.jpg" />
        </div>
      </div>
      <div class="card__info">
        <h1>{{ scene.name }}</h1>
        <div v-for="forms3d of scene.myAll3dForms" :key="forms3d.id">
          <p>{{ forms3d.forms2D.name }}</p>
        </div>
      </div>
      <div class="card__link">
        <RouterLink :to="'/scene/' + scene.id">View Scene</RouterLink>
      </div>
    </div>

    <Transition name="slide-fade">
      <div class="scene--blur" v-if="$store.state.isModalUpdateVisible"></div>
    </Transition>

    <div class="scene__update">
      <Transition name="slide-fade">
        <modal-shape
          v-if="$store.state.isModalUpdateVisible"
          @close="closeModalUpdate"
        >
          <template v-slot:header>
            <h3><u>Update scene</u></h3>
          </template>

          <template v-slot:body>
            <update-scene :scene-id-update="sceneUpdate.id"></update-scene>
          </template>

          <template v-slot:footer>
            <div class="scene__update--footer">
              <p @click="closeModalUpdate()">Close</p>
            </div>
          </template>
        </modal-shape>
      </Transition>
    </div>
  </div>
</template>

<script>
import ModalShape from "../ModalShape.vue";
import axios from "axios";
import UpdateScene from "@/components/scene/UpdateScene.vue";

export default {
  name: "CardScene",
  components: { UpdateScene, ModalShape },
  data() {
    return {
      sceneUpdate: [],
    };
  },
  methods: {
    showModalUpdate(data) {
      this.sceneUpdate = data;
      this.$store.state.isModalUpdateVisible = true;
    },
    closeModalUpdate() {
      this.$store.state.isModalUpdateVisible = false;
    },
    sendDelete: function (id, scene) {
      const forShapeId = { scene: scene };
      axios.delete("http://localhost:9090/Forms3DComposite/" + id).then(() => {
        //Delete data in store
        scene.myAll3dForms.forEach((value, index) => {
          this.$store.state.forms3d[
            this.$store.state.forms3d.findIndex(
              (forms3d) => forms3d.id === value.id
            )
          ].sceneId = 0;
        });
        this.$store.state.scene3d.splice(
          this.$store.state.scene3d.findIndex((scene) => scene.id === id),
          1
        );
        //
      });
    },
  },
  computed: {
    scene3d() {
      return this.$store.state.scene3d;
    },
  },
};
</script>

<style lang="scss" scoped>
@keyframes slideUpdateIn {
  from {
    z-index: -1;
  }
  50% {
    transform: translateX(85px) rotateY(45deg) rotateZ(20deg);
    box-shadow: -10px 10px 40px 1px #5b5b5b;
  }
  to {
    transform: translateX(40px) rotateY(0) rotateX(0);
    box-shadow: 10px 10px 40px 1px #5b5b5b;
    z-index: 0;
  }
}

@keyframes slideUpdateOut {
  from {
    z-index: 0;
    box-shadow: 10px 10px 40px 1px #5b5b5b;
  }
  50% {
    transform: translateX(85px) rotateY(45deg) rotateZ(20deg);
  }
  to {
    transform: translateX(40px) rotateY(0) rotateX(0);
    box-shadow: none;
    z-index: -1;
  }
}

.scene {
  margin: 30px 50px 0 50px;
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  grid-row-gap: 35px;
  align-items: start;
  justify-items: center;

  .card {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
    background-color: #ffeedd;
    box-shadow: 10px 10px 40px 1px #5b5b5b;
    border-radius: 15px;
    width: 250px;
    height: 350px;
    font-size: 1.1rem;
    transition: all 0.1s ease-in-out;

    &:hover {
      box-shadow: none;
    }

    &:hover .card__update {
      animation: slideUpdateIn 0.5s;
      animation-fill-mode: forwards;
    }

    &__info {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;

      h1 {
        font-weight: 600;
      }
    }

    &__picture {
      img {
        width: 150px;
      }
    }

    &__link {
      border-radius: 15px;
      background-color: #d5d6aa;
      padding: 5px 10px 5px 10px;

      a {
        text-decoration: none;
        color: #181818;

        &:hover {
          color: #9381ff;
        }
      }
    }

    &__update {
      display: flex;
      flex-direction: column;
      justify-content: space-around;
      align-items: center;
      width: 50px;
      height: 20%;
      position: absolute;
      top: 0;
      right: 20px;
      background-color: #d5d6aa;
      border-radius: 15px;
      animation: slideUpdateOut 0.5s;
      animation-fill-mode: forwards;

      img {
        cursor: pointer;
      }
    }
  }

  &__update {
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
    opacity: 0.2;
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
}
</style>

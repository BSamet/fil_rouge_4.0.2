<template>
  <div v-if="loading" class="shape3d">
    <div v-for="forms of forms3d" :key="forms.id" class="card">
      <div class="card__update">
        <img src="../assets/update_shape.svg" @click="showModalUpdate(forms)" />
        <img src="../assets/delete_shape.svg" @click="sendDelete(forms.id)" />
      </div>
      <div class="card__picture">
        <div
          v-if="forms.forms2D.type === 'Rectangle'"
          class="card__picture--rectangle"
        ></div>
        <div
          v-if="forms.forms2D.type === 'Triangle'"
          class="card__picture--triangle"
        ></div>
        <div
          v-if="forms.forms2D.type === 'Circle'"
          class="card__picture--circle"
        ></div>
      </div>
      <div class="card__info">
        <h1>{{ forms.forms2D.name }}</h1>
        <p v-if="forms.forms2D.longueur">
          Longueur : {{ forms.forms2D.longueur }} cm
        </p>
        <p v-if="forms.forms2D.largeur">
          Largeur : {{ forms.forms2D.largeur }} cm
        </p>
        <p v-if="forms.forms2D.base">Base : {{ forms.forms2D.base }} cm</p>
        <p v-if="forms.forms2D.rayon">Rayon : {{ forms.forms2D.rayon }} cm</p>
        <p v-if="forms.depths">Profondeur : {{ forms.depths }} cm</p>
      </div>
      <div class="card__link">
        <RouterLink :to="'/shape3d/' + forms.id">View 3D</RouterLink>
      </div>
    </div>

    <Transition name="slide-fade">
      <div class="shape3d--blur" v-if="$store.state.isModalUpdateVisible"></div>
    </Transition>

    <div class="shape3d__forms">
      <Transition name="slide-fade">
        <modal-shape
          v-if="$store.state.isModalUpdateVisible"
          @close="closeModalUpdate"
        >
          <template v-slot:header>
            <h3><u>Update shape</u></h3>
          </template>

          <template v-slot:body>
            <UpdateShape :shape-id-update="formsUpdate.id"></UpdateShape>
          </template>

          <template v-slot:footer>
            <div class="shape3d__forms--footer">
              <p @click="closeModalUpdate()">Close</p>
            </div>
          </template>
        </modal-shape>
      </Transition>
    </div>
  </div>
  <div v-else>
    <img src="../assets/loader.svg" />
  </div>
</template>

<script>
import UpdateShape from "@/components/UpdateShape.vue";
import ModalShape from "@/components/ModalShape.vue";
import axios from "axios";

export default {
  name: "Card3d",
  components: { ModalShape, UpdateShape },
  data() {
    return {
      loading: false,
      formsUpdate: [],
    };
  },
  methods: {
    showModalUpdate(data) {
      this.formsUpdate = data;
      this.$store.state.isModalUpdateVisible = true;
    },
    closeModalUpdate() {
      this.$store.state.isModalUpdateVisible = false;
    },
    sendDelete: function (id) {
      axios.delete("http://localhost:9090/Forms3D/" + id).then((res) => {
        this.$store.state.updateComponent = !this.$store.state.updateComponent;
      });
    },
  },
  computed: {
    forms3d() {
      return this.$store.state.forms3d;
    },
    scene3d() {
      return this.$store.state.scene3d;
    },
  },
  async mounted() {
    await this.$store.dispatch("getForms3d");
    await this.$store.dispatch("getScene3d").then(() => (this.loading = true));
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

.shape3d {
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

      &--rectangle {
        width: 125px;
        height: 125px;
        background: #d5d6aa;
      }

      &--triangle {
        display: inline-block;
        height: 0;
        width: 0;
        border-right: 87px solid transparent;
        border-bottom: 125px solid #d5d6aa;
        border-left: 87px solid transparent;
      }

      &--circle {
        width: 125px;
        height: 125px;
        background: #d5d6aa;
        -webkit-border-radius: 100px;
        -moz-border-radius: 100px;
        -o-border-radius: 100px;
        border-radius: 100px;
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

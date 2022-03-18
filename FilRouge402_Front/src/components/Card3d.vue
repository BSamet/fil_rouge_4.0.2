<template>
  <div v-if="loading" class="shape3d">
    <div
        v-for="forms of forms3d"
        :key="forms.id"
        class="card">
      <div class="card__update">
        <p>update</p>
        <p>delete</p>
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
  </div>
</template>

<script>
export default {
  name: "Card3d",
  data() {
    return {
      loading: false
    };
  },
  computed: {
    forms3d() {
      return this.$store.state.forms3d
    }
  },
  async mounted() {
    await this.$store.dispatch("getForms3d")
        .then(() => (this.loading = true));
  }
};
</script>

<style lang="scss" scoped>
@keyframes slideUpdateIn {
  from {
    z-index: -1;
  }
  50% {
    transform: translateX(85px) rotateY(45deg);
    box-shadow: -10px 10px 40px 1px #5b5b5b;
  }
  to {
    transform: translateX(40px) rotateY(0);
    box-shadow: none;
    z-index: 0;
  }
}
@keyframes slideUpdateOut {
  from {
    z-index: 0;
  }
  50% {
    transform: translateX(85px) rotateY(45deg);
    box-shadow: 10px 10px 40px 1px #5b5b5b;
  }
  to {
    transform: translateX(40px) rotateY(0);
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
      width: 100px;
      height: 100%;
      position: absolute;
      top: 0px;
      right: -20px;
      background-color: #d5d6aa;
      border-radius: 15px;
      animation: slideUpdateOut 0.5s;
      animation-fill-mode: forwards;
    }
  }
}
</style>

<template>
  <div class="shape3d">
    <div
      class="card"
      v-if="loading"
      v-for="forms of myForms"
      v-bind="myForms.id"
    >
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
        <p v-if="forms.forms2D.depths">Profondeur : {{ forms.depths }} cm</p>
      </div>
      <div class="card__link">
        <RouterLink :to="'/shape3d/' + forms.id">View 3D</RouterLink>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Card3d",
  data() {
    return {
      myForms: "",
      loading: false,
    };
  },
  methods: {
    getForms: function () {
      axios.get("http://localhost:9090/Forms3D").then((forms3d) => {
        this.myForms = forms3d.data;
        this.loading = true;
      });
    },
  },
  mounted() {
    this.getForms();
  },
};
</script>

<style lang="scss" scoped>
.shape3d {
  display: grid;
  grid-template-columns: repeat(6, 1fr);
  grid-gap: 10px;
  grid-auto-rows: 100px;
  align-items: start;
  justify-items: center;

  .card {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
    background-color: #ffeedd;
    box-shadow: 20px 20px 40px 1px #5b5b5b;
    border-radius: 15px;
    margin: 10px;
    width: 300px;
    height: 400px;
    font-size: 1.3rem;
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
  }
}
</style>

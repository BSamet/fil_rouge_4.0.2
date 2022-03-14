<template>
  <div class="shape3d">
    <div class="card" v-for="forms of myForms" v-bind="myForms.id">
      <div class="card__info">
        <h1>{{ forms.forms2D.name }}</h1>
        <p v-if="forms.forms2D.longueur">
          Longueur : {{ forms.forms2D.longueur }}
        </p>
        <p v-if="forms.forms2D.largeur">
          Largeur : {{ forms.forms2D.largeur }}
        </p>
        <p v-if="forms.forms2D.base">
          Base : {{ forms.forms2D.base }}
        </p>
        <p v-if="forms.forms2D.rayon">
          Rayon : {{ forms.forms2D.rayon }}
        </p>
        <p v-if="forms.forms2D.depths">
          Profondeur : {{ forms.depths }}
        </p>
      </div>
      <div class="card__picture">
        <div v-if="forms.forms2D.type === 'Rectangle'" class="card__picture--rectangle"></div>
        <div v-if="forms.forms2D.type === 'Triangle'" class="card__picture--triangle"></div>
        <div v-if="forms.forms2D.type === 'Circle'" class="card__picture--circle"></div>
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
    };
  },
  methods: {
    getForms: function () {
      axios.get("http://localhost:9090/Forms3D").then((forms3d) => {
        this.myForms = forms3d.data;
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
  grid-template-columns: repeat(4, 1fr);
  grid-gap: 10px;
  grid-auto-rows: 100px;
  align-items: start;
  justify-items: center;
  .card {
    display: flex;
    justify-content: space-around;
    align-items: center;
    background-color: #ffeedd;
    box-shadow: 20px 20px 40px 1px #5b5b5b;
    border-radius: 15px;
    margin: 10px;
    width: 400px;
    height: 200px;

    &__info {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
    }

    &__picture {
      img {
        width: 150px;
      }
      &--rectangle {
        width:125px;
        height:125px;
        background: #d5d6aa;
      }
      &--triangle {
        display : inline-block;
        height : 0;
        width : 0;
        border-right : 87px solid transparent;
        border-bottom : 125px solid #d5d6aa;
        border-left : 87px solid transparent;
      }
      &--circle {
        width:125px;
        height:125px;
        background:#d5d6aa;
        -webkit-border-radius:100px;
        -moz-border-radius:100px;
        -o-border-radius:100px;
        border-radius:100px;
      }
    }
  }
}
</style>

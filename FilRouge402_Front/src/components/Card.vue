<template>
  <div class="card">
    <div class="card__info">
      <h1>Rectangle</h1>
      <p>Longueur 10</p>
      <p>Largeur 10</p>
      <p>Profondeur 10</p>
    </div>
    <div class="card__picture">
      <img src="/src/assets/rectangle.png">
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Card",
  data(){
    return {
      forms: null
    }
  },
  methods:{
    getForms() {
      axios.get('http://localhost:9090/Forms3D')
          .then((e) => {
            this.getForms =e.data;
          })
    },
    mounted() {
      this.getForms();
    }
  },
}
</script>

<style lang="scss" scoped>
  .card {
    display: flex;
    justify-content: space-around;
    align-items: center;
    background-color: #FFEEDD;
    border-radius: 15px;
    margin: 10px;
    width: 400px;
    height: 250px;
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
    }
  }
</style>
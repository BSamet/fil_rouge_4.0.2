<template>
  <div class="shapeForms__forms">
    <form action="#">
      <div class="shapeForms__forms--container">
        <p class="shapeForms__forms--label">Type of shape </p>
        <select v-model="shapeType" required>
        <option disabled value="">Select type of shape</option>
        <option>Rectangle</option>
        <option>Triangle</option>
        <option>Circle</option>
        </select>
      </div>
      <div class="shapeForms__forms--container">
        <label class="shapeForms__forms--label" for="name">Name :</label>
        <input v-model="name" type="text" id="name" placeholder="Enter a name" required>
      </div>
      <div v-if="shapeType === 'Rectangle'">
        <div class="shapeForms__forms--container">
          <label class="shapeForms__forms--label" for="rLongueur">Longueur :</label>
          <input v-model="longueur" type="number" id="rLongueur" placeholder="Enter a lenght" required>
        </div>
        <div class="shapeForms__forms--container">
          <label class="shapeForms__forms--label" for="rLargeur">Largeur :</label>
          <input v-model="largeur" type="number" id="rLargeur" placeholder="Enter a width" required>
        </div>
        <div class="shapeForms__forms--container">
          <label class="shapeForms__forms--label" for="rDepths">Depths :</label>
          <input v-model="depths" type="number" id="rDepths" placeholder="Enter a deepth" required>
        </div>
      </div>
      <div v-if="shapeType === 'Circle'">
        <div class="shapeForms__forms--container">
          <label class="shapeForms__forms--label" for="cRayon">Rayon :</label>
          <input v-model="rayon" type="number" id="cRayon" placeholder="Enter a rayon" required>
        </div>
        <div class="shapeForms__forms--container">
          <label class="shapeForms__forms--label" for="cDepths">Depths :</label>
          <input v-model="depths" type="number" id="cDepths" placeholder="Enter a deepth" required>
        </div>
      </div>
      <div v-if="shapeType === 'Triangle'">
        <div class="shapeForms__forms--container">
          <label class="shapeForms__forms--label" for="tLongueur">Longueur :</label>
          <input v-model="longueur" type="number" id="tLongueur" placeholder="Enter a length" required>
        </div>
        <div class="shapeForms__forms--container">
          <label class="shapeForms__forms--label" for="tBase">Base :</label>
          <input v-model="base" type="number" id="tBase" placeholder="Enter a base" required>
        </div>
        <div class="shapeForms__forms--container">
          <label class="shapeForms__forms--label" for="tDepths">Depths :</label>
          <input v-model="depths" type="number" id="tDepths" placeholder="Enter a depth" required>
        </div>
      </div>
      <div class="shapeForms__forms--container">
        <p class="shapeForms__forms--submit" @click.prevent="sendPost()">Submit</p>
      </div>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "AddShapeView",
  data() {
    return {
      shapeType: "",
      name: "",
      longueur: "",
      largeur: "",
      base: "",
      rayon: "",
      depths: "",
      forms2dId: "",
    }
  },
  methods: {
    sendPost: function() {
      const post2dShape = { type: this.shapeType, name: this.name, longueur: this.longueur, largeur: this.largeur, base: this.base, rayon: this.rayon };
      axios
      .post("http://localhost:9090/Forms2D", post2dShape)
        .then(res => {
          this.forms2dId = res.data.id;
          const post3dShape = { depths: this.depths, forms2dId: this.forms2dId};
          axios
            .post("http://localhost:9090/Forms3D", post3dShape)
          this.$store.state.updateComponent++
          this.$store.state.isModalVisible = false
        });
    }
  }
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
        input, select {
          text-align: center;
          font-size: 1rem;
          border-radius: 15px;
          width: 250px;
          height: 25px;
          border: none;
          transition: all 0.2s linear;
          &:focus, &:active {
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
        background-color: #FFEEDD;
      }
    }
  }
</style>
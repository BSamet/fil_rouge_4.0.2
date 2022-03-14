<template>
  <section>
    <div class="shapeForms__forms">
      <h1>Add a new Shape</h1>
    </div>
    <div class="shapeForms__forms">
      <form action="#" class="login-form">
        <div>
          <p>Type of shape </p>
            <select v-model="shapeType">
            <option disabled value="">Select</option>
            <option>Rectangle</option>
            <option>Triangle</option>
            <option>Circle</option>
          </select>
        </div>
        <div>
          <label for="name">Name :</label>
          <input v-model="name" type="text" id="name">
        </div>
        <div v-if="shapeType === 'Rectangle'">
          <div>
            <label for="rLongueur">Longueur :</label>
            <input v-model="longueur" type="number" id="rLongueur">
          </div>          <div>
            <label for="rLargeur">Largeur :</label>
            <input v-model="largeur" type="number" id="rLargeur">
          </div>
          <div>
            <label for="rDepths">Depths :</label>
            <input v-model="depths" type="number" id="rDepths">
          </div>
        </div>
        <div v-if="shapeType === 'Circle'">
          <div>
            <label for="cRayon">Rayon :</label>
            <input v-model="rayon" type="number" id="cRayon">
          </div>
          <div>
            <label for="cDepths">Depths :</label>
            <input v-model="depths" type="number" id="cDepths">
          </div>
        </div>
        <div v-if="shapeType === 'Triangle'">
          <div>
            <label for="tLongueur">Longueur :</label>
            <input v-model="longueur" type="number" id="tLongueur">
          </div>
          <div>
            <label for="tBase">Base :</label>
            <input v-model="base" type="number" id="tBase">
          </div>
          <div>
            <label for="tDepths">Depths :</label>
            <input v-model="depths" type="number" id="tDepths">
          </div>
        </div>
        <button @click.prevent="sendPost()">Submit</button>
      </form>
    </div>
  </section>
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
            .post("http://localhost:9090/Forms3D", post3dShape);
        });
    }
  }
};
</script>

<style scoped>

</style>
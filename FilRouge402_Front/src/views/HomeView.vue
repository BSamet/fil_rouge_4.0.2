<template>
  <section class="allShape">
    <h1 class="allShape__title">Shape</h1>
    <card3d></card3d>
  </section>
</template>

<script>
import axios from 'axios';
import Card3d from "@/components/Card3d.vue";

export default {
  components: {Card3d},
  data() {
    return {
      myForms: "",
    };
  },
  methods: {
    getForms: function() {
      axios.get('http://localhost:9090/Forms3D').then((forms3d) => {
        this.myForms = forms3d.data;
      })
    }
  },
  mounted() {
    this.getForms();
  }
};
</script>

<style lang="scss">
.allShape {
  &__title {
    text-align: center;
    font-size: 2.5rem;
    font-weight: bold;
  }
}
</style>

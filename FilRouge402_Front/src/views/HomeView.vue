<template>
  {{ forms }}
</template>

<script lang="ts">
import Card from "@/components/Card.vue";
import axios from "axios";
import Store from '@/store

export default {
  data() {
    return {
      myForms: null,
    };
  },
  computed: {
    forms3d() {
      return this.$store.state.forms3d
    }
  },
  mounted() {
    this.$store.dispatch("getForms3d");
  }
};
</script>

<style lang="scss">
section {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-gap: 10px;
  grid-auto-rows: 100px;
  align-items: start;
  justify-items: center;
}
</style>

<script setup lang="ts">
import { RouterView } from "vue-router";
import MyHeader from "@/components/MyHeader.vue";
</script>

<template>
  <MyHeader></MyHeader>
  <RouterView />
</template>

<style>
@import "@/assets/base.css";
</style>

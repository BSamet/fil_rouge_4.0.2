<template>
  {{this.$store.getters.getScene3dById(this.sceneId)}}
  <section
    v-for="scene3d in getScene3dById"
    :key="scene3d.id"
    v-if="loading"
    class="scene3d"
  >
    <div class="scene3d__name">
      <h1>{{ scene3d.name }}</h1>
    </div>
    <div class="scene3d__scene">
      <Renderer
        ref="renderer"
        alpha="0"
        resize="true"
        antialias
        :orbit-ctrl="{
          autoRotate: true,
          enableDamping: true,
          dampingFactor: 0.05,
        }"
      >
        <Camera :position="{ x: 0, y: 0, z: 100 }" />
        <Scene v-for="shape3d in scene3d.myAll3dForms" :key="shape3d.id">
          <Cone
            ref="mesh"
            :size="1"
            :rotation="{ y: Math.PI / 4, z: Math.PI / 4 }"
            :radius="shape3d.depths"
            :height="
              Math.sqrt(
                shape3d.forms2D.longueur * shape3d.forms2D.longueur -
                  (shape3d.forms2D.base * shape3d.forms2D.base) / 4
              )
            "
            :radialSegments="100"
            :heightSegments="100"
            :position="{ y: 50, z: 0 }"
          >
            <MatcapMaterial name="9F886D_362F25_E8C9A4_5C4C3C" />
          </Cone>

          <Cylinder
            ref="mesh"
            :size="1"
            :rotation="{ y: Math.PI / 4, z: Math.PI / 4 }"
            :height="shape3d.depths"
            :radiusTop="shape3d.forms2D.rayon"
            :radiusBottom="shape3d.forms2D.rayon"
            :radialSegments="100"
            :heightSegments="100"
            :position="{ y: 10, z: 0 }"
          >
            <MatcapMaterial name="9F886D_362F25_E8C9A4_5C4C3C" />
          </Cylinder>

          <Box
            ref="mesh"
            :size="1"
            :rotation="{ y: Math.PI / 4, z: Math.PI / 4 }"
            :width="shape3d.forms2D.largeur"
            :height="shape3d.forms2D.longueur"
            :depth="shape3d.depths"
            :radialSegments="100"
            :heightSegments="100"
            :position="{ y: 10, z: 50 }"
          >
            <MatcapMaterial name="9F886D_362F25_E8C9A4_5C4C3C" />
          </Box>
        </Scene>
      </Renderer>
    </div>
  </section>
</template>

<script>
import { Box, Camera, MatcapMaterial, Renderer, Scene } from "troisjs";

export default {
  name: "Scene3dView",
  components: { Box, Camera, MatcapMaterial, Renderer, Scene },
  data() {
    return {
      sceneId: Number(this.$route.params.id),
      loading: false,
    };
  },
  computed: {
    getScene3dById() {
      return this.$store.getters.getScene3dById(this.sceneId);
    },
  },
  mounted() {
    setTimeout(() => {
      this.loading = true;
    }, 1000);
  },
};
</script>

<style lang="scss" scoped>
.scene3d {
  background-image: url("../assets/backgroundshape.jpg");
  background-size: cover;
  background-position: center center;
  backdrop-filter: blur(15px);
  height: 750px;
  &__name {
    text-align: center;
    font-size: 1.5rem;
    font-weight: bold;
    color: white;
  }
  &__scene {
    height: 690px;
  }
  &__info {
    font-size: 1.2rem;
    display: flex;
    justify-content: center;
    p {
      padding: 0 25px 0 25px;
    }
  }
}
</style>

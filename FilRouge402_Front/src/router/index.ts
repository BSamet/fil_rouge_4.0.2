import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Shape3dView from '../views/Shape3dView.vue'
import Scene3dView from '../views/Scene3dView.vue'
import PageNotFound from '../views/PageNotFound.vue'
import SandboxView from '../views/SandboxView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/shape3d/:id',
      name: 'shape3d',
      component: Shape3dView
    },
    {
      path: '/scene3d/:id',
      name: 'scene3d',
      component: Scene3dView
    },
    {
      path: '/sandbox',
      name: 'sandbox',
      component: SandboxView
    },
    {
      path: '/:catchAll(.*)*',
      name: "PageNotFound",
      component: PageNotFound,
    },
  ]
})

export default router

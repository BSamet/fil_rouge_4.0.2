import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Shape3dView from '../views/Shape3dView.vue'
import AddShape3dView from '../views/AddShapeView.vue'
import PageNotFound from '../views/PageNotFound.vue'

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
      path: '/addshape',
      name: 'addshape3dView',
      component: AddShape3dView
    },
    {
      path: '/:catchAll(.*)*',
      name: "PageNotFound",
      component: PageNotFound,
    },
  ]
})

export default router

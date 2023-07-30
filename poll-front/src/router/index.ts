import { createRouter, createWebHistory } from 'vue-router'
import FormView from '@/views/FormView.vue';


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'form',
      component: FormView
    },
    {
      path: '/vote/:id',
      name: 'vote',
      component: () => import('../views/VoteView.vue')
    },
    {
      path: '/result/:id',
      name: 'result',
      component: () => import('../views/ResultView.vue')
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutView.vue')
    }
  ]
})

export default router

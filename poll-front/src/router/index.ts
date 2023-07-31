import { createRouter, createWebHistory } from 'vue-router'
import FormView from '@/views/FormView.vue';
import VoteView from "@/views/VoteView.vue";


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
      component: VoteView
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

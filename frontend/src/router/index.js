import { createRouter, createWebHistory } from 'vue-router'
import TodoListView from '../views/TodoListView.vue'
import TodoFormView from '../views/TodoFormView.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: TodoListView
  },
  {
    path: '/todos',
    name: 'Todos',
    component: TodoListView
  },
  {
    path: '/todos/new',
    name: 'NewTodo',
    component: TodoFormView
  },
  {
    path: '/todos/:id/edit',
    name: 'EditTodo',
    component: TodoFormView,
    props: true
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
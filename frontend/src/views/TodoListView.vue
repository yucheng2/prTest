<template>
  <div class="todo-list">
    <h1>我的待办事项</h1>
    
    <div class="toolbar">
      <button @click="$router.push('/todos/new')">添加新任务</button>
    </div>

    <div v-if="loading" class="loading">加载中...</div>
    <div v-else-if="error" class="error">{{ error }}</div>
    <div v-else class="todos">
      <TodoItem 
        v-for="todo in todos" 
        :key="todo.id" 
        :todo="todo"
        @update="updateTodo"
        @edit="editTodo"
        @delete="deleteTodo"
      />
    </div>
  </div>
</template>

<script>
import { useTodoStore } from '../stores/todo'
import TodoItem from '../components/TodoItem.vue'

export default {
  name: 'TodoListView',
  components: {
    TodoItem
  },
  data() {
    return {
      todoStore: useTodoStore()
    }
  },
  computed: {
    todos() {
      return this.todoStore.todos
    },
    loading() {
      return this.todoStore.loading
    },
    error() {
      return this.todoStore.error
    }
  },
  methods: {
    updateTodo(todo) {
      this.todoStore.updateTodo(todo.id, todo)
    },
    editTodo(id) {
      this.$router.push(`/todos/${id}/edit`)
    },
    async deleteTodo(id) {
      await this.todoStore.deleteTodo(id)
    }
  },
  mounted() {
    this.todoStore.fetchTodos()
  }
}
</script>

<style scoped>
.todo-list {
  max-width: 800px;
  margin: 0 auto;
}

.toolbar {
  margin-bottom: 20px;
  text-align: right;
}

.toolbar button {
  padding: 8px 16px;
  background-color: #409eff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.toolbar button:hover {
  background-color: #66b1ff;
}

.loading, .error {
  text-align: center;
  padding: 20px;
}
</style>
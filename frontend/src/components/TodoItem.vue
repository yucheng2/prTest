<template>
  <div class="todo-item" :class="{ completed: todo.completed }">
    <input 
      type="checkbox" 
      :checked="todo.completed"
      @change="toggleTodoStatus"
    />
    <div class="todo-content">
      <h3>{{ todo.title }}</h3>
      <p v-if="todo.description">{{ todo.description }}</p>
      <div class="todo-meta">
        <span class="priority" :class="todo.priority">优先级: {{ todo.priority }}</span>
        <span class="date">{{ formatDate(todo.dueDate) }}</span>
      </div>
    </div>
    <div class="actions">
      <button @click="editTodo">编辑</button>
      <button @click="deleteTodo">删除</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'TodoItem',
  props: {
    todo: {
      type: Object,
      required: true
    }
  },
  emits: ['update', 'delete'],
  methods: {
    toggleTodoStatus() {
      this.$emit('update', { ...this.todo, completed: !this.todo.completed })
    },
    editTodo() {
      this.$emit('edit', this.todo.id)
    },
    deleteTodo() {
      if (confirm('确定要删除这个任务吗？')) {
        this.$emit('delete', this.todo.id)
      }
    },
    formatDate(dateString) {
      if (!dateString) return ''
      const date = new Date(dateString)
      return date.toLocaleDateString('zh-CN')
    }
  }
}
</script>

<style scoped>
.todo-item {
  display: flex;
  align-items: center;
  padding: 15px;
  border: 1px solid #ddd;
  border-radius: 4px;
  margin-bottom: 10px;
  background-color: #fafafa;
}

.todo-item.completed {
  background-color: #f0f9eb;
}

.todo-item.completed h3 {
  text-decoration: line-through;
  color: #999;
}

.todo-content {
  flex: 1;
  padding: 0 15px;
}

.todo-content h3 {
  margin: 0 0 5px 0;
}

.todo-content p {
  margin: 5px 0;
  color: #666;
}

.todo-meta {
  display: flex;
  gap: 15px;
  font-size: 0.9em;
  color: #888;
}

.priority {
  padding: 2px 6px;
  border-radius: 3px;
  background-color: #f4f4f5;
}

.priority.high {
  background-color: #fef0f0;
  color: #f56c6c;
}

.priority.medium {
  background-color: #fdf6ec;
  color: #e6a23c;
}

.priority.low {
  background-color: #f0f9eb;
  color: #67c23a;
}

.date {
  color: #909399;
}

.actions {
  display: flex;
  gap: 10px;
}

.actions button {
  padding: 5px 10px;
  border: 1px solid #dcdfe6;
  background-color: white;
  border-radius: 3px;
  cursor: pointer;
}

.actions button:hover {
  background-color: #ecf5ff;
}
</style>
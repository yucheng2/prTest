<template>
  <div class="todo-form">
    <h1>{{ isEditing ? '编辑任务' : '新建任务' }}</h1>
    
    <form @submit.prevent="submitForm">
      <div class="form-group">
        <label for="title">标题 *</label>
        <input 
          id="title"
          v-model="formData.title" 
          type="text" 
          placeholder="请输入任务标题"
          required
        />
      </div>
      
      <div class="form-group">
        <label for="description">描述</label>
        <textarea 
          id="description"
          v-model="formData.description" 
          placeholder="请输入任务描述"
          rows="4"
        ></textarea>
      </div>
      
      <div class="form-group">
        <label for="priority">优先级</label>
        <select id="priority" v-model="formData.priority">
          <option value="low">低</option>
          <option value="medium">中</option>
          <option value="high">高</option>
        </select>
      </div>
      
      <div class="form-group">
        <label for="dueDate">截止日期</label>
        <input 
          id="dueDate"
          v-model="formData.dueDate" 
          type="date"
        />
      </div>
      
      <div class="form-group">
        <label>
          <input 
            v-model="formData.completed" 
            type="checkbox"
          />
          已完成
        </label>
      </div>
      
      <div class="form-actions">
        <button type="submit" :disabled="loading">
          {{ loading ? '提交中...' : (isEditing ? '更新任务' : '创建任务') }}
        </button>
        <button type="button" @click="$router.go(-1)">取消</button>
      </div>
    </form>
  </div>
</template>

<script>
import { useTodoStore } from '../stores/todo'

export default {
  name: 'TodoFormView',
  props: {
    id: {
      type: String,
      default: null
    }
  },
  data() {
    return {
      todoStore: useTodoStore(),
      formData: {
        title: '',
        description: '',
        priority: 'medium',
        dueDate: '',
        completed: false
      },
      loading: false
    }
  },
  computed: {
    isEditing() {
      return !!this.id
    }
  },
  methods: {
    async submitForm() {
      this.loading = true
      
      try {
        if (this.isEditing) {
          await this.todoStore.updateTodo(this.id, this.formData)
        } else {
          await this.todoStore.addTodo(this.formData)
        }
        
        this.$router.push('/todos')
      } catch (error) {
        console.error('提交失败:', error)
        alert('提交失败，请重试')
      } finally {
        this.loading = false
      }
    },
    
    async loadTodo() {
      if (this.isEditing) {
        const todo = this.todoStore.getTodoById(this.id)
        if (todo) {
          this.formData = { ...todo }
        } else {
          // 如果store中没有，则尝试从API获取
          this.todoStore.fetchTodos()
        }
      }
    }
  },
  mounted() {
    if (this.isEditing) {
      this.loadTodo()
    }
  },
  watch: {
    id() {
      if (this.isEditing) {
        this.loadTodo()
      } else {
        this.formData = {
          title: '',
          description: '',
          priority: 'medium',
          dueDate: '',
          completed: false
        }
      }
    }
  }
}
</script>

<style scoped>
.todo-form {
  max-width: 600px;
  margin: 0 auto;
}

.form-group {
  margin-bottom: 20px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

.form-group input,
.form-group textarea,
.form-group select {
  width: 100%;
  padding: 8px 12px;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  box-sizing: border-box;
}

.form-group input:focus,
.form-group textarea:focus,
.form-group select:focus {
  outline: none;
  border-color: #409eff;
}

.form-actions {
  display: flex;
  gap: 10px;
  justify-content: flex-end;
}

.form-actions button {
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.form-actions button[type="submit"] {
  background-color: #409eff;
  color: white;
}

.form-actions button[type="submit"]:hover:not(:disabled) {
  background-color: #66b1ff;
}

.form-actions button[type="submit"]:disabled {
  background-color: #a0cfff;
  cursor: not-allowed;
}

.form-actions button[type="button"] {
  background-color: #c0c4cc;
  color: white;
}

.form-actions button[type="button"]:hover {
  background-color: #d3d4d6;
}
</style>
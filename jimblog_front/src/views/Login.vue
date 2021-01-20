<template>
  <div style="margin-top: 200px">
    <h1>欢迎登录jimblog后台系统</h1>
    <div class="formBox">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="ruleForm.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="pwd">
        <el-input v-model="ruleForm.pwd" show-password></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
    </div>
  </div>
</template>

<script>
import login from '../api/login'
export default {
  name: 'Login',
  data () {
    return {
      ruleForm: {
        username: '',
        pwd: ''
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ],
        pwd: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          login.login(this.ruleForm).then(res => {
            if (res.code === 20000) {
              this.$message({
                message: res.message,
                type: 'success'
              })
              this.$router.push('/admin/index')
            } else {
              this.$message.error(res.message)
            }
          })
        } else {
          this.$message.error('请重新输入账号密码')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>

<style scoped>
.formBox {
  width: 300px;
  height: 300px;
  margin: auto auto auto auto
}
</style>

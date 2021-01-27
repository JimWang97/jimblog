<template>
  <div style="width: 60%; margin: 20px 20px auto 20px">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="标题" prop="title">
        <el-input v-model="ruleForm.title"></el-input>
      </el-form-item>
      <el-form-item label="url" prop="url">
        <el-input v-model="ruleForm.url"></el-input>
      </el-form-item>
      <el-form-item label="内容" prop="content">
        <el-input v-model="ruleForm.content"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">发布</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

import programApi from '../../api/programApi'

export default {
  name: 'AddProgram',
  data () {
    return {
      tagOptions: [],
      ruleForm: {
        content: '',
        url: '',
        title: ''
      },
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'blur' },
          { min: 3, max: 25, message: '长度在 3 到 25 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          programApi.adminAddProgram(this.ruleForm).then(res => {
            if (res.code === 20000) {
              this.$message({
                message: res.message,
                type: 'success'
              })
              this.$router.push('/admin/index/programlist')
            } else {
              this.$message.error(res.message)
            }
          })
        } else {
          this.$message.error('添加项目失败')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    }
  },
  created () {
    if (this.$route.query.id) {
      this.ruleForm.id = parseInt(this.$route.query.id)
      programApi.adminGetProgram(this.ruleForm.id).then(res => {
        if (res.code === 20000) {
          this.ruleForm.title = res.data.program.title
          this.ruleForm.content = res.data.program.content
          this.ruleForm.url = res.data.program.url
        }
      })
    }
  }
}
</script>

<style scoped>

</style>

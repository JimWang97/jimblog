<template>
  <div style="width: 60%; margin: 20px 20px auto 20px">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="标题" prop="title">
        <el-input v-model="ruleForm.title"></el-input>
      </el-form-item>
      <el-row>
        <el-col :span="8">
      <el-form-item label="类型" prop="type">
        <el-select v-model="ruleForm.type" placeholder="请选择类型">
          <el-option label="原创" :value=true></el-option>
          <el-option label="转载" :value=false></el-option>
        </el-select>
      </el-form-item>
        </el-col>
        <el-col :span="8">
      <el-form-item label="标签" prop="tags">
        <el-select
          v-model="ruleForm.tags"
          multiple
          filterable
          allow-create
          default-first-option
          placeholder="请选择文章标签">
          <el-option
            v-for="item in tagOptions"
            :key="item.tag"
            :label="item.tag"
            :value="item.tag">
          </el-option>
        </el-select>
      </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item prop="recommend">
            <el-switch
              v-model="ruleForm.recommend"
              active-text="推荐"
              inactive-text="不推荐">
            </el-switch>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item>
        <div class="markdown">
          <Markdown :height=500 theme="gitHub" v-model="ruleForm.content"/>
        </div>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">发布</el-button>
        <el-button type="primary" @click="submitForm('ruleForm')">保存</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import blogApi from '../../api/blogApi'
import tagApi from '../../api/tagApi'
import Markdown from 'vue-meditor'

var qs = require('qs');
export default {
  name: 'AddBlog',
  components: {
    Markdown
  },
  data () {
    return {
      tagOptions: [],
      ruleForm: {
        recommend: false,
        content: '',
        title: '',
        type: true,
        tags: []
      },
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'blur' },
          { min: 3, max: 25, message: '长度在 3 到 25 个字符', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '请选择类型', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          blogApi.adminAddBlog(qs.stringify(this.ruleForm)).then(res => {
            if (res === 20000) {
              this.$message({
                message: res.message,
                type: 'success'
              })
              this.$router.push('/admin/index/bloglist')
            } else {
              this.$message.error(res.message)
            }
          })
        } else {
          this.$message.error('添加博客失败')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    getTags () {
      tagApi.getTags().then(res => {
        if (res.code === 20000) {
          this.tagOptions = res.data.tags
        }
      })
    }
  },
  created () {
    this.getTags()
  }
}
</script>

<style scoped>

</style>

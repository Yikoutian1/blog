<template>
  <div class="login-container">
    <el-form :model="formData" :rules="rulesList"
             status-icon
             ref="formData"
             label-position="left"
             label-width="0px"
             class="demo-ruleForm login-page">
      <h3 class="title">用户注册</h3>
      <el-form-item prop="username">
        <el-input type="text"
                  v-model="formData.username"
                  auto-complete="off"
                  placeholder="用户登录名"
        ></el-input>
      </el-form-item>
      <el-form-item prop="username">
        <el-input type="text"
                  v-model="formData.username"
                  auto-complete="off"
                  placeholder="用户名"
        ></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password"
                  v-model="formData.password"
                  auto-complete="off"
                  placeholder="密码"
        ></el-input>
      </el-form-item>
<!--      <el-checkbox-->
<!--                v-model="checked"-->
<!--                class="rememberme"-->
<!--            >记住密码</el-checkbox>-->
      <el-form-item style="width:100%;">
        <el-button type="primary" style="width:100%;" :loading="logining" @click="handleSubmit">注册</el-button>
      </el-form-item>

      <router-link tag="div" :to="{ path: 'login' }">
        <el-form-item style="width:100%;">
          <el-button type="primary" style="width:100%;" :loading="logining">登录</el-button>
        </el-form-item>
      </router-link>
    </el-form>
  </div>
</template>

<script>

export default {
  data() {
    return {
      logining: false,
      formData: {
        username: 'test',
        password: '123456',
      },
      rulesList: {
        username: [{required: true, message: 'please enter your account', trigger: 'blur'}],
        password: [{required: true, message: 'enter your password', trigger: 'blur'}]
      },
      checked: false
    }
  },
  methods: {
    handleSubmit() {
      this.$axios.post('/user/register', this.formData).then(res => {
        console.log(res)
        if (res.code === 200) {
          this.$message({
            message: '注册成功，正在跳转登录页面',
            type: 'success'
          });

          setTimeout(() => {
            this.$router.push({path: '/'});
          }, 3000)

        }

      })


    }
  }
};
</script>
<!--lang="less"-->
<style scoped>
.photo {
  display: flex;
  align-items: center;
  justify-content:space-between;
  text-align: center;
}
.login-container {
  width: 100%;
  height: 100%;
}

.login-page {
  -webkit-border-radius: 5px;
  border-radius: 5px;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

label.el-checkbox.rememberme {
  margin: 0px 0px 15px;
  text-align: left;
}

.title {
  text-align: center;
  margin-bottom: 35px;
}
</style>
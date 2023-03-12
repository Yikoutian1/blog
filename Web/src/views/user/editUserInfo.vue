<template>
    <div class="login-container">
        <el-form :model="formData" :rules="rulesList"
                 status-icon
                 ref="formData"
                 label-position="left"
                 label-width="0px"
                 class="demo-ruleForm login-page">
            <h3 class="title">用户信息修改</h3>

            <el-form-item prop="username">
                <el-input type="text"
                          v-model="formData.username"
                          auto-complete="off"
                          placeholder="用户名"
                ></el-input>
            </el-form-item>
          <el-form-item prop="password">
            <el-input type="text"
                      v-model="formData.password"
                      auto-complete="off"
                      placeholder="密码"
            ></el-input>
          </el-form-item>
            <el-form-item prop="email">
                <el-input type="text"
                          v-model="formData.email"
                          auto-complete="off"
                          placeholder="邮箱"
                ></el-input>
            </el-form-item>

            <el-form-item prop="mobile">
                <el-input type="text"
                          v-model="formData.mobile"
                          auto-complete="off"
                          placeholder="手机号"
                ></el-input>
            </el-form-item>

            <el-form-item prop="motto">
                <el-input
                        type="textarea"
                        :rows="3"
                        v-model="formData.motto"
                        auto-complete="off"
                        placeholder="座右铭"
                ></el-input>
            </el-form-item>

            <el-form-item style="width:100%;">
                <el-button type="primary" style="width:100%;"  @click="handleSubmit">修改</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    import utils from "@/utils/utils";
    export default {
        data(){
            return {
                formData: {
                    id:"",
                    username: '',
                    password: '',
                    email: '',
                    mobile: '',
                    address: '',
                    motto: '',
                },
                rulesList: {
                    username: [{required: true, message: '[用户名]不能为空', trigger: 'blur'}],
                    email: [{required: true, message: '[邮箱]不能为空', trigger: 'blur'}],
                    mobile: [{required: true, message: '[手机号]不能为空', trigger: 'blur'}],
                    address: [{required: true, message: '[地址]不能为空', trigger: 'blur'}]
                },
                checked: false
            }
        },
        created() {
            console.log(utils.getUserInfo())
            this.formData = utils.getUserInfo()
        },
        methods: {
            handleSubmit (){
                this.$axios.post('/user/save',this.formData).then(res => {
                    console.log(res)
                    localStorage.setItem("userInfo",JSON.stringify(this.formData))
                    this.$message({
                        message: '修改成功',
                        type: 'success'
                    });
                })
            }
        }
    };
</script>

<style scoped>

    .login-container {

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
        margin-bottom:35px;
    }
</style>
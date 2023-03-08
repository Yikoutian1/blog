

<template>
  <div>
    {{formData.id}}
    <el-drawer
        :size="size"
        :title="formData.id == null ?'新增数据':'编辑数据'"
        :before-close="handleClose"
        :visible.sync="isDrawerDialog"
        direction="rtl"
        custom-class="demo-drawer"
        ref="drawer"
    >
      <div class="demo-drawer__content">
        <el-form :model="formData" :rules="rulesForm" ref="formData" label-width="100px" class="demo-ruleForm">
          <slot name="content">这里是中间</slot>
        </el-form>
<!--        <el-form-item>
          <el-button type="primary" @click="submitForm('formData')">立即创建</el-button>
          <el-button @click="resetForm('formData')">重置</el-button>
        </el-form-item>-->

        <div class="demo-drawer__footer">
          <el-button @click="cancelForm('formData')">取 消</el-button>
          <el-button type="primary" @click="submitForm('formData')" :loading="loading">提 交</el-button>
        </div>
      </div>
    </el-drawer>
  </div>

</template>

<script>
export default {
  mounted() {
    //未定义isDrawerDialog报错
    // Property or method "isDrawerDialog" is not defined on the instance but referenced during render. Make sure that this property is reactive, either in the data option, or for class-based components, by initializing the property
  },
  props: {
    currentPage: {
      type: [String, Number],
      default: 1
    },
    isDrawerDialog: {
      type: Boolean,
      default: false
    },
    formData:{
      type: Object,
      default: function () {
        return {}
      }
    },
    rulesForm:{
      type: Object,
      default: function () {
        return {}
      }
    },
    size:{
      type: String,
      default: '30%'
    }
//100%
  },
  data() {
    return {
      loading: false,
      timer: null,
    };
  },
  methods: {
    handleClose() {
/*      if (this.loading) {
        return;
      }*/
      this.$emit("close-drawer",false)

    },
    submitForm(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$emit("submit-drawer",this.formData)
        } else {
          // console.log('error submit!!');
          this.$message.error('请填写符合条件的数据');

          return false;
        }
      });

    },
    cancelForm(formName) {
      this.$refs[formName].resetFields();
      this.loading = false;
/*      Avoid mutating a prop directly since the value will be overwritten whenever the parent component re-renders. Instead, use a data or computed property based on the prop's value. Prop being mutated: "isDrawerDialog"
      https://blog.csdn.net/qq_37304462/article/details/111306918
      https://www.jianshu.com/p/3c3780d521ce
      */
      this.$emit("close-drawer",false)
      clearTimeout(this.timer);
    },

  },

}
</script>
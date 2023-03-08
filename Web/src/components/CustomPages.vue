<template>
  <el-pagination
      background
      :is-show="isPagesShow"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="pageSizes"
      :page-size="pageSize"
      :layout="layout"
      :total="total">
  </el-pagination>
</template>
<script>
export default {
  props: {
    currentPage: {
      type: [String, Number],
      default: 1
    },
    total: {
      type: [String, Number],
      default: 0
    },
    pageSizes: {
      type: Array,
      default: function () {
        return [10, 20, 50, 100, 200, 300, 400]
      }
    },
    pageSize: {
      type: [String, Number],
      default: 10
    },
    layout: {
      type: String,
      default: 'total, sizes, prev, pager, next, jumper'
    },
    isPagesShow: {
      type: Boolean,
      default: true
    }
  },
  data () {
    return {
    }
  },
  computed: {
  },
  methods: {
    handleSizeChange (val) {
      console.log(1)
      this.$emit('size-change', val)
    },
    handleCurrentChange (val) {
      console.log(1)
      this.$emit('current-change', val)
    }
  }
}
// https://www.cnblogs.com/luoxuemei/p/11820336.html
</script>
<style scoped>
.page {
  text-align: center;
  color: #409eff;
}
</style>
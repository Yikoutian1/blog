<template>
  <div>
    <el-card class="box-card" v-for="item in tableData" :key="item.id">
      <div slot="header" class="clearfix">
        <span>{{ item.title }}</span>
        <el-button style="float: right; padding: 3px 0" type="text" @click="toArticleDetail(item.id)">查看</el-button>
      </div>
      <div class="text item">
        {{ item.content===null?"":item.content.slice(1,200)+"..."}}
      </div>
    </el-card>
  </div>
</template>
<script>

export default {
  props: {
    tableData: {
      type: Array,
      default: function () {
        return [];
      }
    },
    url: {
      type: String,
      default: "",
    },
  },
  data() {
    return {}
  },
  created(){

  },
  methods: {
    toArticleDetail(e) {
      console.log(e)
      this.$router.push({path: this.url, query: {id: e}})
    },
  }
}
</script>
<style>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.box-card {
  /*width: 480px;*/
}
</style>
<template>
  <div>
    <div style="margin: 10px 0">
      <el-input @input="search" style="width: 200px" clearable placeholder="请输入名称" suffix-icon="el-icon-search" v-model="searchParams.title"></el-input>
      <el-button class="ml-5" type="primary" @click="search">搜索</el-button>
      <el-button class="ml-5" type="primary" @click="createDrawer">新建</el-button>
      <el-button type="warning" @click="resetForm">重置</el-button>
      <el-button type="warning" :disabled="delArr.length>0?false:true" @click="delAll">全部删除</el-button>
    </div>

    <el-table
            ref="multipleTable"
            :data="tableData"
            @selection-change="selectionChange"
            tooltip-effect="dark"
            style="width: 100%" >

      <el-table-column
              type="selection"
              width="55">
      </el-table-column>
      <el-table-column
              prop="id"
              label="ID"
              width="50">
      </el-table-column>
      <el-table-column
              prop="title"
              label="标题"
              show-overflow-tooltip>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
                  size="mini"
                  @click="handleEdit(scope.$index, scope.row)">编辑
          </el-button>
          <el-button
                  size="mini"
                  type="danger"
                  @click="handleDelete(scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <CustomPages @size-change="handleSizeChange"  @current-change="handleCurrentChange" :currentPage="pageInfo.pageNum" :total="pageInfo.total" :pageSize="pageInfo.pageSize"></CustomPages>
    <CustomDrawer @close-drawer="closeDrawer" :isDrawerDialog="isDrawerDialog" @submit-drawer="submitDrawer" :form-data="formData" :rules-form="rulesForm">
      <div slot="content">
        <el-form-item label="标题" prop="title">
          <el-input v-model="formData.title"></el-input>
        </el-form-item>
      </div>
    </CustomDrawer>

  </div>
</template>

<script>
  //引入混合
  import create from '@/mixins/create'
  export default {
    mixins: [create],
    data() {
      return {
        //这里对象的层级要高于混合中名称相同的对象
        api:{
          listUrl:"/category/page",
          saveUrl:"/category/save",
          delUrl:"/category/delBatch",
        },
        formData: {
          id: null,
          title:""
        },
        rulesForm: {
          title: [
            { required: true, message: '请输入标题', trigger: 'blur' },
          ],
        }
      }
    },
    methods: {

    }
  }
</script>
<template>
    <div>
        <div style="margin: 10px 0">
            <el-input @input="search" style="width: 200px" clearable placeholder="请输入名称" suffix-icon="el-icon-search"
                      v-model="searchParams.title"></el-input>
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
                style="width: 100%">

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
        <!--    //@sizeChange[错误的] @size-change[正确的]-->
        <CustomPages @size-change="handleSizeChange" @current-change="handleCurrentChange" :currentPage="pageInfo.pageNum"
                     :total="pageInfo.total"></CustomPages>
        <CustomDrawer @close-drawer="closeDrawer" :isDrawerDialog="isDrawerDialog" @submit-drawer="submitDrawer"
                      :form-data="formData" :rules-form="rulesForm" :size="size">
            <div slot="content">

                <el-form-item label="标题" prop="title">
                    <el-input v-model="formData.title"></el-input>
                </el-form-item>

                <el-form-item label="所属栏目" prop="categoryId">

                    <el-select v-model="formData.categoryId" clearable placeholder="请选择">
                        <el-option
                                v-for="item in categoryList"
                                :key="item.id"
                                :label="item.title"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="浏览量" prop="views">
                    <el-input v-model="formData.views"></el-input>
                </el-form-item>

                <el-form-item label="文章内容" prop="content">
<!--                    <el-input v-model="formData.content"></el-input>-->
                     <CustomEditor :content="formData.content" @content-change="contentChange"></CustomEditor>
                </el-form-item>

                <el-form-item label="状态" prop="status">
                    <el-select v-model="formData.status" clearable placeholder="请选择">
                        <el-option
                                v-for="item in statusList"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>

            </div>
        </CustomDrawer>

    </div>
</template>

<script>
    //引入混合
    import create from '@/mixins/create'

    export default {
        created() {
            this.getCategory()
        },
        mixins: [create],
        data() {
            return {
                api: {
                    listUrl: "/article/page",
                    saveUrl: "/article/save",
                    delUrl: "/article/delBatch",
                },
                formData: {
                    id: null,
                    title: ""
                },
                size: '100%',
                rulesForm: {
                    title: [
                        {required: true, message: '请输入标题', trigger: 'blur'},
                    ],
                },
                categoryList:[]
            }
        },
        methods: {
            getCategory(){
                this.$axios.get("category/").then(res => {
                    console.log(res)
                    this.categoryList = res.data
                })
            },
            // list
            contentChange(e) {
                console.log(e)
                this.formData.content=e
            },


        }
    }
</script>
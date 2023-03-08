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
                    prop="name"
                    label="名称"
                    show-overflow-tooltip>
            </el-table-column>

            <el-table-column
                    prop="path"
                    label="前端路由路径"
                    show-overflow-tooltip>
            </el-table-column>

            <el-table-column
                    prop="pagePath"
                    label="页面路口"
                    show-overflow-tooltip>
            </el-table-column>

            <el-table-column
                    prop="sortNum"
                    label="排序"
                    show-overflow-tooltip>
            </el-table-column>

            <el-table-column
                    prop="status"
                    label="状态"
                    show-overflow-tooltip>
            </el-table-column>

            <el-table-column
                    prop="description"
                    label="描述"
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
        <CustomPages @size-change="handleSizeChange" @current-change="handleCurrentChange" :currentPage="pageInfo.pageNum"
                     :total="pageInfo.total"></CustomPages>
        <CustomDrawer @close-drawer="closeDrawer" :isDrawerDialog="isDrawerDialog" @submit-drawer="submitDrawer"
                      :form-data="formData" :rules-form="rulesForm">

            <div slot="content">

                <el-form-item label="名称" prop="name">
                    <el-input v-model="formData.name"></el-input>
                </el-form-item>

                <el-form-item label="前端路由路径" prop="path">
                    <el-input v-model="formData.path"></el-input>
                </el-form-item>
                <el-form-item label="页面路口" prop="pagePath">
                    <el-input v-model="formData.pagePath"></el-input>
                </el-form-item>
                <el-form-item label="排序" prop="sortNum">
                    <el-input v-model="formData.sortNum"></el-input>
                </el-form-item>
                <el-form-item label="描述" prop="description">
                    <el-input
                            type="textarea"
                            :rows="3"
                            v-model="formData.description"></el-input>
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
        mixins: [create],
        data() {
            return {
                api: {
                    listUrl: "/menu/page",
                    saveUrl: "/menu/save",
                    delUrl: "/menu/delBatch",
                },
                pageInfo:{
                    pageSize :1000
                },
                formData: {
                    id: null,
                    // status: true
                },
                rulesForm: {
                    title: [
                        {required: true, message: '请输入标题', trigger: 'blur'},
                    ],
                },
            }
        },
        created(){
            console.log(this.pageInfo)
        },
        methods: {



        }
    }
</script>
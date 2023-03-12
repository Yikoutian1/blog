<template>
    <div>
        <div style="margin: 10px 0">
            <el-input @input="search" style="width: 200px" clearable placeholder="请输入用户名" suffix-icon="el-icon-search" v-model="searchParams.username"></el-input>
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
                    prop="username"
                    label="用户名"
                    show-overflow-tooltip>
            </el-table-column>

            <el-table-column
                    prop="motto"
                    label="座右铭"
                    show-overflow-tooltip>
            </el-table-column>

            <el-table-column
                    prop="email"
                    label="邮箱"
                    show-overflow-tooltip>
            </el-table-column>

            <el-table-column
                    prop="mobile"
                    label="电话"
                    show-overflow-tooltip>
            </el-table-column>

            <el-table-column
                    prop="address"
                    label="地址"
                    show-overflow-tooltip>
            </el-table-column>

            <el-table-column
                    prop="roleType"
                    label="类型"
                    show-overflow-tooltip>
            </el-table-column>

            <el-table-column
                    prop="views"
                    label="总浏览"
                    show-overflow-tooltip>
            </el-table-column>

            <el-table-column
                    prop="status"
                    label="状态"
                    show-overflow-tooltip>
            </el-table-column>

            <el-table-column label="操作" width="300px">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            @click="handleEdit(scope.$index, scope.row)">分配菜单
                    </el-button>
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

                <el-form-item label="用户名" prop="username">
                    <el-input v-model="formData.username"></el-input>
                </el-form-item>
                <el-form-item label="座右铭" prop="motto">
                    <el-input
                            type="textarea"
                            :rows="3"
                            v-model="formData.motto"
                            auto-complete="off"
                            placeholder="座右铭"
                    ></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="formData.email"></el-input>
                </el-form-item>
                <el-form-item label="电话" prop="mobile">
                    <el-input v-model="formData.mobile"></el-input>
                </el-form-item>
                <el-form-item label="地址" prop="address">
                    <el-input v-model="formData.address"></el-input>
                </el-form-item>
                <el-form-item label="类型" prop="roleType">
                    <el-select v-model="formData.roleType" clearable placeholder="请选择">
                        <el-option
                                v-for="item in roleTypeList"
                                :key="item.value"
                                :label="item.value"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="状态" prop="status">
                    <el-select v-model="formData.status" clearable placeholder="请选择">
                        <el-option
                                v-for="item in statusList"
                                :key="item.value"
                                :label="item.value"
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
                    listUrl: "/user/page",
                    saveUrl: "/user/save",
                    delUrl: "/user/delBatch",
                },
                formData: {
                    id: null,
                    title:""
                },
                rulesForm: {
                    username: [
                        { required: true, message: '请输入', trigger: 'blur' },
                    ],
                }
            }
        },
        methods: {

        }
    }
</script>
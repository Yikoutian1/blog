<template>

    <div>
        <CustomCard :table-data="tableData" :url="url"></CustomCard>
        <CustomPages @size-change="handleSizeChange" @current-change="handleCurrentChange" :currentPage="pageInfo.pageNum"
                     :total="pageInfo.total"></CustomPages>
    </div>

</template>
<script>
    //引入混合
    import create from '@/mixins/create'

    export default {
        mixins: [create],
        data() {
            return {
                api:{
                    listUrl:"/article/page",
                },
                url:"detail",
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
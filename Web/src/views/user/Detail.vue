<template>
    <div>
        <!--      {{detail}}-->
        <el-descriptions :title="detail.title">
            <el-descriptions-item label="发布时间">{{ detail.createTime }}</el-descriptions-item>

            <el-descriptions-item label="所属栏目">{{ detail.categoryTitle === null ? "未知" : detail.categoryTitle }}
            </el-descriptions-item>
            <el-descriptions-item label="更新时间">{{ detail.updateTime }}</el-descriptions-item>
            <el-descriptions-item label="发布人">{{ detail.username === null ? "未知" : detail.username }}
            </el-descriptions-item>
            <el-descriptions-item label="浏览量">
                <el-tag size="small">{{ detail.views }}</el-tag>
            </el-descriptions-item>
        </el-descriptions>

        <div style="text-indent: 2em" v-html="detail.content">
        </div>

    </div>
</template>
<script>

</script>
<script>
    export default {
        data() {
            return {
                detail: {}
            }
        },
        created() {
            console.log(this.$route.query)
            this.getOne(this.$route.query.id)
        },
        methods: {
            getOne(id) {
                this.$axios.get(`article/detail?id=${id}`).then(res => {
                    this.detail = res.data
                })
            }
        }
    }

</script>
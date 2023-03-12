
const create = {
    data() {
        return {
            api: {
                listUrl: "",
                saveUrl: "",
                delUrl: "",
            },
            tableData: [],
            //全局弹窗对话框
            isDrawerDialog: false,
            //删除提示框
            isMessageBox: false,
            //删除数据
            delArr: [],
            isNoData: false,
            // isShowLoding: true,
            formData: {},
            list: [
                {
                    id: 1,
                    title: "Java管理系统"
                }
            ],
            pageInfo: {
                pageSize: 10, //每页10条数据
                pageNum: 1,//当前页
                total: 0 //总条数
            },
            //搜索参数
            searchParams: {},
            //合并查询
            searchMerge: {},
            statusList: [
                {
                    value: "启用",
                },
                {
                    value: "禁用"
                }],
            roleTypeList:[
                {
                    value: "ROLE_ADMIN",
                },
                {
                    value: "ROLE_USER",
                }
            ]
        }
    },
    created() {
        //很多问题都是和执行顺序有关
        this.searchMerge = Object.assign(this.pageInfo, this.searchParams)
        try {
            this.getList()
        } catch (e) {
            console.log("getList方法不存在")
            console.log(e)
        }
    },
    methods: {
        //控制每页页数
        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
            this.pageInfo.pageSize = val
            this.searchMerge = Object.assign(this.pageInfo, this.searchParams)
            this.getList()
        },
        //控制页面切换
        handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
            this.pageInfo.pageNum = val
            /*
                        https://blog.csdn.net/guxin_duyin/article/details/88916106
                        Object.assign(target, ...sources)
                        参数：target--->目标对象
                        source--->源对象
                        返回值：target，即目标对象*/
            this.searchMerge = Object.assign(this.pageInfo, this.searchParams)
            console.log(this.searchMerge)
            this.getList()
        },
        //防抖节流
        conduct(fn, delay, masExac) {
            var timer;
            var lastTime = new Date();
            return function (arg) {
                var now = new Date();
                clearTimeout(timer)
                if (now - lastTime < masExac) {
                    timer = setTimeout(() => {
                        fn(arg);
                        lastTime = now;
                    }, delay)
                } else {
                    fn(arg);
                    lastTime = now;
                }
            }
        },
        Fn() {
            this.pageInfo.pageNum = 1
            this.getList()
        },
        closeDrawer(e) {
            this.isDrawerDialog = e
        },

        //编辑删除操作
        handleEdit(i, item) {
            console.log(i)
            this.isDrawerDialog = true
            this.formData = item
        },
        handleDelete(item) {
            this.delArr = []
            this.delArr.push(item.id)
            /*            console.log(i)
                        console.log(item)*/
            // this.isMessageBox = true
            this.delMessage()
        },
        selectionChange(data) {
            console.log(data)
            this.delArr = []
            data.forEach(item => {
                this.delArr.push(item.id)
            })
        },
        delAll() {
            // this.isMessageBox = true
            this.delMessage()
        },

        createDrawer() {
            this.isDrawerDialog = true
            this.formData = {
                status: "启用"
            }
        },
        search() {
            this.searchMerge = Object.assign(this.pageInfo, this.searchParams)
            this.getList()
            // this.throttle = this.conduct(this.Fn, 1000, 2000)
        },
        resetForm() {
            this.searchParams = {}
        },
        submitDrawer(e) {
            console.log("获取提交的数据", e)
            this.isDrawerDialog = false
            this.formData = Object.assign({},e)
            this.saveForm()
        },
        delMessage() {
            this.$confirm(`此操作将永久删除ID：<span style="color:red;font-weight: bold">[${this.delArr}]</span>为的数据, 是否继续?`, '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true,
                dangerouslyUseHTMLString: true,

            }).then(() => {
                this.delData()
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        },
        getList() {
            this.$axios.post(this.api.listUrl, this.searchMerge).then(res => {
                console.log(res)
                this.tableData = res.data.records
                console.log(this.tableData)
                this.pageInfo.total = res.data.total
            })
        },
        saveForm() {
            this.$axios.post(this.api.saveUrl, this.formData).then(res => {
                console.log(res)
                this.$message({
                    message: '操作成功',
                    type: 'success'
                });
                this.getList()
            })
        },
        delData() {
            this.$axios.post(this.api.delUrl, this.delArr).then(res => {
                console.log(res)
                this.$message({
                    message: '操作成功',
                    type: 'success'
                });
                this.getList()
            })
        }

    }
}
export default create;
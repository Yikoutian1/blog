<template>

  <el-container style="height:100%">
    <!--    :default-active="activeIndex2"-->

    <el-header>
      <el-menu
          class="el-menu-demo"
          mode="horizontal"
          @select="handleSelect"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b">
        <el-menu-item v-show="item.isShow" v-for="(item,index) in menuList" :key="index"
                      :index="'1'+index">
          <router-link style="width: 100%;height: 100%" tag="div" :to="item.routePath">{{ item.title }}</router-link>
        </el-menu-item>


        <el-submenu index="10" v-if="isControlShow">
          <template slot="title">操作</template>
          <el-menu-item index="10-1">
            <router-link tag="span" to="editUserInfo">修改个人信息</router-link>
          </el-menu-item>
          <el-menu-item index="10-2" @click="logout">
            退出登录
          </el-menu-item>
        </el-submenu>

        <!-- <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item> -->
      </el-menu>

    </el-header>
    <el-container>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>

  </el-container>

</template>

<script>
// import utils from "@/utils/utils"

import utils from "@/utils/utils"

export default {
  name: 'Layout',
  components: {},
  data() {
    return {
      menuList: [
        {
          routePath: "/article",
          title: "文章列表",
          children: [],
          isShow: true
        },
        {
          routePath: "articleManage",
          title: "文章管理",
          children: [],
          isShow: true
        },
        {
          routePath: "userManage",
          title: "用户管理",
          children: [],
          isShow: true
        },
        {
          routePath: "categoryManage",
          title: "栏目管理",
          children: [],
          isShow: true
        },
        // {
        //   routePath: "menuManage",
        //   title: "菜单管理",
        //   children: [],
        //   isShow: true
        // },
        {
          routePath: "login",
          title: "登录",
          children: [],
          isShow: true
        },
        {
          routePath: "register",
          title: "注册",
          children: [],
          isShow: true
        },
      ],
      isControlShow: false
    };
  },
  created() {


    //ROLE_ADMIN
    let userInfo = utils.getUserInfo()

    if (utils.getToken() != null) {
      //登录和注册不显示
      this.menuList.forEach((item, index) => {
        if (item.routePath === "login" || item.routePath === "register") {
          this.menuList[index].isShow = false
          this.isControlShow = true
        }

      })

      if(userInfo.roleType === "ROLE_USER"){
        this.menuList[0].isShow = true
        this.menuList[1].isShow = false
        this.menuList[2].isShow = false
        this.menuList[3].isShow = false
      }


    }else{
        this.menuList[0].isShow = true
        this.menuList[1].isShow = false
        this.menuList[2].isShow = false
        this.menuList[3].isShow = false
    }
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    logout() {
      localStorage.clear()

      this.$message({
        type: 'success',
        message: '退出登录成功'
      });
      //刷新
      this.$router.go(0)

    }
  }
}
</script>

<style>

.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #fff;
  color: #333;

}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
</style>
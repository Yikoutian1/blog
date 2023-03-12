import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from "@/views/Layout";

Vue.use(VueRouter)

const routes = [
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import(/* webpackChunkName: "about" */ '../views/Register.vue')
  },
  {
    path: '/',
    name: 'Home',
    component: Layout,
    children:[
      {
        path: '/register',
        name: 'Register',
        component: () => import(/* webpackChunkName: "about" */ '../views/Register.vue')
      },
      {
        path: '/test',
        name: 'Test',
        component: () => import(/* webpackChunkName: "about" */ '../views/Test.vue')
      },
      {
        path: '/categoryManage',
        name: 'CategoryManage',
        component: () => import(/* webpackChunkName: "about" */ '../views/admin/categoryManage.vue')
      },
      {
        path: '/userManage',
        name: 'UserManage',
        component: () => import('../views/admin/userManage.vue')
      },
      {
        //页面对应的路由路径
        path: '/articleManage',
        //路由的名称
        name: 'ArticleManage',
        //路径对应的页面地址
        component: () => import('../views/admin/articleManage.vue')
      },
      {
        path: '/article',
        name: 'Article',
        component: () => import('../views/user/Article.vue')
      },
      {
        path: '/detail',
        name: 'Detail',
        component: () => import('../views/user/Detail.vue')
      },
      {
        path: '/editUserInfo',
        name: 'EditUserInfo',
        component: () => import('../views/user/editUserInfo.vue')
      },
    ]
  },
]

const router = new VueRouter({
  //路由模式
  // mode: 'history',
  routes
})

export default router

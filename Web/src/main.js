import Vue from 'vue'
import App from './App.vue'
import router from './router'

Vue.config.productionTip = false
import Element from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
Vue.use(Element)

import axios from '@/axios'
Vue.prototype.$axios = axios
console.log('环境：', process.env.NODE_ENV)
console.log('服务器：', process.env.VUE_APP_SERVER)
console.log(process.env)

import TestComponent from "@/components/TestComponent";
import CustomDrawer from "@/components/CustomDrawer"
import CustomPages from "@/components/CustomPages"
import CustomBox from "@/components/CustomBox"
import CustomCard from "@/components/CustomCard"
import CustomEditor from "@/components/CustomEditor";

Vue.component("TestComponent",TestComponent)
Vue.component("CustomDrawer",CustomDrawer)
Vue.component("CustomPages",CustomPages)
Vue.component("CustomBox",CustomBox)
Vue.component("CustomCard",CustomCard)
//全局引入
Vue.component("CustomEditor",CustomEditor)

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')

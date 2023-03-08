/**
 * 常用变量
 */

const tools = {

    //获取本地缓存并返回
    getToken(){
        return localStorage.getItem("token")
    },
    getUserInfo(){
        return JSON.parse(localStorage.getItem("userInfo"))
    }


};

export default tools
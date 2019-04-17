<template>
    <div class="login">
        <section class="form_container">
            <div class="manage_tip">
                <span class="title">房屋租赁管理系统</span>
                <el-form :model="loginUser" :rules="rules" ref="loginForm" label-width="60px" 
                class="loginForm">
                    <el-form-item label="账号" prop="account">
                        <el-input v-model="loginUser.account" placeholder="请输入账号"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input v-model="loginUser.password" placeholder="请输入密码" type="password"></el-input>
                    </el-form-item>
                    <el-form-item >
                        <el-button type="primary" class="submit_btn" @click="doLogin('loginForm')">
                        登 录</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </section>
    </div>   
</template>

<script>
import jwt_decode from 'jwt-decode'
import userApi from '../api/user'
import {setUser} from '../utils/auth'
import scheduleApi from '../api/schedule.js'

export default {
    name :'login',
    components: {},
    data () {
        return {
            notifyPromise:Promise.resolve(),
            loginUser: {
                account: '',
                password: ''
            },
            rules: {
                account: [
                    {
                    required: true,
                    message: '账号不能为空',
                    trigger: 'blur'
                    }
                ],
                password: [
                    { 
                    required: true, 
                    message: "密码不能为空", 
                    trigger: "blur" 
                    }
                ]
            }
        }
    },
    methods: {
        //通知，解决element-ui，同时调用notify时，通知重叠的问题
        notify(msg) {
            this.notifyPromise = this.notifyPromise.then(this.$nextTick).then(()=>{
                this.$notify.info({
                    title: "公告",
                    message: msg,
                    offset: 70
                });
            })
        },
        isEmpty(value) {
            return (
                value === undefined ||
                value === null ||
                (typeof value === "object" && Object.keys(value).length === 0) ||
                (typeof value === "string" && value.trim().length === 0)
            );
        },
        doLogin(formName){
            let pojo = {account:this.loginUser.account,password:this.loginUser.password}
            userApi.login(pojo).then(res =>{
                //登录成功之后的处理
                if(res.data.flag){
                    
                    let systemRole = res.data.systemRole
                    let id = res.data.userInfo.id
                    let name = res.data.userInfo.name
                    let token = res.data.token

                    //将用户信息存入cookie中
                    setUser(systemRole,name,id,token)

                    const decode = jwt_decode(token);

                    // 存储数据
                    this.$store.dispatch("setIsAutnenticated", !this.isEmpty(decode));
                    this.$store.dispatch("setUser", decode);

                    this.$message({
                        message:"登录成功",
                        type:"success"
                    });
                    this.$router.push("/index")
                    
                    scheduleApi.getScheduleInSevenDays().then(res =>{                
                        if(res.data.flag){
                            if(res.data.data.length != 0){
                                for(var i = 0;i < res.data.data.length;i++){
                                    this.notify(res.data.data[i].content)
                                }
                            }
                            
                        }else{
                            this.$message({
                            message: '获取公告失败',
                            type: 'warning'
                            });
                        }
                    })
                    
                }else{
                    this.$message({
                        message: "账号或密码错误",
                        type: 'error'
                    })
                    this.loginUser.account=''
                    this.loginUser.password=''
                }
            })
        },
    }
}
</script>

<style scoped>
.login {
  position: relative;
  width: 100%;
  height: 100%;
  background: url(../assets/bg.jpg) no-repeat center center;
  background-size: 100% 100%;
}
.form_container {
  width: 370px;
  height: 210px;
  position: absolute;
  top: 20%;
  left: 34%;
  padding: 25px;
  border-radius: 5px;
  text-align: center;
}
.form_container .manage_tip .title {
  font-family: "Microsoft YaHei";
  font-weight: bold;
  font-size: 26px;
  color: #fff;
}
.loginForm {
  margin-top: 20px;
  background-color: #fff;
  padding: 20px 40px 20px 20px;
  border-radius: 5px;
  box-shadow: 0px 5px 10px #cccc;
}

.submit_btn {
  width: 100%;
}
.tiparea {
  text-align: right;
  font-size: 12px;
  color: #333;
}
.tiparea p a {
  color: #409eff;
}
</style>


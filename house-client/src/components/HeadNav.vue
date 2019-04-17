<template>
    <header class="head-nav">
        <el-row>
          <ElCol :span="6" class="logo-container">
              <img src="../assets/houselogo.png" class="logo" alt="">
              <span class="title"> 房屋租赁管理系统</span>
          </ElCol>
          <el-col :span="6" class="user">
              <div class="userinfo">
                  <div class="welcome">
                      <p class="name avatarname"></p>
                  </div>
                  <span class="username">
                      <el-dropdown trigger="click" @command="setDialogInfo">
                            <span class="el-dropdown-link">{{user.name}}
                                <i class="el-icon-caret-bottom el-icon--right"></i>
                            </span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item command="info">个人信息</el-dropdown-item>
                                <el-dropdown-item command="editpassword">修改密码</el-dropdown-item>
                                <el-dropdown-item command="logout">退出</el-dropdown-item>
                            </el-dropdown-menu>
                       </el-dropdown>
                  </span>
                  
              </div>
              
          </el-col>
        </el-row>
        <EditPasswordDialog :dialog1="dialog1"></EditPasswordDialog>
        <InfoShowDialog :dialog2="dialog2"></InfoShowDialog>
    </header>
    
</template>

<script>
import {removeUser,getUser} from '../utils/auth'
import EditPasswordDialog from '../components/EditPasswordDialog'
import InfoShowDialog from '../components/InfoShowDialog'
export default {
    name: 'head-nav',
     components: {
       InfoShowDialog,
       EditPasswordDialog
     },
    data () {
      return {
        dialog1:{
               show:false,
               title:""
        },
        dialog2:{
            show:false,
            title:""
        }
      }
    },
    computed: {
        user(){
            return getUser();
        }
    },
    methods: {
        setDialogInfo(cmdItem){
           switch(cmdItem){
               case 'info':
                    this.showInfoList();
                    break;
               case 'logout':
                    this.logout();
                    break;
               case 'editpassword':
                    this.editPassword();
                    break;
           }
        },
        showInfoList(){
          this.dialog2={
                show:true,
                title:"个人信息"
            };
        },
        logout(){
          //清除用户信息
          removeUser();
          //设置vue store
          this.$store.dispatch('clearCurrentState');
          //跳转
          this.$router.push('/login');
        },
        editPassword(){
          this.dialog1={
                show:true,
                title:"修改密码"
            };
        }
    }
};
</script>


<style scoed>
.head-nav {
  width: 100%;
  height: 60px;
  min-width: 600px;
  padding: 5px;
  background: #484848;
  color: #fff;
  border-bottom: 1px solid #1f2d3d;
}
.logo-container {
  line-height: 60px;
  min-width: 400px;
}
.logo {
  height: 50px;
  width: 50px;
  margin-right: 5px;
  vertical-align: middle;
  display: inline-block;
}
.title {
  vertical-align: middle;
  font-size: 30px;
  font-family: "Tahoma";
  font-weight:bolder;
  letter-spacing: 3px;
}
.user {
  line-height: 60px;
  text-align: right;
  float: right;
  padding-right: 10px;
}
.avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  vertical-align: middle;
  display: inline-block;
}
.welcome {
  display: inline-block;
  width: auto;
  vertical-align: middle;
  padding: 0 5px;
}
.name {
  line-height: 20px;
  text-align: center;
  font-size: 14px;
}
.avatarname {
  color: #fff;
  font-weight: bolder;
  font-size: 16px;
  font-family: "Tahoma";
}
.el-dropdown-link{
  color: #fff;
  font-weight: bolder;
  font-size: 16px;
  font-family: "Tahoma";
  
}
.username {
  cursor: pointer;
  margin-right: 5px;
}
.el-dropdown {
  color: #fff;
}
</style>


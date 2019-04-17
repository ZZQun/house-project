<template>
    <div class="dialog2">
        <el-dialog 
            :title="dialog2.title" 
            :visible.sync="dialog2.show"
            :close-on-click-modal='false'
            :close-on-press-escape='false'
            :modal-append-to-body="false"
            width="30%">
            <div class="form">
                <el-form ref="form" :model="formData" label-width="120px" style="margin:10px;width:auto;">
                    <el-form-item label="姓名：">
                        <span>{{this.formData.name}}</span>
                    </el-form-item>
                    <el-form-item label="身份证：">
                        <span>{{this.formData.idCard}}</span>
                    </el-form-item>
                    <el-form-item label="电话号码：">
                        <span>{{this.formData.phone}}</span>
                    </el-form-item>
                    <el-form-item label="系统角色：">
                        <span>{{this.formData.type}}</span>
                    </el-form-item>
                </el-form>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import userApi from '../api/user.js'
export default {
    name: 'infoshow-dialog',
    props: {
        dialog2:Object
    },
    data () {
        return {
            
            formData:{
               name:'',
               idCard:'',
               phone:'',
               type:'',
            }
        }
    },
    created () {
        this.getUserInfo();
    },
    methods: {
      getUserInfo(){
        
        let pojo = {
            id:this.$store.getters.user.jti
        } 
        userApi.getUserInfoByCondition(pojo).then(res =>{
          //查找成功之后的处理
          if(res.data.flag){                            
              this.formData = {                             
                name : res.data.data.name,
                idCard:res.data.data.idCard,
                phone:res.data.data.phone,
                type:res.data.data.type==1?"管理员":"普通用户",
              };
                                      
          }else{
              this.$message({
              message: "查找个人信息失败",
              type: "warning"
              });
          }
        });
      }
    }
};
</script>


<style scoed>

</style>


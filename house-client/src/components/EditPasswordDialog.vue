<template>
    <div class="dialog1">
        <el-dialog 
            :title="dialog1.title" 
            :visible.sync="dialog1.show"
            :close-on-click-modal='false'
            :close-on-press-escape='false'
            :modal-append-to-body="false"
            width="30%"
            >
            <div class="form">
                <el-form ref="form" :model="formData" :rules="form_rules" label-width="120px" style="margin:10px;width:auto;">
                    <el-form-item label="旧密码：" prop="oldPassword">
                        <el-input v-model="formData.oldPassword" type="password"></el-input>
                    </el-form-item>
                    <el-form-item label="新密码：" prop="newPassword1">
                        <el-input v-model="formData.newPassword1" type="password"></el-input>
                    </el-form-item>
                    <el-form-item label="确认新密码：" prop="newPassword2">
                        <el-input v-model="formData.newPassword2" type="password"></el-input>
                    </el-form-item>
                    
                    <el-form-item  class="text_right">
                        <el-button @click="onCancal()">取消</el-button>
                        <el-button type="primary" @click='onSubmit("form")'>提交</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import userApi from '../api/user.js'
import {removeUser} from '../utils/auth'
export default {
    name: 'passwordedit-dialog',
    props: {
        dialog1:Object
    },
    data () {
        var validatePass2 = (rule, value, callback) => {
            if (value !== this.formData.newPassword1) {
                callback(new Error("两次输入密码不一致!"));
            } else {
                callback();
            }
        };
        return {
            
            formData:{
                oldPassword:'',
                newPassword1:'',
                newPassword2:''
            },
            form_rules:{
                oldPassword:[
                    { required: true, message: '旧密码不能为空', trigger: 'blur' }
                ],
                 newPassword1:[
                    { required: true, message: '新密码不能为空', trigger: 'blur' },
                    { min: 8, max: 30, message: "长度在 8 到 30 个字符", trigger: "blur" }
                ],
                 newPassword2:[
                    { required: true, message: '确认新密码不能为空', trigger: 'blur' },
                    { min: 8, max: 30, message: "长度在 8 到 30 个字符", trigger: "blur" },
                    { validator: validatePass2, trigger: "blur" }
                ]
            } 
        
        }
    },
    methods: {
        onCancal(){
            //清空表单数据
            this.$refs['form'].resetFields();
            //表单关闭
            this.dialog1.show = false;
        },
        onSubmit(form){
            this.$refs[form].validate(valid => {
                if (valid) {
                    let pojo = {
                    userId:this.$store.getters.user.jti,
                    oldPassword:this.formData.oldPassword,
                    newPassword:this.formData.newPassword1,
                    };

                    userApi.editPassword(pojo).then(res =>{
                        //修改成功之后的处理
                        if(res.data.flag){
                            this.$message({
                            message: "密码修改成功，请重新登录",
                            type: "success"
                            });
                            //清空表单数据
                            this.$refs['form'].resetFields();
                            //表单关闭
                            this.dialog1.show = false;
                            //清除用户信息
                            removeUser();
                            //设置vue store
                            this.$store.dispatch('clearCurrentState');
                            //跳转
                            this.$router.push('/login');
                        }else{
                            this.$message({
                            message: res.data.message,
                            type: "warning"
                            });
                            
                        }
                        
                    });
                    
                    
                }
            });
        }
    }
};
</script>


<style scoed>

</style>


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
                <el-form ref="form" :model="formData" :rules="form_rules" label-width="120px" style="margin:10px;width:auto;">
                    <el-form-item label="姓名：" prop="name">
                        <el-input v-model="formData.name" type="name"></el-input>
                    </el-form-item>
                    <el-form-item label="身份证：" prop="idCard">
                        <el-input v-model="formData.idCard" type="idCard"></el-input>
                    </el-form-item>
                    <el-form-item label="电话号码：" prop="phone">
                        <el-input v-model="formData.phone" type="phone"></el-input>
                    </el-form-item>
                    <el-form-item label="系统角色：" prop="type">
                        <el-select v-model="formData.type" :disabled="true">
                            <el-option v-for="(formsystemRole,index) in format_systemRole_list" :key="index" :label="formsystemRole" :value="formsystemRole" ></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item  class="text_right">
                        <el-button @click="dialog2.show = false">取消</el-button>
                        <el-button type="primary" @click='onSubmit("form")'>提交</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-dialog>
    </div>
</template>

<script>

import userApi from '@/api/user'
export default {
    name: 'useredit-dialog',
    props: {
        dialog2:Object,
        formData:Object
    },
    data () {
        return {         
            format_systemRole_list:[
                "管理员","普通用户"
            ],

            form_rules:{
                name:[
                    { required: true, message: '请输入姓名', trigger: 'blur' }
                ],
                idCard:[
                    { required: true, message: '请输入身份证', trigger: 'blur' }
                ],
                phone:[
                    { required: true, message: '请输入电话号码', trigger: 'blur' }
                ],
                type:[
                    { required: true, message: '请选择系统角色', trigger: 'blur' }
                ]
            } 
        }
    },
    created () {
        
    },
    methods: {
        
        onSubmit(form){
            this.$refs[form].validate(valid => {
                if (valid) {
                        
                    let pojo = {
                    id:this.formData.id,
                    name:this.formData.name,
                    idCard:this.formData.idCard,
                    phone:this.formData.phone
                    };
                    
                    userApi.updateUser(pojo).then(res =>{
                        //修改成功之后的处理
                        if(res.data.flag){
                            this.$message({
                            message: "修改成功",
                            type: "success"
                            });
                            this.dialog2.show = false;
                            this.$emit('update');
                        }else{
                            this.$message({
                            message: "修改失败",
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


<template>
    <div class="dialog1">
        <el-dialog 
            :title="dialog1.title" 
            :visible.sync="dialog1.show"
            :close-on-click-modal='false'
            :close-on-press-escape='false'
            :modal-append-to-body="false"
            :before-close='closeDialog'
            width="30%">
            <div class="form">
                <el-form ref="form" :model="formData" :rules="form_rules" label-width="120px" style="margin:10px;width:auto;">
                    <el-form-item label="姓名：" prop="name">
                        <el-input v-model="formData.name"></el-input>
                    </el-form-item>
                    <el-form-item label="身份证：" prop="idCard">
                        <el-input v-model="formData.idCard"></el-input>
                    </el-form-item>
                    <el-form-item label="电话号码：" prop="phone">
                        <el-input v-model="formData.phone"></el-input>
                    </el-form-item>                   
                    <el-form-item label="系统角色：" prop="type">
                        <el-select v-model="formData.type">
                            <el-option v-for="(formsystemRole,index) in format_systemRole_list" :key="index" :label="formsystemRole" :value="formsystemRole"></el-option>
                        </el-select>
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
import userApi from '@/api/user'
export default {
    name: 'useradd-dialog',
    props: {
        dialog1:Object
    },
    data () {
        return {
            formData:{
                name:'',
                idCard:'',
                phone:'',
                type:'',
            },
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
                    name:this.formData.name,
                    idCard:this.formData.idCard,
                    phone:this.formData.phone,
                    type:this.formData.type=="管理员"?1:2,                   
                    };

                    userApi.addUser(pojo).then(res =>{
                        //添加成功之后的处理
                        if(res.data.flag){
                            this.$message({
                            message: "添加成功",
                            type: "success"
                            });
                            //清空表单数据
                            this.$refs['form'].resetFields();
                            //表单关闭
                            this.dialog1.show = false;
                            //初始化用户列表
                            this.$emit('update');
                        }else{
                            this.$message({
                            message: "添加失败",
                            type: "warning"
                            });
                            
                        }
                        
                    });
                    
                    
                }
            });
        },
        closeDialog(done){ 
            if (this.$refs['form'] !== undefined) {
                        this.$refs['form'].resetFields();
                    } 
            done();
        },
    }
};
</script>


<style scoed>

</style>


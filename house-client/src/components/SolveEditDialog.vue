<template>
    <div class="dialog2">
        <el-dialog 
            :title="dialog2.title" 
            :visible.sync="dialog2.show"
            :close-on-click-modal='false'
            :close-on-press-escape='false'
            :modal-append-to-body="false"
            :before-close='closeDialog'
            width="30%">
            <div class="form">
                <el-form ref="form" :model="formData" :rules="form_rules" label-width="120px" style="margin:10px;width:auto;">
                    <el-form-item label="姓名：" prop="name" >
                        <el-input v-model="formData.name" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="地址：" prop="address">
                        <el-input v-model="formData.address" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="申报日期：" prop="date">
                        <el-date-picker v-model="formData.date" type="date" :disabled="true">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="详情：" prop="detail">
                        <el-input v-model="formData.detail" :disabled="true" ></el-input>
                    </el-form-item>
                    <el-form-item label="状态：" prop="status">
                        <el-select v-model="formData.status">
                            <el-option v-for="(formStatus,index) in format_status_list" :key="index" :label="formStatus" :value="formStatus" ></el-option>
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
import solveApi from '../api/solve.js'
export default {
    name: 'useredit-dialog',
    props: {
        dialog2:Object,
        formData:Object
    },
    data () {
        return {         
            format_status_list:[
                "已处理","未处理"
            ],
            form_rules:{
                status:[
                    { required: true, message: '请选择状态', trigger: 'blur' }
                ],
            } 
        }
    },
    created () {
         this.getInfo()
    },
    methods: {
        getInfo(){
        },
        closeDialog(done){ 
            if (this.$refs['form'] !== undefined) {
                        this.$refs['form'].resetFields();
                    } 
            done();
        },
        onSubmit(form){
            this.$refs[form].validate(valid => {
                if (valid) {
                    let pojo = {
                        id:this.formData.id,
                        status:this.formData.status, 
                    }
                    
                    solveApi.updateSolve(pojo).then(res =>{
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


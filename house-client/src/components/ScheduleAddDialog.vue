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
                    
                    <el-form-item label="详情：" prop="content">
                        <el-input v-model="formData.content" type="textarea"></el-input>
                    </el-form-item>
                    <el-form-item label="发布天数：" prop="time">
                        <el-input-number v-model="formData.time" controls-position="right" :min="1" :max="9999" ></el-input-number>
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
import scheduleApi from '../api/schedule.js'
export default {
    name: 'scheduleadd-dialog',
    props: {
        dialog1:Object
    },
    data () {
        var checkNum = (rule,value,callback) => {
                
            if(!Number.isInteger(value)) {
                callback(new Error('请输入整数'))
            }else{
                if(value < 0) {
                    callback(new Error('不能小于0'))
                }else if(value > 20){
                    callback(new Error('不能大于20'))
                }else {
                    callback()
                }
                
            }
        };
        return {
            
            formData:{
                time:1,
                content:'',
            },
            form_rules:{
                
                content:[
                    { required: true, message: '请输入新闻公告详情', trigger: 'blur' }
                ],
                time:[
                    { required: true, message: '请输入持续天数', trigger: 'blur' },
                    {validator:checkNum,trigger:'blur'}
                ],
                
            } 
        
        }
    },
    created () {
        this.getInfo()
    },
    methods: {
           
        getInfo(){
            this.formData.time = 1
        },
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
                        time:this.formData.time,
                        content:this.formData.content,           
                        };
    
                    scheduleApi.addSchedule(pojo).then(res =>{
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


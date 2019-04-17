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
                    <el-form-item label="地址：" prop="address">
                        <el-select v-model="formData.address" filterable>
                            <el-option v-for="(formAddress,index) in format_address_list" :key="index" :label="formAddress" :value="index"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="详情：" prop="detail">
                        <el-input v-model="formData.detail" ></el-input>
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
import solveApi from '../api/solve.js'
import houseApi from '../api/house.js'
export default {
    name: 'useradd-dialog',
    props: {
        dialog1:Object
    },
    data () {
        return {
            formData:{
                address:'',
                detail:''
            },
            format_address_list:[
            ],
            format_houseId_list:[
            ],
            format_houseRenterName_list:[
            ],
            format_houseRenterId_list:[
            ],
            form_rules:{
                address:[
                    { required: true, message: '请选择地址', trigger: 'change' }
                ],
                detail:[
                    { required: true, message: '请输入详情', trigger: 'blur' }
                ],
                
            } 
        
        }
    },
    created () {
        this.getInfo()
    },
    methods: {
        getInfo(){
            let pojo = {
                userlist_Id:this.$store.getters.user.jti
            }
            
            houseApi.getHouseListByCondition(pojo).then(res =>{
                if(res.data.flag == true){
                    var userName = new Array();
                    var userId = new Array();
                    var address = new Array();
                    var houseId = new Array();
                    for(var i = 0;i < res.data.data.length;i++){
                        userName[i] = res.data.data[i].userlist_Name;
                        userId[i] = res.data.data[i].userlist_Id;
                        address[i] = res.data.data[i].address;
                        houseId[i] = res.data.data[i].houseId;
                    }
                    this.format_houseRenterName_list = userName;
                    this.format_houseRenterId_list = userId;
                    this.format_houseId_list = houseId;
                    this.format_address_list = address;
                }else{                    
                    this.$message({
                    message: '获取房屋列表失败',
                    type: 'warning'
                    });
                }
            });
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
                        address:this.format_address_list[this.formData.address],
                        detail:this.formData.detail,
                        userlist_id:this.format_houseRenterId_list[this.formData.address],
                        name:this.format_houseRenterName_list[this.formData.address],
                        houseId:this.format_houseId_list[this.formData.address]                    
                    };

                    solveApi.addSolve(pojo).then(res =>{
                        //添加成功之后的处理
                        if(res.data.flag){
                            this.$message({
                            message: "申报成功",
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
                            message: "申报失败",
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


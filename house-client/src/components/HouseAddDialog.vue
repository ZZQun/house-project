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
                        <el-input v-model="formData.address"></el-input>
                    </el-form-item>
                    <el-form-item label="价格：" prop="price">
                        <el-input v-model="formData.price"></el-input>
                    </el-form-item>
                    <el-form-item label="状态：" prop="status">
                        <el-select v-model="formData.status">
                            <el-option v-for="(formStatus,index) in format_status_list" :key="index" :label="formStatus" :value="formStatus"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="详情：" prop="detail">
                        <el-input v-model="formData.detail" type="textarea" :rows="4"></el-input>
                    </el-form-item>                   
                    <el-form-item label="租客：" prop="renter">
                        <el-select v-model="formData.renter" filterable>
                            <el-option v-for="(formStatus,index) in format_renterName_list" :key="index" :label="formStatus" :value="index"></el-option>
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
import userApi from '../api/user.js'
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
                price:'',
                status:'',
                detail:'',
                renter:''
            },
            format_status_list:[
                "已出租","未出租"
            ],
            format_renterName_list:[
            ],
            format_renterId_list:[
            ],
            form_rules:{
                address:[
                    { required: true, message: '请输入地址', trigger: 'blur' }
                ],
                price:[
                    { required: true, message: '请输入价格', trigger: 'blur' }
                ],
                status:[
                    { required: true, message: '请选择状态', trigger: 'blur' }
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
            this.formData.status = "未出租";
            this.formData.renter = 0;
            userApi.getAllUserList().then(res =>{
                if(res.data.flag == true){
                    var userName = new Array();
                    userName[0] = "无"
                    var userId = new Array();
                    userId[0] = 0
                    for(var i = 0;i < res.data.data.length;i++){
                        userName[i+1] = res.data.data[i].name;
                        userId[i+1] = res.data.data[i].id;
                    }
                    this.format_renterName_list = userName;
                    this.format_renterId_list = userId
                }else{                    
                    this.$message({
                    message: '获取租客列表失败',
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
                    let pojo
                    if(this.formData.renter == 0){
                        pojo = {
                        address:this.formData.address,
                        price:this.formData.price,
                        status:this.formData.status,
                        detail:this.formData.detail,
                        userlist_Id:null,
                        userlist_Name:null             
                        };
                    }else{
                        pojo = {
                        address:this.formData.address,
                        price:this.formData.price,
                        status:this.formData.status,
                        detail:this.formData.detail,
                        userlist_Id:this.format_renterId_list[this.formData.renter],
                        userlist_Name:this.format_renterName_list[this.formData.renter]                
                        };
                    }               
                    
                    houseApi.addHouse(pojo).then(res =>{
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
                            //初始化房屋信息列表
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


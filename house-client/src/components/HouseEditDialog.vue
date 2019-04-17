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
                    <el-form-item label="地址：" prop="address">
                        <el-input v-model="formData.address" type="address"></el-input>
                    </el-form-item>
                    <el-form-item label="价格：" prop="price">
                        <el-input v-model="formData.price"></el-input>
                    </el-form-item>
                    
                    <el-form-item label="状态：" prop="status">
                        <el-select v-model="formData.status">
                            <el-option v-for="(formStatus,index) in format_status_list" :key="index" :label="formStatus" :value="formStatus" ></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="详情：" prop="detail" >
                        <el-input v-model="formData.detail" type="textarea" :rows="4"></el-input>
                    </el-form-item>
                    <el-form-item label="租客：" prop="renter">
                        <el-select v-model="formData.userlist_Name" filterable > 
                            <el-option v-for="(formRenter,index) in format_renterName_list" :key="index" :label="formRenter" :value="index"></el-option>
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
import houseApi from '../api/house.js'
import userApi from '../api/user.js'
export default {
    name: 'useredit-dialog',
    props: {
        dialog2:Object,
        formData:Object
    },
    data () {
        return {       
            index:'',  
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
        closeDialog(done){ 
            if (this.$refs['form'] !== undefined) {
                        this.$refs['form'].resetFields();
                    } 
            done();
        },
        onSubmit(form){
            this.$refs[form].validate(valid => {
                if (valid) {
                    
                    let pojo
                    
                    if(isNaN(this.formData.userlist_Name)){
                        if(this.formData.userlist_Name == "无"){
                            pojo = {
                            houseId:this.formData.id,
                            address:this.formData.address,
                            price:this.formData.price,
                            status:this.formData.status,
                            detail:this.formData.detail,
                            userlist_Id:null,
                            userlist_Name:null             
                        };
                        }else{
                            for(var i = 0;i < this.format_renterId_list.length;i++){
                                if(this.format_renterId_list[i] == this.formData.userlist_Id){
                                    this.index = i
                                    break
                                }
                            }
                            pojo = {
                            houseId:this.formData.id,
                            address:this.formData.address,
                            price:this.formData.price,
                            status:this.formData.status,
                            detail:this.formData.detail,
                            userlist_Id:this.format_renterId_list[this.index],
                            userlist_Name:this.format_renterName_list[this.index]                
                            };
                        }
                    }else{
                        if(this.formData.userlist_Name == 0){
                        pojo = {
                        houseId:this.formData.id,
                        address:this.formData.address,
                        price:this.formData.price,
                        status:this.formData.status,
                        detail:this.formData.detail,
                        userlist_Id:null,
                        userlist_Name:null             
                        };
                    }else{
                        pojo = {
                        houseId:this.formData.id,
                        address:this.formData.address,
                        price:this.formData.price,
                        status:this.formData.status,
                        detail:this.formData.detail,
                        userlist_Id:this.format_renterId_list[this.formData.userlist_Name],
                        userlist_Name:this.format_renterName_list[this.formData.userlist_Name]                
                        };
                    }   
                    }
                                
                    
                    houseApi.updateHouse(pojo).then(res =>{
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


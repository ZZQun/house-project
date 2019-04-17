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
                        <el-select v-model="formData.address" filterable @change="currentSel">
                            <el-option v-for="(formAddress,index) in format_address_list" :key="index" :label="formAddress" :value="index"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="金额：" prop="price">
                        <el-input v-model="formData.price" ></el-input>
                    </el-form-item>
                    <el-form-item label="状态：" prop="status">
                        <el-select v-model="formData.status">
                            <el-option v-for="(formStatus,index) in format_status_list" :key="index" :label="formStatus" :value="formStatus"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="应缴纳日期：" prop="date">
                        <el-date-picker v-model="formData.date" type="date" placeholder="选择日期">
                        </el-date-picker>
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
import houseApi from '../api/house.js'
import paidApi from '../api/paid.js'
import { debug } from 'util';

export default {
    name: 'useradd-dialog',
    props: {
        dialog1:Object
    },
    data () {
        var validate1 = (rule, value, callback) => {
            if (value && !(/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/).test(value)) {
                callback(new Error("最多输入两位小数"));
            } else {
                callback();
            }
        };
        return {
            formData:{
                address:'',
                price:'',
                date:'',
                status:''
            },
            format_status_list:[
                "未缴","已缴"
            ],
            format_address_list:[
            ],
            format_houseId_list:[
            ],
            format_houseRenterName_list:[
            ],
            format_houseRenterId_list:[
            ],
            format_housePrice_list:[
            ],
            form_rules:{
                address:[
                    { required: true, message: '请选择地址', trigger: 'blur' }
                ],
                price:[
                    { required: true, message: '请输入金额', trigger: 'blur' },
                    { validator: validate1, trigger: "blur" }
                ],
                date:[
                    { required: true, message: '请选择应缴纳日期', trigger: 'blur' }
                ],
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
        currentSel(selVal){
            console.log(selVal)
            this.formData.price = this.format_housePrice_list[selVal]
            this.dialogVisible = true;
        },
        getInfo(){
            this.formData.status = "未缴";
            let pojo = {
                status:"已出租"
            }

            houseApi.getHouseListByCondition(pojo).then(res =>{
                if(res.data.flag == true){
                    
                    var userName = new Array();
                    var userId = new Array();
                    var address = new Array();
                    var houseId = new Array();
                    var price = new Array();
                    for(var i = 0;i < res.data.data.length;i++){
                        userName[i] = res.data.data[i].userlist_Name;
                        userId[i] = res.data.data[i].userlist_Id;
                        address[i] = res.data.data[i].address;
                        houseId[i] = res.data.data[i].houseId;
                        price[i] = res.data.data[i].price;
                    }
                    
                    this.format_houseRenterName_list = userName;
                    this.format_houseRenterId_list = userId;
                    this.format_houseId_list = houseId;
                    this.format_address_list = address;
                    this.format_housePrice_list = price;
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
                    let pojo = {
                        address:this.format_address_list[this.formData.address],
                        price:this.formData.price,
                        status:this.formData.status,
                        date:this.formData.date,
                        userlist_id:this.format_houseRenterId_list[this.formData.address],
                        name:this.format_houseRenterName_list[this.formData.address],
                        houseId:this.format_houseId_list[this.formData.address]             
                        };                                
                    
                    paidApi.addPaid(pojo).then(res =>{
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
                            //初始化租金列表
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


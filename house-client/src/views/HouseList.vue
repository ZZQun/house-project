<template>
    <div class="fillcontain">
        <div>
            <el-form :inline="true" ref="add_data" :model="search_data">
                <el-form-item label="地址：">
                    <el-input v-model="search_data.search_address"></el-input>
                </el-form-item>
                <el-form-item label="状态：">
                    <el-select v-model.trim="search_data.search_status">
                        <el-option v-for="(formteam,index) in format_status_list" :key="index" :label="formteam" :value="formteam"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item class="btnSearch">
                    <el-button type="primary " size="small" icon="el-icon-search" @click="handleSearch()">筛选</el-button>
                </el-form-item>
                <el-form-item class="btnRight">
                    <el-button type="primary " size="small" icon="el-icon-plus" @click="handleAdd()">添加房屋信息</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="table_container">
            <el-table v-if="tableData.length > 0" :data="tableData" style="width: 100%" max-height="450" border >
                <el-table-column type="index" label="序号" align="center" width="150">
                </el-table-column>
                <el-table-column prop="address" label="地址" align="center" width="400">
                </el-table-column>
                <el-table-column prop="price" label="价格" align="center" width="200">
                </el-table-column>
                <el-table-column prop="status" label="状态" align="center" width="200">
                </el-table-column>
                <el-table-column prop="userlist_Name" label="租客" align="center" width="200">
                </el-table-column>
                <el-table-column label="操作" prop="operation" align="center">
                    <template slot-scope="scope">
                        <el-button
                        size="small"
                        type="success"
                        icon="el-icon-edit"
                        @click="handleEdit(scope.$index, scope.row)">修改</el-button>
                        <el-button
                        size="small"
                        type="danger"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!--分页-->
             <el-row>
                <el-col :span="24">
                    <div class="pagination">
                        <el-pagination
                            v-if='paginations.total > 0'
                            :page-sizes="paginations.page_sizes"
                            :page-size="paginations.page_size"
                            :layout="paginations.layout"
                            :total="paginations.total"
                            :current-page.sync='paginations.page_index'
                            @current-change='handleCurrentChange'
                            @size-change='handleSizeChange'>
                        </el-pagination>
                    </div>
                </el-col>
            </el-row>

        </div>
        <HouseAddDialog :dialog1="dialog1" @update="getProfile"></HouseAddDialog>
        <HouseEditDialog :dialog2="dialog2" @update="getProfile" :formData="Data"></HouseEditDialog>
    </div>
        
</template>

<script>
import houseApi from '../api/house.js'
import HouseAddDialog from '../components/HouseAddDialog'
import HouseEditDialog from '../components/HouseEditDialog'
export default {
    name:"houseList",
    components: {
        HouseAddDialog,
        HouseEditDialog
    },
    data () {
        return {
            //筛选条件数据
            search_data:{
                search_address:'',
                search_status:''
            },
            //需要给分页组件传的信息
            paginations: {
                page_index: 1, // 当前位于哪页
                total: 0, // 总数
                page_size: 5, // 1页显示多少条
                page_sizes: [5, 10, 15, 20], //每页显示多少条
                layout: "total, sizes, prev, pager, next, jumper" // 翻页属性
            },
            
           tableData:[],
           allTableData: [],
           dialog1:{
               show:false,
               title:""
           },
           dialog2:{
               show:false,
               title:""
           },
           Data:{
                id:'',
                address:'',
                price:'',
                status:'',
                detail:'',
            },
            format_status_list:[
                "全部","未出租","已出租"
            ]
        };
    },
    created () {
        this.getProfile();
    },
    methods: {
        getProfile(){
            this.search_data.search_status = "全部"

            houseApi.getAllHouseList().then(res =>{
                if(res.data.flag == true){
                    this.allTableData = res.data.data;
                    // 设置分页数据
                    this.setPaginations();
                }else{
                    this.$message({
                    message: '获取房屋信息列表失败',
                    type: 'warning'
                    });
                }
            })
        },
        handleSearch(){
            let pojo;
            if(this.search_data.search_status == "全部"){
                pojo = {
                address:this.search_data.search_address,  
                status:''             
                }
            }else{
                pojo = {
                address:this.search_data.search_address,  
                status:this.search_data.search_status             
                }
            }
            
            
            
            houseApi.getHouseListByCondition(pojo).then(res =>{
                if(res.data.flag == true){
                    this.$message({
                    message: '筛选成功',
                    type: 'success'
                    });
                    this.allTableData = res.data.data;
                    // 设置分页数据
                    this.setPaginations();
                }else{
                    this.$message({
                    message: '按条件获取房屋信息列表失败',
                    type: 'warning'
                    });
                }
            })
        },
        handleEdit(index,row){
            this.Data=''
            this.Data={
                id:row.houseId,
                address:row.address,
                price:row.price,
                status:row.status,
                detail:row.detail,
                userlist_Id:row.userlist_Id,
                userlist_Name:row.userlist_Name==null?"无":row.userlist_Name,
            };
            
            this.dialog2={
                show:true,
                title:"修改房屋出租信息"
            };
        },
        handleDelete(index,row){
            this.$confirm('确认删除?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).then(() => {
                    houseApi.deleteHouse(row.houseId).then(res =>{
                        if(res.data.flag == true){
                            this.$message({
                            message: '删除成功',
                            type: 'success'
                            });
                            this.getProfile();
                        }else{
                            this.$message({
                            message: '删除失败',
                            type: 'warning'
                        });
                    }
                })
            })  
        },
        handleAdd(){
            this.dialog1={
                show:true,
                title:"添加房屋出租信息"
            };
        },
        handleCurrentChange(page) {
            // 当前页
            let sortnum = this.paginations.page_size * (page - 1);
            let table = this.allTableData.filter((item, index) => {
                return index >= sortnum;
            });
            // 设置默认分页数据
            this.tableData = table.filter((item, index) => {
                return index < this.paginations.page_size;
            });
            
        },
        handleSizeChange(page_size) {
            // 切换size
            this.paginations.page_index = 1;
            this.paginations.page_size = page_size;
            this.tableData = this.allTableData.filter((item, index) => {
                return index < page_size;
            });
         },
        setPaginations() {
            // 总页数
            this.paginations.total = this.allTableData.length;
            this.paginations.page_index = 1;
            this.paginations.page_size = 5;
            // 设置默认分页数据
            this.tableData = this.allTableData.filter((item, index) => {
                return index < this.paginations.page_size;
            });
        }
    }
}
</script>


<style scoed>
.fillcontain {
  width: 100%;
  height: 100%;
  padding: 16px;
  box-sizing: border-box;
}
.btnRight {
  float: right;
}
.pagination {
  text-align: right;
  margin-top: 10px;
}
</style>


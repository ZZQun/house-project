<template>
    <div class="fillcontain">
        <div>
            <el-form :inline="true" ref="add_data">
                <el-form-item class="btnRight">
                    <el-button type="primary " size="small" icon="el-icon-plus" @click="handleAdd()">添加新闻公告</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="table_container">
            <el-table v-if="tableData.length > 0" :data="tableData" style="width: 100%" max-height="450" border >
                <el-table-column type="index" label="序号" align="center" width="150">
                </el-table-column>
                <el-table-column prop="date" label="时间" align="center" width="400" :formatter="dateFormat">
                </el-table-column>
                <el-table-column prop="content" label="详情" align="center" width="400">
                </el-table-column>
                <el-table-column prop="time" label="公告持续发布天数" align="center" width="200">
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
        <ScheduleAddDialog :dialog1="dialog1" @update="getProfile"></ScheduleAddDialog>
        <ScheduleEditDialog :dialog2="dialog2" @update="getProfile" :formData="Data"></ScheduleEditDialog>
    </div>
        
</template>

<script>
import scheduleApi from '../api/schedule.js'
import ScheduleAddDialog from '../components/ScheduleAddDialog'
import ScheduleEditDialog from '../components/ScheduleEditDialog'
export default {
    name:"scheduleList",
    components: {
        ScheduleAddDialog,
        ScheduleEditDialog
    },
    data () {
        return {
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
                date:'',
                content:'',
                time:''
            },
        };
    },
    created () {
        this.getProfile();
    },
    methods: {
        getProfile(){
            scheduleApi.getAllScheduleList().then(res =>{
                if(res.data.flag == true){
                    this.allTableData = res.data.data;
                    // 设置分页数据
                    this.setPaginations();
                }else{
                    this.$message({
                    message: '获取新闻公告信息列表失败',
                    type: 'warning'
                    });
                }
            })
        },
        handleEdit(index,row){
            this.Data=''
            this.Data={
                id:row.id,
                date:row.date,
                content:row.content,
                time:row.time
            };
            this.dialog2={
                show:true,
                title:"修改新闻公告信息"
            };
        },
        handleDelete(index,row){
            this.$confirm('确认删除?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).then(() => {
                    scheduleApi.deleteSchedule(row.id).then(res =>{
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
                title:"添加新闻公告信息"
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
        },
        dateFormat:function(row, column) { 
            var date = row[column.property]; 
            if (date == undefined) { 
            return ""; 
            } 
            return this.$moment(date).format("YYYY年MM月DD日"); 
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


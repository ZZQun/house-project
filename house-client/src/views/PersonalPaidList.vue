<template>
    <div class="fillcontain">
        <div class="table_container">
            <el-table :data="tableData" style="width: 100%" max-height="450" border >
                <el-table-column type="index" label="序号" align="center" width="150">
                </el-table-column>
                <el-table-column prop="name" label="姓名" align="center" width="200">
                </el-table-column>
                <el-table-column prop="address" label="地址" align="center" width="450">
                </el-table-column>
                <el-table-column prop="price" label="金额" align="center" width="200">
                </el-table-column>
                <el-table-column prop="date" label="应缴纳日期" align="center" width="200" :formatter="dateFormat">
                </el-table-column>
                <el-table-column prop="payDate" label="实际缴纳日期" align="center" width="200" :formatter="dateFormat">
                </el-table-column>
                <el-table-column prop="status" label="状态" align="center">
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
    </div>
        
</template>

<script>
import paidApi from '../api/paid.js'
export default {
    name:"paidList",
    components: {
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
           
        };
    },
    created () {
        this.getProfile();
    },
    methods: {
        getProfile(){

            let pojo = {
                    userlist_id:this.$store.getters.user.jti            
                }
            
            paidApi.getPaidListByCondition(pojo).then(res =>{
                if(res.data.flag == true){
                    
                    this.allTableData = res.data.data;
                    // 设置分页数据
                    this.setPaginations();
                }else{
                    this.$message({
                    message: '获取租金信息列表失败',
                    type: 'warning'
                    });
                }
            })
        },
        
        
        handleAdd(){
            this.dialog1={
                show:true,
                title:"添加租金信息"
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


<template>
    <el-row class="menu_page">
         <el-col>
             <el-menu
                :default-active="activeIndex"
                mode="vertical"
                background-color="#484848"
                text-color="#fff"
                active-text-color="#101010" 
                class="el-menu-vertical-demo">
                <router-link to="/home">
                    <el-menu-item index="0">
                        <i class="fa fa-margin el-icon-menu"></i>
                        <span slot="title">首页</span>
                    </el-menu-item>
                </router-link>
                <template  v-for="item in items" >
                    <el-submenu v-if="item.children" :index="item.path" :key="item.path">
                        <template slot="title">
                            <i :class="'fa fa-margin '+item.icon"></i>
                            <span slot="title">{{item.name}}</span>
                        </template>
                        <router-link v-for="(citem,cindex) in item.children" 
                            :to="citem.path" :key="cindex">
                            <el-menu-item 
                                :index='citem.path'>
                                <span slot="title">{{citem.name}}</span>
                            </el-menu-item> 
                        </router-link>
                    </el-submenu>
                </template>
             </el-menu>
         </el-col>
    </el-row>
</template>

<script>

export default {
    name: 'left-menu',
    created () {
        this.getInfo()
    },
    data () {
        return {
            items:[             
            ]
        }
    },
    computed: {
        activeIndex(){
        return this.$route.path.replace('/','')
        }
    },
    methods: {
        getInfo(){
            if(this.$store.getters.user.roles == 'admin'){
                this.items = [
                {
                    icon: 'el-icon-share',
                    name: '房屋信息管理',
                    path: 'house',
                    children: [{ path: 'houselist',name: '房屋信息操作'}]
                },
                 {
                    icon: 'el-icon-goods',
                    name: '租金管理',
                    path: 'paid',
                    children: [{ path: 'paidlist',name: '租金操作'}]
                },
                {
                    icon: 'el-icon-printer',
                    name: '故障管理',
                    path: 'solve',
                    children: [{ path: 'solvelist',name: '故障处理'}]
                },
                {
                    icon: 'el-icon-view',
                    name: '新闻公告管理',
                    path: 'schedule',
                    children: [{ path: 'schedulelist',name: '新闻公告操作'}]
                },
                {
                    icon: 'el-icon-phone',
                    name: '用户管理',
                    path: 'user',
                    children: [{ path: 'userlist',name: '用户操作'}]
                },
            ]
            }else{
                this.items = [
                {
                    icon: 'el-icon-share',
                    name: '房屋信息管理',
                    path: 'house',
                    children: [{ path: 'personalHouseList',name: '房屋信息查看'}]
                },
                 {
                    icon: 'el-icon-goods',
                    name: '租金管理',
                    path: 'paid',
                    children: [{ path: 'personalPaidList',name: '租金查看'}]
                },
                {
                    icon: 'el-icon-printer',
                    name: '故障管理',
                    path: 'solve',
                    children: [{ path: 'personalSolveList',name: '故障申报'}]
                },
                {
                    icon: 'el-icon-view',
                    name: '新闻公告管理',
                    path: 'schedule',
                    children: [{ path: 'personalScheduleList',name: '新闻公告查看'}]
                }
                ]
                
            }
        }
    }
};
</script>


<style scoed>
.menu_page {
  position: fixed;
  top: 71px;
  left: 0;
  min-height: 100%;
  background-color: #484848;
  z-index: 99;
}
.el-menu {
  border: none;
}
.fa-margin {
  margin-right: 5px;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 180px;
  min-height: 400px;
}
.el-menu-vertical-demo {
  width: 35px;
}
.el-submenu .el-menu-item {
  min-width: 180px;
}

.hiddenDropdown,
.hiddenDropname {
  display: none;
}
a {
  text-decoration: none;
}
</style>


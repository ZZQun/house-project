import Vue from 'vue'
import Router from 'vue-router'
import Index from './views/index.vue'
import NotFound from './views/404.vue'
import Login from './views/Login.vue'
import { getUser } from './utils/auth';
import Home from './views/Home.vue'
import UserList from './views/UserList.vue'
import HouseList from './views/HouseList.vue'
import PaidList from './views/PaidList.vue'
import SolveList from './views/SolveList.vue'
import ScheduleList from './views/ScheduleList.vue'
import PersonalPaidList from './views/PersonalPaidList.vue'
import PersonalHouseList from './views/PersonalHouseList.vue'
import PersonalScheduleList from './views/PersonalScheduleList.vue'
import PersonalSolveList from './views/PersonalSolveList.vue'





Vue.use(Router)

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      redirect: '/index',
    },
    {
      path: '/index',
      name: 'index',
      component: Index,
      children:[
        {
          path:'',
          component: Home
        },
        {
          path:'/home',
          name: 'home',
          component: Home
        },
        {
          path:'/userlist',
          name: 'userlist',
          component: UserList
        },
        {
          path:'/houselist',
          name: 'houselist',
          component: HouseList
        },
        {
          path:'/paidlist',
          name: 'paidlist',
          component: PaidList
        },
        {
          path:'/solvelist',
          name: 'solvelist',
          component: SolveList
        },
        {
          path:'/schedulelist',
          name: 'schedulelist',
          component: ScheduleList
        },
        {
          path:'/personalPaidList',
          name: 'personalPaidList',
          component: PersonalPaidList
        },
        {
          path:'/personalHouseList',
          name: 'personalHouseList',
          component: PersonalHouseList
        },
        {
          path:'/personalScheduleList',
          name: 'personalScheduleList',
          component: PersonalScheduleList
        },
        {
          path:'/personalSolveList',
          name: 'personalSolveList',
          component: PersonalSolveList
        },
      ]
    },
    {
      path: '*',
      name: '/404',
      component:NotFound
    },
    {
      path: '/login',
      name: 'login',
      component:Login
    }
  ]
});

//路由守卫
router.beforeEach((to,from,next) => {
  const isLogin = getUser().token ? true : false;
  if(to.path == "/login" || to.path == "reguster"){
    next();
  }else{
    isLogin ? next() : next('login');
  }
})

export default router;

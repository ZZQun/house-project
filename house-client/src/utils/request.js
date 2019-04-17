import axios from 'axios'
import {getUser} from '@/utils/auth'
// 创建axios实例
const service = axios.create({
    //开发环境
    baseURL: 'http://localhost:9002', // api的base_url

    //上线环境
    baseURL: 'http://47.103.88.133:9002', // api的base_url
    timeout: 30000 ,// 请求超时时间
    headers: { 'Authorization': 'Bearer '+getUser().token  }
  })
export default service
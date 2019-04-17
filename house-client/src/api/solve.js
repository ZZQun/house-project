import request from '@/utils/request'
const api_name = 'solve'
export default{
    getAllSolveList(){
        return request({
            url:`/${api_name}/getallsolvelist`,
            method:'get'
        })
    },
    getSolveListByCondition(pojo){
        return request({
            url:`/${api_name}/getsolvelistbycondition`,
            method:'post',
            data:pojo
        })
    },
    addSolve(pojo){
        return request({
            url:`/${api_name}/addsolve`,
            method:'post',
            data:pojo
        })
    },
    updateSolve(pojo){
        return request({
            url:`/${api_name}/updatesolve`,
            method:'post',
            data:pojo
        })
    },
    deleteSolve(id){
        return request({
            url:`/${api_name}/deletesolve?solveId=`+id,
            method:'delete',
        })
    },
    
}








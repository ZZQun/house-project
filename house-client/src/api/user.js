import request from '@/utils/request'
const api_name = 'user'
export default{
    login(pojo){
        return request({
            url:`/${api_name}/login`,
            method:'post',
            data:pojo
        })
    },
    getAllUserList(){
        return request({
            url:`/${api_name}/getalluserlist`,
            method:'get'
        })
    },
    getUserListByCondition(pojo){
        return request({
            url:`/${api_name}/getuserlistbycondition`,
            method:'post',
            data:pojo
        })
    },
    getUserInfoByCondition(pojo){
        return request({
            url:`/${api_name}/getuserinfobycondition`,
            method:'post',
            data:pojo
        })
    },
    addUser(pojo){
        return request({
            url:`/${api_name}/adduser`,
            method:'post',
            data:pojo
        })
    },
    updateUser(pojo){
        return request({
            url:`/${api_name}/updateuser`,
            method:'post',
            data:pojo
        })
    },
    deleteUser(id){
        return request({
            url:`/${api_name}/deleteuser?userListid=`+id,
            method:'delete',
        })
    },
    editPassword(pojo){
        return request({
            url:`/${api_name}/editpassword`,
            method:'post',
            data:pojo
        })
    },

    
}








import request from '@/utils/request'
const api_name = 'house'
export default{
    getAllHouseList(){
        return request({
            url:`/${api_name}/getallhouselist`,
            method:'get'
        })
    },
    getHouseListByCondition(pojo){
        return request({
            url:`/${api_name}/gethouselistbycondition`,
            method:'post',
            data:pojo
        })
    },
    addHouse(pojo){
        return request({
            url:`/${api_name}/addhouse`,
            method:'post',
            data:pojo
        })
    },
    updateHouse(pojo){
        return request({
            url:`/${api_name}/updatehouse`,
            method:'post',
            data:pojo
        })
    },
    deleteHouse(id){
        return request({
            url:`/${api_name}/deletehouse?houseId=`+id,
            method:'delete',
        })
    },
    
}








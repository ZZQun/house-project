import request from '@/utils/request'
const api_name = 'schedule'
export default{
    getAllScheduleList(){
        return request({
            url:`/${api_name}/getallschedulelist`,
            method:'get'
        })
    },
    getScheduleListByCondition(pojo){
        return request({
            url:`/${api_name}/getschedulelistbycondition`,
            method:'post',
            data:pojo
        })
    },
    addSchedule(pojo){
        return request({
            url:`/${api_name}/addschedule`,
            method:'post',
            data:pojo
        })
    },
    updateSchedule(pojo){
        return request({
            url:`/${api_name}/updateschedule`,
            method:'post',
            data:pojo
        })
    },
    deleteSchedule(id){
        return request({
            url:`/${api_name}/deleteschedule?scheduleId=`+id,
            method:'delete',
        })
    },
    getScheduleInSevenDays(){
        return request({
            url:`/${api_name}/getscheduleinsevendays`,
            method:'get'
        })
    }
}








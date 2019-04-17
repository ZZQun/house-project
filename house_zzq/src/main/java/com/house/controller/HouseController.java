package com.house.controller;

import com.house.common.Result;
import com.house.common.StatusCode;
import com.house.dto.HouseExecution;
import com.house.pojo.HouseList;
import com.house.service.HouseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value="/house")
public class HouseController {

    @Autowired
    private HouseListService houseListService;

    @RequestMapping(value = "/getallhouselist",method = RequestMethod.GET)
    public Result getAllHouseList(){
        List<HouseList> houseList = houseListService.findHouseListByCondition(null,null,null);
        return new Result(true, StatusCode.SUCCESS,"查找房屋信息列表成功",houseList);
    }

    @RequestMapping(value = "/gethouselistbycondition",method = RequestMethod.POST)
    public Result getHouseListByCondition(@RequestBody HouseList houseList){
        return new Result(true, StatusCode.SUCCESS,"按条件查找房屋信息列表成功",houseListService.findHouseListByCondition(houseList.getStatus(),houseList.getAddress(),houseList.getUserlist_Id()));
    }

    @RequestMapping(value="/addhouse",method = RequestMethod.POST)
    public Result addHouse(@RequestBody HouseList houseList){
        HouseExecution he;
        try{
            he = houseListService.addHouse(houseList);
            if(he.isFlag()){
                return new Result(true,StatusCode.SUCCESS,"添加房屋信息成功");
            }else {
                return new Result(false,StatusCode.ERROR,"添加房屋信息失败：" + he.getReason());
            }
        }catch (Exception e){
            return new Result(false,StatusCode.ERROR,"添加房屋信息失败：" + e.toString());
        }
    }

    @RequestMapping(value="/updatehouse",method = RequestMethod.POST)
    public Result updateHouse(@RequestBody HouseList houseList){
        HouseExecution he;
        try{
            he = houseListService.updateHouse(houseList);
            if(he.isFlag()){
                return new Result(true,StatusCode.SUCCESS,"修改房屋信息成功");
            }else {
                return new Result(false,StatusCode.ERROR,"修改房屋信息失败：" + he.getReason());
            }
        }catch (Exception e){
            return new Result(false,StatusCode.ERROR,"修改房屋信息失败：" + e.toString());
        }
    }

    @RequestMapping(value="/deletehouse",method = RequestMethod.DELETE)
    public Result deleteHouse(@RequestParam("houseId")Integer houseId){
        HouseExecution he;
        try{
            he = houseListService.deleteHouse(houseId);
            if(he.isFlag()){
                return new Result(true,StatusCode.SUCCESS,"删除房屋信息成功");
            }else {
                return new Result(false,StatusCode.ERROR,"删除房屋信息失败：" + he.getReason());
            }
        }catch (Exception e){
            return new Result(false,StatusCode.ERROR,"删除房屋信息失败：" + e.toString());
        }
    }


}

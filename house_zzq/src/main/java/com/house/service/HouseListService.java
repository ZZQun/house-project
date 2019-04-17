package com.house.service;

import com.house.dao.HouseListDao;
import com.house.dto.HouseExecution;
import com.house.exception.HouseOperationException;
import com.house.pojo.HouseList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseListService{

    @Autowired
    private HouseListDao houseListDao;

    public List<HouseList> findHouseListByCondition(String status,String address,Integer userListId) {
        return houseListDao.selectHouseListByCondition(status,address,userListId);
    }

    public HouseExecution addHouse(HouseList houseList) {
        if(houseList == null){
            return new HouseExecution(false,"添加房屋信息为空");
        }
        try{
            int effectedNum = houseListDao.insertHouseList(houseList);
            if(effectedNum < 1){
                return new HouseExecution(false,"添加房屋信息失败");
            }
        }catch (Exception e){
            throw new HouseOperationException(e.toString());
        }
        return new HouseExecution(true);
    }

    public HouseExecution deleteHouse(Integer houseId) {
        if(houseId == null){
            return new HouseExecution(false,"删除房屋信息Id为空");
        }
        try{
            int effectedNum = houseListDao.deleteHouseListById(houseId);
            if(effectedNum < 1){
                return new HouseExecution(false,"删除房屋信息失败");
            }
        }catch (Exception e){
            throw new HouseOperationException(e.toString());
        }
        return new HouseExecution(true);
    }

    public HouseExecution updateHouse(HouseList houseList) {
        if(houseList == null){
            return new HouseExecution(false,"更新房屋信息为空");
        }

        try{
            int effectedNum = houseListDao.updateHouseList(houseList);
            if(effectedNum < 1){
                return new HouseExecution(false,"更新房屋信息失败");
            }
        }catch (Exception e){
            throw new HouseOperationException(e.toString());
        }
        return new HouseExecution(true);
    }

    public HouseList findHouseById(Integer id) {
        return houseListDao.selectHouseById(id);
    }

}

package com.house.service;


import com.house.dao.PaidDao;
import com.house.dto.PaidExecution;
import com.house.exception.PaidOperationException;
import com.house.pojo.Paid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PaidService {

    @Autowired
    private PaidDao paidDao;

    public List<Paid> findPaidListByCondition(String status, String name, String address, Integer userlist_id) {
        return paidDao.selectByCondition(status,name,address,userlist_id);
    }

    public PaidExecution addPaid(Paid paid) {
        if(paid == null){
            return new PaidExecution(false,"添加租金信息为空");
        }
        try{
            int effectedNum = paidDao.insertPaid(paid);
            if(effectedNum < 1){
                return new PaidExecution(false,"添加租金信息失败");
            }
        }catch (Exception e){
            throw new PaidOperationException(e.toString());
        }
        return new PaidExecution(true);
    }

    public PaidExecution deletePaid(Integer paidId) {
        if(paidId == null){
            return new PaidExecution(false,"删除租金信息Id为空");
        }
        try{
            int effectedNum = paidDao.deletePaidById(paidId);
            if(effectedNum < 1){
                return new PaidExecution(false,"删除租金信息失败");
            }
        }catch (Exception e){
            throw new PaidOperationException(e.toString());
        }
        return new PaidExecution(true);
    }

    public PaidExecution updatePaid(Paid paid) {
        if(paid == null){
            return new PaidExecution(false,"更新租金信息为空");
        }
        try{
            int effectedNum = paidDao.updatePaid(paid);
            if(effectedNum < 1){
                return new PaidExecution(false,"更新租金信息失败");
            }
        }catch (Exception e){
            throw new PaidOperationException(e.toString());
        }
        return new PaidExecution(true);
    }

    public Paid findPaidById(Integer paidId) {
        return paidDao.selectPaidById(paidId);
    }

}

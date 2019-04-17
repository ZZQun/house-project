package com.house.service;

import com.house.dao.SolveDao;
import com.house.dto.SolveExecution;
import com.house.exception.SolveOperationException;
import com.house.pojo.Solve;
import com.house.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SolveService {

    @Autowired
    private SolveDao solveDao;

    public List<Solve> findSolveListByCondition(String status, String name, String address, Integer userlist_id) {
        return solveDao.selectByCondition(status,name,address,userlist_id);
    }

    public SolveExecution addSolve(Solve solve) {
        if(solve == null){
            return new SolveExecution(false,"添加故障信息为空");
        }
        Date date = DateUtil.dateFormat1(new Date());

        solve.setDate(date);
        solve.setStatus("未处理");
        try{
            int effectedNum = solveDao.insertSolve(solve);
            if(effectedNum < 1){
                return new SolveExecution(false,"添加故障信息失败");
            }
        }catch (Exception e){
            throw new SolveOperationException(e.toString());
        }
        return new SolveExecution(true);
    }

    public SolveExecution deleteSolve(Integer id) {
        if(id == null){
            return new SolveExecution(false,"删除故障信息Id为空");
        }
        try{
            int effectedNum = solveDao.deleteSolveById(id);
            if(effectedNum < 1){
                return new SolveExecution(false,"删除故障信息失败");
            }
        }catch (Exception e){
            throw new SolveOperationException(e.toString());
        }
        return new SolveExecution(true);
    }

    public SolveExecution updateSolve(Solve solve) {
        if(solve == null){
            return new SolveExecution(false,"更新故障信息为空");
        }
        try{
            int effectedNum = solveDao.updateSolve(solve);
            if(effectedNum < 1){
                return new SolveExecution(false,"更新故障信息失败");
            }
        }catch (Exception e){
            throw new SolveOperationException(e.toString());
        }
        return new SolveExecution(true);
    }

    public Solve findSolveById(Integer id) {
        return solveDao.findSolveById(id);
    }
}

package com.house.dao;

import com.house.pojo.Solve;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolveDao {

     List<Solve> selectByCondition(@Param("status") String status,
                                   @Param("name") String name,
                                   @Param("address") String address,
                                   @Param("userlist_id") Integer userlist_id);


     int deleteSolveById(Integer id);

     int insertSolve(Solve solve);

     int updateSolve(Solve solve);

     Solve findSolveById(Integer id);
}

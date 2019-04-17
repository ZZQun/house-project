package com.house.dao;

import com.house.pojo.Paid;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaidDao {

     List<Paid> selectByCondition(@Param("status") String status,
                                  @Param("name") String name,
                                  @Param("address") String address,
                                  @Param("userlist_id") Integer userlist_id);

     int deletePaidById(Integer id);

     int insertPaid(Paid paid);

     int updatePaid(Paid paid);

     Paid selectPaidById(Integer id);
}

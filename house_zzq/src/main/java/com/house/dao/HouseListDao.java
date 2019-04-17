package com.house.dao;


import com.house.pojo.HouseList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseListDao {

     List<HouseList> selectHouseListByCondition(@Param("status") String status,@Param("address") String address,@Param("userListId") Integer userListId);


     HouseList selectHouseById(Integer houseId);

     int deleteHouseListById(Integer houseId);

     int insertHouseList(HouseList houseList);

     int updateHouseList(HouseList houseList);
}

package com.house.dao;

import com.house.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao {

	 User selectByUsernameAndPassword(@Param("username") String username,
									  @Param("password") String password);
	
	 User selectUserById(@Param("id") Integer id);
	
	 int insertUser(User user);
	
	 int deleteUser(int id);

	 int updateUser(User user);
	

}

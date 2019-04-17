package com.house.dao;

import java.util.List;

import com.house.pojo.UserList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface UserListDao {

	 List<UserList> selectUserListByCondition(@Param("name") String name,
									  @Param("id") Integer id);
	
	 int insertUserList(UserList userList);
	
	 int deleteUserList(int id);
	
	 UserList findUserListById(@Param("userListId") Integer userListId);
	
	 int updateUserList(UserList userList);

	 UserList selectUserInfoByCondition(@Param("name") String name, @Param("userId") Integer userId,@Param("id")Integer id);
}

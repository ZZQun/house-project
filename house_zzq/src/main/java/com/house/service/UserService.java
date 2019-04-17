package com.house.service;

import java.util.List;


import com.house.dao.UserDao;
import com.house.dao.UserListDao;
import com.house.dto.UserExecution;
import com.house.exception.UserOperationException;
import com.house.pojo.User;
import com.house.pojo.UserList;
import com.house.vo.PasswordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

	@Autowired
	private UserListDao userListDao;

	@Autowired
	private UserDao userDao;

	public User login(String username, String password) {

		return userDao.selectByUsernameAndPassword(username, password);
	}
	
	public List<UserList> findUserListByCondition(String name, Integer id) {

		return userListDao.selectUserListByCondition(name,id);
	}

	public UserList findUserInfoByCondition(String name, Integer userId,Integer id) {

		return userListDao.selectUserInfoByCondition(name,userId,id);
	}

	public UserExecution updatePassword(PasswordVO passwordVO){
		if(passwordVO == null){
			return new UserExecution(false,"修改的密码信息为空");
		}
		UserList userList = userListDao.selectUserInfoByCondition(null,null,passwordVO.getUserId());
		Integer userId = userList.getUserId();
		User user = userDao.selectUserById(userId);
		if(!passwordVO.getOldPassword().equals(user.getPassword())){
			return new UserExecution(false,"旧密码错误");
		}
		user.setPassword(passwordVO.getNewPassword());
		try{
			int effectedNum = userDao.updateUser(user);
			if(effectedNum < 1){
				return new UserExecution(false,"修改密码失败");
			}
		}catch (Exception e){
			throw new UserOperationException(e.toString());
		}
		return new UserExecution(true);
	}

	@Transactional
	public UserExecution addUserListAndUserAccount(UserList userList) {
		if(userList == null){
			return new UserExecution(false,"添加用户信息为空");
		}
		User user = new User();
		user.setUsername(userList.getPhone());
		user.setPassword(userList.getPhone());
		user.setType(userList.getType());
		try{
			int effectedNum = userDao.insertUser(user);
			if(effectedNum < 1){
				return new UserExecution(false,"添加用户帐号失败");
			}
		}catch (Exception e){
			throw new UserOperationException(e.toString());
		}
		int accountId = user.getId();
		userList.setUserId(accountId);
		try{
			int effectedNum = userListDao.insertUserList(userList);
			if(effectedNum < 1){
				return new UserExecution(false,"添加用户信息失败");
			}
		}catch (Exception e){
			throw new UserOperationException(e.toString());
		}
		return new UserExecution(true);
	}


	public UserExecution updateUserList(UserList userList) {
        if(userList == null){
            return new UserExecution(false,"更新用户信息为空");
        }

        try{
            int effectedNum = userListDao.updateUserList(userList);
            if(effectedNum < 1){
                return new UserExecution(false,"更新用户信息失败");
            }
        }catch (Exception e){
            throw new UserOperationException(e.toString());
        }
        return new UserExecution(true);
	}

    @Transactional
    public UserExecution deleteUser(Integer userListId) {
        if(userListId == null){
            return new UserExecution(false,"删除用户信息Id为空");
        }
        UserList userList = userListDao.findUserListById(userListId);
        Integer userAccountId = userList.getUserId();
        try{
            int effectedNum = userDao.deleteUser(userAccountId);
            if(effectedNum < 1){
                return new UserExecution(false,"删除用户帐号失败");
            }
        }catch (Exception e){
            throw new UserOperationException(e.toString());
        }

        try{
            int effectedNum = userListDao.deleteUserList(userListId);
            if(effectedNum < 1){
                return new UserExecution(false,"删除用户信息失败");
            }
        }catch (Exception e){
            throw new UserOperationException(e.toString());
        }
        return new UserExecution(true);
    }


}

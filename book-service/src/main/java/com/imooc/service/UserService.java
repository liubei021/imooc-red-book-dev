package com.imooc.service;

import com.imooc.bo.UpdatedUserBO;
import com.imooc.pojo.Users;

/**
 * 用户 服务层接口
 */
public interface UserService {

	/**
	 * 判断用户是否存在，如果存在则返回用户信息
	 */
	Users queryMobileIsExist(String mobile);

	/**
	 * 创建用户信息，并且返回用户对象
	 */
	Users createUser(String mobile);

	/**
	 * 根据用户主键查询用户信息
	 */
	Users getUser(String userId);

	/**
	 * 用户信息修改
	 */
	Users updateUserInfo(UpdatedUserBO updatedUserBO);

	/**
	 * 用户信息修改
	 */
	Users updateUserInfo(UpdatedUserBO updatedUserBO, Integer type);

	/**
	 * 根据用户用户账号查询用户
	 */
	Users queryUsersByUsername(String username);

}

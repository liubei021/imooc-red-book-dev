package com.imooc.service;

import com.imooc.mo.MessageMO;

import java.util.List;
import java.util.Map;

public interface MsgService {

	/**
	 * 创建消息
	 */
	void createMsg(String fromUserId, String toUserId, Integer type, Map msgContent);

	/**
	 * 查询消息列表
	 */
	List<MessageMO> queryList(String toUserId, Integer page, Integer pageSize);

}

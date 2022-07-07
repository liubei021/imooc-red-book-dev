package com.imooc.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 粉丝表
 */
@Data
public class Fans {
	@Id
	private String id;
	@Column(name = "vloger_id")
	private String vlogerId;
	@Column(name = "fan_id")
	private String fanId;
	@Column(name = "is_fan_friend_of_mine")
	private Integer isFanFriendOfMine;
}
package com.imooc.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * 用户信息
 */
@Data
public class Users {
	@Id
	private String id;
	private String mobile;
	private String nickname;
	private String username;
	private String password;
	@Column(name = "imooc_num")
	private String imoocNum;
	private String face;
	private Integer sex;
	private Date birthday;
	private String country;
	private String province;
	private String city;
	private String district;
	private String description;
	@Column(name = "bg_img")
	private String bgImg;
	@Column(name = "can_imooc_num_be_updated")
	private Integer canImoocNumBeUpdated;
	@Column(name = "created_time")
	private Date createdTime;
	@Column(name = "updated_time")
	private Date updatedTime;

}
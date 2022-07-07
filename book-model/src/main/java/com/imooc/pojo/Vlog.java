package com.imooc.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * 短视频表
 */
@Data
public class Vlog {
	@Id
	private String id;
	@Column(name = "vloger_id")
	private String vlogerId;
	private String url;
	private String cover;
	private String title;
	private Integer width;
	private Integer height;
	@Column(name = "like_counts")
	private Integer likeCounts;
	@Column(name = "comments_counts")
	private Integer commentsCounts;
	@Column(name = "is_private")
	private Integer isPrivate;
	@Column(name = "created_time")
	private Date createdTime;
	@Column(name = "updated_time")
	private Date updatedTime;

}
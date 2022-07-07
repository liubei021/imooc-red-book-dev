package com.imooc.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * 评论表
 */
@Data
public class Comment {
	@Id
	private String id;
	@Column(name = "vloger_id")
	private String vlogerId;
	@Column(name = "father_comment_id")
	private String fatherCommentId;
	@Column(name = "vlog_id")
	private String vlogId;
	@Column(name = "comment_user_id")
	private String commentUserId;
	private String content;
	@Column(name = "like_counts")
	private Integer likeCounts;
	@Column(name = "create_time")
	private Date createTime;

}
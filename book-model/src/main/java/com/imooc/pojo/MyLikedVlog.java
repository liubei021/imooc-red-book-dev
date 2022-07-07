package com.imooc.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 点赞短视频关联表
 */
@Data
@Table(name = "my_liked_vlog")
public class MyLikedVlog {
    @Id
    private String id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "vlog_id")
    private String vlogId;
}
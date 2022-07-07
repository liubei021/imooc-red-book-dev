package com.imooc.service;

import com.imooc.bo.CommentBO;
import com.imooc.pojo.Comment;
import com.imooc.pojo.Vlog;
import com.imooc.utils.PagedGridResult;
import com.imooc.vo.CommentVO;

public interface CommentService {

	/**
	 * 发表评论
	 */
	CommentVO createComment(CommentBO commentBO);

	/**
	 * 查询评论的列表
	 */
	PagedGridResult queryVlogComments(String vlogId, String userId, Integer page, Integer pageSize);

	/**
	 * 删除评论
	 */
	void deleteComment(String commentUserId, String commentId, String vlogId);

	/**
	 * 根据主键查询comment
	 */
	Comment getComment(String id);
}

package com.imooc.bo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户登录对象
 */
@Data
public class LoginBo {
	@NotBlank(message = "用户名不能为空")
	private String username;
	@NotBlank(message = "密码不能为空")
	private String password;
}

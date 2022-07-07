package com.imooc.controller;

import com.imooc.base.BaseInfoProperties;
import com.imooc.bo.LoginBo;
import com.imooc.bo.RegistLoginBO;
import com.imooc.grace.result.GraceJSONResult;
import com.imooc.grace.result.ResponseStatusEnum;
import com.imooc.pojo.Users;
import com.imooc.service.UserService;
import com.imooc.utils.IPUtil;
import com.imooc.utils.Md5Util;
import com.imooc.utils.MyInfo;
import com.imooc.utils.SMSUtils;
import com.imooc.vo.UsersVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@Api(tags = "PassportController 通信证接口模块")
@RequestMapping("passport")
@RestController
public class PassportController extends BaseInfoProperties {

	@Autowired
	private SMSUtils smsUtils;
	@Autowired
	private UserService userService;

	@PostMapping("getSMSCode")
	@ApiOperation("发送短信验证码")
	public GraceJSONResult getSMSCode(@RequestParam String mobile, HttpServletRequest request) throws Exception {
		if (StringUtils.isBlank(mobile)) {
			return GraceJSONResult.ok();
		}
		// 获得用户ip，
		String userIp = IPUtil.getRequestIp(request);
		// 根据用户ip进行限制，限制用户在60秒之内只能获得一次验证码
		redis.setnx60s(MOBILE_SMSCODE + ":" + userIp, userIp);
		String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
		smsUtils.sendSMS(MyInfo.getMobile(), code);
		log.info("验证码：" + code);
		// 把验证码放入到redis中，用于后续的验证
		redis.set(MOBILE_SMSCODE + ":" + mobile, code, 30 * 60);
		return GraceJSONResult.ok();
	}

	@PostMapping("login")
	@ApiOperation("手机号和短信验证码登录")
	public GraceJSONResult login(@Valid @RequestBody RegistLoginBO registLoginBO, HttpServletRequest request) throws Exception {
		String mobile = registLoginBO.getMobile();
		String code = registLoginBO.getSmsCode();
		String redisCode = redis.get(MOBILE_SMSCODE + ":" + mobile);
		if (StringUtils.isBlank(redisCode) || !redisCode.equalsIgnoreCase(code)) {
			return GraceJSONResult.errorCustom(ResponseStatusEnum.SMS_CODE_ERROR);
		}
		Users user = userService.queryMobileIsExist(mobile);
		if (user == null) {
			user = userService.createUser(mobile);
		}
		String uToken = UUID.randomUUID().toString();
		redis.set(REDIS_USER_TOKEN + ":" + user.getId(), uToken);
		redis.del(MOBILE_SMSCODE + ":" + mobile);
		UsersVO usersVO = new UsersVO();
		BeanUtils.copyProperties(user, usersVO);
		usersVO.setUserToken(uToken);
		return GraceJSONResult.ok(usersVO);
	}

	@PostMapping("login1")
	@ApiOperation("账号和密码登录")
	public GraceJSONResult login1(@Valid @RequestBody LoginBo loginBo, HttpServletRequest request) {
		Users user = userService.queryUsersByUsername(loginBo.getUsername());
		String password = Md5Util.md5Hex(loginBo.getPassword());
		if (!password.equals(user.getPassword())) {
			return GraceJSONResult.error();
		}
		String uToken = UUID.randomUUID().toString();
		UsersVO usersVO = new UsersVO();
		BeanUtils.copyProperties(user, usersVO);
		usersVO.setUserToken(uToken);
		usersVO.setPassword("******");
		return GraceJSONResult.ok(usersVO);
	}

	@PostMapping("logout")
	@ApiOperation("退出登录")
	public GraceJSONResult logout(@RequestParam String userId, HttpServletRequest request) throws Exception {
		redis.del(REDIS_USER_TOKEN + ":" + userId);
		return GraceJSONResult.ok();
	}

}

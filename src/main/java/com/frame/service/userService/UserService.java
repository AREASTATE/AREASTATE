package com.frame.service.userService;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.frame.entity.user.User;

public interface UserService {
	public Map<String, Object>  saveUser(User user, HttpServletRequest request);
	
	public void deleteUser(Long userId, HttpServletRequest request);
	
	public User findUserById(Long userId, HttpServletRequest request);

	public User findUserByLoginNo(String loginNo, HttpServletRequest request);

	public Map<String, Object> checkLoginUser(User user,HttpServletRequest request);

	public User findUserById(Integer id, HttpServletRequest request);

	public User updateUser(User user, HttpServletRequest request);

	public void loginOut(HttpServletRequest request);

	public Map<String, Object> changePwd(Integer id, String oldPwd,
			String newPwd, HttpServletRequest request);

	public boolean readedAnouncement(String loginNo, HttpServletRequest request);
}

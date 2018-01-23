package com.frame.dao.userDao;

import com.frame.entity.user.User;

public interface UserDao {
	public User saveUser(User user) throws Exception;
	
	public void deleteUser(Long userId) throws Exception;
	
	public User findUserById(Long userId) throws Exception;

	public User findUserByLoginNo(String loginNo) throws Exception;

	public User findUserById(Integer id) throws Exception;

	public User updateUser(User user) throws Exception;

	public boolean publishAnouncementToUser(Integer anouncementId,Integer userId) throws Exception;

	public boolean readedAnouncement(String loginNo) throws Exception;
}

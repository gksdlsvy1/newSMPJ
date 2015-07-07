package model.service;

import model.dao.UserDao;
import model.exception.IdPasswordNotMatchingException;
import model.spring.AuthInfo;
import model.vo.User;

public class AuthService {
	
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public AuthInfo authenticate(String email, String password) {
		User user = userDao.selectByEmail(email);
		
		if(user == null) {
			throw new IdPasswordNotMatchingException();
		}
		if(!user.matchPassword(password)){
			throw new IdPasswordNotMatchingException();
		}
		return new AuthInfo(user.getEmail(), user.getName());
	}

}

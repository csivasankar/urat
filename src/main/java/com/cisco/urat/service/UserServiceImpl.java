package com.cisco.urat.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisco.urat.dao.UserDao;
import com.cisco.urat.model.User;
import com.cisco.urat.utilities.PasswordEncoder;
import com.cisco.urat.utilities.UserUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	UserUtil userUtil;
	
	public long countAllUsers() {
		return userDao.count();
	}

	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	public void deleteUser(User user) {
		userUtil.deleteUser(user.getUsername());
		userDao.delete(user);
	}

	public User findUser(Integer id) {
		return userDao.findOne(id);
	}

	public List<User> findAllUsers() {
		return userDao.findAll();
	}

	public void saveUser(User user) {
		encodePassword(user);
		user.setEnabled(true);
		user.setCreated(Calendar.getInstance());
		userDao.save(user);
	}

	public User updateUser(User user) {
		encodePassword(user);
		return userDao.update(user);
	}

	private void encodePassword(User user) {
		try {
			user.setPassword(PasswordEncoder.encodePassword(user.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}

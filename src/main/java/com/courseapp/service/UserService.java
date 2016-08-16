package com.courseapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseapp.domain.User;
import com.courseapp.exception.UserNotFoundException;
import com.courseapp.repositories.UserRepository;

/**
 * @author appi_usa 
 * This is a user service class which handles crud operations
 */
@Service
@Transactional(rollbackOn = { Exception.class, RuntimeException.class })
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailService emailService;

	public void delete(Long userId) throws Exception {
		User user = userRepository.findOne(userId);
		if (user == null) {
			throw new UserNotFoundException("User with " + userId + "Not found");
		} else {
			userRepository.delete(userId);
		}
	}

	public User save(User user) throws Exception {
		if (user.getUserId() != null) {
			User dbUser = findByUserId(user.getUserId());
			if (dbUser == null) {
				throw new UserNotFoundException("User with " + user.getUserId() + "Not found");
			}
		}
		String text = user.getUserId() == null ? "You enrollment with course application is successfull. Start registering for courses"
				: "Your changes to your account is successfull";
		User dbUser = userRepository.save(user);
		emailService.sendemail(dbUser.getUserName(), "Course Application Notification", text);
		return dbUser;
	}

	public User findUserByName(String userName) throws Exception {
		return userRepository.findByUserName(userName);
	}

	public List<User> findAll() throws Exception {
		return userRepository.findAll();
	}

	public User findByUserId(Long userId) {
		return userRepository.findOne(userId);
	}
}

package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.repository.UsersDAO;

@Service
public class UsersService {
	
	@Autowired
	private UsersDAO uDAO;

}

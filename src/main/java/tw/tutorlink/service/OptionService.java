package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.repository.OptionDAO;

@Service
public class OptionService {

	@Autowired
	private OptionDAO oDAO;
}

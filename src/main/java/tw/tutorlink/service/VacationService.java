package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.repository.VacationDAO;

@Service
public class VacationService {

	@Autowired
	private VacationDAO vDAO;
}

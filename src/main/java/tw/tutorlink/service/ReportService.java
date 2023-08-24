package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.repository.ReportDAO;

@Service
public class ReportService {

	@Autowired
	private ReportDAO rDAO;
}

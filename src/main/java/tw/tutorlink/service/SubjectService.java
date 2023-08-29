package tw.tutorlink.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.Subject;
import tw.tutorlink.repository.SubjectDAO;

@Service
public class SubjectService {

	@Autowired
	private SubjectDAO sDAO;
	
	public SubjectService(SubjectDAO sDAO) {
		this.sDAO = sDAO;
	}
	
	//查詢全部課程類別
	public List<Subject> getAllSubject(){
		return sDAO.findAll();
	}
	
	//靠ID查詢課程類別
	public Subject findSubjectById(Subject subject){
		Optional<Subject> Subject = sDAO.findById(subject.getSubjectId());
		if(Subject.isPresent()) {
			
			return Subject.get();
		}
		return null;
	}
	
//	//靠類別名稱查詢課程資料
//	public Subject getSubjectByName(Subject subject){
//		return sDAO.findBySubjectContentContaining(SubjectContent);
//		Optional<Subject> subjects = sDAO.findById(subject.getSubjectId());
//		subjects.get().getSubjectContent();
//		return subject;
//	}
	
	//新增課程類別
	public Subject insertSubject(Subject subject) {
		return sDAO.save(subject);
	}
	
	//修改課程類別
	public Subject updateSubject(Subject subject) {
		Optional<Subject> Subject = sDAO.findById(subject.getSubjectId());
		if(Subject.isPresent()) {
			Subject result = Subject.get();
			result.setSubjectContent(subject.getSubjectContent());
			
			return sDAO.save(result);
	}
		return null;
	}
	
	//刪除課程類別
	public String deleteSubject(Subject subject) {
		Optional<Subject> Subject = sDAO.findById(subject.getSubjectId());
		if(Subject.isPresent()) {
	 	sDAO.deleteById(subject.getSubjectId());
	 	return "刪除成功";
	}
		return "刪除失敗";
	}
}

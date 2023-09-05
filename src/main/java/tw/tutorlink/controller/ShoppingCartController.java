package tw.tutorlink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.tutorlink.bean.Subject;
import tw.tutorlink.service.CartService;
import tw.tutorlink.service.LessonDetailService;
import tw.tutorlink.service.LessonsService;
import tw.tutorlink.service.SubjectService;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
	@Autowired
	private SubjectService sService;
	
	@Autowired
	private LessonsService lService;
	
	@Autowired
	private LessonDetailService ldService;
	
	@Autowired
	private CartService cService;

//	@GetMapping(path="/member/shoppingcart/step1",produces="application/json;charset=UTF-8")
//	public List<Subject> findAllSubjects(){
//		return cService.getAll();
//	}
}

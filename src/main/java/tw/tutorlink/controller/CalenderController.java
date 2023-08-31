package tw.tutorlink.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import tw.tutorlink.bean.Calender;
import tw.tutorlink.service.CalenderService;

@RestController
public class CalenderController {
	@Autowired
	private CalenderService cService;
	

	@GetMapping("/calender")
    public Optional<Calender> findCalender(@RequestParam("id") Integer id) {
        Optional<Calender> userCalender = cService.findById(id);
        return userCalender;
    }
}

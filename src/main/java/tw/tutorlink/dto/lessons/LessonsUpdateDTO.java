package tw.tutorlink.dto.lessons;

import tw.tutorlink.bean.Subject;

public class LessonsUpdateDTO {
    public LessonsUpdateDTO() {
    }
    private String lessonName  ;
    private Integer price ;
    private String image;
    private Subject subject;
    
    public String getLessonName() {
        return lessonName;
    }
    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public Subject getSubject() {
        return subject;
    }
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    
    
}
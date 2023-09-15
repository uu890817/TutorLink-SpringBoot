package tw.tutorlink.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="reCAPTCHA")
public class ReCAPTCHA {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id ;
	
	@Column(name="TokenId")
	private String reCAPTCHA;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReCAPTCHA() {
		return reCAPTCHA;
	}

	public void setReCAPTCHA(String reCAPTCHA) {
		this.reCAPTCHA = reCAPTCHA;
	}
	
}

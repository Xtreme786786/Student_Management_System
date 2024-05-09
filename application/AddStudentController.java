package application;

import java.sql.Date;

public class AddStudentController {
	private Integer studentNum;
	private String year;
	private String course;
	private String firstName;
	private String lastName;
	private String gender;
	private Date birth;
	private String status;
	private String image;
	private Double firstSem;
	private Double SecondSem;
	private Double finals;
	public AddStudentController(Integer studentNum, String year, String course, String firstName, String lastName,
			String gender, Date birth, String status, String image) {
		super();
		this.studentNum = studentNum;
		this.year = year;
		this.course = course;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birth = birth;
		this.status = status;
		this.image = image;
	}
	
	public AddStudentController(Integer studentNum, String year, String course, Double firstSem, Double secondSem,
			Double finals) {
		super();
		this.studentNum = studentNum;
		this.year = year;
		this.course = course;
		this.firstSem = firstSem;
		SecondSem = secondSem;
		this.finals = finals;
	}
	
	
	public Integer getStudentNum() {
		return studentNum;
	}
	
	public String getYear() {
		return year;
	}
	public String getCourse() {
		return course;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getGender() {
		return gender;
	}
	public Date getBirth() {
		return birth;
	}
	public String getStatus() {
		return status;
	}
	public String getImage() {
		return image;
	}

	public Double getFirstSem() {
		return firstSem;
	}

	public Double getSecondSem() {
		return SecondSem;
	}

	public Double getFinals() {
		return finals;
	}
	
	
	
	
}

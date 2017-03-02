package model;
import javax.persistence.*;
import java.io.Serializable;



@Entity

@Table(name = "scores")
public class Score implements Serializable{
	@Id
	@Column(name = "id")
	private String id;
	@Id
	@Column(name = "course")
	private String course;
	@Basic
	@Column(name = "score")
	private int score;
	@Basic
	@Column(name = "teacher")
	private String teacher;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	
	
	
}

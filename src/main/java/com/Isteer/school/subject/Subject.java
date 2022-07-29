package com.Isteer.school.subject;

import com.Isteer.school.student.Student;
import com.Isteer.school.teacher.Teacher;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	private String subjectName;

	private String author;

	@ManyToMany
	@JoinTable(name = "student_enrolled", joinColumns = @JoinColumn(name = "subject_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
	Set<Student> enrolledStudents = new HashSet<>();

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "teacher_id", referencedColumnName = "id")
	private Teacher teacher;

	public Long getId() {
		return id;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Set<Student> getEnrolledStudents() {
		return enrolledStudents;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Subject(Long id, String subjectName, String author, Set<Student> enrolledStudents, Teacher teacher) {
		super();
		this.id = id;
		this.subjectName = subjectName;
		this.author = author;
		this.enrolledStudents = enrolledStudents;
		this.teacher = teacher;
	}

	public Subject() {
		super();

	}

}

package com.Isteer.school.subject;

import com.Isteer.school.student.Student;
import com.Isteer.school.student.StudentRepository;
import com.Isteer.school.teacher.Teacher;
import com.Isteer.school.teacher.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

	@Autowired
	SubjectRepository subjectRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	TeacherRepository teacherRepository;

	@GetMapping
	List<Subject> getSubjects() {
		return subjectRepository.findAll();
	}

	@PostMapping
	Subject createSubject(@RequestBody Subject subject) {
		return subjectRepository.save(subject);
	}

	
	//Many to Many
	@PutMapping("/{subjectId}/students/{studentId}")
	Subject addStudentToSubject(@PathVariable Long subjectId, @PathVariable Long studentId) {
		Subject subject = subjectRepository.findById(subjectId).get();
		Student student = studentRepository.findById(studentId).get();
		subject.enrolledStudents.add(student);
		return subjectRepository.save(subject);
	}
               
	
	//Many to One
	@PutMapping("/{subjectId}/teacher/{teacherId}")
	Subject assignTeacherToSubject(@PathVariable Long subjectId, @PathVariable Long teacherId) {
		Subject subject = subjectRepository.findById(subjectId).get();
		Teacher teacher = teacherRepository.findById(teacherId).get();
		subject.setTeacher(teacher);
		return subjectRepository.save(subject);
	}
}

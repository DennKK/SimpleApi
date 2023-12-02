package com.boot.simpleapi.services;

import com.boot.simpleapi.entities.StudentEntity;
import com.boot.simpleapi.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentEntity> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(StudentEntity student) {
        Optional<StudentEntity> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email already exist!");
        }
        studentRepository.save(student);
    }

    public void deleteStudentById(Long studentId) {
        boolean studentExist = studentRepository.existsById(studentId);
        if (!studentExist) {
            throw new IllegalStateException("Student with id" + studentId + "does not exist!");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        boolean studentExist = studentRepository.existsById(studentId);
        if (!studentExist) {
            throw new IllegalStateException("Student with id" + studentId + "does not exist!");
        }

        StudentEntity student = studentRepository.findById(studentId).get();

        if (name != null && !name.isEmpty() && !name.equals(student.getName())) {
            student.setName(name);
        }

        if (email != null && !email.isEmpty() && !email.equals(student.getEmail())) {
            Optional<StudentEntity> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email already exist!");
            }
            student.setName(name);
        }
    }
}
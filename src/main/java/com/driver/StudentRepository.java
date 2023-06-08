package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class StudentRepository {

    HashMap<String, Student> studentDb;
    HashMap<String, Teacher> teacherDb;

    HashMap<String, List<Student>> teacherStudentPairs;

    public StudentRepository() {
        studentDb = new HashMap<>();
        teacherDb = new HashMap<>();
        teacherStudentPairs = new HashMap<>();
    }

    public void addStudent(Student student) {
        studentDb.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher) {
        teacherDb.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        if(studentDb.containsKey(student) && teacherDb.containsKey(teacher)) {
            Teacher teacher1 = teacherDb.get(teacher);
            teacher1.setNumberOfStudents(teacher1.getNumberOfStudents() + 1);
            if(!teacherStudentPairs.containsKey(teacher)) {
                teacherStudentPairs.put(teacher, new ArrayList<>());
            }
            teacherStudentPairs.get(teacher).add(studentDb.get(student));
        }
    }

    public Student getStudentByName(String name) {
        return studentDb.getOrDefault(name, null);
    }

    public Teacher getTeacherByName(String name) {
        return teacherDb.getOrDefault(name, null);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        if(teacherStudentPairs.containsKey(teacher)) {
            return teacherStudentPairs.get(teacher)
                    .stream()
                    .map(Student::getName)
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }

    public List<String> getAllStudents() {
        return new ArrayList<>(studentDb.keySet());
    }

    public void deleteTeacherByName(String teacher) {
        if(teacherDb.containsKey(teacher)) {
            teacherDb.remove(teacher);
            if(teacherStudentPairs.containsKey(teacher))
                teacherStudentPairs.remove(teacher);
        }
    }

    public void deleteAllTeachers() {
        teacherDb.clear();
        teacherStudentPairs.clear();
    }
}

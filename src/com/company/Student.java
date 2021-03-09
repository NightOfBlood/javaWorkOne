package com.company;

import java.io.Serializable;

public class Student extends Person implements Specializable{
    protected String group;
    protected String course;
    protected String favoriteSubject;

    public Student() {
        super();
    }

    //обработка ошибочных данных
    public Student(String[] info) {
        // считывание из person
        super(info);
        if (info.length <= 5) {
            throw new IllegalArgumentException("Not enough parameters for student");
        } else {
            group = info[3];
            course = info[4];
            favoriteSubject=info[5];
        }
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String favoriteSubject() {
        return favoriteSubject;
    }

    public void favoriteSubject(String favoriteSubject) {
        this.favoriteSubject = favoriteSubject;
    }

    // Дисс-я для студентов
    @Override
    public Object deserialize(String path) {
        Object deserialize = super.deserialize(path);
        if (deserialize == null || !(deserialize instanceof Student))
            return null;
        Student student = (Student) deserialize;
        this.group = student.group;
        this.course = student.course;
        this.favoriteSubject = student.favoriteSubject;
        return student;
    }

    @Override
    public String getSpecialization() {
        return favoriteSubject;
    }
}

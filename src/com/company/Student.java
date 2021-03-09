package com.company;

import java.io.Serializable;

public class Student extends Person implements Specializable{
    protected String group;
    protected String course;
    protected String favoriteSubject;
    //вызов конструктора Student
    public Student() {
        super();
    }

    //обработка ошибочных данных
    public Student(String[] info) {
        // считывание из person
        super(info);
        //Проверка
        if (info.length <= 5) {
            throw new IllegalArgumentException("Not enough parameters for student");
        } else {
            group = info[3];
            course = info[4];
            favoriteSubject=info[5];
        }
    }
    //Возвращение значения заданного поля
    public String getGroup() {
        return group;
    }
    //Передача аргумента в качестве строки и присвоение значения поля
    public void setGroup(String group) {
        this.group = group;
    }
    //Возвращение значения заданного поля
    public String getCourse() { return course; }
    //Передача аргумента в качестве строки и присвоение значения поля
    public void setCourse(String course) {
        this.course = course;
    }
    //Возвращение значения заданного поля
    public String favoriteSubject() {
        return favoriteSubject;
    }
    //Передача аргумента в качестве строки и присвоение значения поля
    public void favoriteSubject(String favoriteSubject) {
        this.favoriteSubject = favoriteSubject;
    }

    // Дисериализация (Загрузка полей)
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
    //
    @Override
    public String getSpecialization() {
        return favoriteSubject;
    }
}

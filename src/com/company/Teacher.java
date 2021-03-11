package com.company;

public class Teacher extends Person implements Specializable {
    protected String nameofsubject;
    protected String workexperience;

    //вызов конструктора Teacher
    public Teacher() {
        super();
    }

    public Teacher(String[] info) {
        super(info);
        //Проверка
        if (info.length > 4) {
            nameofsubject = info[3];
            workexperience = info[4];
        } else {
            //исключение
            throw new IllegalArgumentException("Not enough parameters for teacher");
        }
    }
    //Возвращение значения заданного поля
    public String getNameofsubject() {
        return nameofsubject;
    }
    //Передача аргумента в качестве строки и присвоение значения поля
    public void setNameofsubject(String nameofsubject) {
        this.nameofsubject = nameofsubject;
    }
    //Возвращение значения заданного поля
    public String getWorkexperience() {
        return workexperience;
    }
    //Передача аргумента в качестве строки и присвоение значения поля
    public void setWorkexperience(String workexperience) {
        this.workexperience = workexperience;
    }

    @Override
    //Загрузка сохраненных полей
    public Object deserialize(String path) throws Throwable {
        // Вызов метода родительского класса
        Object deserialize = super.deserialize(path);
        if (deserialize == null || !(deserialize instanceof Teacher))
            return null;
        Teacher teacher = (Teacher) deserialize;
        this.nameofsubject = teacher.nameofsubject;
        this.workexperience = teacher.workexperience;
        return teacher;
    }
    //
    @Override
    public String getSpecialization() {
        return nameofsubject;
    }
    //
    @Override
    public String toString() {
        return super.toString() + "This person is a teacher with a " +workexperience +" years of experience in " + nameofsubject + ".";
    }
}

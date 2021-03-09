package com.company;

public class Teacher extends Person implements Specializable {
    protected String nameofsubject;
    protected String workexperience;

    public Teacher() {
        super();
    }

    public Teacher(String[] info) {
        super(info);
        if (info.length > 4) {
            nameofsubject = info[3];
            workexperience = info[4];
        } else {
            throw new IllegalArgumentException("Not enough parameters for teacher");
        }
    }

    public String getNameofsubject() {
        return nameofsubject;
    }

    public void setNameofsubject(String nameofsubject) {
        this.nameofsubject = nameofsubject;
    }

    public String getWorkexperience() {
        return workexperience;
    }

    public void setWorkexperience(String workexperience) {
        this.workexperience = workexperience;
    }

    @Override
    public Object deserialize(String path) {
        Object deserialize = super.deserialize(path);
        if (deserialize == null || !(deserialize instanceof Teacher))
            return null;
        Teacher teacher = (Teacher) deserialize;
        this.nameofsubject = teacher.nameofsubject;
        this.workexperience = teacher.workexperience;
        return teacher;
    }

    @Override
    public String getSpecialization() {
        return nameofsubject;
    }

    @Override
    public String toString() {
        return super.toString() + "This person is a teacher with a " +workexperience +" years of experience in " + nameofsubject + ".";
    }
}

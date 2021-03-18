package com.company;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class Person implements Serializable, ISerializable {
    protected String lastname;
    protected String age;
    protected String dateofbirth;

    public Person(){

    }
    public Person(String[] info) {
        if (info.length > 2) {
            lastname = info[0];
            age = info[1];
            dateofbirth = info[2];
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }
    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    @Override

    public void serialize(String path) throws Throwable {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path))){
            objectOutputStream.writeObject(this);
        } catch (Exception e) {
            throw e instanceof InvocationTargetException ? e.getCause() : e;
        }
    }

    @Override
    public Object deserialize(String path) throws Throwable {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path))){
            Person person = (Person) objectInputStream.readObject();
            this.lastname = person.lastname;
            this.age = person.age;
            this.dateofbirth = person.dateofbirth;
            return person;
        }
        catch (Exception e) {
            throw e instanceof InvocationTargetException ? e.getCause() : e;
        }
    }

    @Override

    public String toString() {
        return "This person lastname is " + lastname + "; age is " + age + "; dateofbirth is " + dateofbirth + "; ";
    }
}



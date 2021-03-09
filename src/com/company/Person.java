package com.company;

import java.io.*;

// отвечает за все сво-ва
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
    public void serialize(String path) {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object deserialize(String path) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Person person = (Person) objectInputStream.readObject();
            this.lastname = person.lastname;
            this.age = person.age;
            this.dateofbirth = person.dateofbirth;
            return person;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO: сделать для потомков
    @Override
    public String toString() {
        return "This person lastname is " + lastname + "; age is " + age + "; dateofbirth is " + dateofbirth + "; ";
    }
}



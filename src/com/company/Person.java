package com.company;

import java.io.*;

// класс, содержащий общие поля для классов Student и Teacher
public class Person implements Serializable, ISerializable {
    protected String lastname;
    protected String age;
    protected String dateofbirth;

    //вызов конструктора Person
    public Person(){

    }
    public Person(String[] info) {
        //проверка
        if (info.length > 2) {
            lastname = info[0];
            age = info[1];
            dateofbirth = info[2];
        } else {
            //исключение
            throw new IllegalArgumentException();
        }
    }

    //Возвращение значения заданного поля
    public String getLastname() {
        return lastname;
    }
    //Передача аргумента в качестве строки и присвоение значения поля
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    //Возвращение значения заданного поля
    public String getAge() {
        return age;
    }
    //Передача аргумента в качестве строки и присвоение значения поля
    public void setAge(String age) {
        this.age = age;
    }
    //Возвращение значения заданного поля
    public String getDateofbirth() {
        return dateofbirth;
    }
    //Передача аргумента в качестве строки и присвоение значения поля
    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    @Override
    //Сериализация
    public void serialize(String path) {
        FileOutputStream outputStream = null;
        try {
            //создание 2-ух потоков и сохраняем их в файл
            outputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            //сохранение
            objectOutputStream.writeObject(this);
            //Закрытие потока и освобождение ресурсов
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    //Десериализация
    public Object deserialize(String path) {
        FileInputStream fileInputStream = null;
        //Загрузка сохраненных полей
        try {
            fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Person person = (Person) objectInputStream.readObject();
            this.lastname = person.lastname;
            this.age = person.age;
            this.dateofbirth = person.dateofbirth;
            return person;

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO: сделать для потомков
    @Override
    //
    public String toString() {
        return "This person lastname is " + lastname + "; age is " + age + "; dateofbirth is " + dateofbirth + "; ";
    }
}



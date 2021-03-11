package com.company;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

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
    public void serialize(String path) throws Throwable {
        // Создание стрима для сериализации модели в файл
        // Патерн декоратор
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path))){
            //сохранение
            objectOutputStream.writeObject(this);
        } catch (Exception e) {
            throw e instanceof InvocationTargetException ? e.getCause() : e;
        }
    }

    @Override
    //Десериализация
    public Object deserialize(String path) throws Throwable {
        //Загрузка сохраненных полей
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

    //TODO: сделать для потомков
    @Override
    //
    public String toString() {
        return "This person lastname is " + lastname + "; age is " + age + "; dateofbirth is " + dateofbirth + "; ";
    }
}



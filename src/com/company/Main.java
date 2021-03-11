package com.company;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final String PATH = "E:\\Дгту\\Современные технологии программирования\\";

    public static void main(String[] args) throws IOException {
        //Build writer instance//Записываются только логи
        FileWriter writer = new FileWriter(PATH + "log.txt", true);
        //блок, для обработки всех исключений
        try {
            //Считывание данных из файла
            ArrayList<Teacher> teachers = readFile(PATH + "Teacher.csv", Teacher.class, writer);
            ArrayList<Student> students = readFile(PATH + "Student.csv", Student.class, writer);
            //сериализация учителей и студентов
            serialize(teachers, PATH + "saveteachers.txt", writer);
            serialize(students, PATH + "savestudents.txt", writer);
            //десириализацияучителей и студентов
            ArrayList<Teacher> deserializeTeacher = deserialize(PATH + "saveteachers.txt", writer);
            ArrayList<Student> deserializeStudent = deserialize(PATH + "savestudents.txt", writer);
            //создание объекта реализующего интерфейса(ISerializable)
            ISerializable teacher = new Teacher(new String[]{"Сухов", "39", "1980", "Современные технологии программирования", "12"});

            teacher.serialize(PATH + "uniqueTeacher.txt");

            Teacher uniqueTeacher = new Teacher();
            uniqueTeacher.deserialize(PATH + "uniqueTeacher.txt");

        } catch (Throwable e) {
            //
            writer.write("\n" + e.getClass().toString() + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            //
            writer.close();
        }
    }

    //дженерикс
    public static <T extends Person> ArrayList<T> readFile(String path, Class<T> tClass, FileWriter writer)
            throws Throwable {
        ArrayList<T> persons = new ArrayList<>();
        try {
            //Запись содержимого в файл
            writer.write("Start read file: " + path + "\n");
            //
            CSVReader reader = null;
            //Установка кодировки
            reader = new CSVReader(new InputStreamReader(new FileInputStream(path), Charset.forName("windows-1251")));
            //считываем все строки
            List<String[]> allRows = reader.readAll();
            //
            Constructor<T> constructor = tClass.getConstructor(String[].class);
            //
            for (String[] row : allRows) {
                T t = constructor.newInstance((Object) row);
                persons.add(t);
                writer.write(t.toString() + "\n");
            }

        } catch (Exception e) {
            //вывод ошибки в файл
            writer.write("Catch exception when read file " + path);
            throw e instanceof InvocationTargetException ? e.getCause() : e;
        }
        //
        writer.write("Success read file: " + path + "\n");
        return persons;
    }

    //сериализация
    public static void serialize(Object obj, String path, FileWriter writer) throws IOException {
        FileOutputStream outputStream = null;
        writer.write(obj + " start seriallize to path: " + path + "\n");
        try {
            //создание 2-ух потоков и сохраняем их в файл
            outputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            //сохранение
            objectOutputStream.writeObject(obj);
            //Закрытие потока и освобождение ресурсов
            objectOutputStream.close();
            writer.write(" Seriallized success; \n");

        } catch (Exception e) {
            writer.write(obj + " seriallized failed; \n");
            //0шибка уходит
            throw e;
        }
    }

    //Дессириализация
    public static <T> T deserialize(String path, FileWriter writer) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = null;
        try {
            writer.write(" start deseriallize to path: " + path + "\n");
            fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            writer.write(" Deseriallized success; \n");
            return (T) objectInputStream.readObject();
        } catch (Exception e) {
            writer.write(path + " Deseriallized failed; \n");
            //0шибка уходит
            throw e;
        }
    }
}

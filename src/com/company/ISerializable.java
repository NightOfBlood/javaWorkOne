package com.company;

public interface ISerializable {
    void serialize(String path) throws Throwable;
    Object deserialize(String path) throws Throwable;
}

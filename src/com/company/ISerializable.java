package com.company;

public interface ISerializable {
    void serialize(String path);
    Object deserialize(String path);
}

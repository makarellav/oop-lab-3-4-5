package com.example.lab_3.dataaccess;

import java.io.FileNotFoundException;
import java.util.List;

public interface FileRepository<T> {
    void Write(String filePath, List<T> data);
    List<T> Read(String filePath) throws FileNotFoundException;
}

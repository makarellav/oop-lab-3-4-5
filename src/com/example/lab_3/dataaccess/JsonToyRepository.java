package com.example.lab_3.dataaccess;

import com.example.lab_3.toys.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Deque;
import java.util.List;

public class JsonToyRepository implements FileRepository<Toy> {
    public List<Toy> Read(String filePath) throws FileNotFoundException {
        var file = new File(filePath);

        if (!file.exists()) {
            throw new FileNotFoundException(STR."Файл \{filePath} не знайдено");
        }

        try {
            var mapper = new ObjectMapper();
            var typeFactory = mapper.getTypeFactory();

            var listType = typeFactory.constructCollectionType(List.class, Toy.class);

            return mapper.readValue(file, listType);
        } catch (IOException e) {
            System.out.println(e.getMessage());

            return null;
        }
    }

    @Override
    public void Write(String filePath, List<Toy> data) {
        try {
            var mapper = new ObjectMapper();

            var json = mapper.writerFor(new TypeReference<List<Toy>>() {
            }).writeValueAsString(data);

            try (var file = new FileWriter(filePath)) {
                file.write(json);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

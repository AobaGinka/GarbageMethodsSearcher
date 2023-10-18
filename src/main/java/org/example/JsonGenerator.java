package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JsonGenerator {
    private final ObjectNode root;
    private final ObjectMapper mapper;

    public JsonGenerator(List<GarbageMethod> methods){
        this.mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        this.root = mapper.createObjectNode();
        generate(methods);
    }

    public void printJson(){
        try {
            System.out.println(mapper.writeValueAsString(root));
        } catch (JsonProcessingException e) {
            System.out.println("[Error]JSONのプリントに失敗しました.");
            throw new RuntimeException(e);
        }
    }

    public void outputJsonFile(String path){
        try{
            Files.createDirectories(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        path += "/output.json";
        File file = new File(path);
        try {
            if(file.createNewFile()){
                System.out.println("[Info]ファイルを作成しました.");
            }
            mapper.writeValue(file, this.root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void generate(List<GarbageMethod> methods){
        ArrayNode array = mapper.createArrayNode();
        for (GarbageMethod methodObj : methods){
            ObjectNode method = mapper.createObjectNode();
            method.put("id", methodObj.getId());
            method.put("file_id", methodObj.getFileId());
            method.put("begin", methodObj.getBegin());
            method.put("end", methodObj.getEnd());
            array.add(method);
        }
        this.root.set("methods", array);
    }
}
package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;

public class JsonLoader {
    private FileData[] file_data;
    private final ObjectMapper mapper;

    public JsonLoader(String path) {
        this.mapper = new ObjectMapper();
        loadJson(path);
    }

    public FileData[] getFile_data(){
        return file_data;
    }

    private void loadJson(String path){
        try{
            JsonNode json = mapper.readTree(Paths.get(path).toFile());
            this.file_data = mapper.readValue(json.get("file_table").toString(), FileData[].class);
        }catch (IOException e){
            System.out.println("[Error]ファイルリスト用のJSONファイルのロードで問題が発生しました.");
            e.printStackTrace(System.err);
        }
    }
}

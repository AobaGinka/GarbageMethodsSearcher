package org.example;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Analyzer {
    private final Visitor visitor;

    public Analyzer(){
        this.visitor = new Visitor();
    }

    public void analyze(String path, int id){
        try {
            CompilationUnit unit = StaticJavaParser.parse(Paths.get(path));
            unit.accept(visitor, id);
        }catch (IOException e){
            System.out.println("[Error]ファイルの読み込みに失敗しました.");
            e.printStackTrace(System.err);
        }
    }

    public void analyze_file_table(FileData[] fileData){
        for (FileData fileDatum: fileData){
            analyze(fileDatum.getPath(), fileDatum.getId());
        }
    }

    public List<GarbageMethod> getGarbageMethods(){
        return visitor.getMethods();
    }
}

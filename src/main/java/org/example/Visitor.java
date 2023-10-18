package org.example;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

public class Visitor extends VoidVisitorAdapter<Integer> {
    private final List<GarbageMethod> methods;
    private int id;

    public Visitor(){
        this.methods = new ArrayList<>();
        this.id = 0;
    }

    @Override
    public void visit(MethodDeclaration m, Integer arg){
        int begin;
        int end;
        String name = m.getNameAsString();

        // Getter/Setterの検出
        if(name.matches("get.*") || name.matches("set.*")){
            if(m.getRange().isPresent()){
                begin = m.getRange().get().begin.line;
                end = m.getRange().get().end.line;
                methods.add(new GarbageMethod(this.id, arg, begin, end));
                id++;
            }
        }
        super.visit(m, arg);
    }

    public List<GarbageMethod> getMethods() {
        return this.methods;
    }
}

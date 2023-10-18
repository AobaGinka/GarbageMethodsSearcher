package org.example;

public class GarbageMethod {
    private final int id;
    private final int file_id;
    private final int begin;
    private final int end;

    public GarbageMethod(int id, int file_id, int begin, int end){
        this.id = id;
        this.file_id = file_id;
        this.begin = begin;
        this.end = end;
    }

    public int getId(){
        return id;
    }

    public int getFileId() { return file_id; }

    public int getBegin(){
        return begin;
    }

    public int getEnd(){
        return end;
    }
}

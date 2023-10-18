package org.example;


public class Main {
    private static final String OUTPUT_PATH = "out/json";

    public static void main(String[] args){
        // コマンドライン引数チェック
        if (args.length == 0) {
            System.out.println("[Error]引数が不正です.");
            return;
        }
        // JSONファイル読み込み
        JsonLoader loader = new JsonLoader(args[0]);
        // JavaParserによる分析
        Analyzer analyzer = new Analyzer();
        analyzer.analyze_file_table(loader.getFile_data());
        //出力JSONファイル構築
        JsonGenerator generator = new JsonGenerator(analyzer.getGarbageMethods());
        generator.printJson();
        generator.outputJsonFile(OUTPUT_PATH);
    }
}
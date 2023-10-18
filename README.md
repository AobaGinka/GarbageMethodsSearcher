# 概要
[CCFinder-SW](https://github.com/YuichiSemura/CCFinderSW)で出力されたJSONから, JavaのGetter/SetterのリストをJSONで出力するプログラムです.
# 使い方
- Java17における実行のみテストを行いました.
- コマンドライン上で引数を与えて実行します.
  - `java -jar GarbageMethodsSearcher.jar <json>`
  - \<json>にはCCFinder-SWで出力されたJSONファイルのパスを指定する.
- `./out/json/output.json`に出力されます.
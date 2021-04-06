# DDD-Output
主にドメイン層に設定したクラスを想定し、出力対象としたクラスをPlantUML形式でクラス図に出力するツールです。

## 【Requirements】
* Javaのプロジェクトを対象としてます。
* Java8以上を対象としています。

## 【Usage】
### アノテーションの設定
* [ddd-output.jar](https://github.com/someone7140/java/blob/master/ddd-output/ddd-output.jar "ddd-output.jar")をダウンロードして、対象のプロジェクトで参照設定してください。
* 出力対象のクラス・メソッドに以下の通りアノテーションを設定してください。
  * クラス
    * `@DddOutputClass`を設定してください。以下の属性を任意で設定可能です。
      * name：クラスに対して任意の名前（日本語名など）を設定できます。
      * comment：クラスに対して補足情報を設定できます。
  * メソッド
    * `@DddOutputClass`を設定してください。以下の属性を任意で設定可能です。
* 設定例は[DeliveryNormal.java](https://github.com/someone7140/java/blob/master/ddd-sample/src/main/java/com/ddd/sampleDomain/delivery/DeliveryNormal.java "DeliveryNormal.java")を参照ください。

### PlantUMLファイル（pu）の出力
* [ddd-output.zip](https://github.com/someone7140/java/blob/master/ddd-output/ddd-output.zip "ddd-output.zip")のファイルを任意のパスにダウンロードし解凍してください。
* 解凍後のbinフォルダから、以下の形式でスクリプトを実行するとpuファイルが出力されます。  
    ./ddd-output com.ddd.Main `クラスファイルのパス` `puファイルの出力先` `出力対象のパッケージ名（配下のパッケージが出力対象）`
* 実行形式の例は以下の通りです。  
    `./ddd-output com.ddd.Main /Users/someone7140/git/java/ddd-sample/build/classes /Users/someone7140/git/java/ddd-output/output-file-sample/ddd-output.pu com.ddd.sampleDomain`

* puファイルの参照方法は、[PlantUMLを使ってみる(MacOS) Visual Studio Codeでもっと使いやすく](https://qiita.com/Takaichi00/items/8e03258c3c956d4848c4 "PlantUMLを使ってみる(MacOS) ~Visual Studio Codeでもっと使いやすく~")などを参照ください。

### その他注意点
* 親クラス（インターフェース）は子クラスよりパスの文字列の昇順ソートで上位に来るよう、プロジェクトでの配置を行ってください。
* プロジェクトのビルド時、コンパイルのオプションに`-parameters`を設定してください。Gradleでの設定方法は[GradleでJavaのコンパイルオプションを設定する方法](https://qiita.com/someone7140/items/317307fa0ca9f53917bb "GradleでJavaのコンパイルオプションを設定する方法")を参照ください。
  なお、設定しない場合はメソッドの引数名が`args`という名称で出力されます。

## 【OutputDetail】
* 以下の通りの内容を、クラス図としてpuファイルに出力します。
  * クラス情報
    * アノテーションを設定したクラスとメソッドを出力します。
    * アノテーションを設定したクラスのフィールドを出力します。
  * 継承
    * 親クラス（もしくはインターフェース）がpu出力対象として指定されている場合、継承関係として出力します。
  * 関連
    * 他のpu出力対象のクラスがフィールドに設定されている場合、関連として出力します。
    * コレクションもしくは配列にて保持している場合、集約関係として出力します。
  * 依存
    * 依存の厳密な定義からは外れるかもしれませんが、pu出力対象のクラスのメソッドを使用し呼ばれる場合、依存関係とみなして出力します。


* 以下は[こちら](https://github.com/someone7140/java/tree/master/ddd-sample "こちら")のサンプルプロジェクトで、アノテーションを付与した結果です。
![ddd-output](https://user-images.githubusercontent.com/33390784/113724751-a0153900-972d-11eb-83c0-68e3fbec5f2d.png)

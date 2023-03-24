package Sample;

public class StringBuilderTest {
    public static void main(String[] args) {

        //appendメソッド：文字列を末尾に追加するためのメソッドです。
        StringBuilder sb1 = new StringBuilder();
        sb1.append("Hello ");
        sb1.append("world1");
        String result = sb1.toString(); // "Hello world"
        System.out.println(result);

        //insertメソッド：文字列を指定した位置に挿入するためのメソッドです。
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Hello world2");
        sb2.insert(5, ", "); // "Hello, world"
        System.out.println(sb2);

        //deleteメソッド：文字列の一部を削除するためのメソッドです。
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Hello world3");
        sb3.delete(5, 8); // "Hello"
        System.out.println(sb3);

        //appendメソッド：文字列の一部を追加するためのメソッドです。
        StringBuilder sb4 = new StringBuilder();
         //reverseメソッド：文字列の反転するメソッドです。
        System.out.println(sb4.append("Hello world4"));
        System.out.println(sb4.reverse());

        //ダブルクォーテーションで囲んだ文字列リテラルを使用する方法
        String str1 = "Hello, World5!";
        System.out.println(str1);

        //Stringクラスのコンストラクタを使用する方法
        String str2 = new String("Hello, World6!");
        System.out.println(str2);

        //文字列の結合
        String str3 = "Hello, ";
        String str4 = "World7!";
        String result1 = str3 + str4;  // "Hello, World!"となる

        //文字列の検索
        String str5 = "Java is a programming language.";
        int index = str5.indexOf("programming");
        System.out.println(str5.indexOf("programming"));// 10が返る

        //replaceメソッド：文字列の一部を置換するためのメソッドです。
        String str6 = "Java is a programming language.";
        String newStr = str6.replace("Java", "Python");
        System.out.println(str6.replace("Java", "Python")); // "Python is a programming language."となる

        //文字列の分割
        String str7 = "Java,Python,Ruby";
        String[] strArray = str7.split(",");
        System.out.println(str7.split(",")); //[Ljava.lang.String;@27d6c5e0

        String str8 = "Java";
        String str9 = "Python";
        int result2 = str8.compareTo(str9);
        System.out.println(str8.compareTo(str9)); //-6

    }
}
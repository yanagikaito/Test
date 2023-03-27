package test1;

public class Sample {
    String name1 = "勇者A";

    static String name = "勇者";//使われる側もstaticでないといけない
    static int lv = 100;

    public static void main(String[] args) {
        Sample a = new Sample();
        a.putJyosyou();

        if (lv < 40) {//ネスト
            if (lv < 30)
                if (lv < 20)
                    if (lv <= 10)

                        putGameOver();
        } else {
            putGameClear();
        }
    }

    void putJyosyou() //staticのところで使う場合
    {
        System.out.println("魔王が世界を滅ぼそうとしています。");
        name = name1;
        System.out.println(name + "はレベル" + lv + "のツワモノです。");
    }

    static void putGameOver() {
        System.out.println("魔王に敗れましたorz");
        System.out.println("GAME OVER");
    }

    static void putGameClear() {
        String s0 = name + "は魔王を倒しました";
        put(s0);//メソッド

        System.out.print("レベル" + lv);

        if (lv > 80) {
            put("なので余裕でした");

        } else if (lv > 50) {
            put("なので倒せました");
        } else if (lv > 40) {
            put("でしたが倒せました");
        } else if (lv <= 10) {
            put("でしたので苦戦しました");
            
        } else {

        }
        put(" GAME CLEAR！ ");
    }

    static void put(String str) {
        System.out.println(str);
    }
}
package test1;

public class Sample {

    String name = "勇者"; // 使われる側もstaticでないといけない
    int lv = 30; //プレイヤーのレベル


    public static void main(String[] args) throws java.io.IOException {

        Sample a = new Sample();
        a.putJyosyou1();
        a.putJyosyou();

    }

    void putJyosyou1() throws java.io.IOException {

        System.out.println("1. すぐに魔王を倒しに行く");
        System.out.println("2. 修行してから魔王を倒しに行く");

        int c = System.in.read(); // 文字に対応した数字がcに代入される


        if (c == '2') {
            lv = 110;

        } else {
            lv = 30;
        }
    }

    /**
     * 　序章を表示します
     */


    void putJyosyou() {


        System.out.println("魔王が世界を滅ぼそうとしています。");

        System.out.println(name + "はレベル" + lv + "のツワモノです。");

        System.out.println(name + "はレベル" + lv + "で魔王を倒しに行く");

        String str1 = "スライムが出現したので倒した。次の道に進む。残りHP100";

        String str2 = "巨人が出現したので倒した。次の道に進む。残りHP90";

        String str3 = "ドラゴンが出現したので倒した。次の道に進む。残りHP50";

        String str4 = "魔王が出現した。魔王の全力の攻撃で残りHP0";

        if (lv < 100) {
            // TODO:lvが100未満のときは～～～する
            put1(str1);
            if (lv < 90)
                // TODO:lvが90未満のときは～～～する
                put2(str2);
            if (lv < 80)
                // TODO:lvが80未満のときは～～～する
                put3(str3);
            if (lv < 49)
                put4(str4);
            // GAME OVER


            putGameOver();
        } else {
            putGameClear();
        }
    }

    /**
     * 　引数で指定された文字を表示します。
     */
    void put1(String str1) {
        System.out.println(str1);
    }

    void put2(String str2) {
        System.out.println(str2);
    }

    void put3(String str3) {
        System.out.println(str3);
    }

    void put4(String str4) {
        System.out.println(str4);
    }


    void putGameOver() {
        System.out.println("魔王に敗れましたorz");
        System.out.println("GAME OVER");
    }

    void putGameClear() {
        String s0 = name + "は魔王を倒しました";
        put(s0);

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

    /**
     * 　引数で指定された文字を表示します。
     */
    void put(String str) {
        System.out.println(str);
    }
}
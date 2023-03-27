package Shooting;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Shooting {
    public static ShootingFrame shootingFrame;
    public static boolean loop;

    public static void main(String[] args) {
        shootingFrame = new ShootingFrame();// ゲームのフレームまたはウィンドウを表すカスタム クラスです。
        loop = true;

        Graphics gra = shootingFrame.panel.image.getGraphics();//ゲーム画面に要素を描画するために使用されるオブジェクト。

        //FPS
        long startTime;
        long fpsTime = 0;
        int fps = 30;
        int FPS = 0;
        int FPSCount = 0;
        //列挙型は、変数を取ることが、できる固定された値のセットを定義する方法を提供し、
        // コードの読み取りと保守を容易にするため便利です。
        // また、変数が常に有効な値に設定されるようにすることで、エラーを防ぐのにも役立ちます。
        EnumShootingScreen screen = EnumShootingScreen.START;

        //GAME
        int playerX = 0, playerY = 0;
        int bulletInterval = 0;
        int score = 0;
        int level = 0;
        long levelTimer = 0;
        //プレイヤーが発射した弾丸を表すオブジェクト。
        ArrayList<Bullet> bullets_player = new ArrayList<>();
        //敵が発射した弾丸を表すオブジェクト。
        ArrayList<Bullet> bullets_enemy = new ArrayList<>();
        //ゲーム内の敵を表すオブジェクト。
        ArrayList<Enemy> enemies = new ArrayList<>();
        //乱数を生成するために使用できるオブジェクト。
        Random random = new Random();

        while (loop) {
            // 'fpsTimeの差があるかどうかをチェックします変数が'fpsTimeの差があるかどうかをチェックします
            if ((System.currentTimeMillis() - fpsTime) >= 1000) {
                //変数が1000 ミリ秒 (1 秒) 以上です。
                fpsTime = System.currentTimeMillis();
                FPS = FPSCount;
                // そうである場合は、リセットされますfpsTime変数を現在の時刻に更新し、
                // 'FPS前の秒で処理されたフレームの数を持つ変数(FPSCount)、およびリセットFPSCountゼロに。
                FPSCount = 0;
            }
            //FPSCount変数を 1 で、現在の時刻 (ミリ秒単位) をstartTime変数。
            FPSCount++;
            startTime = System.currentTimeMillis();

            //Graphicsオブジェクトを使用して、500 x 500 ピクセルのキャンバスに塗りつぶされた四角形を描画します。
            gra.setColor(Color.BLACK);
            gra.fillRect(0, 0, 500, 500);

            switch (screen) {
                case START:
                    //白色で文字を描画するように色を設定する
                    gra.setColor(Color.WHITE);
                    //大きめのフォントを設定する
                    Font font = new Font("SansSerif", Font.PLAIN, 50);
                    //フォントのメトリックスを取得する
                    gra.setFont(font);
                    //Shooting" という文字を画面の中央に描画する
                    FontMetrics metrics = gra.getFontMetrics(font);
                    //小さめのフォントを設定する
                    gra.drawString("Shooting", 250 - (metrics.stringWidth("Shooting") / 2), 100);
                    //フォント オブジェクトは、"SansSerif" という名前、スタイル "PLAIN"、およびサイズ 20 で作成されます。
                    font = new Font("SansSerif", Font.PLAIN, 20);
                    //フォントオブジェクトは、グラフィックオブジェクトに対して、setFont方式。
                    gra.setFont(font);

                    //getFontMetricsメソッドは、フォントのサイズや間隔などのフォントに関する情報を
                    // 含むフォント・メトリックス・オブジェクトを取得するために使用されます。
                    metrics = gra.getFontMetrics(font);

                    //drawStringメソッドを使用して、グラフィックス オブジェクトに文字列を描画します。
                    // フォント メトリックスを使用して文字列の幅を計算し、
                    // 画面の中心の x 座標からその値の半分を減算することで、文字列を画面上の水平方向に中央に配置します。
                    gra.drawString("Press SPACE to Start", 250 - (metrics.stringWidth("Press SPACE to Start") / 2), 160);

                    //スペースキーが押された場合( 'isKeyPressedのメソッドキーボードクラス)、コードはさまざまなゲーム変数をリセットし、
                    // 画面をゲーム画面に設定します。
                    // これにより、プレイヤーはスペースキーを押したときに新しいゲームを開始できます。
                    // 「bullets_player、bullets_playerそしてbullets_enemy変数は空のリストにリセットされ、
                    // enemies変数が空のリストにリセットされ、プレーヤーの位置がデフォルトの開始位置にリセットされ、スコアとレベルが0にリセットされます。
                    if (Keyboard.isKeyPressed(KeyEvent.VK_SPACE)) {
                        screen = EnumShootingScreen.GAME;
                        bullets_player = new ArrayList<>();
                        bullets_enemy = new ArrayList<>();
                        enemies = new ArrayList<>();
                        playerX = 235;
                        playerY = 430;
                        score = 0;
                        level = 0;
                    }
                    break;
                case GAME:
                    //現在の時刻 (System.currentTimeMillis()) を、レベルが最後に増加した時刻まで (levelTimer変数)。
                    // 10秒が経過すると、コードは「レベルタイマー」をリセットします
                    // levelTimer現在の時刻まで(System.currentTimeMillis()) をインクリメントします。
                    if (System.currentTimeMillis() - levelTimer > 10 * 1000) {
                        levelTimer = System.currentTimeMillis();
                        level++;
                    }
                    //Color.BLUEを使用して青に設定されます。
                    gra.setColor(Color.BLUE);
                    //最初の四角形は、左上隅をポイント (playerX+ 10,playerY)、幅は 10、高さは 10 です。
                    gra.fillRect(playerX + 10, playerY, 10, 10);
                    //最初の四角形は、左上隅をポイント (playerX+ 10,playerY)、幅は 10、高さは 10 です。
                    gra.fillRect(playerX, playerY + 10, 30, 10);

                    //メソッドは、0.0から1.0未満の範囲でランダムな値を返します。それに360をかけることで、0から359の間のランダムな方向を取得します。
                    double direction = Math.random() * 360;
                    //オブジェクトを作成して、 bullets_player リストに追加します。これはプレイヤーの位置から始まる弾丸です。
                    bullets_player.add(new Bullet(playerX + 12, playerY, (int) direction));

                    //bullets_player リスト内のすべての弾丸を処理するためのループを開始します。
                    //bullets_player リストから現在処理している弾丸を取得します。
                    for (int i = 0; i < bullets_player.size(); i++) {


                        //　弾丸を描画するための fillRect() メソッドを呼び出します。
                        Bullet bullet = bullets_player.get(i);


                        // 弾丸の位置（ bullet.x および bullet.y ）とサイズ（10×10）が指定されています。
                        gra.fillRect(bullet.x, bullet.y, 10, 10);

                        //弾丸の速度を設定します。
                        int speed = 20;


                        //弾丸のX座標を更新します。 bullet.direction は、弾丸が進む方向を表します。
                        // Math.cos() メソッドは、与えられた角度の余弦を返します。
                        bullet.x += (int) (Math.cos(Math.toRadians(bullet.direction)) * speed);

                        //弾丸のY座標を更新します。 Math.sin() メソッドは、与えられた角度の正弦を返します。
                        bullet.y -= (int) (Math.sin(Math.toRadians(bullet.direction)) * speed);

                        //弾丸が画面の外に出たかどうかをチェックします。
                        // もし弾丸が画面外に出た場合、弾丸を bullets_player リストから削除します。
                        if (bullet.y < 0 || bullet.x < 0 || bullet.x > 500 || bullet.y > 500) {
                            bullets_player.remove(i);
                            i--;
                        }
                        //forループの始まりで、敵のリストenemiesの全要素に対して処理を行います。
                        for (int l = 0; l < enemies.size(); l++) {

                            //リストenemiesから要素を取得し、Enemyオブジェクトに代入します。
                            // これにより、敵の座標やその他の情報にアクセスできるようになります。
                            Enemy enemy = enemies.get(l);

                            //if文で、弾の座標が敵の矩形内にあるかどうかをチェックします。敵は矩形で表されており、
                            // enemy.xとenemy.yは左上の座標、enemy.x + 30とenemy.y + 20は右下の座標を表します。
                            // 弾の座標が矩形の範囲内にある場合、敵を消去してスコアを加算します。
                            if (bullet.x >= enemy.x && bullet.x <= enemy.x + 30 &&
                                    bullet.y >= enemy.y && bullet.y <= enemy.y + 20) {
                                enemies.remove(l);
                                score += 10;
                            }
                        }
                    }

                    //gra.setColor(Color.RED)は、描画に使用する色を設定します。ここでは、赤色を指定しています。
                    gra.setColor(Color.RED);

                    //forループの始まりで、敵のリストenemiesの全要素に対して処理を行います。
                    for (int i = 0; i < enemies.size(); i++) {

                        //リストenemiesから要素を取得し、Enemyオブジェクトに代入します。
                        // これにより、敵の座標やその他の情報にアクセスできるようになります。
                        Enemy enemy = enemies.get(i);

                        //gra.fillRect(enemy.x, enemy.y, 30, 10)とgra.fillRect(enemy.x + 10, enemy.y + 10, 10, 10)は、
                        // 敵を描画するためのコードです。
                        gra.fillRect(enemy.x, enemy.y, 30, 10);

                        //gra.fillRect()メソッドは、四角形を描画します。ここでは、敵の形状が2つの長方形で表されており、
                        //左側の長方形の大きさは30x10で、右側の長方形の大きさは10x10です。
                        gra.fillRect(enemy.x + 10, enemy.y + 10, 10, 10);

                        //enemy.y += 3は、敵を下に移動させます。ここでは、敵のy座標に3を加算しています。
                        enemy.y += 3;

                        //if文で、敵が画面下端に到達したかどうかをチェックします。
                        // もし画面下端に到達している場合、リストenemiesから対象の敵を削除します。
                        if (enemy.y > 500) {

                            //enemies.remove(i)は、リストenemiesから対象の敵を削除します。
                            enemies.remove(i);

                            //i--は、削除した敵の位置をリスト内で調整するために必要です。
                            i--;
                        }

                        //敵が弾を発射する条件をチェックしています。
                        //random.nextInt()メソッドは、0から指定された整数未満のランダムな整数を返します。
                        // このコードでは、levelが50未満の場合は80からlevelを引いた値を上限とし、それ以上の場合は30を上限とします。
                        //== 1は、ランダムに選ばれた整数が1である場合にのみ、弾を発射するように指示しています。
                        if (random.nextInt(level < 10 ? 50 - level : 80) == 1)

                            //Enemyオブジェクトで表され、enemyという変数で参照されます。
                            // 弾丸の初期位置は敵の位置に設定され、x座標はenemy.x + 12に設定されます。
                            // これにより、弾丸が敵の中央に水平に配置されます。また、y座標はenemy.yに設定されます。
                            // これにより、弾丸が敵の上部に配置されます。
                            //Bulletコンストラクターに渡される3番目の引数は、0から359までのランダムな角度で、
                            // random.nextInt(360)メソッドを使って生成されます。この角度は、弾丸の初期の移動方向を決定します。
                            bullets_enemy.add(new Bullet(enemy.x + 12, enemy.y, random.nextInt(360)));

                        if ((enemy.x >= playerX && enemy.x <= playerX + 30 &&
                                enemy.y >= playerY && enemy.y <= playerY + 20) ||
                                (enemy.x + 30 >= playerX && enemy.x + 30 <= playerX + 30 &&
                                        enemy.y + 20 >= playerY && enemy.y + 20 <= playerY + 20)) {
                            screen = EnumShootingScreen.GAME_OVER;
                            score += (level - 1) * 100;
                        }
                    }
                    if (random.nextInt(level < 10 ? 30 - level : 10) == 1)
                        enemies.add(new Enemy(random.nextInt(470), 0));

                    for (int i = 0; i < bullets_enemy.size(); i++) {
                        Bullet bullet = bullets_enemy.get(i);
                        gra.fillRect(bullet.x, bullet.y, 5, 5);
                        int speed = 10;
                        bullet.x += (int) (Math.cos(Math.toRadians(bullet.direction)) * speed);
                        bullet.y += (int) (Math.sin(Math.toRadians(bullet.direction)) * speed);
                        if (bullet.y > 500 || bullet.x < 0 || bullet.x > 500) {
                            bullets_enemy.remove(i);
                            i--;
                        }
                        if (bullet.x >= playerX && bullet.x <= playerX + 30 &&
                                bullet.y >= playerY && bullet.y <= playerY + 20) {
                            screen = EnumShootingScreen.GAME_OVER;
                            score += (level - 1) * 100;
                        }
                    }


                    if (Keyboard.isKeyPressed(KeyEvent.VK_LEFT) && playerX > 0) playerX -= 8;
                    if (Keyboard.isKeyPressed(KeyEvent.VK_RIGHT) && playerX < 470) playerX += 8;
                    if (Keyboard.isKeyPressed(KeyEvent.VK_UP) && playerY > 30) playerY -= 8;
                    if (Keyboard.isKeyPressed(KeyEvent.VK_DOWN) && playerY < 450) playerY += 8;

                    if (Keyboard.isKeyPressed(KeyEvent.VK_SPACE) && bulletInterval == 0) {

                        bullets_player.add(new Bullet(playerX + 12, playerY, (int) direction));
                        bulletInterval = 8;
                    }
                    if (bulletInterval > 0) bulletInterval--;

                    gra.setColor(Color.WHITE);
                    font = new Font("SansSerif", Font.PLAIN, 20);
                    metrics = gra.getFontMetrics(font);
                    gra.setFont(font);
                    gra.drawString("SCORE:" + score, 470 - metrics.stringWidth("SCORE:" + score), 430);
                    gra.drawString("LEVEL:" + level, 470 - metrics.stringWidth("LEVEL:" + level), 450);

                    break;
                case GAME_OVER:
                    gra.setColor(Color.WHITE);

                    font = new Font("SansSerif", Font.PLAIN, 50);
                    gra.setFont(font);
                    metrics = gra.getFontMetrics(font);
                    gra.drawString("Game Over", 250 - (metrics.stringWidth("Game Over") / 2), 100);
                    font = new Font("SansSerif", Font.PLAIN, 20);
                    gra.setFont(font);
                    metrics = gra.getFontMetrics(font);
                    gra.drawString("Score:" + score, 250 - (metrics.stringWidth("Score:" + score) / 2), 150);
                    gra.drawString("Press ESC to Return Start Screen", 250 - (metrics.stringWidth("Press ESC to Return Start Screen") / 2), 200);
                    if (Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE)) {
                        screen = EnumShootingScreen.START;
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + screen);
            }

            gra.setColor(Color.WHITE);
            gra.setFont(new Font("SansSerif", Font.PLAIN, 10));
            gra.drawString(FPS + "FPS", 0, 470);

            shootingFrame.panel.draw();

            try {
                long runTime = System.currentTimeMillis() - startTime;
                if (runTime < (1000 / fps)) {
                    Thread.sleep((1000 / fps) - (runTime));

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
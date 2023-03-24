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
                    //getFontMetricsメソッドは、フォントのサイズや間隔などのフォントに関する情報を含むフォント・メトリックス・オブジェクトを取得するために使用されます。
                    metrics = gra.getFontMetrics(font);
                    //drawStringメソッドを使用して、グラフィックス オブジェクトに文字列を描画します。
                    // フォント メトリックスを使用して文字列の幅を計算し、画面の中心の x 座標からその値の半分を減算することで、文字列を画面上の水平方向に中央に配置します。
                    gra.drawString("Press SPACE to Start", 250 - (metrics.stringWidth("Press SPACE to Start") / 2), 160);
                    //スペースキーが押された場合( 'isKeyPressedのメソッドキーボードクラス)、コードはさまざまなゲーム変数をリセットし、画面をゲーム画面に設定します。
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


                    double direction = Math.random() * 360;
                    bullets_player.add(new Bullet(playerX + 12, playerY, (int) direction));

                for (int i = 0; i < bullets_player.size(); i++) {
                        Bullet bullet = bullets_player.get(i);
                        gra.fillRect(bullet.x, bullet.y, 10, 10);
                        int speed = 20;
                        bullet.x += (int) (Math.cos(Math.toRadians(bullet.direction)) * speed);
                        bullet.y -= (int) (Math.sin(Math.toRadians(bullet.direction)) * speed);
                        if (bullet.y < 0 || bullet.x < 0 || bullet.x > 500 || bullet.y > 500) {
                            bullets_player.remove(i);
                            i--;
                        }

                        for (int l = 0; l < enemies.size(); l++) {
                            Enemy enemy = enemies.get(l);
                            if (bullet.x >= enemy.x && bullet.x <= enemy.x + 30 &&
                                    bullet.y >= enemy.y && bullet.y <= enemy.y + 20) {
                                enemies.remove(l);
                                score += 10;
                            }
                        }
                    }

                    gra.setColor(Color.RED);
                    for (int i = 0; i < enemies.size(); i++) {
                        Enemy enemy = enemies.get(i);
                        gra.fillRect(enemy.x, enemy.y, 30, 10);
                        gra.fillRect(enemy.x + 10, enemy.y + 10, 10, 10);
                        enemy.y += 3;
                        if (enemy.y > 500) {
                            enemies.remove(i);
                            i--;
                        }
                        if (random.nextInt(level < 50 ? 80 - level : 30) == 1)
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
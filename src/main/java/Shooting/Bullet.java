package Shooting;

public class Bullet {
    public final int direction;
    public int x, y;


    public Bullet(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
}
package dk.aau.student.meda2a220a.p2protype;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GameSprite {
    private Bitmap image;
    private int x;
    private int y;
    private int width;
    private int height;
    private String type;


    public GameSprite(Bitmap image, int x, int y, int width, int height, String type){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        this.image = Bitmap.createScaledBitmap(image, this.width, this.height, true);
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);
    }

    public void moveX(int speed){
        x += speed;
    }

    public void moveY(int speed){
        y += speed;
    }

    public String getType(){
        return this.type;
    }

}

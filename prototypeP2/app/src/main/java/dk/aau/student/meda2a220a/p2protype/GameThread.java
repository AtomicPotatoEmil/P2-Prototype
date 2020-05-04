package dk.aau.student.meda2a220a.p2protype;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread{
    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean gameActive;
    public static Canvas canvas;
    private int targetFPS = 60;

    public GameThread(SurfaceHolder surfaceHolder, GameView gameView){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }

    @Override
    public void run() {
        super.run();
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000 / targetFPS;

        while (gameActive){
            canvas = null;
            startTime = System.nanoTime();
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.gameView.update();
                    this.gameView.draw(canvas);
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if (canvas == null){
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;

            try {
                this.sleep(waitTime);
            }catch (Exception e){
                e.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == targetFPS){
                frameCount = 0;
                totalTime = 0;
            }
        }
    }

    public void setGameActive(boolean gameActive){
        this.gameActive = gameActive;
    }
}

package dk.aau.student.meda2a220a.p2protype;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread thread;
    private Timer obstacleSpawnTimer;
    private int obstacleSpawnWaitTime = 3;
    private GameSprite background;

    private String [] obstacleTypes;
    private int obstacleTypesIndex;
    private ArrayList<GameSprite> obstacles;
    private ArrayList<GameSprite> obstaclesToRemove;

    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);
        thread = new GameThread(getHolder(), this);
        setFocusable(true);
    }

    TimerTask obstacleTimerTask = new TimerTask() {
        @Override
        public void run() {
            obstacleSpawnWaitTime++;
            System.out.println(obstacleSpawnWaitTime);
        }
    };


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setGameActive(true);
        thread.start();
        obstacleSpawnTimer = new Timer();
        obstacleSpawnTimer.scheduleAtFixedRate(obstacleTimerTask, 3000, 1000);

        background = new GameSprite(BitmapFactory.decodeResource(getResources(), R.drawable.baggrund), 0, 0, 1920, 1080, "");
        obstacleTypes = new String[] {"duck", "jumpRight", "jumpLeft", "jump"};
        obstacles = new ArrayList<GameSprite>();
        obstaclesToRemove = new ArrayList<GameSprite>();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry){
            try {
                thread.setGameActive(false);
                thread.join();
            }catch (Exception e){
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        background.draw(canvas);
        for (GameSprite obstacle : obstacles){
            obstacle.draw(canvas);
        }
    }

    public void update(){
        if (obstacleSpawnWaitTime > 5){
            obstacleTypesIndex = (int) (Math.random() * 4);
            switch (obstacleTypes[obstacleTypesIndex]){
                case "duck":
                    obstacles.add(new GameSprite(BitmapFactory.decodeResource(getResources(), R.drawable.jumpunder), 400, -200, 1800, 1000, "duck"));
                    break;
                case "jumpRight":
                    obstacles.add(new GameSprite(BitmapFactory.decodeResource(getResources(), R.drawable.stenvenstre), 600, -200, 1000, 1000, "jumpRight"));
                    break;
                case "jumpLeft":
                    obstacles.add(new GameSprite(BitmapFactory.decodeResource(getResources(), R.drawable.stenhoijre), 300, -250, 1000, 1000, "jumpLeft"));
                    break;
                case "jump":
                    obstacles.add(new GameSprite(BitmapFactory.decodeResource(getResources(), R.drawable.treelog), 620, 100, 700, 700, "jump"));
            }
            obstacleSpawnWaitTime = 0;
        }

        for (GameSprite obstacle : obstacles){
            switch (obstacle.getType()){
                case "duck":
                    obstacle.moveY(5);
                    obstacle.moveX(-5);
                    break;
                case "jumpRight":
                    obstacle.moveY(5);
                    obstacle.moveX(-4);
                    break;
                case "jumpLeft":
                    obstacle.moveY(5);
                    obstacle.moveX(3);
                    break;
                case "jump":
                    obstacle.moveY(5);
                    break;
            }
            if (obstacle.getY() > 1080){
                obstaclesToRemove.add(obstacle);
            }
        }

        obstacles.removeAll(obstaclesToRemove);
    }
}

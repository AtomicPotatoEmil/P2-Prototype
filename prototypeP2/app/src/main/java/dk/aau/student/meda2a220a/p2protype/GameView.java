package dk.aau.student.meda2a220a.p2protype;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread thread;
    private Timer obstacleSpawnTimer;
    private int obstacleSpawnWaitTime;
    private GameSprite background;

    private String [] obstacleTypes;
    private int obstacleTypesIndex;
    private ArrayList<GameSprite> obstacles;
    private ArrayList<GameSprite> obstaclesToRemove;

    private String currentObstacle;

    private SensorManager accelerationManager;
    private Sensor accelerationSensor;
    private int accelerationY;
    private int accelerationX;

    private SensorManager stepManager;
    private Sensor stepSensor;

    private Paint paint;

    private int obstaclesDodged;
    private int obstaclesHit;
    private int stepsTaken;

    private SharedPreferences save;
    private SharedPreferences.Editor editSave;

    private int time;
    private Timer timer;

    private Context context;
    private Intent intent;

    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);
        thread = new GameThread(getHolder(), this);
        setFocusable(true);

        accelerationManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        accelerationSensor = accelerationManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        stepManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        stepSensor = stepManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        this.save = context.getSharedPreferences("statistics", Context.MODE_PRIVATE);

        this.context = context;
        intent = new Intent(context, GameSummary.class);
    }

    TimerTask obstacleTimerTask = new TimerTask() {
        @Override
        public void run() {
            obstacleSpawnWaitTime++;
            System.out.println(obstacleSpawnWaitTime);
        }
    };

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            time++;
            editSave = save.edit();
            editSave.putInt("time", time);
            editSave.apply();
        }
    };



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setGameActive(true);
        thread.start();
        obstacleSpawnTimer = new Timer();
        obstacleSpawnTimer.scheduleAtFixedRate(obstacleTimerTask, 1000, 1000);
        obstacleSpawnWaitTime = 2;

        background = new GameSprite(BitmapFactory.decodeResource(getResources(), R.drawable.baggrund), 0, 0, 1920, 1080, "");
        obstacleTypes = new String[] {"duck", "jumpRight", "jumpLeft", "jump"};
        obstacles = new ArrayList<GameSprite>();
        obstaclesToRemove = new ArrayList<GameSprite>();

        currentObstacle = "none";

        SensorEventListener accelerationListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                accelerationY = (int) event.values[0];
                accelerationX = (int) event.values[1];
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        accelerationManager.registerListener(accelerationListener, accelerationSensor, SensorManager.SENSOR_DELAY_FASTEST);

        SensorEventListener stepListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                stepsTaken += 1;
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }

        };
        stepManager.registerListener(stepListener, stepSensor, SensorManager.SENSOR_DELAY_FASTEST);

        paint = new Paint();
        paint.setColor(Color.rgb(255, 255, 255));
        paint.setTextSize(70);

        obstaclesDodged = 0;
        obstaclesHit = 0;
        stepsTaken = 0;

        editSave = save.edit();
        editSave.putInt("obstaclesDodged", obstaclesDodged);
        editSave.putInt("obstaclesHit", obstaclesHit);
        editSave.putInt("time", time);
        editSave.apply();

        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask,1000, 1000);

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
        canvas.drawText("Dodged: "+(obstaclesDodged), 100, 100, paint);
        canvas.drawText("Hit: "+(obstaclesHit), 100, 200, paint);
        canvas.drawText("Steps: "+(stepsTaken)+"/100", 1420, 100, paint);
        switch (currentObstacle){
            case "tree":
                GameSprite arrowDown = new GameSprite(BitmapFactory.decodeResource(getResources(), R.drawable.arrow_down_red), 700, 150, 500, 500, "");
                arrowDown.draw(canvas);
                break;
            case "stoneLeft":
                GameSprite arrowRight = new GameSprite(BitmapFactory.decodeResource(getResources(), R.drawable.arrow_right_red), 700, 150, 500, 500, "");
                arrowRight.draw(canvas);
                break;
            case "stoneRight":
                GameSprite arrowLeft = new GameSprite(BitmapFactory.decodeResource(getResources(), R.drawable.arrow_left_red), 700, 150, 500, 500, "");
                arrowLeft.draw(canvas);
                break;
            case "treeLog":
                GameSprite arrowUp = new GameSprite(BitmapFactory.decodeResource(getResources(), R.drawable.arrow_up_red), 700, 150, 500, 500, "");
                arrowUp.draw(canvas);
                break;
            default:
                return;
        }

    }

    public void update(){
        if (obstacleSpawnWaitTime > 4){
            obstacleTypesIndex = (int) (Math.random() * 4);
            switch (obstacleTypes[obstacleTypesIndex]){
                case "duck":
                    obstacles.add(new GameSprite(BitmapFactory.decodeResource(getResources(), R.drawable.jumpunder), 400, -200, 1800, 1000, "duck"));
                    currentObstacle = "tree";
                    break;
                case "jumpRight":
                    obstacles.add(new GameSprite(BitmapFactory.decodeResource(getResources(), R.drawable.stenvenstre), 600, -200, 1000, 1000, "jumpRight"));
                    currentObstacle = "stoneLeft";
                    break;
                case "jumpLeft":
                    obstacles.add(new GameSprite(BitmapFactory.decodeResource(getResources(), R.drawable.stenhoijre), 300, -250, 1000, 1000, "jumpLeft"));
                    currentObstacle = "stoneRight";
                    break;
                case "jump":
                    obstacles.add(new GameSprite(BitmapFactory.decodeResource(getResources(), R.drawable.treelog), 620, 100, 700, 700, "jump"));
                    currentObstacle = "treeLog";
                    break;
            }
            obstacleSpawnWaitTime = 0;
        }

        for (GameSprite obstacle : obstacles){
            switch (obstacle.getType()){
                case "duck":
                    obstacle.moveY(11);
                    obstacle.moveX(-11);
                    break;
                case "jumpRight":
                    obstacle.moveY(11);
                    obstacle.moveX(-10);
                    break;
                case "jumpLeft":
                    obstacle.moveY(11);
                    obstacle.moveX(10);
                    break;
                case "jump":
                    obstacle.moveY(11);
                    break;
            }
            if (obstacle.getY() > 1080){
                obstaclesHit += 1;
                editSave = save.edit();
                editSave.putInt("obstaclesHit", obstaclesHit);
                editSave.apply();
                obstaclesToRemove.add(obstacle);
                currentObstacle = "none";
            }

            if (obstacle.getType().equals("duck") && accelerationY > 6){
                obstaclesDodged += 1;
                editSave = save.edit();
                editSave.putInt("obstaclesDodged", obstaclesDodged);
                editSave.apply();
                obstaclesToRemove.add(obstacle);
                currentObstacle = "none";

            }
            if (obstacle.getType().equals("jumpRight") && accelerationX > 4){
                obstaclesDodged += 1;
                editSave = save.edit();
                editSave.putInt("obstaclesDodged", obstaclesDodged);
                editSave.apply();
                obstaclesToRemove.add(obstacle);
                currentObstacle = "none";
            }
            if (obstacle.getType().equals("jumpLeft") && accelerationX < -4){
                obstaclesDodged += 1;
                editSave = save.edit();
                editSave.putInt("obstaclesDodged", obstaclesDodged);
                editSave.apply();
                obstaclesToRemove.add(obstacle);
                currentObstacle = "none";
            }
            if (obstacle.getType().equals("jump") && accelerationY < -6){
                obstaclesDodged += 1;
                editSave = save.edit();
                editSave.putInt("obstaclesDodged", obstaclesDodged);
                editSave.apply();
                obstaclesToRemove.add(obstacle);
                currentObstacle = "none";
            }

            //System.out.println("acc X "+ accelerationX);
            System.out.println("acc Y "+ accelerationY);

        }

        obstacles.removeAll(obstaclesToRemove);

        if (stepsTaken >= 100) {
            context.startActivity(intent);
        }
    }
}

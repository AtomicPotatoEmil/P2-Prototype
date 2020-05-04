package dk.aau.student.meda2a220a.p2protype;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread thread;
    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);
        this.thread = new GameThread(getHolder(), this);
        setFocusable(true);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setGameActive(true);
        thread.start();
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

    public void update(){

    }
}

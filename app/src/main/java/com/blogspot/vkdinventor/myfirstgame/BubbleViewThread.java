package com.blogspot.vkdinventor.myfirstgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by vikash on 04-11-2016.
 */
public class BubbleViewThread extends Thread {

    Paint paint;
    SurfaceHolder holder;
    OnPositionListner positionListner;
    Boolean isRunning=false;
    int width;
    int height;
    int x_centre=(int)(width*Math.random());
    int y_centre=(int)(height*Math.random());


    BubbleViewThread(SurfaceHolder sh, OnPositionListner positionListner) {
        holder = sh;
        this.positionListner = positionListner;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
    }

    public void setCentre(int xaxis,int yaxis){
        x_centre=xaxis;
        y_centre=yaxis;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void startGame(Boolean isRunning)
    {
        this.isRunning=isRunning;
    }

    @Override
    public void run() {
        super.run();
        int r = 0, g = 0, b = 0, i = 0;
        while (true) {
            i=0;
            while (isRunning) {
                r=(int)(255*Math.random());
                g=(int)(255*Math.random());
                b=(int)(255*Math.random());
                paint.setColor(Color.rgb(r, g, b));
                i=i%50;
                i = i + 2;
                Canvas canvas = null;
                synchronized (holder) {
                    Rect rect=new Rect();
                    Log.v("BubbleView", "printing canvas");
                    canvas = holder.lockCanvas(null);
                    canvas.drawCircle(x_centre,y_centre , i, paint);
                    holder.unlockCanvasAndPost(canvas);
                    positionListner.onPositionChanged("new radius is:" + i);
                }
                Log.v("BubbleView", "canvas complete");
                try {
                    Thread.sleep(100);
                } catch (Exception e) {

                }
            }
        }
    }

}

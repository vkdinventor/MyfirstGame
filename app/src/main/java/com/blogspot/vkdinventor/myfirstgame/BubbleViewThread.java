package com.blogspot.vkdinventor.myfirstgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by vikash on 04-11-2016.
 */
public class BubbleViewThread extends Thread {

    Paint paint;
    Context context;
    SurfaceHolder holder;
    OnPositionListner positionListner;
    Boolean isRunning=false;
    int width;
    int height;
    int x_centre=(int)(width*Math.random());
    int y_centre=(int)(height*Math.random());
    Bitmap rocket;


    BubbleViewThread(Context mContext,SurfaceHolder sh, OnPositionListner positionListner) {
        context=mContext;
        holder = sh;
        this.positionListner = positionListner;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        rocket= BitmapFactory.decodeResource(context.getResources(),R.drawable.lander_firing);
    }

    public void setCentre(int xaxis,int yaxis){
        x_centre=xaxis;
        y_centre=yaxis;
    }

    public void setSize(int width, int height) {
        synchronized (holder) {
            this.width = width;
            this.height = height;
        }
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
            while (isRunning) {
                i=i%height;
                i=i+5;
                Canvas canvas = null;
                synchronized (holder) {
                    Rect rect=new Rect(width/2,0,height,100);
                    Log.v("BubbleView", "printing canvas");
                    canvas = holder.lockCanvas(null);
                    canvas.drawColor(Color.BLACK);
                    canvas.drawBitmap(rocket,width/2,height-i,null);
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

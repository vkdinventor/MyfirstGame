package com.blogspot.vkdinventor.myfirstgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by vikash on 04-11-2016.
 */
public class BubbleView extends SurfaceView implements SurfaceHolder.Callback {

    final String TAG = "BubbleView";
    OnPositionListner positionListner;

    BubbleViewThread bubbleViewThread;

    BubbleView(Context context) {
        super(context);
        getHolder().addCallback(this);
        Log.v(TAG, "first Constructor");
    }

    public BubbleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        Log.v(TAG, "second Constructor");
    }

    public BubbleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getHolder().addCallback(this);
        Log.v(TAG, "third Constructor");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        bubbleViewThread.setSize(width, height);
        bubbleViewThread.start();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        bubbleViewThread = new BubbleViewThread(surfaceHolder, positionListner);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    void setPositionListener(OnPositionListner m1) {
        positionListner = m1;
    }
}

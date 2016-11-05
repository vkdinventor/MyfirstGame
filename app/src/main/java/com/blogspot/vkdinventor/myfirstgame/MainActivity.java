package com.blogspot.vkdinventor.myfirstgame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Boolean start=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView=(TextView)findViewById(R.id.text);
        final BubbleView bubbleView=(BubbleView)findViewById(R.id.game_view);
        final Button startBtn=(Button)findViewById(R.id.startanim);
        if (bubbleView != null) {
            bubbleView.setPositionListener(new OnPositionListner() {
                @Override
                public void onPositionChanged(final String newPosition) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(newPosition);
                        }
                    });
                    Log.v("MainActivity",newPosition);
                }
            });
        }

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               bubbleView.startAnimation(start);
                start=!start;
            }
        });

    }
}

package com.example.car_ui;

import android.content.pm.ActivityInfo;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;

import com.thekhaeng.pushdownanim.PushDownAnim;

import it.beppi.tristatetogglebutton_library.TriStateToggleButton;

public class MainActivity extends AppCompatActivity {

    int check = 0;
    Button btn_R,btn_D,btn_N;
    ImageButton btn_Foward, btn_Stop;
    TriStateToggleButton gear;
    SeekBar sk;
    private MyCountDownTimer myCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        btn_Foward  = findViewById(R.id.btn_Foward);
        btn_Stop    = findViewById(R.id.btn_Stop);
        gear        = findViewById(R.id.gear);
        sk          = findViewById(R.id.sk);
        btn_N        = findViewById(R.id.btn_N);
        btn_R         = findViewById(R.id.btn_R);
        btn_D         = findViewById(R.id.btn_D);

        gear.setOnToggleChanged(new TriStateToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(TriStateToggleButton.ToggleStatus toggleStatus, boolean b, int i) {
                switch (toggleStatus) {
                    case off:
                        btn_N.setTextColor(16711680);
                        btn_D.setTextColor(0);
                        btn_R.setTextColor(0);
                        break;
                    case mid:
                        btn_N.setTextColor(0);
                        btn_D.setTextColor(16776960);
                        btn_R.setTextColor(0);
                        break;
                    case on:
                        btn_N.setTextColor(0);
                        btn_D.setTextColor(0);
                        btn_R.setTextColor(65280);
                        break;
                }
            }
        });

        btn_Foward.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;

            @Override public boolean onTouch(View v, MotionEvent event) {

                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        btn_Foward.setBackgroundResource(R.drawable.b);
                        if (myCountDownTimer!=null){
                            myCountDownTimer.cancel();
                        }
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 500);
                        break;
                    case MotionEvent.ACTION_UP:
                        btn_Foward.setBackgroundResource(R.drawable.a);
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        mHandler = null;
                        myCountDownTimer = new MyCountDownTimer(check*500, 500);
                        myCountDownTimer.start();
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override public void run() {
                    increateProgress();
                    mHandler.postDelayed(this, 500);

                }
            };

        });

            }
   /* private void decreateProgress(){
        int progress = this.sk.getProgress();
        if(progress - 0 < 0)  {
            this.sk.setProgress(0);
        }
        else  {
            this.sk.setProgress(progress - 10);
        }
    }*/

    private void increateProgress(){
        int progress = this.sk.getProgress();
        if(progress  == this.sk.getMax())  {

        }else {
            this.sk.setProgress(progress + 10);
            check++;
        }

    }

    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            sk.setProgress(sk.getProgress()-10);

        }

        @Override
        public void onFinish() {

        }
    }
}

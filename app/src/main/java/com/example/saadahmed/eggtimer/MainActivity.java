package com.example.saadahmed.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    boolean counterIsActive=false;
    Button startButton;

    public void resetTimer(){
        seekBar.setEnabled(true);
        startButton.setText("GO!");
        seekBar.setProgress(30);
        textView.setText("00:30");
        counterIsActive=false;
    }

    public void updateTimer(int secondsLeft){
        int minute=secondsLeft/60;
        int seconds=secondsLeft%60;

        String stringSeconds=Integer.toString(seconds);
        if (seconds<10){
            stringSeconds="0"+stringSeconds;
        }
        textView.setText(Integer.toString(minute)+":"+stringSeconds);
    }

    public void Start(View view){
        if(counterIsActive){
            resetTimer();

        }else{
            seekBar.setEnabled(false);
            startButton.setText("STOP!");
            counterIsActive=true;
            new CountDownTimer(seekBar.getProgress()*1000,1000){

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int)millisUntilFinished/1000);
                    Log.i("fff","gy");
                }

                @Override
                public void onFinish() {
                    MediaPlayer mp=MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                    mp.start();
                    resetTimer();


                }
        }.start();
    }}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar=findViewById(R.id.seekBar);
        textView=findViewById(R.id.textView);
        startButton=findViewById(R.id.button);

        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}

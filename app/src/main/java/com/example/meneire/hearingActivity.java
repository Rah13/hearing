package com.example.meneire;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class hearingActivity extends AppCompatActivity {

//        private AudioManager audioManager;
//        //private AudioStream audioStreamVol;
//        private static final String TAG = "AudioStream";
//
//        private Set<Integer> deviceTypes =
//                new HashSet<>(Arrays.asList(AudioDeviceInfo.TYPE_BUILTIN_EARPIECE,
//                        AudioDeviceInfo.TYPE_BUILTIN_SPEAKER,
//                        AudioDeviceInfo.TYPE_WIRED_HEADSET,
//                        AudioDeviceInfo.TYPE_WIRED_HEADPHONES,
//                        AudioDeviceInfo.TYPE_LINE_ANALOG,
//                        AudioDeviceInfo.TYPE_LINE_DIGITAL,
//                        AudioDeviceInfo.TYPE_BLUETOOTH_SCO,
//                        AudioDeviceInfo.TYPE_BLUETOOTH_A2DP,
//                        AudioDeviceInfo.TYPE_HDMI,
//                        AudioDeviceInfo.TYPE_HDMI_ARC,
//                        AudioDeviceInfo.TYPE_USB_DEVICE,
//                        AudioDeviceInfo.TYPE_USB_ACCESSORY,
//                        AudioDeviceInfo.TYPE_DOCK,
//                        AudioDeviceInfo.TYPE_FM,
//                        AudioDeviceInfo.TYPE_BUILTIN_MIC,
//                        AudioDeviceInfo.TYPE_TELEPHONY,
//                        AudioDeviceInfo.TYPE_AUX_LINE,
//                        AudioDeviceInfo.TYPE_IP,
//                        AudioDeviceInfo.TYPE_BUS,
//                        AudioDeviceInfo.TYPE_USB_HEADSET,
//                        AudioDeviceInfo.TYPE_HEARING_AID));
//
//        public int findDeviceType() {
//            final AudioDeviceInfo[] devices = audioManager.getDevices(AudioManager.GET_DEVICES_OUTPUTS);
//            for (AudioDeviceInfo device : devices) {
//                final int type = device.getType();
//                if (deviceTypes.contains(type)) {
//                    return type;
//                }
//            }
//            return -1;
//
//        }
//
//        @RequiresApi(api = Build.VERSION_CODES.P)
//        public float audioStreamVol() {
//            audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
//            int curType = AudioManager.STREAM_MUSIC;
//            int vol = audioManager.getStreamVolume(curType);
//            int deviceType = findDeviceType();
//            float volumedB = audioManager.getStreamVolumeDb(curType, vol, deviceType);
//            return volumedB;
//
//
//        }

    private AudioManager audioManager;
    int volume_level;
    int max_volume_level;
    float db_volume_level;



    final int DB_LEVELS = 3;
    final int FREQ_LEVELS = 3;

    int dbSelect;
    int freqSelect;

    int[] audioSelect = {0,1,2,3,4,5,6,7,8};

    int[][] audiogram = new int[3][3];


    private MediaPlayer player;

    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;

    private TextView counter;
    private TextView nullText;

   private int count = 0;
   private int audioIndex = 0;


   private CountDownTimer mCountDownTimer;
   private boolean mTimerRunning;
   private long mTimeLeftInMillis = 5000;
   int timercount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hearing);


        audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);




        chronometer = findViewById(R.id.chronometer);
        counter = findViewById(R.id.countTextView);
        nullText = findViewById(R.id.nullTextView);


        Button nextBtn = (Button)findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlayer();
                Intent nextIntent = new Intent(getApplicationContext(),MainActivity.class);
                // pass info
                startActivity(nextIntent);
            }
        });


        Button backBtn = (Button)findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlayer();
                Intent backIntent = new Intent(getApplicationContext(),vertigoActivity.class);
                // pass info
                startActivity(backIntent);
            }
        });

    }


//    public void play(View v){
//
//        if(player == null){
//            nullText.setText("released");
//        }
//
//
//        for(int i = 0; i < FREQ_LEVELS; i++){
//
//            freqSelect = i;
//
//            shuffleArray(audioSelect);
//
//            int j = 0;
//            while(j<DB_LEVELS){
//
//            //for(int j = 0; j < DB_LEVELS; j++){
//
//                count = 0;
//
//                if(nullText.getText() == "released"){
//
//                    dbSelect = audioSelect[j];
//
//                    //String audioID = "rightlefthearing";
//
//                    String audioID = "audio1hz0db";
//
//                    //String audioID = "audio" + freqSelect + "hz" + dbSelect + "db";
//                    int audiofile=getResources().getIdentifier(audioID,"raw",getPackageName());
//                    player=MediaPlayer.create(this,audiofile);
//
//
//                    player.start();
//                    nullText.setText("open");
//
//
//                    while(nullText.getText() == "open"){
//
//                        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                            @Override
//                            public void onCompletion(MediaPlayer mp) {
//
//                                if(count>0){
//                                    audiogram[dbSelect][freqSelect] = 1;
//                                }
//
//                                stopPlayer();
//                            }
//                        });
//
//                    }
//
//                }
//
//
//
//
//                j++;
//
//
//            }
//        }
//
//
//    }

//    public void play(View v){
//
//            count = 0;
//            player = MediaPlayer.create(hearingActivity.this, R.raw.audio1hz0db);
//            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mp) {
//                    if (count < 2) {
//                        player.create(getApplicationContext(), R.raw.audio1hz1db);
//                        player.start();
//                        count++;
//                    } else {
//                        stopPlayer();
//                    }
//
//                }
//            });
//            player.start();
//    }

//    public void play(View v){
//
//
//        final Handler handler = new Handler();
//        audioIndex = 0;
//        count = 0;
//
//        final Runnable runnable = new Runnable() {
//            public void run() {
//                // need to do tasks on the UI thread
//                if(player == null){
//                    player = MediaPlayer.create(hearingActivity.this,R.raw.audio1hz0db);
//
//
//                    player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                        @Override
//                        public void onCompletion(MediaPlayer mp) {
//
//                            if(count>0){
//                                Toast.makeText(hearingActivity.this,"Hear Pressed", Toast.LENGTH_SHORT).show();
//                            }
//
//                            stopPlayer();
//                        }
//                    });
//
//                    player.start();
//                    startTimer();
//                }
//
//
//
//                if (audioIndex++ <= 3) {
//                    count = 0;
//                    counter.setText(Integer.toString(count));
//                    nullText.setText("waiting");
//                    handler.postDelayed(this, 5000);
//
//                }
//            }
//        };
//
//// trigger first time
//        handler.post(runnable);
//
//
//
//
//
//
//    }

    public void play(View v){

        shuffleArray(audioSelect);

        startTimer();

    }

    private void startTimer(){
        mCountDownTimer = new CountDownTimer(5000,100) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                nullText.setText(Long.toString(mTimeLeftInMillis));

            }

            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onFinish() {


                    mTimerRunning = false;
                    Toast.makeText(hearingActivity.this,"Timer Finished", Toast.LENGTH_SHORT).show();

                    if(count>0){
                        audiogram[dbSelect][freqSelect] = 1;
                        Toast.makeText(hearingActivity.this,"Hear Pressed", Toast.LENGTH_SHORT).show();
                    }

                    stopPlayer();

                if(timercount++ < audioSelect.length){

                    volume_level= audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                    max_volume_level = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                    db_volume_level = audioManager.getStreamVolumeDb(AudioManager.STREAM_MUSIC,volume_level,AudioDeviceInfo.TYPE_WIRED_HEADPHONES);
                    //audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,5,0);


                    count = 0;
                    counter.setText(Integer.toString(count));

                    freqSelect = audioSelect[timercount-1]%FREQ_LEVELS;
                    dbSelect = audioSelect[timercount-1]/DB_LEVELS;

                    //String audioID = "audio1hz0db";
                    //String audioID = "audio" + freqSelect + "hz" + dbSelect + "db";
                    String audioID = "audio" + 1 + "hz" + dbSelect + "db";

                    int audiofile=getResources().getIdentifier(audioID,"raw",getPackageName());

                    player=MediaPlayer.create(hearingActivity.this,audiofile);
                    player.start();
                    startTimer();
                } else{
                    String result = Integer.toString(audiogram[0][0]) +
                            Integer.toString(audiogram[0][1]) +
                            Integer.toString(audiogram[0][2]) + "\n" +
                            Integer.toString(audiogram[1][0]) +
                            Integer.toString(audiogram[1][1]) +
                            Integer.toString(audiogram[1][2]) + "\n" +
                            Integer.toString(audiogram[2][0]) +
                            Integer.toString(audiogram[2][1]) +
                            Integer.toString(audiogram[2][2]);
                    nullText.setText(result);
                }


            }
        }.start();

        mTimerRunning = true;
    }



    public void pause(View v){
        if(player != null){
            player.pause();
        }
    }

    public void stopPlayer(){
        if(player != null){
            player.release();
            player = null;
            //nullText.setText("released");
            Toast.makeText(this,"MediaPlayer released", Toast.LENGTH_SHORT).show();
        }
    }

    // Implementing Fisher–Yates shuffle
    static void shuffleArray(int[] ar)
    {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    public void hear(View v){
        count++;
        counter.setText(Integer.toString(count));
    }

/*
    public void play(View v){
        if(player == null){
            player = MediaPlayer.create(this,R.raw.rightlefthearing);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }
        player.start();

        chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
        chronometer.start();
    }

    public void pause(View v){
        if(player != null){
            player.pause();

            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
        }
    }

    private void stopPlayer(){
        if(player != null){
            player.release();
            player = null;
            Toast.makeText(this,"MediaPlayer released",Toast.LENGTH_SHORT).show();

            chronometer.setBase(SystemClock.elapsedRealtime());
            pauseOffset = 0;
        }
    }

*/



    // For when app is closed
    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }
}

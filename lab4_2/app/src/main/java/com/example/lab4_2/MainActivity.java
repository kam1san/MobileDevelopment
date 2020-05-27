package com.example.lab4_2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mplayer; SeekBar position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String path = Environment.getExternalStorageDirectory().getPath() + "/Download/Виктор Цой - Пачка сигарет.mp3";
        File file = new File(path);
        TextView audio_name= findViewById(R.id.audio_name);
        audio_name.setText(file.getName());
        mplayer = MediaPlayer.create(this, Uri.parse(path));
        confVideoActivity();
    }

    public void confVideoActivity(){
        Button go_to_video = (Button) findViewById(R.id.open_video);
        go_to_video.setOnClickListener(new View.OnClickListener(){
            @Override
             public void onClick(View view){
                   startActivity(new Intent(MainActivity.this, VideoActivity.class));
             }
            });
    }

    public void play(View v) {
        if (mplayer == null){
            String path = Environment.getExternalStorageDirectory().getPath() + "/Download/Виктор Цой - Пачка сигарет.mp3";
            File file = new File(path);
            TextView audio_name= findViewById(R.id.audio_name);
            audio_name.setText(file.getName());
            mplayer = MediaPlayer.create(this, Uri.parse(path));
            mplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
            {
                public void onCompletion(MediaPlayer mp){
                    stopMPlayer();
                }
            });
            mplayer.start();
        }
        else
            mplayer.start();
    }
    public void pause(View v) {
        if(mplayer!=null)
            mplayer.pause();
    }

    public void stop(View v) {
        stopMPlayer();
    }

    private void stopMPlayer(){
        if(mplayer!=null){
            mplayer.release();
            mplayer=null;
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        stopMPlayer();
    }
}

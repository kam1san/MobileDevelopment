package com.example.lab4_2;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

public class VideoActivity extends AppCompatActivity {
Button video_play; VideoView v_view; MediaController cont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        video_play=findViewById(R.id.video_play);
        v_view=findViewById(R.id.videoView);
        cont= new MediaController(this);

        video_play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                play_video();
            }
        });

        confBackButton();
    }

    private void confBackButton(){
        Button back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void play_video(){
        String path = Environment.getExternalStorageDirectory().getPath() + "/Download/Upper Palatinate - 31556.mp4";
        File file = new File(path);
        v_view.setVideoURI(Uri.parse(path));
        v_view.setMediaController(cont);
        cont.setAnchorView(v_view);
        v_view.start();
    }
}

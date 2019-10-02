package com.example.blood_bank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class video extends AppCompatActivity {

    VideoView vv;
    MediaController mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        vv = (VideoView) findViewById(R.id.videoView);
        mc = new MediaController(this);

        vv.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.dblood);

        mc.setAnchorView(vv);

        vv.setMediaController(mc);

        vv.start();
    }
}

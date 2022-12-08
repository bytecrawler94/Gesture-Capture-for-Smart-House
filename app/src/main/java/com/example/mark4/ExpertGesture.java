package com.example.mark4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class ExpertGesture extends AppCompatActivity {

    private static final String TAG = "ExpertGesture: ";
    TextView tv;
    VideoView expertVideo;
    Button practiceButton;
    Button repeatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert_gesture);

        tv = findViewById(R.id.normal_text_view);
        practiceButton = (Button) findViewById(R.id.btn_practice);
        repeatButton = (Button) findViewById(R.id.btn_repeat);

        Bundle b = getIntent().getExtras();
        int expGestSel = b.getInt("GestureSelected");

        System.out.println(TAG + "Selected Gesture Imported: " + expGestSel);

        expertVideo = (VideoView) findViewById(R.id.vv_expert_act);
        String uri = "android.resource://" + getPackageName() + "/R.raw.vid" + expGestSel;

        playVideo(expGestSel);

        repeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0; i<3; i++)
                    playVideo(expGestSel);
            }
        });

        practiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent practiceGestureIntent;

                practiceGestureIntent = new Intent(ExpertGesture.this, PracticeGestureCapture.class);
                practiceGestureIntent.putExtra("GestureSelected", expGestSel);
                startActivity(practiceGestureIntent);
            }
        });

    }

    private void playVideo(int expGestSel) {
        switch(expGestSel) {
            case 0:
                expertVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid1));
                break;
            case 1:
                expertVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid2));
                break;
            case 2:
                expertVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid3));
                break;
            case 3:
                expertVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid4));
                break;
            case 4:
                expertVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid5));
                break;
            case 5:
                expertVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid6));
                break;
            case 6:
                expertVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid7));
                break;
            case 7:
                expertVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid8));
                break;
            case 8:
                expertVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid9));
                break;
            case 9:
                expertVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid10));
                break;
            case 10:
                expertVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid11));
                break;
            case 11:
                expertVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid12));
                break;
            case 12:
                expertVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid13));
                break;
            case 13:
                expertVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid14));
                break;
            case 14:
                expertVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid15));
                break;
            case 15:
                expertVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid16));
                break;
            case 16:
                expertVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid17));
                break;
        }

        //media controller code for more better UI
        MediaController mediaController = new MediaController(this);
        mediaController.setEnabled(true);
        mediaController.show();

        expertVideo.setMediaController(mediaController);
        expertVideo.start();
    }
}
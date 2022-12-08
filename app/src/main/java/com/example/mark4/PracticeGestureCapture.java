package com.example.mark4;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
//import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



//import javax.security.auth.callback.Callback;

public class PracticeGestureCapture extends AppCompatActivity {

    Button recordVideoButton;
    Button uploadVideoButton;
    public static int [] videoCounter = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private Intent main;
    public String video;
    public String videoName;
    public static final int CAMERA_PERMISSION_REQUEST = 1996;
    public static final int WRITE_EXTERNAL_STORAGE_REQUEST =112;
    private static final int VIDE_CAPTURE = 101;
    private Uri uriForFile;
    public int practiceNum = 0;
    public String gestureitem;
    private static int VIDEO_REQUEST=201;
    public String ipv4AddressServer="192.168.0.12";
    public String portNumber="5000";
    public String videolocation;
    public File videoFile;
    String filePath;
    public int videoIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_gesture_capture);

        Bundle b = getIntent().getExtras();
        int expGestSel = b.getInt("GestureSelected");

        if (expGestSel == 0) {
            video = String.valueOf(R.raw.vid1);
            videoName = "lighton";
            videoIndex = 0;
        } else if (expGestSel == 1) {
            video = String.valueOf(R.raw.vid2);
            videoName = "lightoff";
            videoIndex = 1;
        } else if (expGestSel == 2) {
            video = String.valueOf(R.raw.vid3);
            videoName = "fanon";
            videoIndex = 2;
        } else if (expGestSel == 3) {
            video = String.valueOf(R.raw.vid4);
            videoName = "fanoff";
            videoIndex = 3;
        } else if (expGestSel == 4) {
            video = String.valueOf(R.raw.vid5);
            videoName = "increasefanspeed";
            videoIndex = 4;
        } else if (expGestSel == 5) {
            video = String.valueOf(R.raw.vid6);
            videoName = "decreasefanspeed";
            videoIndex = 5;
        } else if (expGestSel == 6) {
            video = String.valueOf(R.raw.vid7);
            videoName = "setthermo";
            videoIndex = 6;
        } else if (expGestSel == 7) {
            video = String.valueOf(R.raw.vid8);
            videoName = "h0";
            videoIndex = 7;
        } else if (expGestSel == 8) {
            video = String.valueOf(R.raw.vid9);
            videoName = "h1";
            videoIndex = 8;
        } else if (expGestSel == 9) {
            video = String.valueOf(R.raw.vid10);
            videoName = "h2";
            videoIndex = 9;
        } else if (expGestSel == 10) {
            video = String.valueOf(R.raw.vid11);
            videoName = "h3";
            videoIndex = 10;
        } else if (expGestSel == 11) {
            video = String.valueOf(R.raw.vid12);
            videoName = "h4";
            videoIndex = 11;
        } else if (expGestSel == 12) {
            video = String.valueOf(R.raw.vid13);
            videoName = "h5";
            videoIndex = 12;
        } else if (expGestSel == 13) {
            video = String.valueOf(R.raw.vid14);
            videoName = "h6";
            videoIndex = 13;
        } else if (expGestSel == 14) {
            video = String.valueOf(R.raw.vid15);
            videoName = "h7";
            videoIndex = 14;
        } else if (expGestSel == 15) {
            video = String.valueOf(R.raw.vid16);
            videoName = "h8";
            videoIndex = 15;
        } else if (expGestSel == 16) {
            video = String.valueOf(R.raw.vid17);
            videoName = "h9";
            videoIndex = 16;
        }

        main = new Intent(this, MainActivity.class);
        recordVideoButton = (Button) findViewById(R.id.btn_record_video);

        recordVideoButton.setOnClickListener((view) -> {
            this.StartCamera();
        });

        uploadVideoButton = (Button) findViewById(R.id.btn_upload_video);

        uploadVideoButton.setOnClickListener((view) -> {
            Log.d("UPLOAD", "upload function called");
            BeginUpload(filePath);
            Log.d("UPLOAD", "upload function called");
        });
    }

    private void StartCamera() {
        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            this.StartRecording();
        } else {
            String[] permissionReq = {Manifest.permission.CAMERA};
            requestPermissions(permissionReq, CAMERA_PERMISSION_REQUEST);

            String[] permissionReq2 = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            requestPermissions(permissionReq2, WRITE_EXTERNAL_STORAGE_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                this.StartRecording();
            } else {
                this.finish();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void StartRecording() {
        Context ctx = getApplicationContext();
        String videoPath = ctx.getExternalFilesDir(null).getAbsolutePath();
        videoFile = new File(videoPath, getFileName(videoCounter[videoIndex]));
        uriForFile =  FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".fileprovider", videoFile);

        Intent cameraIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 4);
        cameraIntent.putExtra("android.intent.extras.CAMERA_FACING", 1);

        startActivityForResult(cameraIntent, VIDEO_REQUEST);
    }

    public void BeginUpload(String videoURL) {
        String filename = "";
        File fstream = null;
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.INTERNET,Manifest.permission.ACCESS_NETWORK_STATE};
        requestPermissions(permissions,1);

        String postUrl = "http://" + ipv4AddressServer + ":" + portNumber + "/";

        RequestBody postBodyImage = null;

        filename = getFileName(videoCounter[videoIndex]-1);

        try
        {
            fstream = new File(videoURL);

            postBodyImage = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("image",
                            filename, RequestBody.create(MediaType.parse("video/*"), fstream))
                    .build();

        }
        catch (Exception ioexp) {
            ioexp.printStackTrace();
        }

        postRequest(postUrl, postBodyImage);

    }

    public String getFileName(int num) {
        return videoName + "_PRACTICE_"+ (num + 1) + "_Bawane.mp4";
    }

    public void postRequest(String postUrl,RequestBody httpRequestBody) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(postUrl)
                .post(httpRequestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PracticeGestureCapture.this, "Video is uploaded to server !!!", Toast.LENGTH_SHORT).show();
                        Intent HomePage = new Intent(PracticeGestureCapture.this, MainActivity.class);
                        finish();
                        startActivity(HomePage);
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
            }
        });
    }

    public String getPathFromURI(Context context, Uri contentUri) {

        if ( contentUri.toString().indexOf("file:///") > -1 ){
            return contentUri.getPath();
        }

        Cursor thisCursor = null;
        try {
            String[] temp = { MediaStore.Images.Media.DATA };
            thisCursor = context.getContentResolver().query(contentUri,  temp, null, null, null);
            int column_index = thisCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            thisCursor.moveToFirst();
            return thisCursor.getString(column_index);
        }finally {
            if (thisCursor != null) {
                thisCursor.close();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == VIDEO_REQUEST && resultCode == RESULT_OK) {

            if(intent.getData()!=null) {
                filePath = getPathFromURI(getApplicationContext(), intent.getData());
            }
            videoCounter[videoIndex] = (videoCounter[videoIndex] + 1) % 3;

        } else {
            Toast.makeText(this, "Recording Cancelled", Toast.LENGTH_LONG).show();
            super.onActivityResult(requestCode, resultCode, intent);
        }
    }



}
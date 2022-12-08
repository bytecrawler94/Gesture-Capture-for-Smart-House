package com.example.mark4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {



    String[] ftv_welcome = {"Welcome, Sir", "How can I help you ?"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        fadingTextView =findViewById(R.id.tv_hello);
//        fadingTextView.setTexts(ftv_welcome);

//        /* Below handler stops the animation in 3 seconds */
/*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fadingTextView.stop();
            }
        }, 3000);
*/
        Spinner spinner = (Spinner) findViewById(R.id.spin_gesture_list);

        Button nextButton = (Button) findViewById(R.id.btn_selectGestureAndMove);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.gesture_list, android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        final Intent[] expertGestureIntent = new Intent[1];

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onClick(View v) {

            }

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {


                expertGestureIntent[0] = new Intent(MainActivity.this, ExpertGesture.class);
                expertGestureIntent[0].putExtra("GestureSelected", position);
//                startActivity(expertGestureIntent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(expertGestureIntent[0]);
            }
        });
    }
}
package com.forif.park.delion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class More_Main extends AppCompatActivity {

    Button button_app;
    Button button_dev;

    private final long   FINSH_INTERVAL_TIME    = 2000;
    private long      backPressedTime        = 0;
    @Override
    public void onBackPressed() {
        long tempTime        = System.currentTimeMillis();
        long intervalTime    = tempTime - backPressedTime;

        if ( 0 <= intervalTime && FINSH_INTERVAL_TIME >= intervalTime ) {
            super.onBackPressed();
        }
        else {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(),"'뒤로'버튼을 한 번 더 누르면 종료.",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_main);

        getSupportActionBar().setTitle("더 보기");

        button_app = (Button)findViewById(R.id.button_intro_app);
        button_dev = (Button)findViewById(R.id.button_intro_dev);


        button_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), More_intro_app.class);
                startActivity(intent);
            }
        });
        button_dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), More_intro_dev.class);
                startActivity(intent);
            }
        });



    }



}

package com.example.imagetotext;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity1 extends AppCompatActivity {

    Button abc;
    LinearLayout l1,l2;
    static MediaPlayer as;
    Animation uptodown,downtoup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        abc=(Button)findViewById(R.id.buttonsub);

    }
    @Override
    protected void onResume(){
        super.onResume();
        abc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // as.start();
                Intent ii =new Intent(MainActivity1.this,selection.class);
                startActivity(ii);

            }
        });

    }

}

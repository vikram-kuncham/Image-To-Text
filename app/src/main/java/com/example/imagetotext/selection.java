package com.example.imagetotext;

import android.content.Intent;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class selection extends AppCompatActivity implements View.OnClickListener{
    private CardView card3,card2,card1,card4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        card3=(CardView)findViewById(R.id.card1234);
        card2=(CardView)findViewById(R.id.card12345);
        card4=(CardView)findViewById(R.id.card123456);
        card1=(CardView)findViewById(R.id.card1234567);

        card3.setOnClickListener(this);
        card2.setOnClickListener(this);
        card4.setOnClickListener(this);
        card1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.card1234) {
            Intent ii = new Intent(selection.this, MainActivity.class);
            startActivity(ii);
        } else if (id == R.id.card12345) {
            Intent iz = new Intent(selection.this, MainActivity2.class);
            startActivity(iz);
        } else if (id == R.id.card123456) {
            Intent io = new Intent(selection.this, scrollview.class);
            startActivity(io);
        }


    }
}


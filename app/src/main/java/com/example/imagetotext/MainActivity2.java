package com.example.imagetotext;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.speech.RecognizerIntent;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {

    private EditText mResultEt;
    private static final int WRITE_EXTERNAL_STORAGE_CODE=1;
    String mstring;
    private Button button_save;
    private ActivityResultLauncher<Void> speechRecognitionLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mResultEt = (EditText) findViewById(R.id.txvResult);
        button_save=(Button)findViewById(R.id.button_save2);
        speechRecognitionLauncher = registerForActivityResult(new SpeechRecognitionContract(),
                result -> {
                    if (result != null && !result.isEmpty()) {
                        String recognizedText = result.get(0);
                        mResultEt.setText(recognizedText);
                    }
                });
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mstring= mResultEt.getText().toString().trim();
                if(mstring.isEmpty()){
                    Toast.makeText(MainActivity2.this, "No text to save", Toast.LENGTH_SHORT).show();

                }
                else{
                    try {
                        ClipboardManager myClipboard;
                        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                        ClipData myClip;
                        myClip = ClipData.newPlainText("text", mstring);
                        myClipboard.setPrimaryClip(myClip);
                        Toast.makeText(MainActivity2.this, "Copied Text to ClipBoard", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(MainActivity2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });




    }

    public void getSpeechInput(View view) {
        speechRecognitionLauncher.launch(null);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){
            case WRITE_EXTERNAL_STORAGE_CODE:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED ){
                    saveToText(mstring);
                }
                else{
                    Toast.makeText(MainActivity2.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void saveToText(String mstring) {
        String timestamp=new SimpleDateFormat("yyyyMMdd_HHmmss",Locale.getDefault()).format(System.currentTimeMillis());
        try{

            File path= Environment.getExternalStorageDirectory();
            File dir=new File(path+"/Image To Text/");
            dir.mkdir();
            String filename="VoiceFile_ "+timestamp+".txt";
            File file=new File(dir,filename);
            FileWriter fw=new FileWriter(file.getAbsoluteFile());
            BufferedWriter bf=new BufferedWriter(fw);
            bf.write(mstring);
            bf.close();
            Toast.makeText(MainActivity2.this, filename+" is saved to\n "+dir, Toast.LENGTH_LONG).show();

        }
        catch (Exception e){
            Toast.makeText(MainActivity2.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }



    }
}


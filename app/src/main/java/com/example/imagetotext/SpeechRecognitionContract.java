package com.example.imagetotext;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Locale;

public class SpeechRecognitionContract extends ActivityResultContract<Void, ArrayList<String>> {
    @NonNull
    @Override
    public Intent createIntent(@NonNull Context context, Void input) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        return intent;
    }

    @Override
    public ArrayList<String> parseResult(int resultCode, @Nullable Intent intent) {
        if (resultCode == Activity.RESULT_OK && intent != null) {
            return intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
        }
        return null;
    }
}

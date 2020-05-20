package com.example.android.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        String question = intent.getStringExtra("question");
        String answer = intent.getStringExtra("answer");
        TextView questionView = findViewById(R.id.question);
        TextView answerView = findViewById(R.id.answer);
        questionView.setText(question);
        answerView.setText(answer);
    }
}

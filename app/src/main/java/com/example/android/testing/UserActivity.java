package com.example.android.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class UserActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }

    public void openMain(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void openArticle(View view) {
        int id = view.getId();
        String question = view.getTag().toString();
        String answer = "null";
        switch (id) {
            case R.id.q1:
                answer = getString(R.string.a1);
                break;
            case R.id.q2:
                answer = getString(R.string.a2);
                break;
            case R.id.q3:
                answer = getString(R.string.a3);
                break;
            case R.id.q4:
                answer = getString(R.string.a4);
                break;
            case R.id.q5:
                answer = getString(R.string.a5);
                break;
            case R.id.q6:
                answer = getString(R.string.a6);
                break;
            case R.id.q7:
                answer = getString(R.string.a7);
                break;
            case R.id.q8:
                answer = getString(R.string.a8);
                break;
            case R.id.q9:
                answer = getString(R.string.a9);
                break;
            case R.id.q10:
                answer = getString(R.string.a10);
                break;
            case R.id.q11:
                answer = getString(R.string.a11);
                break;
            case R.id.q12:
                answer = getString(R.string.a12);
                break;
            case R.id.q13:
                answer = getString(R.string.a13);
                break;
            case R.id.q14:
                answer = getString(R.string.a14);
                break;
            case R.id.q15:
                answer = getString(R.string.a15);
                break;
            case R.id.q16:
                answer = getString(R.string.a16);
                break;
            case R.id.q17:
                answer = getString(R.string.a17);
                break;
            case R.id.q18:
                answer = getString(R.string.a18);
                break;
            case R.id.q19:
                answer = getString(R.string.a19);
                break;
            case R.id.q20:
                answer = getString(R.string.a20);
                break;
            case R.id.q21:
                answer = getString(R.string.a21);
                break;
            case R.id.q22:
                answer = getString(R.string.a22);
                break;
            case R.id.q23:
                answer = getString(R.string.a23);
                break;
            case R.id.q24:
                answer = getString(R.string.a24);
                break;
            default:
                return;

        }


        Intent intent = new Intent(this,ArticleActivity.class);
        intent.putExtra("question", question);
        intent.putExtra("answer", answer);
        startActivity(intent);

    }
}

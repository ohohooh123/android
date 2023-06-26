package com.jys.test30;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JavaActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);

        TextView tvResult = findViewById((R.id.tvResult));
        tvResult.setText(getIntent().getExtras().getString("이름") + "님 환영합니다");
    }
}

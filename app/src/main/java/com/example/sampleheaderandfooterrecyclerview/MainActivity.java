package com.example.sampleheaderandfooterrecyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sampleheaderandfooterrecyclerview.activitys.GridLayoutActivity;
import com.example.sampleheaderandfooterrecyclerview.activitys.HeaderActivity;
import com.example.sampleheaderandfooterrecyclerview.activitys.HeaderAndFooterActivity;
import com.example.sampleheaderandfooterrecyclerview.activitys.LoadMoreActivity;
import com.example.sampleheaderandfooterrecyclerview.activitys.NormalActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button normal = findViewById(R.id.normal);
        Button loadMore = findViewById(R.id.loadMore);
        Button header = findViewById(R.id.header);
        Button headerAndFooter = findViewById(R.id.headerAndFooter);
        Button grid = findViewById(R.id.grid);
        normal.setOnClickListener(this);
        loadMore.setOnClickListener(this);
        header.setOnClickListener(this);
        headerAndFooter.setOnClickListener(this);
        grid.setOnClickListener(this);
     }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.normal:
                Intent intentNormal = new Intent(MainActivity.this,NormalActivity.class);
                startActivity(intentNormal);
                break;
            case R.id.loadMore:
                Intent intentLoadMore = new Intent(MainActivity.this,LoadMoreActivity.class);
                startActivity(intentLoadMore);
                break;
            case R.id.header:
                Intent intentHeader = new Intent(MainActivity.this,HeaderActivity.class);
                startActivity(intentHeader);
                break;
            case R.id.headerAndFooter:
                Intent intentHeaderAndFooter = new Intent(MainActivity.this,HeaderAndFooterActivity.class);
                startActivity(intentHeaderAndFooter);
                break;
            case R.id.grid:
                Intent grid = new Intent(MainActivity.this,GridLayoutActivity.class);
                startActivity(grid);
                break;
        }
    }
}

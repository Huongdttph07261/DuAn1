package com.example.game2048;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.Toolbar;

import com.example.game2048.adapter.ItemAdapter;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    private GridView griPlay;
    private ItemAdapter itemAdapter;
    private ClipData.Item item;
    private View.OnTouchListener listener;
    private float X, Y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("2048"); //Thiết lập tiêu đề nếu muốn
        String title = actionBar.getTitle().toString(); //Lấy tiêu đề nếu muốn

        anhXa();
        khoitao();
        setData();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }


    private void anhXa() {
        griPlay = findViewById(R.id.gdvGamePlay);
    }

    private void khoitao() {
        DataGame.getDataGame().intt(MainActivity.this);
        itemAdapter = new ItemAdapter(MainActivity.this, 0, DataGame.getDataGame().getArrSo());

        listener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        X = event.getX();
                        Y = event.getY();
                        break;

                    case MotionEvent.ACTION_UP:
                        if (Math.abs(event.getX() - X) > Math.abs(event.getY() - Y)) {
                            if (event.getX() < X) {
                                DataGame.getDataGame().vuotTrai();
                                itemAdapter.notifyDataSetChanged();
                            } else {
                                DataGame.getDataGame().vuotPhai();
                                itemAdapter.notifyDataSetChanged();
                            }
                        } else {
                            if (event.getY() < Y) {
//
                                DataGame.getDataGame().vuotLen();
                                itemAdapter.notifyDataSetChanged();
                            } else {
                                DataGame.getDataGame().vuotXuong();
                                itemAdapter.notifyDataSetChanged();
                            }

                        }
                        break;
                }

                return true;
            }
        };
    }


    private void setData() {
        griPlay.setAdapter(itemAdapter);
        griPlay.setOnTouchListener(listener);
    }

}

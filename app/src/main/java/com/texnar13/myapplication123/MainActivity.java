package com.texnar13.myapplication123;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements ExitClickAble, AbleCreateRedactorActivity {

    static int clickCount = 0;
    private static final String CLICK_COUNT_PREF = "clickCount";

    Menu menu;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.activity_main_menu_title:
                Toast.makeText(this, "Нажат: title", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_main_menu_app:
                Toast.makeText(this, "Нажат: app", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_main_menu_settings:

                if (menu != null) {
                    onPrepareOptionsMenu(menu);

                    item.setChecked(!item.isChecked());
                    menu.findItem(R.id.activity_main_menu_app).setVisible(
                            item.isChecked()
                    );

                }
                Toast.makeText(this, "Нажат: settings", Toast.LENGTH_SHORT).show();
                break;
        }

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("MyTag", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.base_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        // получение данных
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        clickCount = preferences.getInt(CLICK_COUNT_PREF, 0);


        //if (preferences.contains(CLICK_COUNT_PREF))


        TextView text = findViewById(R.id.main_text);

        text.setText(Integer.toString(clickCount));

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCount++;
                text.setText(Integer.toString(clickCount));
            }
        });
    }


    @Override
    public void onBackPressed() {


        if (canExit) {
            super.onBackPressed();
        } else {
            ExitDialog dialog = new ExitDialog();
            dialog.show(getSupportFragmentManager(), "ExitDialog");
        }
    }


    boolean canExit = false;


    @Override
    public void dialogExitYes() {
        canExit = true;
        onBackPressed();
    }



    @Override
    public void startRedactorActivity(int width, int height) {
        Intent intent = new Intent(this, MyBasicActivity.class);
        intent.putExtra(MyBasicActivity.ARG_WIDTH, width);
        intent.putExtra(MyBasicActivity.ARG_HEIGHT, height);
        startActivity(intent);
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e("MyTag", "onPause");


        // сохранение данных
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(CLICK_COUNT_PREF, clickCount);
        editor.apply();
        //getPreferences(Context.MODE_PRIVATE).edit().putInt(CLICK_COUNT_PREF, clickCount).apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("MyTag", "onDestroy");


    }


}

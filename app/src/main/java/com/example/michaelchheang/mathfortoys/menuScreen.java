package com.example.michaelchheang.mathfortoys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class menuScreen extends AppCompatActivity {
    private MediaPlayer bEffect;
    private TextView money;
    private gameplay game;
    private SharedPreferences load;
    private static int coins;
    private static int coinsWon = 0;
    private static final String PREF_NAME = "CurrencyFolder";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);
        load = getSharedPreferences(PREF_NAME, 0);
        coins = load.getInt("coins", 0);
        money = (TextView) findViewById(R.id.currency);

        bEffect = MediaPlayer.create(this,R.raw.button_click);

        init();
    }

    public void init(){
        updateCurrency();
        money.setText("S Coins: " + String.valueOf(getCurrency()));
        Button playButton = (Button) findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                bEffect.start();
                startActivity(new Intent(menuScreen.this, selectGrade.class));
            }
        });
        Button setting = (Button) findViewById(R.id.settingsButton);
        setting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                bEffect.start();
                startActivity(new Intent(menuScreen.this, settingsPage.class));
            }
        });
        Button store = findViewById(R.id.storeButton);
        store.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                bEffect.start();
                startActivity(new Intent(menuScreen.this, store.class));
            }
        });

    }
    public void update(int n){
        coinsWon = n;
        updateCurrency();
    }
    public int getCoinsWon(){
        return coinsWon;
    }
    public void updateCurrency(){
        coins = coins + coinsWon;
        load = getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor save = load.edit();
        save.putInt("coins", coins);
        save.commit();
    }

    public int getCurrency(){
        return coins;
    }
}

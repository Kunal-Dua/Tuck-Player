package com.example.tuckplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    TextView textView;
    ImageView maroon5,coldplay,diamonds,applause,fireball,dark_horse;
    ImageView info,download;

    String[] songs_url = {"https://themamaship.com/music/Catalog/Maroon%205%20-%20This%20Love%20(Radio%20Edit).mp3",
            "https://themamaship.com/music/Catalog/Coldplay%20-%20Viva%20La%20Vida.mp3",
            "https://themamaship.com/music/Catalog/Rihanna%20-%20Diamonds%20(2012).mp3",
            "https://themamaship.com/music/Catalog/Katy%20Perry%20-%20Dark%20Horse%20(Audio)%20ft.%20Juicy%20J.mp3",
            "https://themamaship.com/music/Catalog/Lady%20Gaga%20-%20Applause%20(Official).mp3",
            "https://themamaship.com/music/Catalog/Fireball%20-%20Pitbull%20ft.%20John%20Ryan.mp3"
    };

    String[] songs_name = {"   Maroon 5 - This Love (Radio Edit)  ", "   Coldplay - Viva La Vida   ", "   Rihanna - Diamonds (2012)   ",
        "Katy Perry - Dark Horse (Audio) ft. Juicy J.mp3","Lady Gaga - Applause (Official).mp3","Fireball - Pitbull ft. John Ryan"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        mediaPlayer = new MediaPlayer();
        textView = findViewById(R.id.textView);
        maroon5 = findViewById(R.id.imageView1);
        coldplay = findViewById(R.id.imageView2);
        diamonds = findViewById(R.id.imageView3);
        dark_horse=findViewById(R.id.imageView4);
        applause=findViewById(R.id.imageView5);
        fireball=findViewById(R.id.imageView6);
        info=findViewById(R.id.info);
        download=findViewById(R.id.download);

        maroon5.setOnClickListener(new View.OnClickListener() {
            Integer current_pic = 0;

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, playSong.class);
                intent.putExtra("current song", songs_name[0].toString());
                intent.putExtra("current song url", songs_url[0].toString());
                intent.putExtra("current pic", current_pic.toString());
                startActivity(intent);
            }
        });
        coldplay.setOnClickListener(new View.OnClickListener() {
            Integer current_pic = 1;

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, playSong.class);

                intent.putExtra("current song", songs_name[1].toString());
                intent.putExtra("current song url", songs_url[1].toString());
                intent.putExtra("current pic", current_pic.toString());
                startActivity(intent);
            }
        });
        diamonds.setOnClickListener(new View.OnClickListener() {
            Integer current_pic = 2;

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, playSong.class);
                intent.putExtra("current song", songs_name[2].toString());
                intent.putExtra("current song url", songs_url[2].toString());
                intent.putExtra("current pic", current_pic.toString());
                startActivity(intent);
            }
        });
        dark_horse.setOnClickListener(new View.OnClickListener() {
            Integer current_pic = 3;

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, playSong.class);
                intent.putExtra("current song", songs_name[3].toString());
                intent.putExtra("current song url", songs_url[3].toString());
                intent.putExtra("current pic", current_pic.toString());
                startActivity(intent);
            }
        });
        applause.setOnClickListener(new View.OnClickListener() {
            Integer current_pic = 4;

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, playSong.class);
                intent.putExtra("current song", songs_name[4].toString());
                intent.putExtra("current song url", songs_url[4].toString());
                intent.putExtra("current pic", current_pic.toString());
                startActivity(intent);
            }
        });
        fireball.setOnClickListener(new View.OnClickListener() {
            Integer current_pic = 5;

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, playSong.class);
                intent.putExtra("current song", songs_name[5].toString());
                intent.putExtra("current song url", songs_url[5].toString());
                intent.putExtra("current pic", current_pic.toString());
                startActivity(intent);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,info_page.class);
                startActivity(intent);
            }
        });
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,downloads.class);
                startActivity(intent);
            }
        });
    }
}
package com.example.tuckplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

public class playSong extends AppCompatActivity {
    TextView textView;
    MediaPlayer mediaPlayer;
    ImageView play,next,previous,loop;
    SeekBar seekBar;
    String currentSong, currentSongURL, current_pic;
    Thread updateSeek;
    int position = 0;



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
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
        updateSeek.interrupt();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        mediaPlayer = new MediaPlayer();
        textView = findViewById(R.id.textView2);
        textView.setSelected(true);
        seekBar = findViewById(R.id.seekBar2);
        previous = findViewById(R.id.previous);
        next = findViewById(R.id.next);
        play = findViewById(R.id.play);
        loop=findViewById(R.id.loop);
        ImageView img = findViewById(R.id.imageView);
        Intent intent = getIntent();
        currentSong = intent.getStringExtra("current song");
        currentSongURL = intent.getStringExtra("current song url");
        current_pic = intent.getStringExtra("current pic");

        if (current_pic.equals("0")) {
            img.setImageResource(R.drawable.maroon5);
            position = 0;
        } else if (current_pic.equals("1")) {
            img.setImageResource(R.drawable.coldplay_viva_la_vida);
            position = 1;
        } else if (current_pic.equals("2")) {
            img.setImageResource(R.drawable.rihanna_diamonds_cover);
            position = 2;
        } else if (current_pic.equals("3")) {
            img.setImageResource(R.drawable.katy_perry_dark_horse);
            position = 3;
        } else if (current_pic.equals("4")) {
            img.setImageResource(R.drawable.applause_cover);
            position = 4;
        } else if (current_pic.equals("5")) {
            img.setImageResource(R.drawable.fireball);
            position = 5;
        } else
            img.setImageResource(R.drawable.music_icon);


        textView.setText(currentSong);

        //Start Playing Song
        try {
            mediaPlayer.setDataSource(currentSongURL);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seekBar.setMax(mp.getDuration());
                play.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24);
                mp.start();
            }
        });

        //play.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    play.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
                    mediaPlayer.pause();
                } else {
                    play.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24);
                    mediaPlayer.start();
                }
            }
        });

        updateSeek = new Thread(){
            @Override
            public void run() {
                int currentPosition = 0;
                try{
                    while(currentPosition<mediaPlayer.getDuration()){
                        currentPosition = mediaPlayer.getCurrentPosition();
                        seekBar.setProgress(currentPosition);
                        sleep(800);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        updateSeek.start();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                // resetting mediaPlayer instance to evade problems
                mediaPlayer.reset();//Important (causes crash if not resetting)
//                mediaPlayer.release(); //cause crash when releasing mediaPlayer

                if (position != songs_url.length -1) {
                    position = position + 1;
//                    current_pic=Integer.toString(position);
                } else {
                    position = 0;
//                    current_pic=Integer.toString(position);
                }

                //Start Playing Song
                try {
                    mediaPlayer.setDataSource(songs_url[position]);
                    mediaPlayer.prepareAsync();
                } catch (IOException e) {
                    Log.d("Check","ERROR");
                    e.printStackTrace();
                }
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                        play.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24);
                        seekBar.setMax(mediaPlayer.getDuration());
                    }
                });

                textView.setText(songs_name[position]);
                //Change image
                if(position==0) {
                    img.setImageResource(R.drawable.maroon5);
                }
                else if(position==1) {
                    img.setImageResource(R.drawable.coldplay_viva_la_vida);

                }
                else if(position==2) {
                    img.setImageResource(R.drawable.rihanna_diamonds_cover);
                }else if(position==3) {
                    img.setImageResource(R.drawable.katy_perry_dark_horse);
                }else if(position==4) {
                    img.setImageResource(R.drawable.applause_cover);
                }else if(position==5) {
                    img.setImageResource(R.drawable.fireball);
                }
                else
                    img.setImageResource(R.drawable.music_icon);
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                // resetting mediaPlayer instance to evade problems
                mediaPlayer.reset();//Important (causes crash if not resetting)
//                mediaPlayer.release(); //cause crash when releasing mediaPlayer

                if (position != 0) {
                    position = position - 1;
                    current_pic=Integer.toString(position);

                } else {
                    position = songs_url.length-1;
                    current_pic=Integer.toString(position);
                }

                //Start Playing Song
                try {
                    String current_song=songs_url[position];
                    mediaPlayer.setDataSource(current_song);
                    mediaPlayer.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                        play.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24);
                        seekBar.setMax(mediaPlayer.getDuration());
                    }
                });

                textView.setText(songs_name[position]);
                //Change image
                if(position==0) {
                    img.setImageResource(R.drawable.maroon5);
                }
                else if(position==1) {
                    img.setImageResource(R.drawable.coldplay_viva_la_vida);

                }
                else if(position==2) {
                    img.setImageResource(R.drawable.rihanna_diamonds_cover);
                }
                else if(position==3) {
                    img.setImageResource(R.drawable.katy_perry_dark_horse);
                }else if(position==4) {
                    img.setImageResource(R.drawable.applause_cover);
                }else if(position==5) {
                    img.setImageResource(R.drawable.fireball);
                }
                else
                    img.setImageResource(R.drawable.music_icon);
            }
        });

        loop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isLooping()) {
                    mediaPlayer.setLooping(false);
                    loop.setImageResource(R.drawable.loop);
                }
                else{
                    mediaPlayer.setLooping(true);
                    loop.setImageResource(R.drawable.loop_on);
                }
            }
        });

    }
}
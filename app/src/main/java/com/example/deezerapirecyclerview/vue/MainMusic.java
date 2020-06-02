package com.example.deezerapirecyclerview.vue;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deezerapirecyclerview.R;
import com.squareup.picasso.Picasso;

public class MainMusic extends AppCompatActivity {
    private String titreStr;
    private String album;
    private String artist;
    private int duration;
    private TextView titre;
    private TextView desc;
    private ImageButton retour;
    private String linkImage;
    private ImageView img;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        Intent intent = getIntent();
        this.titreStr = intent.getStringExtra("Titre");
        this.album = intent.getStringExtra("Album");
        this.artist = intent.getStringExtra("Artist");
        this.linkImage = intent.getStringExtra("Image");
        this.duration = Integer.parseInt(intent.getStringExtra("Duration"));

        titre = (TextView) findViewById(R.id.musicTitle);
        titre.setText(titreStr);
        img = (ImageView) findViewById(R.id.imageMusic);
        Picasso.get().load(linkImage).into(img);
        retour = (ImageButton) findViewById((R.id.returnButton2));
        desc = (TextView) findViewById(R.id.Description);
        desc.setText("Album : "+album+"\nArtiste : "+artist+"\nDurée : "+(duration/60)+":"+(duration%60)+" min");

        retour.setOnClickListener(e->{
            finish();
        });
    }
}
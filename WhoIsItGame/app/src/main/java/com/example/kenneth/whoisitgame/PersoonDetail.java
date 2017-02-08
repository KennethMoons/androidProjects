package com.example.kenneth.whoisitgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersoonDetail extends AppCompatActivity {

    @BindView(R.id.persoonDetailImage)ImageView persoonImage;
    @BindView(R.id.namePersoonDetail)TextView persoonNaam;
    @BindView(R.id.geslachtPersoonDetail)TextView persoonGeslacht;
    @BindView(R.id.brilPersoonDetail)TextView persoonBril;
    @BindView(R.id.huidskleurPersoonDetail)TextView persoonHuidskleur;
    @BindView(R.id.haarkleurPersoonDetail)TextView persoonHaarkleur;
    @BindView(R.id.snorPersoonDetail)TextView persoonSnor;
    @BindView(R.id.baardPersoonDetail)TextView persooBaard;
    @BindView(R.id.kaalPersoonDetail)TextView persoonKaal;
    @BindView(R.id.hoofddekselPersoonDetail)TextView persoonHoofdeksel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persoon_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        persoonImage.setImageResource(intent.getIntExtra("image",0));
        persoonNaam.setText("naam : " +  intent.getStringExtra("naam"));
        if(intent.getIntExtra("baard",0) == 1){
            persooBaard.setText("baard : " + " ja");
        }else{
            persooBaard.setText("baard : " + " neen");
        }
        if(intent.getIntExtra("bril",0) == 1){
            persoonBril.setText("bril : " + " ja");
        }else {
            persoonBril.setText("bril : " + " neen");
        }
        if(intent.getIntExtra("geslacht",0) == 1){
            persoonGeslacht.setText("geslacht : " + " vrouw");
        }else {
            persoonGeslacht.setText("geslacht : " + " man");
        }
        persoonHaarkleur.setText("kleur haar : " +  intent.getStringExtra("haarkleur"));
        if(intent.getIntExtra("kaal",0) == 1){
            persoonKaal.setText("kaal : " + " ja");
        }else{
            persoonKaal.setText("kaal : " + " neen");
        }
        if(intent.getIntExtra("hoofddeksel",0) == 1){
            persoonHoofdeksel.setText("hoofddeksel : " + " ja");
        }else{
            persoonHoofdeksel.setText("hoofddeksel : " + " neen");
        }
        persoonHaarkleur.setText("huidskleur : " + intent.getStringExtra("huidskleur"));
        if(intent.getIntExtra("snor",0) == 1){
            persoonSnor.setText("snor : " + " ja");
        }else{
            persoonSnor.setText("snor : "+ " neen");
        }
    }
}

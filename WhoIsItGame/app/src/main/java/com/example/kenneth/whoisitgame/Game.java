package com.example.kenneth.whoisitgame;

import android.content.ClipData;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Game extends AppCompatActivity {

    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.landscape_myFigureImage)ImageView landscape_myFigureImage;
    @BindView(R.id.landscape_myfiguretxt)TextView landscape_myfiguretxt;
    @BindView(R.id.reclycer_view)RecyclerView recyclerView;

    ArrayList<Persoon> personenlist;

    PersoonAdapterLandscape persoonAdapterLandscape;
    RecyclerView.LayoutManager layoutManager;

    PersoonAdapterPortrait persoonAdapterPortrait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        MyDbHandler myDbHandler = new MyDbHandler(this,null,null,1);
        personenlist = myDbHandler.databaseToList();
        myDbHandler.delete();
        myDbHandler.addPersonenBulk();
        personenlist = AddImages(personenlist);
        Random random = new Random();
        int keuze = random.nextInt(personenlist.size());
        landscape_myFigureImage.setImageResource(personenlist.get(keuze).get_imageId());
        landscape_myfiguretxt.setText(toonGegevens(personenlist.get(keuze)));
        landscape_myfiguretxt.setText(toonGegevens(personenlist.get(keuze)));

        recyclerView =(RecyclerView) findViewById(R.id.reclycer_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        if(getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT ){
            Log.i("orientatie","portrait");
            persoonAdapterPortrait = new PersoonAdapterPortrait(personenlist);
            recyclerView.setAdapter(persoonAdapterPortrait);
            ItemTouchHelper.SimpleCallback callback = new SwipeHelperPortrait(persoonAdapterPortrait);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
            itemTouchHelper.attachToRecyclerView(recyclerView);
            recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(getApplicationContext(),PersoonDetail.class);
                    int positie = recyclerView.getChildAdapterPosition(view);
                    Persoon persoon = personenlist.get(positie);
                    intent.putExtra("naam",persoon.get_naam());
                    intent.putExtra("geslacht",persoon.get_geslacht());
                    intent.putExtra("huidskleur",persoon.get_huidskleur());
                    intent.putExtra("haarkleur",persoon.get_kleurHaar());
                    intent.putExtra("hoofddeksel",persoon.get_hoofddeksel());
                    intent.putExtra("bril",persoon.get_bril());
                    intent.putExtra("baard",persoon.get_baard());
                    intent.putExtra("snor",persoon.get_snor());
                    intent.putExtra("kaal",persoon.get_kaal());
                    intent.putExtra("image",persoon.get_imageId());
                    startActivity(intent);
                }
            }));
        }else {
            Log.i("orientatie","landscape");
            persoonAdapterLandscape = new PersoonAdapterLandscape(personenlist);
            recyclerView.setAdapter(persoonAdapterLandscape);
            ItemTouchHelper.SimpleCallback calback = new SwipeHelper(persoonAdapterLandscape);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(calback);
            itemTouchHelper.attachToRecyclerView(recyclerView);
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            persoonAdapterLandscape = new PersoonAdapterLandscape(personenlist);
            recyclerView.setAdapter(persoonAdapterLandscape);
            ItemTouchHelper.SimpleCallback calback = new SwipeHelper(persoonAdapterLandscape);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(calback);
            itemTouchHelper.attachToRecyclerView(recyclerView);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            persoonAdapterPortrait = new PersoonAdapterPortrait(personenlist);
            recyclerView.setAdapter(persoonAdapterPortrait);
            ItemTouchHelper.SimpleCallback callback = new SwipeHelperPortrait(persoonAdapterPortrait);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
            itemTouchHelper.attachToRecyclerView(recyclerView);
        }
    }

    public ArrayList<Persoon>AddImages(ArrayList<Persoon>persons){
        for(Persoon p : persons){
            p.set_imageId(setImageToImageView(p.get_naam()));
        }
        return persons;
    }
    public int setImageToImageView(String naam){
        int resourceId = 0;
        if(naam.equals("alex"))
            resourceId = R.drawable.alex;
        if(naam.equals("alfred"))
            resourceId = R.drawable.alfred;
        if(naam.equals("anita"))
            resourceId = R.drawable.anita;
        if(naam.equals("anne"))
            resourceId = R.drawable.anne;
        if(naam.equals("bernard"))
            resourceId = R.drawable.bernard;
        if(naam.equals("bill"))
            resourceId = R.drawable.bill;
        if(naam.equals("charles"))
            resourceId = R.drawable.charles;
        if(naam.equals("claire"))
            resourceId = R.drawable.claire;
        if(naam.equals("david"))
            resourceId = R.drawable.david;
        if(naam.equals("eric"))
            resourceId = R.drawable.eric;
        if(naam.equals("frans"))
            resourceId = R.drawable.frans;
        if(naam.equals("george"))
            resourceId = R.drawable.george;
        if(naam.equals("herman"))
            resourceId = R.drawable.herman;
        if(naam.equals("joe"))
            resourceId = R.drawable.joe;
        if(naam.equals("maria"))
            resourceId = R.drawable.maria;
        if(naam.equals("max"))
            resourceId = R.drawable.max;
        if(naam.equals("paul"))
            resourceId = R.drawable.paul;
        if(naam.equals("peter"))
            resourceId = R.drawable.peter;
        if(naam.equals("philip"))
            resourceId = R.drawable.philip;
        if(naam.equals("richard"))
            resourceId = R.drawable.richard;
        if(naam.equals("robert"))
            resourceId = R.drawable.robert;
        if(naam.equals("sam"))
            resourceId = R.drawable.sam;
        if(naam.equals("susan"))
            resourceId = R.drawable.susan;
        if(naam.equals("tom"))
            resourceId = R.drawable.tom;
        return resourceId;
    }
    public String toonGegevens(Persoon p){

        String gegevens = "";
        gegevens = "Naam : " + p.get_naam() + "\n";
        if(p.get_geslacht() == 0){
            gegevens += "Geslacht : Man, ";
        }else{
            gegevens += "geslacht : Vrouw,";
        }
        gegevens += "met " + p.get_kleurHaar() + " haarskleur ";
        if(p.get_kaal()== 1)
            gegevens += ", kaal ";
        if(p.get_hoofddeksel() == 1)
            gegevens += " met een hoofdeksel";
        if(p.get_bril()== 1)
            gegevens += " en met een bril";
        gegevens += "\nhuidskleur : " + p.get_huidskleur();
        if(p.get_baard()== 1)
            gegevens += "met een baard";
        if(p.get_snor()== 1)
            gegevens += " en heeft een snor";
        return gegevens;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        startActivity(getIntent());
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }
}

package com.example.kenneth.whoisitgame;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kenneth on 24/01/2017.
 */

public class PersoonAdapterLandscape extends RecyclerView.Adapter<PersoonAdapterLandscape.PersoonLandscape_Viewholder>  {

    ArrayList<Persoon>persoons = new ArrayList<Persoon>();

    @Override
    public PersoonLandscape_Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_landscape,parent,false);
        PersoonLandscape_Viewholder persoonLandscape_viewholder = new PersoonLandscape_Viewholder(view);
        return persoonLandscape_viewholder;
    }

    public PersoonAdapterLandscape(ArrayList<Persoon>persons){
        this.persoons = persons;
    }

    @Override
    public void onBindViewHolder(PersoonLandscape_Viewholder holder, int position) {
        Persoon p = persoons.get(position);
        holder.person_img.setImageResource(p.get_imageId());
        holder.person_naam.setText("naam : " +  p.get_naam());
        if(p.get_baard()== 1){
            holder.person_baard.setText("baard : " + " ja");
        }else{
            holder.person_baard.setText("baard : " + " Neen");
        }
        if(p.get_bril()== 1){
            holder.person_bril.setText("bril : " + " ja");
        }else {
            holder.person_bril.setText("bril : " + " neen");
        }
        if(p.get_geslacht()== 0){
            holder.person_geslacht.setText("geslacht : " + " man");
        }else {
            holder.person_geslacht.setText("geslacht : " + " vrouw");
        }
        holder.person_haarskleur.setText("kleur haar : " +  p.get_kleurHaar());
        if(p.get_kaal()== 1){
            holder.person_kaal.setText("kaal : " + " ja");
        }else{
            holder.person_kaal.setText("kaal : " + " neen");
        }
        if(p.get_hoofddeksel() == 1){
            holder.person_hoofddeksel.setText("hoofddeksel : " + " ja");
        }else{
            holder.person_hoofddeksel.setText("hoofddeksel : " + " neen");
        }
        holder.person_huidskleur.setText("huidskleur : " + p.get_huidskleur());
        if(p.get_snor()==1){
            holder.person_snor.setText("snor : " + " ja");
        }else{
            holder.person_snor.setText("snor : "+ " neen");
        }
    }

    @Override
    public int getItemCount() {
        return persoons.size();
    }

    public void dismiss(int pos){
        persoons.remove(pos);
        this.notifyItemRemoved(pos);
    }

    public static class PersoonLandscape_Viewholder extends RecyclerView.ViewHolder{

        ImageView person_img;
        TextView person_geslacht,person_naam,person_haarskleur,person_kaal,person_hoofddeksel,person_bril,person_huidskleur,person_baard,person_snor;

        public PersoonLandscape_Viewholder(View itemView) {
            super(itemView);
            person_img = (ImageView)itemView.findViewById(R.id.imagePersoon_landscape);
            person_naam = (TextView)itemView.findViewById(R.id.namePersoon_landscape);
            person_geslacht = (TextView)itemView.findViewById(R.id.geslachtPersoon_landscape);

            person_haarskleur = (TextView)itemView.findViewById(R.id.haarskleurPersoon_landscape);
            person_kaal = (TextView)itemView.findViewById(R.id.kaalPersoon_landscape);
            person_hoofddeksel = (TextView)itemView.findViewById(R.id.hoofddekselPersoon_landscape);

            person_bril = (TextView)itemView.findViewById(R.id.brilPersoon_landscape);
            person_huidskleur = (TextView)itemView.findViewById(R.id.huidskleurPersoon_landscape);
            person_baard = (TextView)itemView.findViewById(R.id.baardPersoon_landscape);

            person_snor = (TextView)itemView.findViewById(R.id.snorPersoon_landscape);

        }
    }
}


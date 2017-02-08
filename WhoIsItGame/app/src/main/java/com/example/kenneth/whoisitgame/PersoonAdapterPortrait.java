package com.example.kenneth.whoisitgame;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kenneth on 28/01/2017.
 */

public class PersoonAdapterPortrait extends RecyclerView.Adapter<PersoonAdapterPortrait.PersoonPortrait_Viewholder> {

    ArrayList<Persoon> persoons = new ArrayList<Persoon>();
    @Override
    public PersoonPortrait_Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout,parent,false);
        PersoonPortrait_Viewholder persoonPortrait_viewholder = new PersoonPortrait_Viewholder(view);
        return persoonPortrait_viewholder;
    }

    public PersoonAdapterPortrait(ArrayList<Persoon> persoons) {
        this.persoons = persoons;
    }

    @Override
    public void onBindViewHolder(PersoonPortrait_Viewholder holder, int position) {
        Persoon p = persoons.get(position);
        holder.person_img.setImageResource(p.get_imageId());
        holder.person_naam.setText("naam : " +  p.get_naam());
        if(p.get_geslacht()== 0){
            holder.person_geslacht.setText("geslacht : " + " man");
        }else {
            holder.person_geslacht.setText("geslacht : " + " vrouw");
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

    public static class PersoonPortrait_Viewholder extends RecyclerView.ViewHolder{

        ImageView person_img;
        TextView person_geslacht,person_naam;

        public PersoonPortrait_Viewholder(View itemView) {
            super(itemView);
            person_img = (ImageView)itemView.findViewById(R.id.imagePersoon_landscape);
            person_naam = (TextView)itemView.findViewById(R.id.namePersoon_landscape);
            person_geslacht = (TextView)itemView.findViewById(R.id.geslachtPersoon_landscape);

        }
    }
}

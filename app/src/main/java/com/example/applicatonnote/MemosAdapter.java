package com.example.applicatonnote;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.parceler.Parcels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class MemosAdapter extends RecyclerView.Adapter<MemosAdapter.MemoViewHolder>
{
    final static String LASTITEMSHARED="lastMemoClicked";
    // Liste d'objets métier :
    private List<MemoDTO> listeMemos;
    private AppCompatActivity activity;
    // Constructeur :
    public MemosAdapter(List<MemoDTO> listeMemos, AppCompatActivity activity)
    {
        this.listeMemos=listeMemos;
        this.activity=activity;
    }
    @Override
    public MemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View viewMemo = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.memo_item_liste, parent, false);
        return new MemoViewHolder(viewMemo);
    }
    @Override
    public void onBindViewHolder(MemoViewHolder holder, int position)
    {
        holder.textViewLibelleCourse.setText(listeMemos.get(position).text);
    }
    @Override
    public int getItemCount()
    {
        return listeMemos.size();
    }
    public boolean onItemMove(int positionDebut, int positionFin)
    {
        Collections.swap(listeMemos, positionDebut, positionFin);
        notifyItemMoved(positionDebut, positionFin);
        return true;
    }
    // Appelé une fois à la suppression.
    public void onItemDismiss(int position)
    {
        if (position > -1)
        {
            listeMemos.remove(position);
            notifyItemRemoved(position);
        }
    }
    public class MemoViewHolder extends RecyclerView.ViewHolder
    {
        // TextView intitulé course :
        public TextView textViewLibelleCourse;
        // Constructeur :
        public MemoViewHolder(View itemView)
        {
            super(itemView);
            textViewLibelleCourse = itemView.findViewById(R.id.text_memo);

            textViewLibelleCourse.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    SharedPreferences preferences =
                            PreferenceManager.getDefaultSharedPreferences(view.getContext());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt(LASTITEMSHARED, getAdapterPosition());
                    editor.apply();
                    MemoDTO memo = listeMemos.get(getAdapterPosition());
                    //Toast.makeText(view.getContext(), course.text,Toast.LENGTH_LONG).show();
                    NetworkState.callRestApi(memo.text,view.getContext());
                    if(activity.findViewById(R.id.FragmentLayout) != null)
                    {
                        DetailFragment fragment = new DetailFragment();
                        //Passage de paramètres
                        Bundle bundle = new Bundle();
                        bundle.putString(DetailFragment.ARG_PARAM1, memo.text);
                        fragment.setArguments(bundle);
                        //Ajout du fragment au layout de la main activity
                        FragmentManager fragmentManager = activity.getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.FragmentLayout, fragment, "detail_fragment");
                        fragmentTransaction.commit();
                    }
                    //Mode portrait
                    else
                    {
                        //Lancement d'une nouvelle activité et passage de l'objet mémo
                        Intent intent = new Intent(view.getContext(), DetailActivity.class);
                        intent.putExtra(DetailActivity.PARAMETER, Parcels.wrap(memo));
                        view.getContext().startActivity(intent);
                    }
                }
            });
        }
    }
}
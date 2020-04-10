package com.example.applicatonnote;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class MemosAdapter extends RecyclerView.Adapter<MemosAdapter.MemoViewHolder>
{
    // Liste d'objets métier :
    private List<Memo> listeMemos;
    // Constructeur :
    public MemosAdapter(List<Memo> listeMemos)
    {
        this.listeMemos=listeMemos;
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

                    Memo course = listeMemos.get(getAdapterPosition());
                    Toast.makeText(view.getContext(), course.text,Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
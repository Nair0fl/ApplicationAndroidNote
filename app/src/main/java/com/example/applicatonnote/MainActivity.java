package com.example.applicatonnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MemosAdapter memosAdapter;
    private List<MemoDTO> listeMemo;
    private EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppDatabaseHelper.getDatabase(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.liste_memos);
        this.mEdit= (EditText)findViewById(R.id.newMemo);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        this.listeMemo = new ArrayList<>();
        this.listeMemo= AppDatabaseHelper.getDatabase(this).memosDAO().getListeMemo();

        this.memosAdapter = new MemosAdapter(listeMemo,this);
        recyclerView.setAdapter(this.memosAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new MemoTouchHelperCallback(memosAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        int valeur = preferences.getInt(MemosAdapter.LASTITEMSHARED, 0);
        Toast.makeText(this,Integer.toString(valeur),Toast.LENGTH_LONG).show();

    }

    public void addMemo(View view){
        MemoDTO memo=new MemoDTO(this.mEdit.getText().toString());
        this.listeMemo.add(memo);
        AppDatabaseHelper.getDatabase(this).memosDAO().insert(memo);
        memosAdapter.notifyItemInserted(this.listeMemo.size());
        this.mEdit.setText("");
    }

}

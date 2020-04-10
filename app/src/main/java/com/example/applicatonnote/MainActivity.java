package com.example.applicatonnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MemosAdapter memosAdapter;
    private List<Memo> listeMemo;
    private EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.liste_memos);
        this.mEdit= (EditText)findViewById(R.id.newMemo);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        this.listeMemo = new ArrayList<>();
        for(int i=0;i<50;i++){
            this.listeMemo.add(new Memo("Memo "+i));
        }

        this.memosAdapter = new MemosAdapter(listeMemo);
        recyclerView.setAdapter(this.memosAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new MemoTouchHelperCallback(memosAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public void addMemo(View view){
        this.listeMemo.add(new Memo(this.mEdit.getText().toString()));
        memosAdapter.notifyItemInserted(this.listeMemo.size());
        this.mEdit.setText("");
    }

}

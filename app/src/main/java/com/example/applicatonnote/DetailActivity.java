package com.example.applicatonnote;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {

    private TextView mTextView;
    static String PARAMETER = "memo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        DetailFragment fragment = new DetailFragment();
        MemoDTO memo = Parcels.unwrap(getIntent().getParcelableExtra(PARAMETER));

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString(DetailFragment.ARG_PARAM1, memo.text);
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_detail, fragment, "exemple2");
        fragmentTransaction.commit();

    }
}

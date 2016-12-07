package com.example.guest.messagingboard.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.messagingboard.R;
import com.example.guest.messagingboard.model.Category;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.button) Button mButton;
    @Bind(R.id.listView) ListView mList;

    private DatabaseReference mCatagoryReference;
    private ValueEventListener mCatagoryReferenceListener;

    private FirebaseListAdapter<Category> mCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mCatagoryReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("catagories");

        mCatagoryReferenceListener = mCatagoryReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        mCategory = new FirebaseListAdapter<Category>(this, Category.class, R.layout.category_list, mCatagoryReference) {

            @Override
            protected void populateView(View v, Category model, int position) {
                ((TextView)v.findViewById(R.id.category)).setText(model.getName());

                model.setPushId(getRef(position).getKey());
            }
        };

        mList.setAdapter(mCategory);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                intent.putExtra("category", Parcels.wrap(mCategory.getItem(position)));
                startActivity(intent);

            }
        });

        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent myIntent = new Intent(MainActivity.this, NewCategory.class);
        startActivity(myIntent);
    }
}

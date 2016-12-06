package com.example.guest.messagingboard.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.guest.messagingboard.R;
import com.example.guest.messagingboard.model.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewCategory extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.addCategory) Button mAdd;
    @Bind(R.id.name) EditText mName;

    private DatabaseReference mCatagoryReference;
    private ValueEventListener mCatagoryReferenceListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);

        ButterKnife.bind(this);

        mAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String category = mName.getText().toString();
        saveCategoryToFirebase(category);
        Intent intent = new Intent(NewCategory.this, MainActivity.class);
        startActivity(intent);
    }

    public void saveCategoryToFirebase(String category) {
        Category newCategory = new Category(category);
        mCatagoryReference.push().setValue(newCategory);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCatagoryReference.removeEventListener(mCatagoryReferenceListener);

    }
}
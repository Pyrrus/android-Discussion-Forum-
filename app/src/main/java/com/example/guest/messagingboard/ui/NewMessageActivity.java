package com.example.guest.messagingboard.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.guest.messagingboard.R;
import com.example.guest.messagingboard.adapters.MessagesAdapter;
import com.example.guest.messagingboard.model.Category;
import com.example.guest.messagingboard.model.Message;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewMessageActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.addMessage) Button mButton;

    @Bind(R.id.messageTitleEdit) EditText mTitle;
    @Bind(R.id.messageEditBody) EditText mBody;

    private DatabaseReference mCatagoryReference;
    private ValueEventListener mCatagoryReferenceListener;
    private DatabaseReference mMessagesReference;
    private ValueEventListener mMessagesReferenceListener;
    private ChildEventListener childMessagesEventListener;

    private Category mCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
        ButterKnife.bind(this);
        Intent myIntent = getIntent();
        mCategory =  Parcels.unwrap(getIntent().getParcelableExtra("category"));

        mCatagoryReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("catagories");

        childMessagesEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mCatagoryReference.addChildEventListener(childMessagesEventListener);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String title = mTitle.getText().toString();
        String body = mBody.getText().toString();
        Message newMassage = new Message(body, title);
        mCategory.getMessage().add(newMassage);
        saveMessageToFirebase(newMassage);
        Intent intent = new Intent(NewMessageActivity.this, MessagesAdapter.class);
        intent.putExtra("category", Parcels.wrap(mCategory));
        startActivity(intent);
    }


    public void saveMessageToFirebase(Message message) {

        mCatagoryReference.child(mCategory.getPushId()).(mCategory.getMessage());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCatagoryReference.removeEventListener(mCatagoryReferenceListener);

    }


}

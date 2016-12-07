package com.example.guest.messagingboard.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.guest.messagingboard.R;
import com.example.guest.messagingboard.adapters.MessagesAdapter;
import com.example.guest.messagingboard.model.Category;
import com.example.guest.messagingboard.model.Message;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {
    private Category mCategory;
    @Bind(R.id.Category) TextView mTitle;
    @Bind(R.id.addMessage) Button mButton;
    @Bind(R.id.messageList) ListView mView;
    private ArrayList<Message> mMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        Intent myIntent = getIntent();
        mCategory =  Parcels.unwrap(getIntent().getParcelableExtra("category"));

        mMessages = mCategory.getMessage();

        if (mMessages.isEmpty()) {
            MessagesAdapter adapter = new MessagesAdapter(this, mMessages);

            mView.setAdapter(adapter);
        }

        mTitle.setText(mCategory.getName());

        mButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(CategoryActivity.this, NewMessageActivity.class);
        intent.putExtra("category", Parcels.wrap(mCategory));
        startActivity(intent);
    }
}

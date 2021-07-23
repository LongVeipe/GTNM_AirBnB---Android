package com.example.airbnb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.airbnb.View.HostingRoom.HostingRoomActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toHostingRoom(View view) {
        Intent intent = new Intent(getApplicationContext(), HostingRoomActivity.class);
        startActivity(intent);
    }
}
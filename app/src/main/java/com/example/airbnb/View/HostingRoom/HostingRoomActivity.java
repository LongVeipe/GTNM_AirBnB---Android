package com.example.airbnb.View.HostingRoom;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.example.airbnb.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HostingRoomActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rt_actv)
    AutoCompleteTextView rt_actv;
    @BindView(R.id.range_actv)
    AutoCompleteTextView range_actv;
    @BindView(R.id.st_actv)
    AutoCompleteTextView st_actv;


    ArrayAdapter rt_ad;
    ArrayAdapter range_ad;
    ArrayAdapter st_ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosting_room);
        ButterKnife.bind(this);

        initToolbar();
        initOptions();

    }
    public void ChangeNumber(View view) {

        EditText edt;
        int number;
        if(view.getId() == R.id.snl_add)
        {
            edt = findViewById(R.id.snl);
            number = Integer.parseInt(edt.getText().toString());
            edt.setText(number + 1 + "");
        }
        if(view.getId() == R.id.snl_minus)
        {
            edt = findViewById(R.id.snl);
            number = Integer.parseInt(edt.getText().toString());
            if(number > 0)
                edt.setText(number - 1 + "");
        }

        if(view.getId() == R.id.ste_add)
        {
            edt = findViewById(R.id.ste);
            number = Integer.parseInt(edt.getText().toString());
            edt.setText(number + 1 + "");
        }
        if(view.getId() == R.id.ste_minus)
        {
            edt = findViewById(R.id.ste);
            number = Integer.parseInt(edt.getText().toString());
            if(number > 0)
                edt.setText(number - 1 + "");
        }
        if(view.getId() == R.id.sgn_add)
        {
            edt = findViewById(R.id.sgn);
            number = Integer.parseInt(edt.getText().toString());
            edt.setText(number + 1 + "");
        }
        if(view.getId() == R.id.sgn_minus)
        {
            edt = findViewById(R.id.sgn);
            number = Integer.parseInt(edt.getText().toString());
            if(number > 0)
                edt.setText(number - 1 + "");
        }
        if(view.getId() == R.id.snvs_add)
        {
            edt = findViewById(R.id.snvs);
            number = Integer.parseInt(edt.getText().toString());
            edt.setText(number + 1 + "");
        }
        if(view.getId() == R.id.snvs_minus)
        {
            edt = findViewById(R.id.snvs);
            number = Integer.parseInt(edt.getText().toString());
            if(number > 0)
                edt.setText(number - 1 + "");
        }
    }
    private  void initOptions ()
    {
        String []rt_options = {"Nhà nguyên căn","Chung cư", "Phòng riêng","Nhà hàng", "Homestay","Không gian độc đáo" };
        String []range_options = {"Toàn bộ","Theo phòng", "Theo khu vực"};
        String []st_options = {"Riêng tư","Tiệc tùng", "Họp mặt"};

        rt_ad = new ArrayAdapter(this, R.layout.item_option, rt_options);
        rt_actv.setText(rt_ad.getItem(0).toString(), true);
        rt_actv.setAdapter(rt_ad);

        range_ad = new ArrayAdapter(this, R.layout.item_option, range_options);
        range_actv.setText(range_ad.getItem(0).toString(), true);
        range_actv.setAdapter(range_ad);

        st_ad = new ArrayAdapter(this, R.layout.item_option, st_options);
        st_actv.setText(st_ad.getItem(0).toString(), true);
        st_actv.setAdapter(st_ad);
    }
    private void initToolbar() {
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
        {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            setTitle("Thông tin phòng");
        }
    }
}
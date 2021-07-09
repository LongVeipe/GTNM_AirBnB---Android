package com.example.airbnb.View.HostingRoom;

import androidx.appcompat.app.AppCompatActivity;

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

public class HostingRoomActivity extends AppCompatActivity {

    AutoCompleteTextView rt_actv;
    AutoCompleteTextView range_actv;
    AutoCompleteTextView st_actv;
    String []rt_options = {"Nhà nguyên căn","Chung cư", "Phòng riêng","Nhà hàng", "Homestay","Không gian độc đáo" };
    String []range_options = {"Toàn bộ","Theo phòng", "Theo khu vực"};
    String []st_options = {"Riêng tư","Tiệc tùng", "Họp mặt"};
    ArrayAdapter rt_ad;
    ArrayAdapter range_ad;
    ArrayAdapter st_ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosting_room);
        rt_actv = findViewById(R.id.rt_actv);
        rt_ad = new ArrayAdapter(this, R.layout.item_option, rt_options);
        rt_actv.setText(rt_ad.getItem(0).toString(), true);
        rt_actv.setAdapter(rt_ad);

        range_actv = findViewById(R.id.range_actv);
        range_ad = new ArrayAdapter(this, R.layout.item_option, range_options);
        range_actv.setText(range_ad.getItem(0).toString(), true);
        range_actv.setAdapter(range_ad);

        st_actv = findViewById(R.id.st_actv);
        st_ad = new ArrayAdapter(this, R.layout.item_option, st_options);
        st_actv.setText(st_ad.getItem(0).toString(), true);
        st_actv.setAdapter(st_ad);
    }

    @SuppressLint("WrongConstant")
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
            edt.setText(number - 1 + "");
        }
    }
}
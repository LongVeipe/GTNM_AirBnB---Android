package com.example.airbnb.View.HostingRoom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.airbnb.Adapter.PhotoAdapter;
import com.example.airbnb.MainActivity;
import com.example.airbnb.R;
import com.example.airbnb.View.BookingInfo.BookingInfoActivity;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gun0912.tedbottompicker.TedBottomPicker;


public class HostingRoomActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;
    private static final int REQUEST_EXTERNAL_STORAGE = 100;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rt_actv)
    AutoCompleteTextView rt_actv;
    @BindView(R.id.range_actv)
    AutoCompleteTextView range_actv;
    @BindView(R.id.st_actv)
    AutoCompleteTextView st_actv;
    @BindView(R.id.rcv_photo)
    RecyclerView rcv_photo;

    private PhotoAdapter photoAdapter;
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
        initAdapter();
    }

    private void initAdapter() {
        photoAdapter = new PhotoAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3, LinearLayoutManager.VERTICAL,false);
        rcv_photo.setLayoutManager(gridLayoutManager);
        rcv_photo.setFocusable(false);
        rcv_photo.setAdapter(photoAdapter);
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
        String []rt_options = {"Nh?? nguy??n c??n","Chung c??", "Ph??ng ri??ng","Nh?? h??ng", "Homestay","Kh??ng gian ?????c ????o" };
        String []range_options = {"To??n b???","Theo ph??ng", "Theo khu v???c"};
        String []st_options = {"Ri??ng t??","Ti???c t??ng", "H???p m???t"};

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
            setTitle("Cho thu?? Ph??ng/Nh??");
        }
    }

    public void Next(View view) {
        Intent intent = new Intent(getApplicationContext(), RoomLocationActivity.class);
        startActivity(intent);
    }

    public void getImage(View view) {
        requestPermissions();
    }

    private void requestPermissions() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                openBottomPicker();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(HostingRoomActivity.this, "Quy???n b??? t??? ch???i\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("B???n kh??ng th??? s??? d???ng t??nh n??ng n??y n???u t??? ch???i quy???n truy c???p\n\nH??y m??? quy???n truy c???p ??? [C??i ?????t] > [Quy???n Truy C???p]")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }
    private void openBottomPicker(){
        TedBottomPicker.OnMultiImageSelectedListener listener = new TedBottomPicker.OnMultiImageSelectedListener() {
            @Override
            public void onImagesSelected(ArrayList<Uri> uriList) {
                photoAdapter.setData(uriList);
            }
        };
        TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(HostingRoomActivity.this)
                .setOnMultiImageSelectedListener(listener)
                .setCompleteButtonText("HO??N TH??NH")
                .setEmptySelectionText("Ch??a c?? h??nh ???nh n??o")
                .create()   ;
        tedBottomPicker.show(getSupportFragmentManager());

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }
}
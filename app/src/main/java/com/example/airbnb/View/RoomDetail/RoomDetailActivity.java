package com.example.airbnb.View.RoomDetail;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.airbnb.Adapter.ViewPagerImageAdapter;
import com.example.airbnb.Model.Room;
import com.example.airbnb.R;
import com.example.airbnb.Utils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoomDetailActivity extends AppCompatActivity implements RoomDetailView, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    boolean isPermissionGranted;
    GoogleMap map;

    Room room;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.map_mv)
    MapView mapView;
    @BindView(R.id.location_tv)
    TextView location_tv;
    @BindView(R.id.image_vp)
    ViewPager image_vp;
    @BindView(R.id.location_cv)
    CardView location_cv;
    @BindView(R.id.description_tv)
    TextView description_tv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);
        ButterKnife.bind(this);

        initRoom();
        setupActionBar();

        checkMyPermission();
        if(isPermissionGranted)
            setupMapView(savedInstanceState);

        RoomDetailPresenter presenter = new RoomDetailPresenter(this);
        presenter.setRoom(room);
        setupCardView();
        setupTextView();
    }

    private void initRoom() {
        String id = "00001";
        String name = "CPR white House - Căn hộ cao cấp ven biển Hồ";
        String location = "99-89 Phó Đức Chính, P. Yên Thế, Thành phố Pleiku, Gia Lai 600000, Vietnam";
        String description = "“CPR white House” là căn hộ cao cấp bậc nhất ngay trung tâm thành phố Pleyku. nơi đây có vị trí thuận lợi, tọa lạc bên bờ biển thơ mộng nổi tiếng thế giới - Biển Hồ, xung quanh là các quán ăn, cửa hàng tiện lợi, nhà thuốc,... đáp ứng mọi nhu cầu về giải trí, sinh hoạt của khách hàng";
        ArrayList<String> images = new ArrayList<String>();
        images.add("https://xuonggooccho.com/wp-content/uploads/2019/07/Hinh-anh-phong-ngu-dep-1.jpg");
        images.add("https://thietkenoithat.com/Portals/0/xNews/uploads/2018/6/14/176.jpg");
        images.add("https://dnudecor.vn/wp-content/uploads/2020/10/mau-phong-ve-sinh-dep.jpg");
        this.room = new Room(id,name,location,description,images);
    }

    void setupCardView()
    {
        location_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + room.getLocation() + "/");

                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.google.android.apps.maps");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }catch (ActivityNotFoundException e )
                {
                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
    }
    void setupTextView()
    {
        Utils.makeTextViewResizable(description_tv, 4, "Xem thêm", true);
    }
    private void checkMyPermission() {
        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                isPermissionGranted = true;
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), "");
                intent.setData(uri);
                startActivity(intent);
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }

    private void setupMapView(Bundle savedInstanceState) {
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    private void setupActionBar() {
        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorWhite));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorPrimary));
        collapsingToolbarLayout.setTitle(room.getName());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    void setupColorActionBarIcon(MenuItem favoriteItem) {
        Drawable favoriteItemColor = favoriteItem.getIcon();
        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if ((collapsingToolbarLayout.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(collapsingToolbarLayout))) {
                if (toolbar.getNavigationIcon() != null)
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                favoriteItemColor.mutate().setColorFilter(getResources().getColor(R.color.colorAccent),
                        PorterDuff.Mode.SRC_ATOP);

            } else {
                if (toolbar.getNavigationIcon() != null)
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                if (favoriteItem.isChecked())
                    return;
                favoriteItemColor.mutate().setColorFilter(getResources().getColor(R.color.colorAccent),
                        PorterDuff.Mode.SRC_ATOP);
            }
        });
    }


    private void goToLocation(double latitude, double longitude)
    {
        LatLng lng = new LatLng(latitude, longitude);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(lng, 15);
        map.moveCamera(cameraUpdate);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_room_detail, menu);
        MenuItem favoriteItem = menu.findItem(R.id.favorite);
        Drawable favoriteItemColor = favoriteItem.getIcon();
        setupColorActionBarIcon(favoriteItem);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //onBackPressed();
                item.setChecked(!item.isChecked());
                return true;
            case R.id.favorite:
                if (item.isChecked()) {
                    item.setIcon(R.drawable.ic_baseline_favorite_border_24);
                    Toast.makeText(getApplicationContext(), "Đã xóa khỏi danh sách yêu thích!", Toast.LENGTH_SHORT).show();
                } else {
                    item.setIcon(R.drawable.ic_baseline_favorite_24);
                    Toast.makeText(getApplicationContext(), "Đã thêm vào danh sách yêu thích!", Toast.LENGTH_SHORT).show();
                }
                item.getIcon().mutate().setColorFilter(getResources().getColor(R.color.colorRed), PorterDuff.Mode.SRC_ATOP);
                item.setChecked(!item.isChecked());
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this, "Error", message);
    }

    @Override
    public void setRoom(Room room) {
        if(room == null)
            return;
        ViewPagerImageAdapter adapter = new ViewPagerImageAdapter(this, room.getImages());
        image_vp.setAdapter(adapter);
        image_vp.setPadding(20, 0, 150, 0);
        adapter.notifyDataSetChanged();

        location_tv.setText(room.getLocation());
        description_tv.setText(room.getDescription());
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocationName(room.getLocation(), 1);
            if(addresses.size()>0) {
                Address address = addresses.get(0);
                goToLocation(address.getLatitude(), address.getLongitude());
                map.addMarker(new MarkerOptions().position(new LatLng(address.getLatitude(), address.getLongitude())));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}
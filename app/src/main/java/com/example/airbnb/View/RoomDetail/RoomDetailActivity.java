package com.example.airbnb.View.RoomDetail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.airbnb.Model.Room;
import com.example.airbnb.R;
import com.example.airbnb.Utils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoomDetailActivity extends AppCompatActivity implements RoomDetailView{

    Menu optionMenu;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);
        ButterKnife.bind(this);

        setupActionBar();
    }

    private void setupActionBar() {
        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorWhite));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorPrimary));
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
                if(favoriteItem.isChecked())
                    return;
                favoriteItemColor.mutate().setColorFilter(getResources().getColor(R.color.colorAccent),
                        PorterDuff.Mode.SRC_ATOP);
            }
        });
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
            case android.R.id.home :
                //onBackPressed();
                item.setChecked(!item.isChecked());
                return true;
            case R.id.favorite:
                if(item.isChecked()) {
                    item.setIcon(R.drawable.ic_baseline_favorite_border_24);
                    Toast.makeText(getApplicationContext(), "Đã xóa khỏi danh sách yêu thích!", Toast.LENGTH_SHORT).show();
                }
                else {
                    item.setIcon(R.drawable.ic_baseline_favorite_24);
                    Toast.makeText(getApplicationContext(), "Đã thêm vào danh sách yêu thích!", Toast.LENGTH_SHORT).show();
                }
                item.getIcon().mutate().setColorFilter(getResources().getColor(R.color.colorRed),PorterDuff.Mode.SRC_ATOP);
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
            Utils.showDialogMessage(this,"Error", message);
    }

    @Override
    public void setRoom(Room room) {

    }
}
package com.example.airbnb.View.BookingInfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.util.Pair;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.airbnb.Model.Room;
import com.example.airbnb.R;
import com.example.airbnb.Utils;
import com.example.airbnb.View.RoomDetail.RoomDetailActivity;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookingInfoActivity extends AppCompatActivity {

    private Room room;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.editDay_btn)
    MaterialButton editDay_btn;
    @BindView(R.id.edit_check_in_time_btn)
    MaterialButton editCheckInTime_btn;
    @BindView(R.id.edit_number_of_people_title_btn)
    MaterialButton editNumberOfPeople_btn;
    @BindView(R.id.date_tv)
    TextView date_tv;
    @BindView(R.id.check_in_time_tv)
    TextView time_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_info);
        ButterKnife.bind(this);

        initRoom();
        initToolbar();
        initEditBtn();
    }

    private void initRoom() {
        room = (Room) getIntent().getSerializableExtra(RoomDetailActivity.EXTRA_CURRENT_ROOM);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorBackground));
        if(getSupportActionBar() != null)
        {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            setTitle("Thông tin đặt phòng");
        }
    }

    void  initEditBtn()
    {
        editDay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker<Pair<Long, Long>> materialDatePicker = Utils.createDateRangePicker();
                materialDatePicker.show(getSupportFragmentManager(), materialDatePicker.toString());
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
                    @Override
                    public void onPositiveButtonClick(Pair<Long, Long> selection) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(selection.first);
                        Date startDate = calendar.getTime();
                        String strStartDate = calendar.get(Calendar.DAY_OF_MONTH) + " thg " + (calendar.get(Calendar.MONTH) + 1);
                        calendar.setTimeInMillis(selection.second);
                        Date endDate = calendar.getTime();
                        String strEndDate = calendar.get(Calendar.DAY_OF_MONTH) + " thg " + (calendar.get(Calendar.MONTH) + 1);

                        date_tv.setText(strStartDate + " - " + strEndDate);
                    }
                });
            }
        });

        editCheckInTime_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialTimePicker timePicker = Utils.createTimePicker();

                timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        time_tv.setText(Utils.getTimeStrFromTimePicker(timePicker));
                    }
                });
                timePicker.show(getSupportFragmentManager(), timePicker.toString());
            }
        });

        editNumberOfPeople_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogNumberOfPeople();
            }
        });
    }

    void showDialogNumberOfPeople()
    {
        View dialog = getLayoutInflater().inflate(R.layout.dialog_bottom_sheet_number_of_people, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(dialog);
        bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        bottomSheetDialog.show();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
}
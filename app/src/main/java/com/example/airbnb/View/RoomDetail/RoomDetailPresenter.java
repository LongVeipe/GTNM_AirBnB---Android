package com.example.airbnb.View.RoomDetail;

import com.example.airbnb.Model.Room;

public class RoomDetailPresenter {
    RoomDetailView view;

    public RoomDetailPresenter(RoomDetailView view)
    {
        this.view = view;
    }

    void setRoom(Room room)
    {
        view.showLoading();
        view.setRoom(room);
        view.hideLoading();
    }
}

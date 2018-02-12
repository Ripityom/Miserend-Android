package com.frama.miserend.hu.churchlist;

import android.arch.paging.PagedListAdapter;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.frama.miserend.hu.R;
import com.frama.miserend.hu.database.relations.ChurchWithMasses;

import java.util.Calendar;

/**
 * Created by Balazs on 2018. 02. 11..
 */

public class NearChurchesAdapter extends PagedListAdapter<ChurchWithMasses, ChurchViewHolder> {

    private int dayOfWeekToday;
    private Location currentLocation;

    public NearChurchesAdapter() {
        super(new ChurchDiffCallback());
        dayOfWeekToday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    @Override
    public ChurchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChurchViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chuch, parent, false), dayOfWeekToday);
    }

    @Override
    public void onBindViewHolder(ChurchViewHolder holder, int position) {
        ChurchWithMasses churchWithMasses = getItem(position);
        if (churchWithMasses != null) {
            holder.bindTo(churchWithMasses, currentLocation);
        } else {
            holder.clear();
        }
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }
}

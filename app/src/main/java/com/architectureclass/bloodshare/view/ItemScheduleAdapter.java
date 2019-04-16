package com.architectureclass.bloodshare.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.architectureclass.bloodshare.R;
import com.architectureclass.bloodshare.model.Schedule;

import java.text.SimpleDateFormat;
import java.util.List;

public class ItemScheduleAdapter extends RecyclerView.Adapter<ItemScheduleAdapter.ViewHolder> {

    List<Schedule> schedules;

    public ItemScheduleAdapter(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Schedule schedule = schedules.get(position);
        holder.place.setText(schedule.getPlace());
        holder.recipient.setText(schedule.getRecipient());
        holder.due.setText(new SimpleDateFormat("yyyy-MM-dd").format(schedule.getDueDate()));
    }

    @Override
    public int getItemCount() {
        return this.schedules.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView place;
        private TextView recipient;
        private TextView due;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            place = itemView.findViewById(R.id.place);
            recipient = itemView.findViewById(R.id.recipient);
            due = itemView.findViewById(R.id.due);
        }
    }
}

package com.architectureclass.bloodshare;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.architectureclass.bloodshare.controller.ScheduleController;
import com.architectureclass.bloodshare.model.Schedule;
import com.architectureclass.bloodshare.view.ItemScheduleAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Schedule> scheduleList = new ArrayList<>();
    ScheduleController controller;

    Toolbar toolbar;
    FloatingActionButton fab;
    RecyclerView recyclerViewSchedules;
    TextView emptySpaceSchedules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        controller = new ScheduleController(this);
        scheduleList = controller.findAll();

        recyclerViewSchedules = findViewById(R.id.schedules);
        recyclerViewSchedules.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSchedules.setAdapter(new ItemScheduleAdapter(scheduleList));

        emptySpaceSchedules = findViewById(R.id.emptySpaceSchedules);
        defineVisibility();

        FloatingActionButton fab = findViewById(R.id.buttonAdd);
        fab.setOnClickListener(new FloatingActionButtonOnClickListener());
    }

    protected void createSchedule(String place, String recipient, Date dueDate) {
        controller.create(place, recipient, dueDate);
    }

    private void createDialogSchedule(final View parentView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View view = getLayoutInflater().inflate(R.layout.dialog_schedule, null);
        final EditText dialogPlace = view.findViewById(R.id.place);
        final EditText dialogRecipient = view.findViewById(R.id.recipient);
        final DatePicker dialogDueDate = view.findViewById(R.id.due);
        builder
            .setView(view)
            .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        createSchedule(dialogPlace.getText().toString(),
                                   dialogRecipient.getText().toString(),
                                   new SimpleDateFormat("yyyy-MM-dd").parse(dialogDueDate.getYear()
                                           +"-"+
                                           checkDigit(dialogDueDate.getMonth())
                                           +"-"+
                                           checkDigit(dialogDueDate.getDayOfMonth())));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    refresh();
                    Snackbar.make(parentView, "Schedule successfully created!", Snackbar.LENGTH_LONG).show();
                }
            })
            .setNegativeButton("Cancel", null);
        builder.create().show();
    }

    public String checkDigit(int number)
    {
        return number<=9?"0"+number:String.valueOf(number);
    }

    public void refresh() {
        scheduleList.clear();
        scheduleList.addAll(controller.findAll());
        recyclerViewSchedules.getAdapter().notifyDataSetChanged();
        defineVisibility();
    }

    private void defineVisibility() {
        emptySpaceSchedules.setVisibility(scheduleList.isEmpty() ? View.VISIBLE : View.INVISIBLE);
    }

    private class FloatingActionButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            createDialogSchedule(view);
        }
    }
}

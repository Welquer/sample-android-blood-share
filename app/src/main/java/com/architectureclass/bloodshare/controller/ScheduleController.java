package com.architectureclass.bloodshare.controller;

import android.content.Context;

import com.architectureclass.bloodshare.model.Schedule;
import com.architectureclass.bloodshare.model.ScheduleLocalDatabaseRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ScheduleController {

    private ScheduleLocalDatabaseRepository repository;

    public ScheduleController(Context context) {
        this.repository = new ScheduleLocalDatabaseRepository(context);
    }

    public Schedule create(String place, String recipient, Date due) {
        Schedule schedule = new Schedule();
        schedule.setId(UUID.randomUUID().toString());
        schedule.setPlace(place);
        schedule.setRecipient(recipient);
        schedule.setDueDate(due);
        schedule.setCreatedAt(new Date());
        schedule.setDone(false);

        repository.create(schedule);

        return schedule;
    }

    public List<Schedule> findAll() {
        return repository.findAll();
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() > 40) {
            throw new RuntimeException("Invalid name!");
        }
    }
}

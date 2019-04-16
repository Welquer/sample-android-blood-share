package com.architectureclass.bloodshare.model;

import java.util.ArrayList;
import java.util.List;

public class ScheduleMemoryRepository {

    List<Schedule> schedules = new ArrayList<>();

    public void create(Schedule schedule) {
        schedules.add(schedule);
    }

    public List<Schedule> findAll() {
        return schedules;
    }
}

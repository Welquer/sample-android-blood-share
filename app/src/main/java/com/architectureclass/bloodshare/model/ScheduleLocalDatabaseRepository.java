package com.architectureclass.bloodshare.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ScheduleLocalDatabaseRepository {

    private SQLiteDatabase instance;
    private Database database;
    private SimpleDateFormat dateFormat;

    public ScheduleLocalDatabaseRepository(Context context) {
        database = new Database(context);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public void create(Schedule schedule) {
        ContentValues contentValues = new ContentValues();
        instance = database.getWritableDatabase();

        contentValues.put(DatabaseConstants.COLUMN_ID, schedule.getId());
        contentValues.put(DatabaseConstants.COLUMN_CREATED_AT, dateFormat.format(schedule.getCreatedAt()));
        contentValues.put(DatabaseConstants.COLUMN_PLACE, schedule.getPlace());
        contentValues.put(DatabaseConstants.COLUMN_RECIPIENT, schedule.getRecipient());
        contentValues.put(DatabaseConstants.COLUMN_DUE_DATE, dateFormat.format(schedule.getDueDate()));
        contentValues.put(DatabaseConstants.COLUMN_DONE, schedule.isDone());

        instance.insert(DatabaseConstants.TABLE_SCHEDULE, null, contentValues);
        instance.close();
    }

    public List<Schedule> findAll() {
        List<Schedule> schedules = new ArrayList<>();
        String[] fields = {
                DatabaseConstants.COLUMN_ID,
                DatabaseConstants.COLUMN_CREATED_AT,
                DatabaseConstants.COLUMN_PLACE,
                DatabaseConstants.COLUMN_RECIPIENT,
                DatabaseConstants.COLUMN_DUE_DATE,
                DatabaseConstants.COLUMN_DONE
        };
        instance = database.getReadableDatabase();

        Cursor cursor = instance.query(DatabaseConstants.TABLE_SCHEDULE, fields, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Schedule schedule = new Schedule();
                schedule.setId(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.COLUMN_ID)));
                try {
                    schedule.setCreatedAt(dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.COLUMN_CREATED_AT))));
                    schedule.setDueDate(dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.COLUMN_DUE_DATE))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                schedule.setPlace(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.COLUMN_PLACE)));
                schedule.setRecipient(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.COLUMN_RECIPIENT)));
                schedule.setDone(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseConstants.COLUMN_DONE))==1);
                schedules.add(schedule);
            } while(cursor.moveToNext());
        }
        return schedules;
    }

}

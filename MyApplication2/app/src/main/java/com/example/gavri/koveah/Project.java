package com.example.gavri.koveah;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.Update;
import android.content.Context;

import java.time.Duration;
import java.util.ArrayList;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Created by avihirsch on 3/11/2018.
 */


@Entity
public class Project{
    @PrimaryKey
    private int id;
    private String bookName;   //should change
    private int page;
    private String textMessage;
    private int time;
    private String imageFile;//get absolute path
    private String audioFile;//

    public Project(int id, String bookName, int page, String textMessage, int time, String imageFile, String audioFile){
        this.id = id;
        this.bookName = bookName;
        this.page = page;
        this.textMessage = textMessage;
        this.time = time;
        this.imageFile = imageFile;
        this.audioFile = audioFile;
    }

    public int getPage(){
        return page;
    }
    public String getTextMessage(){
        return textMessage;
    }
    public int getTime(){
        return time;
    }
    public String getImageFile(){
        return imageFile;
    }
    public String getAudioFile(){
        return audioFile;
    }
    public String getBookName() {
        return bookName;
    }
    public int getId() {
        return id;
    }
}

///**
//    //
//    private void fetchData(int id) {
//        AppDatabase DB = AppDatabase.getInMemoryDatabase(getApplicationContext());
//
//        StringBuilder sb = new StringBuilder();
//        Projects project = DB.projectsDao().findProjectById(id);
//    }
// */

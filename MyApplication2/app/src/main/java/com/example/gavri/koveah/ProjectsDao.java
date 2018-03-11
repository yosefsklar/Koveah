package com.example.gavri.koveah;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Created by avihirsch on 3/11/2018.
 */

@Dao
public interface ProjectsDao {

        @Insert(onConflict = IGNORE)
        void insert(Project project);
        @Delete
        void delete(Project project);

        @Query("UPDATE Project SET bookName = :name WHERE id == :userIds")
        void updateBookName(String name, int userIds);

        @Query("UPDATE Project SET page = :page WHERE id LIKE :userIds")
        void  updatePage(int page, int userIds);

        @Query("UPDATE Project SET textMessage = :textMessage WHERE id LIKE :userIds")
        void  updateTextMessage(String textMessage, int userIds);

        @Query("UPDATE Project SET time = :time WHERE id LIKE :userIds")
        void  updateTime(int time, int userIds);

        @Query("UPDATE Project SET imageFile = :imageFile WHERE id LIKE :userIds")
        void  updateImage(String imageFile, int userIds);

        @Query("UPDATE Project SET audioFile = :audioFile WHERE id LIKE :userIds")
        void  updateAudio(String audioFile, int userIds);

//        @Query("UPDATE Project SET audioFile = :audioFile, page = :page, textMessage = :textMessage, time = :time, imageFile = :imageFile, audioFile= :audioFile  WHERE id LIKE :userIds")
//        void  update(String name, int page, String textMessage, int time, String imageFile, String audioFile, int userIds);

        @Query("Select * FROM Project WHERE id LIKE :userIds")
        public Project findProjectById(int userIds);

        @Query("Select * FROM Project")
        public List<Project> getAllProjects();

    }

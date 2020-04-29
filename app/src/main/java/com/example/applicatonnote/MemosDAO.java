package com.example.applicatonnote;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public abstract class MemosDAO {
    @Query("SELECT * FROM courses")
    public abstract List<MemoDTO> getListeMemo();
    @Query("SELECT COUNT(*) FROM courses WHERE text   = :intitule")
    public abstract long countCoursesParIntitule(String intitule);
    @Insert
    public abstract void insert(MemoDTO... memos);
    @Update
    public abstract void update(MemoDTO... memos);
    @Delete
    public abstract void delete(MemoDTO... memos);
}
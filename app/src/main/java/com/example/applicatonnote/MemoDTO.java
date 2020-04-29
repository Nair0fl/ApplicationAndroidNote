package com.example.applicatonnote;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.parceler.Parcel;

@Parcel
@Entity(tableName = "courses")
public class MemoDTO{
    @PrimaryKey(autoGenerate = true)
    public long courseId = 0;
    public String text;

    // Constructeur public vide (obligatoire si autre constructeur existant) :
    public MemoDTO() {}
    // Autre constructeur :
    public MemoDTO(String text)
    {
        this.text = text;
    }
}
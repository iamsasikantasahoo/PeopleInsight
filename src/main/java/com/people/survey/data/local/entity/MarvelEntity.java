package com.people.survey.data.local.entity;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "marvel", indices = {@Index(value = {"name"}, unique = true)})

public class MarvelEntity {
    // Creates a table named users.
    // tableName is the property name, users is the value

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String realname;
    public String team;
    public String firstappearance;
    public String createdby;
    public String publisher;
    public String imageurl;
    public String bio;
    @Ignore // Tells Room to ignore this field
            Bitmap picture;

    @Ignore
    public MarvelEntity(String name, String realname, String team, String firstappearance, String createdby, String publisher, String imageurl, String bio) {
       this.name = name;
        this.realname = realname;
        this.team = team;
        this.firstappearance = firstappearance;
        this.createdby = createdby;
        this.publisher = publisher;
        this.imageurl = imageurl;
        this.bio = bio;
    }

    public MarvelEntity(int id, String name, String realname, String team, String firstappearance, String createdby, String publisher, String imageurl, String bio) {
        this.id = id;
        this.name = name;
        this.realname = realname;
        this.team = team;
        this.firstappearance = firstappearance;
        this.createdby = createdby;
        this.publisher = publisher;
        this.imageurl = imageurl;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public String getRealname() {
        return realname;
    }

    public String getTeam() {
        return team;
    }

    public String getFirstappearance() {
        return firstappearance;
    }

    public String getCreatedby() {
        return createdby;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getBio() {
        return bio;
    }

    public Bitmap getPicture() {
        return picture;
    }
}

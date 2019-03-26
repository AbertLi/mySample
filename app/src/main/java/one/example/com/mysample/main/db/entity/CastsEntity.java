package one.example.com.mysample.main.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import one.example.com.mysample.main.db.DbConstant;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = DbConstant.MOVEINFO_CASTS_TABLE, foreignKeys = @ForeignKey(entity = SubjectsEntity.class,
        parentColumns = "id", childColumns = "subjects_id", onDelete = CASCADE))
public class CastsEntity {
    private int subjects_id;//关联SubjectsEntity里面id 的外键id

    @PrimaryKey
    private String id;
    private String alt;
    private String name;

    private String small;
    private String large;
    private String medium;


    public int getSubjects_id() {
        return subjects_id;
    }

    public void setSubjects_id(int subjects_id) {
        this.subjects_id = subjects_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}

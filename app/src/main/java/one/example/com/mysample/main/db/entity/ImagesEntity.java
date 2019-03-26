package one.example.com.mysample.main.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import one.example.com.mysample.main.db.DbConstant;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = DbConstant.MOVEINFO_IMAGES_TABLE, foreignKeys = @ForeignKey(entity = SubjectsEntity.class,
        parentColumns = "id", childColumns = "subjects_id", onDelete = CASCADE))
public class ImagesEntity {
    private int subjects_id;//关联SubjectsEntity里面id 的外键id

    private String small;
    private String large;
    private String medium;

    public int getSubjects_id() {
        return subjects_id;
    }

    public void setSubjects_id(int subjects_id) {
        this.subjects_id = subjects_id;
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

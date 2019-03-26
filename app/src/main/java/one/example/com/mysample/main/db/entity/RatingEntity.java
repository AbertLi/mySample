package one.example.com.mysample.main.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import one.example.com.mysample.main.db.DbConstant;
import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = DbConstant.MOVEINFO_RATING_TABLE, foreignKeys = @ForeignKey(entity = SubjectsEntity.class,
        parentColumns = "id", childColumns = "subjects_id", onDelete = CASCADE))
public class RatingEntity {
    private int subjects_id;//关联SubjectsEntity里面id 的外键id

    private int max;
    private double average;
    private String stars;
    private int min;

    public int getSubjects_id() {
        return subjects_id;
    }

    public void setSubjects_id(int subjects_id) {
        this.subjects_id = subjects_id;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}

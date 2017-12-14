package ru.arink_group.deliveryapp.domain.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kirillvs on 14.12.2017.
 */

public class CompanyWorkingDayDTO {

    @SerializedName("week_day")
    @Expose
    private String dayOfWeek;

    @SerializedName("time_start")
    @Expose
    private String startWork;

    @SerializedName("time_end")
    @Expose
    private String endWork;

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getStartWork() {
        return startWork;
    }

    public void setStartWork(String startWork) {
        this.startWork = startWork;
    }

    public String getEndWork() {
        return endWork;
    }

    public void setEndWork(String endWork) {
        this.endWork = endWork;
    }
}

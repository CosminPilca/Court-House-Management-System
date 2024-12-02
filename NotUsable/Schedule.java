package NotUsable;

import java.util.Date;

/**
 * Represents a schedule in the Court House Management System.
 */

public class Schedule {
    private String scheduleID;
    private Date date;

    public Schedule(String scheduleID, Date date) {
        this.scheduleID = scheduleID;
        this.date = date;
    }

    public String getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(String scheduleID) {
        this.scheduleID = scheduleID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

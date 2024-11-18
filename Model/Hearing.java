package Model;

import java.util.Date;

/**
 * Represents a hearing in the Court House Management System.
 */

public class Hearing {
    private Date hearingDate;
    private String hearingTime;

    public Hearing(Date hearingDate, String hearingTime) {
        this.hearingDate = hearingDate;
        this.hearingTime = hearingTime;
    }

    public Date getHearingDate() {
        return hearingDate;
    }

}

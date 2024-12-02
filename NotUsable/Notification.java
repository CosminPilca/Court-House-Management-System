package NotUsable;

/**
 * Represents a notification in the Court House Management System.
 */

public class Notification {
    private String notificationID;
    private String message;

    public Notification(String notificationID, String message) {
        this.notificationID = notificationID;
        this.message = message;
    }

    public String getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
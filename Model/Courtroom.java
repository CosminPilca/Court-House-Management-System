package Model;

/**
 * Represents a courtroom in the Court House Management System.
 */

public class Courtroom {
    private String roomNumber;
    private int capacity;

    public Courtroom(String roomNumber, int capacity) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

}

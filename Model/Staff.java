package Model;

/**
 * Represents a staff member in the Court House Management System.
 */


public class Staff {
    private String staffID;
    private String name;
    private String role;

    public Staff(String staffID, String name, String role) {
        this.staffID = staffID;
        this.name = name;
        this.role = role;
    }

    public String getStaffID() {
        return staffID;
    }


}

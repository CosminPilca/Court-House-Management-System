package Model;

/**
 * Represents a staff member in the Court House Management System.
 */


public class Staff extends Person{
    private String staffID;
    private String role;

    public Staff(String staffID, String name, String role) {
        super(name);
        this.staffID = staffID;
        this.role = role;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Overrides the abstract method to display lawyer details.
     */
    @Override
    public String getDetails() {
        return "StaffID: " + staffID + " Name: " + getName() + " Role: " + role;
    }
}
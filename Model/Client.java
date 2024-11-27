package Model;

/**
 * Represents a client in the Court House Management System.
 */
public class Client extends Person{
    private String clientID;
    private String address;

    /**
     * Constructs a new Client.
     *
     * @param clientID The unique identifier for the client.
     * @param name     The name of the client.
     * @param address  The address of the client.
     */
    public Client(String clientID, String name, String address) {
        super(name);
        this.clientID = clientID;
        this.address = address;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the details of the client.
     *
     * @return A string containing the details of the client.
     */
    @Override
    public String getDetails() {
        return "Client ID: " + clientID + ", Name: " + getName() + ", Address: " + address;
    }
}

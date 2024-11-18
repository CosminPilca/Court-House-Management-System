package Model;

/**
 * Represents a client in the Court House Management System.
 */
public class Client {
    private String clientID;
    private String name;
    private String address;

    /**
     * Constructs a new Client.
     *
     * @param clientID The unique identifier for the client.
     * @param name     The name of the client.
     * @param address  The address of the client.
     */
    public Client(String clientID, String name, String address) {
        this.clientID = clientID;
        this.name = name;
        this.address = address;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

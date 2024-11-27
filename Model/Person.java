package Model;

public abstract class Person {
    private String id;
    private String name;
    private String contactInfo;


    public Person(String name, String contactInfo) {

        this.name = name;
        this.contactInfo = contactInfo;
    }

    public Person(){
        this.name = "";
        this.contactInfo = "";
    }



    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public abstract String getDetails();

    public void updateContactInfo(String newContactInfo) {
        this.contactInfo = newContactInfo;
    }

}


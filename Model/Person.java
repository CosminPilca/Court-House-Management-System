package Model;

public abstract class Person {
    private String givenName;


    public Person(){}
    public Person(String givenName) {
        this.givenName = givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getGivenName() {
        return givenName;
    }
    /**
     * Abstract method for returning person details.
     *
     * @return A string representing person details.
     */
    public abstract String getDetails();

}




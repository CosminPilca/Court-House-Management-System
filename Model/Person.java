package Model;

public abstract class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    /**
     * Abstract method for returning person details.
     *
     * @return A string representing person details.
     */
    public abstract String getDetails();

}




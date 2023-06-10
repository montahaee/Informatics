package process;

import java.util.Objects;

public class Customer {
    private final String firstname;
    private final String lastname;


    public Customer() {
        this("", "");
    }
    public Customer(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public boolean isSpecified() {
        return !this.equals(new Customer());
    }

    @Override
    public String toString() {
        return lastname + ", " + firstname;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other == null || (this.getClass() != other.getClass())) {
            return false;
        } else {
            Customer that = (Customer) other;
            boolean equal = Objects.equals(this.firstname, that.firstname);
            equal &= Objects.equals(this.lastname, that.lastname);
            return equal;
        }

    }
}

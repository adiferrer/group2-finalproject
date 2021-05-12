package prog2.finalgroup;

/**
 *
 */
public class Citizen implements Comparable {
    private static final boolean DEFAULT_BOOLEAN = false;
    private static final char DEFAULT_CHAR = 'x';
    private static final int DEFAULT_INT = 0;
    private static final String DEFAULT_STRING = "";


    private String fullName; // a combination of the first name
                             // and last name (separated by a space)
    private String email;
    private String address; // should not contain double quotes
    private int age;
    private boolean isResident; // should hold true if the citizen is a
                              // resident and false if otherwise
    private int district;
    private char gender; // should store M for males and F for females

    Citizen(String fullName, String email, String address, int age,
            boolean isResident, int district, char gender) {
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.age = age;
        this.isResident = isResident;
        this.district = district;
        this.gender = gender;
    }

    Citizen() {
        this(DEFAULT_STRING, DEFAULT_STRING, DEFAULT_STRING, DEFAULT_INT,
                DEFAULT_BOOLEAN, DEFAULT_INT, DEFAULT_CHAR);
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public boolean isResident() {
        return isResident;
    }

    public int getDistrict() {
        return district;
    }

    public char getGender() {
        return gender;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setIsResident(boolean resident) {
        this.isResident = resident;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        String r = "", g = "";
        if (isResident()) r = "Resident";
        else r = "Non-Resident";
        if (getGender() == 'F') g = "Female";
        else g = "Male";

        return String.format("%-30s %-50s %-40s %5d %-15s %5d %-10s",
                getFullName(), getEmail(), getAddress(), getAge(),
                r, getDistrict(), g);
    }

    public int compareTo(Object o) {
        return 0;
    }
}

package prog2.finalgroup;

/**
 * The reference class for a Citizen object.
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

    /**
     * @return an the full name of a Citizen
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @return an the email of a Citizen
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return an the address of a Citizen
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return an the age of a Citizen
     */
    public int getAge() {
        return age;
    }

    /**
     * @return an the full name of a Citizen
     */
    public boolean isResident() {
        return isResident;
    }

    /**
     * @return an the district number of a Citizen
     */
    public int getDistrict() {
        return district;
    }

    /**
     * @return an the gender of a Citizen
     */
    public char getGender() {
        return gender;
    }

    /**
     * This method changes the full name of a Citizen.
     *
     * @param fullName the new full name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * This method changes the email of a Citizen.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method changes the address of a Citizen.
     *
     * @param address the new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method changes the age of a Citizen.
     *
     * @param age the new age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * This method changes the resident state of a Citizen.
     *
     * @param resident the new resident boolean value (true/false)
     */
    public void setIsResident(boolean resident) {
        this.isResident = resident;
    }

    /**
     * This method changes the district of a Citizen.
     *
     * @param district the new district
     */
    public void setDistrict(int district) {
        this.district = district;
    }

    /**
     * This method changes the gender of a Citizen.
     *
     * @param gender the new gender char value (M/F)
     */
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

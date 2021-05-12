package prog2.finalgroup;

import java.io.*;
import java.util.*;
import java.util.stream.*;

/**
 * Contains the code that will convert the input file (data.csv) into a
 * list of Citizen objects and processes the list to come up with useful information
 */
public class MyProgramUtility {
    // Source for regex used:
    // https://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes
    private static BufferedReader br;

    /**
     * This method parses a CSV file into an ArrayList. <br>
     * Done by: Kurt Nudo
     *
     * @param fileName the name of the CSV file
     * @return an ArrayList of Citizens from the CSV file
     */
    protected static ArrayList<Citizen> parseCSV(String fileName) {
        ArrayList<Citizen> citizenArrayList = new ArrayList<>();
        String line;
        try {
            br = new BufferedReader(new FileReader(fileName));
            br.readLine();
            while ((line = br.readLine()) != null) {
                // Split on comma
                String[] citizenCSV = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                // Create course object to store values
                Citizen citizenTemp = new Citizen();
                // add values from csv to Course object
                citizenTemp.setFullName(citizenCSV[1] + " " + citizenCSV[0]);
                citizenTemp.setEmail(citizenCSV[2]);
                // EDITED: address field should not contain double quotes (as per letter c in the specification)
                citizenTemp.setAddress(citizenCSV[3].replace("\"", ""));
                citizenTemp.setAge(Integer.parseInt(citizenCSV[4]));
                citizenTemp.setIsResident(citizenCSV[5].equalsIgnoreCase("Resident"));
                citizenTemp.setDistrict(Integer.parseInt(citizenCSV[6]));
                citizenTemp.setGender(citizenCSV[7].charAt(0));
                citizenArrayList.add(citizenTemp);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found.");
        } catch (IOException ioException) {
            System.out.println("I/O error: " + ioException);
        }
        return citizenArrayList;
    }

    // SORT OPERATIONS (GLOBALLY)

    /**
     * 3. By: Enrico Castro
     * This method sorts a stream of citizens according to their age. <br>
     * <p>
     * Method Algorithm:
     * 1. sort the value of the citizen's ages in a decreasing order from oldest to youngest
     * 2. collect obtained values of age to a new Collection
     *
     * @param citizenList stream implying the list of citizens
     * @return an arrayList sorted according to the citizen's ages
     */
    protected static ArrayList<Citizen> sortAccordingToAgeGlobal(Stream<Citizen> citizenList) {
        return citizenList
                .sorted((r1, r2)
                        -> r1.getAge() - r2.getAge())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * By: Kurt Matthew C. Nudo
     * This method sorts a stream of citizens according to their name. <br>
     * <p>
     * Method Algorithm:
     * 1. sort the arraylist lexicographically, using getFullName() <br>
     * 2. store elements of the stream in a new arraylist, then return it <br>
     *
     * @param citizenList stream implying the list of citizens <br>
     * @return an arrayList sorted according to the citizen's ages <br>
     */
    protected static ArrayList<Citizen> sortAccordingToNameGlobal(Stream<Citizen> citizenList) {
        return citizenList
                .sorted((e1, e2) -> e1.getFullName().compareToIgnoreCase(e2.getFullName()))
                .collect(Collectors.toCollection(ArrayList::new));

    }

    /**
     * This method sorts a stream of citizens according to district. <br>
     * Done by: Jeanne Ferrer
     *
     * @param citizenList a stream generated from a Citizen ArrayList
     * @return an ArrayList of Citizens that is sorted according to district
     */
    protected static ArrayList<Citizen> sortAccordingToDistrictGlobal(Stream<Citizen> citizenList) {
        return citizenList
                .sorted((c1, c2) -> c1.getDistrict() - c2.getDistrict())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // SORT OPERATIONS (PER DISTRICT)

    /**
     * The method is assigned to sort the data according to their age within their respective districts.
     * Done by: EJ Pangwi
     *
     * @param citizenList a stream generated from a Citizen ArrayList
     * @param district    a given district number
     * @return an ArrayList of Citizens that is sorted according to age in a specified district
     */
    protected static ArrayList<Citizen> sortAccordingToAgePerDistrict(Stream<Citizen> citizenList, int district) {
        return citizenList
                .filter(c -> c.getDistrict() == district)
                .sorted((c1, c2) -> c1.getAge() - c2.getAge())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * This method sorts a stream according to last name in a specific district. <br>
     * Done by: Jerome Bustarde
     * <p>
     * Method Algorithm:
     * 1. filter the stream to gather all the values in the specific district
     * 2. sort the values by its full name using comparator
     * 3. use collect to store the newly created stream into a new Collection
     *
     * @param citizenList stream of citizen list
     * @param district    district inputted by the user
     * @return an ArrayList of Citizens that is sorted according to last name in a specified district
     */
    protected static ArrayList<Citizen> sortAccordingToNamePerDistrict(Stream<Citizen> citizenList, int district) {
        return citizenList
                .filter(c -> c.getDistrict() == district)
                .sorted(Comparator.comparing(Citizen::getFullName))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // SHOW OPERATIONS (GLOBALLY)

    /**
     * 1. By: Enrico Castro
     * This method amasses the names of the residents present in the arrayList . <br>
     * <p>
     * Method Algorithm:
     * 1. filter the stream to check all observed residents within the list
     * 2. collect obtained names of citizens who are listed as residents
     *
     * @param citizenList stream implying the list of citizens
     * @return an arrayList of citizens who are residents
     */
    protected static ArrayList<Citizen> showResidentsGlobal(Stream<Citizen> citizenList) {
        return citizenList
                .filter(c -> c.isResident() == true)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * By: Kurt Matthew C. Nudo
     * This method aggregates non-residents of every district.<br>
     * <br>
     * Method Algorithm: <br>
     * 1. Use the filter operation on the received citizenList stream, the predicate
     * is !isResident.<br>
     * 2. Collect obtained citizen objects who are non-residents<br>
     *
     * @param citizenList stream implying the list of citizens
     * @return an arrayList of non-residents
     */
    protected static ArrayList<Citizen> showNonResidentsGlobal(Stream<Citizen> citizenList) {
        return citizenList
                .filter(citizen -> !citizen.isResident())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * By: Kurt Matthew C. Nudo
     * This method aggregates non-residents of every district.<br>
     * <br>
     * Method Algorithm: <br>
     * 1. Use the filter operation on the received citizenList stream, the predicate
     * is c.getGender() == 'M'.<br>
     * 2. Collect obtained citizen objects who are males <br>
     *
     * @param citizenList stream implying the list of citizens
     * @return an ArrayList of males
     */
    protected static ArrayList<Citizen> showMalesGlobal(Stream<Citizen> citizenList) {
        return citizenList
                .filter(c -> c.getGender() == 'M')
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * This method creates a collection of all the female citizens. <br>
     * Done by: Jerome Bustarde
     * <p>
     * Method Algorithm: <br>
     * 1. Filter to stream to gather all the values of all the female citizens <br>
     * 2. Use collect to store the newly created stream into a new Collection
     *
     * @param citizenList stream of citizen list
     * @return an ArrayList of female citizens
     */
    protected static ArrayList<Citizen> showFemalesGlobal(Stream<Citizen> citizenList) {
        return citizenList
                .filter(f -> f.getGender() == 'F')
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * This method uses 3 parameters, includes a min and max value. It shows the given age within the stated range
     * (from min to max) with no other restrictions or in other words globally.
     * Done by: EJ Pangwi
     *
     * @param citizenList stream of citizen list
     * @param min         minimum range
     * @param max         maximum range
     */
    protected static ArrayList<Citizen> showAgesWithinRangeGlobal(Stream<Citizen> citizenList, int min, int max) {
        return citizenList
                .filter(c -> c.getAge() >= min && c.getAge() <= max)
                //.sorted(Comparator.reverseOrder())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // SHOW OPERATIONS (PER DISTRICT)

    /**
     * This method filters citizens that belong to a specified district
     * and at the same time, are identified as residents. <br>
     * Done by: Jeanne Ferrer
     *
     * @param citizenList a stream generated from a Citizen ArrayList
     * @param district    the user indicated district number
     * @return an ArrayList of resident Citizens in specified district
     */
    protected static ArrayList<Citizen> showResidentsPerDistrict(Stream<Citizen> citizenList, int district) {
        return citizenList
                .filter(c -> c.getDistrict() == district && c.isResident())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * 2. TODO Andre - Generate a stream from a Citizen ArrayList then use Stream.filter, then display outputs directly
     */
    protected static ArrayList<Citizen> showNonResidentsPerDistrict(Stream<Citizen> citizenList, int district) {
        return citizenList.collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * This method is designed to show the given males in their own respective districts.
     * Done by: EJ Pangwi
     *
     * @param citizenList a stream generated from a Citizen ArrayList
     * @param district    the user indicated district number
     * @return an ArrayList of male Citizens in specified district
     */
    protected static ArrayList<Citizen> showMalesPerDistrict(Stream<Citizen> citizenList, int district) {
        return citizenList
                .filter(c -> c.getGender() == 'M' && c.getDistrict() == district)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * This method filters citizens that belong to a specified district,
     * and at the same time, are identified as females. <br>
     * Done by: Jeanne Ferrer
     *
     * @param district    the user indicated district number
     * @param CitizenList a stream generated from a Citizen ArrayList
     * @return an ArrayList of female Citizens in specified district
     */
    protected static ArrayList<Citizen> showFemalesPerDistrict(Stream<Citizen> CitizenList, int district) {
        //.forEach(System.out::println);
        return CitizenList
                .filter(c -> c.getGender() == 'F' && c.getDistrict() == district)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * This method gets a group of Citizens within the specified age range in a district. <br>
     * Done by: Jeanne Ferrer
     * <p>
     *
     * @param citizenList a stream generated from a Citizen ArrayList
     * @param district    the user indicated district number
     * @param max         the maximum age
     * @param min         the minimum age
     * @return an ArrayList of Citizens within an age range in a specified district
     */
    protected static ArrayList<Citizen> showAgesWithinRangePerDistrict(Stream<Citizen> citizenList, int district,
                                                                       int min, int max) {
        return citizenList
                .filter(c -> c.getDistrict() == district && c.getAge() <= max && c.getAge() >= min)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // COUNT OPERATIONS (GLOBALLY)

    /**
     * This method counts the people within a range. <br>
     * Done by: Jerome Bustarde
     * <p>
     * Method Algorithm
     * 1. filter the stream to gather all the values within the range
     * 2. count the elements in the stream
     *
     * @param CitizenList stream of citizen list
     * @param min         minimum range
     * @param max         maximum range
     */
    protected static long countPeopleWithinRange(Stream<Citizen> CitizenList, int min, int max) {
        return CitizenList
                .filter(c -> c.getDistrict() >= min && c.getDistrict() <= max)
                .count();
    }

    /**
     * This method counts the number of males present within an arrayList. <br>
     * By: Enrico Castro
     * <p>
     * Method Algorithm: <br>
     * 1. filter the stream to check the arrayList of citizens who are identified as males
     * 2. count the number of residents who are observed to be males
     *
     * @param CitizenList stream implying the list of citizens
     */
    protected static long countMales(Stream<Citizen> CitizenList) {
        return CitizenList
                .filter(p -> p.getGender() == 'M')
                .count();
    }

    /**
     * Returns the number of females in a population demographic <br>
     * By: Kurt Matthew C. Nudo <br>
     * <br>
     * Method Algorithm:
     * 1. Invoke countMales() and subtract it with the total amount of elements
     * inside the stream parameter.
     *
     * @param citizenList
     * @return the number of female citizens
     */
    protected static long countFemales(Stream<Citizen> citizenList) {
        return citizenList.count() - countMales(citizenList);
    }
}

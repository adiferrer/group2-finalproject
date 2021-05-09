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

    /** TODO Kurt: comments */
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
     *   
     * Method Algorithm:
     * 1. sort the value of the citizen's ages in a decreasing order from oldest to youngest
     * 2. collect obtained values of age to a new Collection
     * @param CitizenList stream implying the list of citizens
     * @return an arrayList sorted according to the citizen's ages
     */
    protected static ArrayList<Citizen> sortAccordingToAgeGlobal(Stream<Citizen> CitizenList) {
      return CitizenList
                .sorted((r1, r2)
                        -> r1.getAge() - r2.getAge())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /** 2. TODO Kurt  */
    protected static ArrayList<Citizen> sortAccordingToLastNameGlobal(Stream<Citizen> CitizenList) {
        return CitizenList.collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * This method sorts a stream of citizens according to district. <br>
     * Done by: Jeanne Ferrer
     *
     * @param CitizenList a stream generated from a Citizen ArrayList
     * @return an ArrayList of Citizens that is sorted according to district
     */
    protected static ArrayList<Citizen> sortAccordingToDistrictGlobal(Stream<Citizen> CitizenList) {
        //.forEach(System.out::println);
        return CitizenList
                .sorted((c1, c2) -> c1.getDistrict() - c2.getDistrict())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // SORT OPERATIONS (PER DISTRICT)
    /** TODO EJ: comments */
    protected static ArrayList<Citizen> sortAccordingToAgePerDistrict(Stream<Citizen> CitizenList, int district) {
        return CitizenList
                .filter(c -> c.getDistrict() == district)
                .sorted((c1,c2) -> c1.getAge() - c2.getAge())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * This method sorts a stream according to last name in a specific district. <br>
     * Done by: Jerome Bustarde
     *
     * Method Algorithm:
     * 1. filter the stream to gather all the values in the specific district
     * 2. sort the values by its full name using comparator
     * 3. use collect to store the newly created stream into a new Collection
     *
     * @param CitizenList stream of citizen list
     * @param district district inputted by the user
     * @return an ArrayList of Citizens that is sorted according to last name in a specified district
     */
    protected static ArrayList<Citizen> sortAccordingToLastNamePerDistrict(Stream<Citizen> CitizenList, int district) {
        return CitizenList
            .filter(c -> c.getDistrict() == district)
            .sorted(Comparator.comparing(Citizen::getFullName))
            .collect(Collectors.toCollection(ArrayList::new));
    }

    // SHOW OPERATIONS (GLOBALLY)
    /**
     * 1. By: Enrico Castro 
     * This method amasses the names of the residents present in the arrayList . <br>
     *   
     * Method Algorithm:
     * 1. filter the stream to check all observed residents within the list
     * 2. collect obtained names of citizens who are listed as residents
     * @param CitizenList stream implying the list of citizens
     * @return an arrayList of citizens who are residents
     */
    protected static ArrayList<Citizen> showResidentsGlobal(Stream<Citizen> CitizenList) {
        return CitizenList
                .filter(c -> c.isResident() == true)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /** 2. TODO Jomari - iterate manually through loops */
    protected static ArrayList<Citizen> showNonResidentsGlobal(Stream<Citizen> CitizenList) {
        return CitizenList.collect(Collectors.toCollection(ArrayList::new));
    }

    /** 3. TODO Kurt - iterate manually through loops */
    protected static ArrayList<Citizen> showMalesGlobal(Stream<Citizen> CitizenList) {
        return CitizenList.collect(Collectors.toCollection(ArrayList::new));
    }

     /**
      * This method creates a collection of all the female citizens. <br>
      * Done by: Jerome Bustarde
      * <p>
      * Method Algorithm: <br>
      * 1. Filter to stream to gather all the values of all the female citizens <br>
      * 2. Use collect to store the newly created stream into a new Collection
      *
      * @param CitizenList stream of citizen list
      * @return
      */
    protected static ArrayList<Citizen> showFemalesGlobal(Stream<Citizen> CitizenList) {
        return CitizenList
            .filter(f -> f.getGender() == 'F')
            .collect(Collectors.toCollection(ArrayList::new));
    }

    /** TODO EJ: comments  */
    protected static ArrayList<Citizen> showAgesWithinRangeGlobal(Stream<Citizen> CitizenList, int min, int max) {
        return CitizenList
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
     * @param CitizenList a stream generated from a Citizen ArrayList
     * @param district the user indicated district number
     * @return an ArrayList of Citizens
     */
    protected static ArrayList<Citizen> showResidentsPerDistrict(Stream<Citizen> CitizenList, int district) {
        //forEach(System.out::println);
        return CitizenList
                .filter(c -> c.getDistrict() == district && c.isResident())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /** 2. TODO Andre - Generate a stream from a Citizen ArrayList then use Stream.filter, then display outputs directly */
    protected static ArrayList<Citizen> showNonResidentsPerDistrict(Stream<Citizen> CitizenList, int district) {
        return CitizenList.collect(Collectors.toCollection(ArrayList::new));
    }

    /** 3. TODO EJ - Generate a stream from a Citizen ArrayList then use Stream.filter, then display outputs directly */
    protected static ArrayList<Citizen> showMalesPerDistrict(Stream<Citizen> CitizenList, int district) {
        return CitizenList
                .filter(c -> c.getGender() == 'M' && c.getDistrict() == district)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * This method filters citizens that belong to a specified district,
     * and at the same time, are identified as females. <br>
     * Done by: Jeanne Ferrer
     *
     * @param district a stream generated from a Citizen ArrayList
     * @param CitizenList the user indicated district number
     * @return an ArrayList of Citizens
     */
    protected static ArrayList<Citizen> showFemalesPerDistrict(Stream<Citizen> CitizenList, int district) {
        //.forEach(System.out::println);
        return CitizenList
                .filter(c -> c.getGender() == 'F' && c.getDistrict() == district)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /** 5. TODO Jomari - Generate a stream from a Citizen ArrayList then use Stream.filter, then display outputs directly */
    protected static ArrayList<Citizen> showAgesWithinRangePerDistrict(Stream<Citizen> CitizenList, int district,
                                                                       int min, int max) {
        return CitizenList.collect(Collectors.toCollection(ArrayList::new));
    }

    // COUNT OPERATIONS (GLOBALLY)
     /**
      * This method counts the people within a range. <br>
      * Done by: Jerome Bustarde
      *
      * Method Algorithm
      * 1. filter the stream to gather all the values within the range
      * 2. count the elements in the stream
      * @param CitizenList stream of citizen list
      * @param min minimum range
      * @param max maximum range
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
     * @param CitizenList stream implying the list of citizens
     */
    protected static long countMales(Stream<Citizen> CitizenList) {
       return CitizenList
                .filter(p -> p.getGender() == 'M')
                .count();
    }

    /** 3. TODO Kurt - Use the result returned by item number 3, then subtract from the total number of Citizen objects */
    protected static ArrayList<Citizen> countFemales(Stream<Citizen> CitizenList) {
        return CitizenList.collect(Collectors.toCollection(ArrayList::new));
    }
}

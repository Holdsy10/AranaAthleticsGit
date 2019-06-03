package com.example.curtisholdsworth1.aranaathletics1;

/**
 * Getter and Setter class for reading the Athlete Information into the System
 */
class AthleteSample {

    //String Declarations
    private String athleteName;
    private String athleteLastName;
    private String athleteNumber;
    private String athleteAge;
    private String athleteGender;
    private String parent1Name;
    private String parent2Name;

    /**
     * Gets the athlete names
     * @return athletes name as string.
     */
    public String getAthleteName() {
        return athleteName;
    }

    /**
     * Sets the athletes name
     * @param athleteName Athletes name as a string.
     */
    public void setAthleteName(String athleteName) {
        this.athleteName = athleteName;
    }

    /**
     * Gets the athletes Bib number
     * @return athletes bib number as a string
     */
    public String getAthleteNumber() {
        return athleteNumber;
    }

    /**
     * Sets the athletes Bib number
     * @param athleteNumber Athletes bib number as a string.
     */
    public void setAthleteNumber(String athleteNumber) {
        this.athleteNumber = athleteNumber;
    }

    /**
     * Gets the athletes age
     * @return Athletes age as a string.
     */
    public String getAthleteAge() {
        return athleteAge;
    }

    /**
     * Sets the athletes age
     * @param athleteAge Athletes age as a string
     */
    public void setAthleteAge(String athleteAge) {
        this.athleteAge = athleteAge;
    }

    /**
     * Gets the athletes gender
     * @return Athletes gender as a string
     */
    public String getAthleteGender() {
        return athleteGender;
    }

    /**
     * Sets the athletes gender
     * @param athleteGender Athletes gender as a string
     */
    public void setAthleteGender(String athleteGender) {
        this.athleteGender = athleteGender;
    }

    /**
     * Gets the first parent name for the athlete
     * @return first parents name as a string
     */
    public String getParent1Name(){
        return parent1Name;
    }

    /**
     * Sets the first parent name for the athlete
     * @param parent1Name first parents name as a string.
     */
    public void setParent1Name(String parent1Name) {
        this.parent1Name = parent1Name;
    }

    /**
     * Gets the second parent name for the athlete
     * @return second parent name for the athlete
     */
    public String getParent2Name(){
        return parent2Name;
    }

    /**
     * Sets the seocond parent name for the athlete
     * @param parent2Name second parent name for the athlete
     */
    public void setParent2Name(String parent2Name) {
        this.parent2Name = parent2Name;
    }
}

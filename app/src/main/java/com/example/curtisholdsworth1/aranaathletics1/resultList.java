package com.example.curtisholdsworth1.aranaathletics1;

/**
 * Getter and Setter Class for the Results
 */
public class resultList {

    //Declarations
    Integer raceNumber;
    String raceDistance;
    String raceType;
    String lane1AthleteNumber;
    String lane2AthleteNumber;

    /**
     * gets the race number
     * @return the race number as an int
     */
    public Integer getRaceNumber() {
        return raceNumber;
    }

    /**
     *
     * @param raceNumber
     */
    public void setRaceNumber(Integer raceNumber) {
        this.raceNumber = raceNumber;
    }

    /**
     *
     * @return
     */
    public String getRaceDistance() {
        return raceDistance;
    }

    /**
     *
     * @param raceDistance
     */
    public void setRaceDistance(String raceDistance) {
        this.raceDistance = raceDistance;
    }

    /**
     *
     * @return
     */
    public String getRaceType() {
        return raceType;
    }

    /**
     *
     * @param raceType
     */
    public void setRaceType(String raceType) {
        this.raceType = raceType;
    }

    /**
     *
     * @return
     */
    public String getLane1AthleteNumber() {
        return lane1AthleteNumber;
    }

    /**
     *
     * @param lane1AthleteNumber
     */
    public void setLane1AthleteNumber(String lane1AthleteNumber) {
        this.lane1AthleteNumber = lane1AthleteNumber;
    }

    /**
     *
     * @return
     */
    public String getLane2AthleteNumber() {
        return lane2AthleteNumber;
    }

    /**
     *
     * @param lane2AthleteNumber
     */
    public void setLane2AthleteNumber(String lane2AthleteNumber) {
        this.lane2AthleteNumber = lane2AthleteNumber;
    }


}

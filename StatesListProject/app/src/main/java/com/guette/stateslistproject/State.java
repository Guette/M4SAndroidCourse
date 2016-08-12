package com.guette.stateslistproject;

/**
 * Created by MAGUETTE on 02/08/2016.
 */
public class State {

    private long idStates;
    private String nameStates;
    private String capitalStates;
    private String latitude;
    private String longitude;

    public State() {
    }

    public State(long idStates, String nameStates, String capitalStates, String latitude, String longitude) {
        this.idStates = idStates;
        this.nameStates = nameStates;
        this.capitalStates = capitalStates;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getIdStates() {
        return idStates;
    }

    public void setIdStates(Long idStates) {
        this.idStates = idStates;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCapitalStates() {
        return capitalStates;
    }

    public void setCapitalStates(String capitalStates) {
        this.capitalStates = capitalStates;
    }

    public String getNameStates() {
        return nameStates;
    }

    public void setNameStates(String nameStates) {
        this.nameStates = nameStates;
    }
}

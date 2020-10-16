package com.example.user.emergencyapps;

/**
 * Created by user on 8/7/2020.
 */

public class reportcase {
    private String CrimeType;
    private String Imageid;
    private String Email;
    private String Report;
    private String Station;


    public reportcase() {
    }

    public reportcase(String crimeType, String imageid, String email, String report, String station) {
        this.CrimeType = crimeType;
        this.Imageid   = imageid;
        this.Email     = email;
        this.Report    = report;
        this.Station   = station;
    }

    public String getCrimeType() {
        return CrimeType;
    }

    public void setCrimeType(String crimeType) {
        CrimeType = crimeType;
    }

    public String getImageid() {
        return Imageid;
    }

    public void setImageid(String imageid) {
        Imageid = imageid;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getReport() {
        return Report;
    }

    public void setReport(String report) {
        Report = report;
    }

    public String getStation() {
        return Station;
    }

    public void setStation(String station) {
        Station = station;
    }
}

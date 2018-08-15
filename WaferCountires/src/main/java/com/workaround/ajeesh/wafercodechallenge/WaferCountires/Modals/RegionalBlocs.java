package com.workaround.ajeesh.wafercodechallenge.WaferCountires.Modals;

/**
 * Package Name : com.workaround.ajeesh.wafercodechallenge.WaferCountires.Modals
 * Created by ajesh on 15-08-2018.
 * Project Name : WaferCodeChallenge
 */
public class RegionalBlocs {
    private String[] otherAcronyms;

    private String acronym;

    private String name;

    private String[] otherNames;

    public String[] getOtherAcronyms() {
        return otherAcronyms;
    }

    public void setOtherAcronyms(String[] otherAcronyms) {
        this.otherAcronyms = otherAcronyms;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(String[] otherNames) {
        this.otherNames = otherNames;
    }

    @Override
    public String toString() {
        return "RegionalBlocs [otherAcronyms = " + otherAcronyms + ", acronym = "
                + acronym + ", name = " + name + ", otherNames = " + otherNames + "]";
    }
}

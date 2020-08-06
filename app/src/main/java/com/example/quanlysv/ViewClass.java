package com.example.quanlysv;

public class ViewClass {
    private String viewName;
    private String flagName;
    private String population;

    public ViewClass(String viewName, String flagName, String population) {
        this.viewName = viewName;
        this.flagName = flagName;
        this.population = population;
    }

    @Override
    public String toString() {
        return "ViewClass{" +
                "viewName='" + viewName + '\'' +
                ", flagName='" + flagName + '\'' +
                ", population=" + population +
                '}';
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getFlagName() {
        return flagName;
    }

    public void setFlagName(String flagName) {
        this.flagName = flagName;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public ViewClass() {
    }
}

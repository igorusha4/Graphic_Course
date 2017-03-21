package ru.nsu.fit.g14205.ryzhakov.filter.model;

public class FilterSettings implements Cloneable {
    private double lifeBegin = 2.0;
    private double lifeEnd = 3.3;
    private double birthBegin = 2.3;
    private double birthEnd = 2.9;
    private double firstImpact = 1.0;
    private double secondImpact = 0.3;

    public double getLifeBegin() {
        return lifeBegin;
    }

    public FilterSettings(double lifeBegin, double lifeEnd, double birthBegin, double birthEnd, double firstImpact, double secondImpact) {
        this.lifeBegin = lifeBegin;
        this.lifeEnd = lifeEnd;
        this.birthBegin = birthBegin;
        this.birthEnd = birthEnd;
        this.firstImpact = firstImpact;
        this.secondImpact = secondImpact;
    }

    public void setLifeBegin(double lifeBegin) {
        this.lifeBegin = lifeBegin;
    }

    public double getLifeEnd() {
        return lifeEnd;
    }

    public void setLifeEnd(double lifeEnd) {
        this.lifeEnd = lifeEnd;
    }

    public double getBirthBegin() {
        return birthBegin;
    }

    public void setBirthBegin(double birthBegin) {
        this.birthBegin = birthBegin;
    }

    public double getBirthEnd() {
        return birthEnd;
    }

    public void setBirthEnd(double birthEnd) {
        this.birthEnd = birthEnd;
    }

    public double getFirstImpact() {
        return firstImpact;
    }

    public void setFirstImpact(double firstImpact) {
        this.firstImpact = firstImpact;
    }

    public double getSecondImpact() {
        return secondImpact;
    }

    public void setSecondImpact(double secondImpact) {
        this.secondImpact = secondImpact;
    }

    public FilterSettings(){

    }

    @Override
    public FilterSettings clone() throws CloneNotSupportedException{
        return (FilterSettings) super.clone();
    }
}

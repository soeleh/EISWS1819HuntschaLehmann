package com.huntschalehmann2018.eis2018calmrunter.Resources;

public class Karteikarte {
    private String karteikartenfrage;
    private String karteikartenantwort;
    private int durationInMinutes;

    public Karteikarte(String karteikartenantwort, String karteikartenfrage, int durationInMinutes){

        this.karteikartenfrage = karteikartenfrage;
        this.karteikartenantwort = karteikartenantwort;
        this.durationInMinutes = durationInMinutes;
    }

    //Get/Set Methoden
    public String getKarteikartenfrage( ){
        return karteikartenfrage;
    }

    public String getKarteikartenantwort(){
        return karteikartenantwort;
    }

    public int getKarteikarteDurationInMinutes(){ return durationInMinutes; }

    public void setKarteikartenfrage(String karteikartenfrage){
        this.karteikartenfrage = karteikartenfrage;
    }

    public void setKarteikartenantwort(String karteikartenantwort){
        this.karteikartenantwort = karteikartenantwort;
    }

    public void setKarteikarteDurationInMinutes(int durationInMinutes){
        this.durationInMinutes = durationInMinutes;
    }
}

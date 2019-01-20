package com.huntschalehmann2018.eis2018calmrunter.Resources;

import java.util.ArrayList;

public class Karteikartenstapel{
     private int durationInMinutes = 0;
     private String nameErsteller;
     private String nameKarteikartenstapel;

     public Karteikartenstapel(String nameKarteikartenstapel, String nameErsteller){
         this.nameKarteikartenstapel = nameErsteller;
         this.nameErsteller = nameErsteller;
     }

    private ArrayList<Karteikarte> karteikartenListe = new ArrayList<Karteikarte>();

    //Get/Set Methoden
    public String getNameErsteller(String getNameErsteller){
        return nameErsteller;
    }
    public String getNameKarteikartenstapel() { return nameKarteikartenstapel; }
    public String getNameErsteller(){ return this.nameErsteller; }
    public int getDurationInMinutes(){
        return durationInMinutes;
    }
    public int getKarteikartenlistenLaenge(){ return karteikartenListe.size(); }
    public ArrayList getKarteikartenListe(){ return karteikartenListe; }

    public void setNameKarteikartenstapel(String nameKarteikartenstapel){ this.nameKarteikartenstapel = nameKarteikartenstapel; }
    public void setNameErsteller(String nameErsteller){ this.nameErsteller = nameErsteller; }
    public void setDurationInMinutes(int durationInMinutes){ this.durationInMinutes = durationInMinutes; }
    public void setKarteikartenListe(ArrayList karteikartenListe){ this.karteikartenListe = karteikartenListe; }

    public void addKarteikarte(Karteikarte karteikarte) {
        karteikartenListe.add(karteikarte);
        durationInMinutes =+ karteikarte.getKarteikarteDurationInMinutes();
    }
}

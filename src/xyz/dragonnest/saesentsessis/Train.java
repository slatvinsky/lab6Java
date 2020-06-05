package xyz.dragonnest.saesentsessis;

import java.util.Objects;

class Train {
    private String wayPoint;
    private int id;
    private int tHours;
    private int tMins;
    private int pAll;
    private int pKoupe;
    private int pPlatz;
    private int pLux;

    public Train(String wayPoint, int id, int tHours, int tMins, int pKoupe, int pPlatz, int pLux) {
        this.wayPoint = wayPoint;
        this.id = id;
        this.tHours = tHours;
        this.tMins = tMins;
        this.pKoupe = pKoupe;
        this.pPlatz = pPlatz;
        this.pLux = pLux;
        this.pAll = pKoupe+pPlatz+pLux;
    }

    public String getWayP() {
        return wayPoint;
    }
    public void setWayP(String newWP) {
        this.wayPoint = newWP;
    }
    public int getTrNum() {
        return id;
    }
    public void setTrNum(int newTNum) {
        this.id = newTNum;
    }
    public String getTime() {
        return tHours + ":" + tMins;
    }
    public int getHours() {
        return tHours;
    }
    public int getMinutes() {
        return tMins;
    }
    public void setTime(int newH, int newM) {
        this.tHours = newH;
        this.tMins = newM;
    }
    public String getData() {
        return this.getWayP() + "\n" + this.getTrNum() + " " + this.getHours() + " " + this.getMinutes() + " " + this.getpPlatz() + " " + this.getpKoupe() + " " + this.getpLux() + " " ;
    }
    public int getpLux() {
        return pLux;
    }
    public int getpKoupe() {return pKoupe;}
    public int getpPlatz() {return pPlatz;}
    public String getPlases() {
        return String.valueOf("All: "+pAll+"\nKoupe: "+pKoupe+"\nPlatzcart: "+pPlatz+"\nLux: "+pLux);
    }
    public void setPlases(int pKoupe, int pPlatzcart, int pLux) {
        this.pKoupe = pKoupe;
        this.pPlatz = pPlatzcart;
        this.pLux = pLux;
        this.pAll = pKoupe + pPlatzcart + pLux;
    }
    @Override
    public String toString() {
        return "Train{" +
                "WayPoint=" + wayPoint +
                ", TrainNumber=" + id +
                ", TimeToGo=" + tHours + ":" + tMins +
                ", PassangerPlaces=" + pAll + " Koupe=" + pKoupe + " Platzcart=" + pPlatz + " pLux" + pLux + '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train tr = (Train)o;
        return Objects.equals(wayPoint, tr.wayPoint) && id == tr.id && tHours == tr.tHours && tMins == tr.tMins && pKoupe == tr.pKoupe && pPlatz == tr.pPlatz && pLux == tr.pLux;
    }
}


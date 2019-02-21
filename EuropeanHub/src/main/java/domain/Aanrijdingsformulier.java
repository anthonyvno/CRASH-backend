
package domain;

import java.io.Serializable;

public class Aanrijdingsformulier implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private int id;
    private String land;
    private String straat;
    private String huisnummer;
    private String stad;
    private String postcode;
    private boolean gewonden;
    private boolean[] toedrachtA;
    private boolean[] toedrachtB;
    private int VerzekeraarAId;
    private int VerzekeraarBId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVerzekeraarAId() {
        return VerzekeraarAId;
    }

    public void setVerzekeraarAId(int VerzekeraarAId) {
        this.VerzekeraarAId = VerzekeraarAId;
    }

    public int getVerzekeraarBId() {
        return VerzekeraarBId;
    }

    public void setVerzekeraarBId(int VerzekeraarBId) {
        this.VerzekeraarBId = VerzekeraarBId;
    }
    
    

    public boolean isGewonden() {
        return gewonden;
    }

    public void setGewonden(boolean gewonden) {
        this.gewonden = gewonden;
    }
    
    

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getStad() {
        return stad;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public boolean[] getToedrachtA() {
        return toedrachtA;
    }

    public void setToedrachtA(boolean[] toedrachtA) {
        this.toedrachtA = toedrachtA;
    }

    public boolean[] getToedrachtB() {
        return toedrachtB;
    }

    public void setToedrachtB(boolean[] toedrachtB) {
        this.toedrachtB = toedrachtB;
    }
    
    
    
    
}

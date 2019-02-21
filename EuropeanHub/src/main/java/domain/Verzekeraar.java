
package domain;

import java.io.Serializable;

public class Verzekeraar implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private int id;
    private String naam;
    private String land;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }
    
    
    
    
}

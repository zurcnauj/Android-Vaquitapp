package zn.vaquitapp2.vaquitapp.model;

import java.io.Serializable;

public class Persona implements Serializable {
    
    private String nombre;
    
    private float plata_puso;
    private float plata_aPoner;

    public Persona(String nombre, float plata){
        this.nombre = nombre;
        this.plata_puso = plata;
    }
//   settter
    public void setPlata_puso(float plata_puso) {
        this.plata_puso = plata_puso;
    }

    public void setPlata_aPoner(float plata_aPoner) {
        this.plata_aPoner = this.plata_puso - plata_aPoner;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
//    getter
    public float getPlata_puso() {
        return plata_puso;
    }

    public float getPlata_aPoner() {
        return plata_aPoner;
    }

    public String getNombre() {
        return nombre;
    }
}

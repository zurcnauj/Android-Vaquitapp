package zn.vaquitapp2.vaquitapp.control;

import java.util.LinkedList;

import zn.vaquitapp2.vaquitapp.model.Persona;

public class CalculadorDeCostos {

    public CalculadorDeCostos(int cantPersonas, LinkedList<Persona> personas) {
        float total = 0;
        for( int i = personas.size() - 1; i >= 0 ; i-- ){
            total += personas.get(i).getPlata_puso();
        }

        total /= cantPersonas;

        if( cantPersonas > personas.size() ){
            personas.add( new Persona("resto",0));
        }

        for( int i = personas.size() - 1; i >= 0 ; i-- ){
            personas.get(i).setPlata_aPoner(total);
        }
    }
}

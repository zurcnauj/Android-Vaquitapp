package zn.vaquitapp2.vaquitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import zn.vaquitapp2.vaquitapp.model.Persona;

public class ResultadosActivity extends AppCompatActivity {

    LinearLayout contenedor;

    public void btn_terminar(View view) {
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        contenedor = this.findViewById(R.id.ra_llContenedor);

        this.llenarLista();
    }

    private void llenarLista(){
        ArrayList<Persona> personas = (ArrayList<Persona>) this.getIntent().getSerializableExtra("personas");
        for(int i = personas.size() - 1 ; i >= 0 ; i-- ){
            this.nuevaPersona(personas.get(i));
        }
    }
    private void nuevaPersona(Persona persona){
        LayoutInflater inflate = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflate.inflate(R.layout.l_personas_resumen, null);
        TextView nombre = view.findViewById(R.id.l_pr_tvNombre);
        TextView plata = view.findViewById(R.id.l_pr_tvPlata);

        nombre.setText(persona.getNombre());
        if( persona.getPlata_aPoner() >= 0 ){
            plata.setText("Recibe: "+persona.getPlata_aPoner());
        }
        else{
            plata.setText("Pone: "+(persona.getPlata_aPoner() * -1 ));
        }
        contenedor.addView(view);
    }
}

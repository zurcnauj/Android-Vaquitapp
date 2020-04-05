package zn.vaquitapp2.vaquitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import zn.vaquitapp2.vaquitapp.control.CalculadorDeCostos;
import zn.vaquitapp2.vaquitapp.model.Persona;

public class MainActivity extends AppCompatActivity {

    private int cantPersonas;
    
    private LinkedList<Persona> personas;

    private EditText et_cantidad_personas;
    private EditText et_nuevapersona_nombre;
    private EditText et_nuevapersona_plata;

    private Button btn_cantidadPersonas;
    private Button btn_agregarPersonas;
    private Button btn_calcular;

    private LinearLayout l_cantidadPersona;
    private LinearLayout l_nuevaPersona;
    private LinearLayout l_contenedorPersonas;

//    Cantidad de personas
    public void btn_setCantidadPersonas(View view) {
        this.activate_LinearLayout(l_cantidadPersona,true);
    }

    public void btn_cp_cancelar(View view) {
        this.activate_LinearLayout(l_cantidadPersona,false);
    }

    public void btn_cp_aceptar(View view) {
//        Toast.makeText(this, "btn_cp_aceptar", Toast.LENGTH_SHORT).show();
        int cantidad;
        String t_cantidad = et_cantidad_personas.getText().toString();
        cantidad = (t_cantidad.length() == 0 )? 0 : Integer.parseInt(t_cantidad);
        this.cantPersonas = cantidad;
        this.acomodarCantidadPersonas();
        this.activate_LinearLayout(l_cantidadPersona,false);
    }

//    Nueva personas
    public void btn_nuevaPersona(View view) {
        this.activate_LinearLayout(l_nuevaPersona,true);
    }

    public void btn_np_cancelar(View view) {
        this.activate_LinearLayout(l_nuevaPersona,false);
    }

    public void btn_np_aceptar(View view) {
//        Toast.makeText(this, "btn_np_aceptar", Toast.LENGTH_SHORT).show();
        float plata;
        String nombre = this.et_nuevapersona_nombre.getText().toString();
        String t_plata = this.et_nuevapersona_plata.getText().toString();
        if( nombre.length() != 0 ){
            plata = (t_plata.length() == 0 )? 0 : Float.parseFloat(t_plata);

            this.nuevaPersona(nombre,plata);
            this.et_nuevapersona_nombre.setText("");
            this.et_nuevapersona_plata.setText("");
            this.activate_LinearLayout(l_nuevaPersona,false);
            this.acomodarCantidadPersonas();
        }
    }

//    btn calcular
    public void btn_calcular(View view) {
//        Toast.makeText(this, "btn_calcular", Toast.LENGTH_SHORT).show();
        Intent intent;
        if( this.personas.size() > 0 ){
            intent = new Intent(this,ResultadosActivity.class);
            new CalculadorDeCostos( this.cantPersonas, this.personas);
            intent.putExtra("personas", personas);
            this.startActivity(intent);
        }
        else {
            Toast.makeText(this, this.getResources().getString(R.string.error_no_hay_personas), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.cantPersonas = 0 ;
        
        this.personas = new LinkedList<>();
       
        this.et_cantidad_personas = this.findViewById(R.id.ma_cp_etCantidad);
        this.et_nuevapersona_nombre = this.findViewById(R.id.ma_np_etNombre);
        this.et_nuevapersona_plata = this.findViewById(R.id.ma_np_etPlata);

        this.btn_cantidadPersonas = this.findViewById(R.id.ma_btnCantpersonas);
        this.btn_agregarPersonas = this.findViewById(R.id.ma_btnNewpersona);
        this.btn_calcular = this.findViewById(R.id.ma_btnCalcular);

        this.l_cantidadPersona = this.findViewById(R.id.ma_cp_llCantidadPersonas);
        this.l_nuevaPersona = this.findViewById(R.id.ma_np_llAgregarpsersona);
        this.l_contenedorPersonas = this.findViewById(R.id.ma_llContenedor);

        this.activate_LinearLayout(l_cantidadPersona,false);
        this.activate_LinearLayout(l_nuevaPersona,false);

    }

    private void activate_LinearLayout(LinearLayout ll,boolean estado){
        if(estado){
            ll.setEnabled(true);
            ll.setVisibility(View.VISIBLE);
            this.btn_agregarPersonas.setEnabled(false);
            this.btn_cantidadPersonas.setEnabled(false);
            this.btn_calcular.setEnabled(false);
        }
        else{
            ll.setEnabled(false);
            ll.setVisibility(View.INVISIBLE);
            this.btn_agregarPersonas.setEnabled(true);
            this.btn_cantidadPersonas.setEnabled(true);
            this.btn_calcular.setEnabled(true);
        }
    }

    private void nuevaPersona(String nombre, float plata){
        Persona persona = new Persona(nombre, plata);

        LayoutInflater inflate = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflate.inflate(R.layout.l_personas_new, null);
        TextView tv_nombre = view.findViewById(R.id.l_ps_tvNombre);
        TextView tv_plata = view.findViewById(R.id.l_ps_tvPlata);
        Button btn_cerrar = view.findViewById(R.id.l_ps_btnBorrar);

        tv_nombre.setText(nombre);
        tv_plata.setText(""+plata);
        btn_cerrar.setOnClickListener( new OyenteBotonCerrar(persona,view));

        this.personas.add(persona);
        this.l_contenedorPersonas.addView(view);
    }

    private void acomodarCantidadPersonas(){
        int genteAnotada = this.personas.size();
        int cantidad;
        if(this.cantPersonas < genteAnotada){
            cantidad = genteAnotada;
            this.cantPersonas = genteAnotada;
        }
        else{
            cantidad = this.cantPersonas;
        }
        this.btn_cantidadPersonas.setText(this.getResources().getString(R.string.cant_personas)+cantidad);
        this.et_cantidad_personas.setText(""+cantidad);
    }

    private class OyenteBotonCerrar implements View.OnClickListener{

        private Persona persona;
        private View view;

        public OyenteBotonCerrar(Persona persona , View view){
            this.persona = persona;
            this.view = view;
        }

        @Override
        public void onClick(View v) {
            l_contenedorPersonas.removeView(view);
            personas.remove(persona);
        }
    }
}

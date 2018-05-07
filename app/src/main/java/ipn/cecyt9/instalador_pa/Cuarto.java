package ipn.cecyt9.instalador_pa;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

public class Cuarto extends AppCompatActivity {
    agregaCuarto agCu = new agregaCuarto();
    agregaCasa agCa = new agregaCasa();
    agregaUsuario agUsr = new agregaUsuario();

    int idUsr , idCasa ;
    EditText numeroPiso, nombreCuarto;
    ImageButton foco, puerta, camara, clima;
    Button cerrar, cambiarC;

    boolean conD = true, namaC, numPi, tipoD;
    String nn, np;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuarto);
        Toast.makeText(getApplicationContext(), "Agregar pisos/cuartos/dispositivos", Toast.LENGTH_SHORT).show();

        idUsr = getIntent().getExtras().getInt("idUsr");
        idCasa = getIntent().getExtras().getInt("idCasa");

        agCu.setIdCasa(idCasa);

        numeroPiso = (EditText)findViewById(R.id.numeroPiso);
        nombreCuarto = (EditText)findViewById(R.id.nombreCuarto);

        foco = (ImageButton)findViewById(R.id.foco);
        puerta = (ImageButton)findViewById(R.id.puerta);
        camara = (ImageButton)findViewById(R.id.camara);
        clima = (ImageButton)findViewById(R.id.clima);

        cerrar = (Button)findViewById(R.id.cerrar);
        cambiarC = (Button)findViewById(R.id.habilitarNombre);

    }

    public void agregaFoco(View view){
        np = numeroPiso.getText().toString().trim();nn = nombreCuarto.getText().toString().trim();
        if( np.isEmpty() || nn.isEmpty() ){
            Toast.makeText(getApplicationContext(), "Campo(s) vacíos", Toast.LENGTH_SHORT).show();
            conD = false;
        }else{
            numPi = agCu.setNumero_Piso(Integer.parseInt(np));namaC = agCu.setNombreCuarto(nn);
            if(!numPi || !namaC){
                Toast.makeText(getApplicationContext(), "Campo(s) incorrectos", Toast.LENGTH_SHORT).show();
                numeroPiso.setText("");
                conD = false;
            }else{
                numeroPiso.setFocusable(false);
                nombreCuarto.setFocusable(false);
                cambiarC.setVisibility(View.VISIBLE);
                tipoD = agCu.setTipoDisp(1);
                conD = true;
            }


        }

        if(conD){
            Toast.makeText(getApplicationContext(), "Dispositivo dado de alta", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), agCu.agregaCu(), Toast.LENGTH_LONG).show();

        }



    }

    public void agregaPuerta(View view){
        np = numeroPiso.getText().toString().trim();nn = nombreCuarto.getText().toString().trim();
        if( np.isEmpty() || nn.isEmpty() ){
            Toast.makeText(getApplicationContext(), "Campo(s) vacíos", Toast.LENGTH_SHORT).show();
            conD = false;
        }else{
            numPi = agCu.setNumero_Piso(Integer.parseInt(np));namaC = agCu.setNombreCuarto(nn);
            if(!numPi || !namaC){
                Toast.makeText(getApplicationContext(), "Campo(s) incorrectos", Toast.LENGTH_SHORT).show();
                numeroPiso.setText("");
                conD = false;
            }else{
                numeroPiso.setFocusable(false);
                nombreCuarto.setFocusable(false);
                cambiarC.setVisibility(View.VISIBLE);
                tipoD = agCu.setTipoDisp(2);
                conD = true;
            }


        }

        if(conD){
            Toast.makeText(getApplicationContext(), "Dispositivo dado de alta", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), agCu.agregaCu(), Toast.LENGTH_LONG).show();

        }


    }

    public void agregaCamara(View view){
        np = numeroPiso.getText().toString().trim();nn = nombreCuarto.getText().toString().trim();
        if( np.isEmpty() || nn.isEmpty() ){
            Toast.makeText(getApplicationContext(), "Campo(s) vacíos", Toast.LENGTH_SHORT).show();
            conD = false;
        }else{
            numPi = agCu.setNumero_Piso(Integer.parseInt(np));namaC = agCu.setNombreCuarto(nn);
            if(!numPi || !namaC){
                Toast.makeText(getApplicationContext(), "Campo(s) incorrectos", Toast.LENGTH_SHORT).show();
                numeroPiso.setText("");
                conD = false;
            }else{
                numeroPiso.setFocusable(false);
                nombreCuarto.setFocusable(false);
                cambiarC.setVisibility(View.VISIBLE);
                tipoD = agCu.setTipoDisp(3);
                conD = true;
            }


        }

        if(conD){
            Toast.makeText(getApplicationContext(), "Dispositivo dado de alta", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), agCu.agregaCu(), Toast.LENGTH_LONG).show();

        }


    }

    public void agregaClima(View view){
        np = numeroPiso.getText().toString().trim();nn = nombreCuarto.getText().toString().trim();
        if( np.isEmpty() || nn.isEmpty() ){
            Toast.makeText(getApplicationContext(), "Campo(s) vacíos", Toast.LENGTH_SHORT).show();
            conD = false;
        }else{
            numPi = agCu.setNumero_Piso(Integer.parseInt(np));namaC = agCu.setNombreCuarto(nn);
            if(!numPi || !namaC){
                Toast.makeText(getApplicationContext(), "Campo(s) incorrectos", Toast.LENGTH_SHORT).show();
                numeroPiso.setText("");
                conD = false;
            }else{
                numeroPiso.setFocusable(false);
                nombreCuarto.setFocusable(false);
                cambiarC.setVisibility(View.VISIBLE);
                tipoD = agCu.setTipoDisp(4);
                conD = true;
            }


        }

        if(conD){
            Toast.makeText(getApplicationContext(), "Dispositivo dado de alta", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), agCu.agregaCu(), Toast.LENGTH_LONG).show();

        }


    }

    public void cambiarC(View view){
        numeroPiso.setText("");
        numeroPiso.setFocusable(true);
        nombreCuarto.setText("");
        nombreCuarto.setFocusable(true);
        cambiarC.setVisibility(View.INVISIBLE);
    }

    public void cerrar(View view){
        finish();
    }
}
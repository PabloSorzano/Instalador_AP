package ipn.cecyt9.instalador_pa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

public class Cuarto extends AppCompatActivity {
    agregaCuarto agCu = new agregaCuarto();
    agregaCasa agCa = new agregaCasa();
    agregaUsuario agUsr = new agregaUsuario();

    int idUsr , idCasa ;
    EditText numeroPiso, nombreCuarto;
    ImageButton foco, puerta, camara, clima, focoC, puertaC, camaraC, climaC;
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

        focoC = (ImageButton)findViewById(R.id.focoC);
        puertaC = (ImageButton)findViewById(R.id.puertaC);
        camaraC = (ImageButton)findViewById(R.id.camaraC);
        climaC = (ImageButton)findViewById(R.id.climaC);

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
                nombreCuarto.setText("");
                conD = false;
            }else{
                cerrar.setVisibility(View.VISIBLE);

                focoC.setVisibility(View.VISIBLE);
                puertaC.setVisibility(View.VISIBLE);
                camaraC.setVisibility(View.VISIBLE);
                climaC.setVisibility(View.VISIBLE);

                numeroPiso.setEnabled(false);
                nombreCuarto.setEnabled(false);
                cambiarC.setVisibility(View.VISIBLE);
                tipoD = agCu.agregaDisp(1);
                conD = true;
            }


        }

        if(conD){
            Toast.makeText(getApplicationContext(), "Dispositivo dado de alta", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), agCu.despliegueDatos(), Toast.LENGTH_LONG).show();

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
                nombreCuarto.setText("");
                numeroPiso.setText("");
                conD = false;
            }else{
                cerrar.setVisibility(View.VISIBLE);

                focoC.setVisibility(View.VISIBLE);
                puertaC.setVisibility(View.VISIBLE);
                camaraC.setVisibility(View.VISIBLE);
                climaC.setVisibility(View.VISIBLE);

                numeroPiso.setEnabled(false);
                nombreCuarto.setEnabled(false);
                cambiarC.setVisibility(View.VISIBLE);
                tipoD = agCu.agregaDisp(2);
                conD = true;
            }


        }

        if(conD){
            Toast.makeText(getApplicationContext(), "Dispositivo dado de alta", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), agCu.despliegueDatos(), Toast.LENGTH_LONG).show();

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
                nombreCuarto.setText("");
                numeroPiso.setText("");
                conD = false;
            }else{
                cerrar.setVisibility(View.VISIBLE);

                focoC.setVisibility(View.VISIBLE);
                puertaC.setVisibility(View.VISIBLE);
                camaraC.setVisibility(View.VISIBLE);
                climaC.setVisibility(View.VISIBLE);

                numeroPiso.setEnabled(false);
                nombreCuarto.setEnabled(false);
                cambiarC.setVisibility(View.VISIBLE);
                tipoD = agCu.agregaDisp(3);
                conD = true;
            }


        }

        if(conD){
            Toast.makeText(getApplicationContext(), "Dispositivo dado de alta", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), agCu.despliegueDatos(), Toast.LENGTH_LONG).show();

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
                nombreCuarto.setText("");
                numeroPiso.setText("");
                conD = false;
            }else{
                cerrar.setVisibility(View.VISIBLE);

                focoC.setVisibility(View.VISIBLE);
                puertaC.setVisibility(View.VISIBLE);
                camaraC.setVisibility(View.VISIBLE);
                climaC.setVisibility(View.VISIBLE);

                numeroPiso.setEnabled(false);
                nombreCuarto.setEnabled(false);
                cambiarC.setVisibility(View.VISIBLE);
                tipoD = agCu.agregaDisp(4);
                conD = true;
            }


        }

        if(conD){
            Toast.makeText(getApplicationContext(), "Dispositivo dado de alta", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), agCu.despliegueDatos(), Toast.LENGTH_LONG).show();

        }


    }

    public void cambiarC(View view){
        numeroPiso.setText("");
        numeroPiso.setEnabled(true);
        nombreCuarto.setText("");
        nombreCuarto.setEnabled(true);

        focoC.setVisibility(View.INVISIBLE);
        puertaC.setVisibility(View.INVISIBLE);
        camaraC.setVisibility(View.INVISIBLE);
        climaC.setVisibility(View.INVISIBLE);

        cambiarC.setVisibility(View.INVISIBLE);
    }

    public void quitarFoco(View view){
        agCu.quitaDisp(1);
        Toast.makeText(getApplicationContext(), agCu.despliegueDatos(), Toast.LENGTH_LONG).show();
    }

    public void quitarPuerta(View view){
        agCu.quitaDisp(2);
        Toast.makeText(getApplicationContext(), agCu.despliegueDatos(), Toast.LENGTH_LONG).show();
    }

    public void quitarCamara(View view){
        agCu.quitaDisp(3);
        Toast.makeText(getApplicationContext(), agCu.despliegueDatos(), Toast.LENGTH_LONG).show();
    }

    public void quitarClima(View view){
        agCu.quitaDisp(4);
        Toast.makeText(getApplicationContext(), agCu.despliegueDatos(), Toast.LENGTH_LONG).show();
    }

    public void cerrar(View view){
        finish();
    }
}
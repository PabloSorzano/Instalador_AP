package ipn.cecyt9.instalador_pa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import ipn.cecyt9.instalador_pa.data.CuartoEntity;
import ipn.cecyt9.instalador_pa.data.Cuarto_DispositivosEntity;
import ipn.cecyt9.instalador_pa.data.SmartHouseDBHelper;

public class Cuarto extends AppCompatActivity {
    agregaCuarto agCu = new agregaCuarto();
    agregaCasa agCa = new agregaCasa();
    agregaUsuario agUsr = new agregaUsuario();
    SmartHouseDBHelper jj = new SmartHouseDBHelper(getApplicationContext());

    int idUsr , idCasa ;
    String xnombre, xaPat, xaMat, xcel, xmail, xpass;
    String xcoorde ,xEstado, xMuni, xCodigoP, xCol, xCalle, xNumInt;
    EditText numeroPiso, nombreCuarto;
    ImageButton foco, puerta, camara, clima, focoC, puertaC, camaraC, climaC;
    Button cerrar, cambiarC;

    boolean conD = true, namaC, numPi, tipoD;
    String nn, np;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        idUsr = getIntent().getExtras().getInt("idUsr");
        xnombre = getIntent().getExtras().getString("xnombre");
        xaPat = getIntent().getExtras().getString("xaPat");
        xaMat = getIntent().getExtras().getString("xaMat");
        xcel = getIntent().getExtras().getString("xcel");
        xmail = getIntent().getExtras().getString("xmail");
        xpass = getIntent().getExtras().getString("xpass");

        idCasa = getIntent().getExtras().getInt("idCasa");
        xEstado = getIntent().getExtras().getString("xEstado");
        xMuni = getIntent().getExtras().getString("xMuni");
        xCodigoP = getIntent().getExtras().getString("xCodigoP");
        xCol = getIntent().getExtras().getString("xCol");
        xCalle = getIntent().getExtras().getString("xCalle");
        xNumInt = getIntent().getExtras().getString("xNumInt");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuarto);
        Toast.makeText(getApplicationContext(), "Agregar pisos/cuartos/dispositivos", Toast.LENGTH_SHORT).show();



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
                //cerrar.setVisibility(View.VISIBLE);

                focoC.setVisibility(View.VISIBLE);
                puertaC.setVisibility(View.VISIBLE);
                camaraC.setVisibility(View.VISIBLE);
                climaC.setVisibility(View.VISIBLE);

                numeroPiso.setEnabled(false);
                nombreCuarto.setEnabled(false);
                cambiarC.setVisibility(View.VISIBLE);
                tipoD = agCu.agregaDisp(1);
                jj.saveCuarto1(new CuartoEntity(agCu.getIdCuarto(), idCasa, agCu.getNombreCuarto(), String.valueOf(agCu.getNumero_Piso()), ""));
                conD = true;
            }


        }

        if(conD){
            Toast.makeText(getApplicationContext(), agCu.despliegueDatos(), Toast.LENGTH_LONG).show();
            jj.saveCuarto2(new Cuarto_DispositivosEntity(agCu.getIdCuartoDisp(), agCu.getIdCuarto(), agCu.getTipoDesp()));
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
                //cerrar.setVisibility(View.VISIBLE);

                focoC.setVisibility(View.VISIBLE);
                puertaC.setVisibility(View.VISIBLE);
                camaraC.setVisibility(View.VISIBLE);
                climaC.setVisibility(View.VISIBLE);

                numeroPiso.setEnabled(false);
                nombreCuarto.setEnabled(false);
                cambiarC.setVisibility(View.VISIBLE);
                tipoD = agCu.agregaDisp(2);
                jj.saveCuarto1(new CuartoEntity(agCu.getIdCuarto(), idCasa, agCu.getNombreCuarto(), String.valueOf(agCu.getNumero_Piso()), ""));
                conD = true;
            }


        }

        if(conD){
            Toast.makeText(getApplicationContext(), agCu.despliegueDatos(), Toast.LENGTH_LONG).show();
            jj.saveCuarto2(new Cuarto_DispositivosEntity(agCu.getIdCuartoDisp(), agCu.getIdCuarto(), agCu.getTipoDesp()));
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
                //cerrar.setVisibility(View.INVISIBLE);

                focoC.setVisibility(View.VISIBLE);
                puertaC.setVisibility(View.VISIBLE);
                camaraC.setVisibility(View.VISIBLE);
                climaC.setVisibility(View.VISIBLE);

                numeroPiso.setEnabled(false);
                nombreCuarto.setEnabled(false);
                cambiarC.setVisibility(View.VISIBLE);
                tipoD = agCu.agregaDisp(3);
                jj.saveCuarto1(new CuartoEntity(agCu.getIdCuarto(), idCasa, agCu.getNombreCuarto(), String.valueOf(agCu.getNumero_Piso()), ""));
                conD = true;
            }


        }

        if(conD){
            Toast.makeText(getApplicationContext(), agCu.despliegueDatos(), Toast.LENGTH_LONG).show();
            jj.saveCuarto2(new Cuarto_DispositivosEntity(agCu.getIdCuartoDisp(), agCu.getIdCuarto(), agCu.getTipoDesp()));

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
                //cerrar.setVisibility(View.INVISIBLE);

                focoC.setVisibility(View.VISIBLE);
                puertaC.setVisibility(View.VISIBLE);
                camaraC.setVisibility(View.VISIBLE);
                climaC.setVisibility(View.VISIBLE);

                numeroPiso.setEnabled(false);
                nombreCuarto.setEnabled(false);
                cambiarC.setVisibility(View.VISIBLE);
                tipoD = agCu.agregaDisp(4);
                jj.saveCuarto1(new CuartoEntity(agCu.getIdCuarto(), idCasa, agCu.getNombreCuarto(), String.valueOf(agCu.getNumero_Piso()), ""));
                conD = true;
            }


        }

        if(conD){
            Toast.makeText(getApplicationContext(), agCu.despliegueDatos(), Toast.LENGTH_LONG).show();
            jj.saveCuarto2(new Cuarto_DispositivosEntity(agCu.getIdCuartoDisp(), agCu.getIdCuarto(), agCu.getTipoDesp()));
        }


    }

    public void cambiarC(View view){
        numeroPiso.setText("");
        numeroPiso.setEnabled(true);
        nombreCuarto.setText("");
        nombreCuarto.setEnabled(true);
        
        cerrar.setVisibility(View.VISIBLE);
        
        focoC.setVisibility(View.INVISIBLE);
        puertaC.setVisibility(View.INVISIBLE);
        camaraC.setVisibility(View.INVISIBLE);
        climaC.setVisibility(View.INVISIBLE);

        cambiarC.setVisibility(View.INVISIBLE);
        
        agCu.setPuertas(0);
        agCu.setFocos(0);
        agCu.setClimas(0);
        agCu.setCamaras(0);
    }

    public void quitarFoco(View view){
        agCu.quitaDisp(1);
        Toast.makeText(getApplicationContext(), agCu.despliegueDatos(), Toast.LENGTH_LONG).show();
        jj.saveCuarto2(new Cuarto_DispositivosEntity(agCu.getIdCuartoDisp(), agCu.getIdCuarto(), agCu.getTipoDesp()));
    }

    public void quitarPuerta(View view){
        agCu.quitaDisp(2);
        Toast.makeText(getApplicationContext(), agCu.despliegueDatos(), Toast.LENGTH_LONG).show();
        jj.saveCuarto2(new Cuarto_DispositivosEntity(agCu.getIdCuartoDisp(), agCu.getIdCuarto(), agCu.getTipoDesp()));
    }

    public void quitarCamara(View view){
        agCu.quitaDisp(3);
        Toast.makeText(getApplicationContext(), agCu.despliegueDatos(), Toast.LENGTH_LONG).show();
        jj.saveCuarto2(new Cuarto_DispositivosEntity(agCu.getIdCuartoDisp(), agCu.getIdCuarto(), agCu.getTipoDesp()));
    }

    public void quitarClima(View view){
        agCu.quitaDisp(4);
        Toast.makeText(getApplicationContext(), agCu.despliegueDatos(), Toast.LENGTH_LONG).show();
        jj.saveCuarto2(new Cuarto_DispositivosEntity(agCu.getIdCuartoDisp(), agCu.getIdCuarto(), agCu.getTipoDesp()));
    }

    public void cerrar(View view){
        finish();
    }
}
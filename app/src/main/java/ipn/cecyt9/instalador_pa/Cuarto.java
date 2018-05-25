package ipn.cecyt9.instalador_pa;

import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import ipn.cecyt9.instalador_pa.data.CuartoEntity;
import ipn.cecyt9.instalador_pa.data.Cuarto_DispositivosEntity;
import ipn.cecyt9.instalador_pa.data.SmartConstract;
import ipn.cecyt9.instalador_pa.data.SmartHouseDBHelper;

public class Cuarto extends AppCompatActivity {
    agregaCuarto agCu = new agregaCuarto();
    agregaCasa agCa = new agregaCasa();
    agregaUsuario agUsr = new agregaUsuario();


    int idUsr , idCasa ;
    String xnombre, xaPat, xaMat, xcel, xmail, xpass;
    String xcoorde ,xEstado, xMuni, xCodigoP, xCol, xCalle, xNumInt;
    EditText numeroPiso, nombreCuarto;
    ImageButton foco, puerta, camara, clima, focoC, puertaC, camaraC, climaC;
    Button cerrar, cambiarC;

    boolean conD = true, namaC, numPi, tipoD;
    String nn, np, mensaje;

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
        xcoorde = getIntent().getExtras().getString("xcoorde");

        mensaje ="";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuarto);



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

                conD = true;
            }


        }

        if(conD){
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
                //cerrar.setVisibility(View.VISIBLE);

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
                //cerrar.setVisibility(View.INVISIBLE);

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
                //cerrar.setVisibility(View.INVISIBLE);

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
            Toast.makeText(getApplicationContext(), agCu.despliegueDatos(), Toast.LENGTH_LONG).show();
        }


    }

    public void cambiarC(View view){
        SmartHouseDBHelper jj = new SmartHouseDBHelper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase = jj.getWritableDatabase();

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
        saveCuarto1(view);
        saveCuarto2(view);
        jj.close();

        agCu.setPuertas(0);
        agCu.setFocos(0);
        agCu.setClimas(0);
        agCu.setCamaras(0);
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

    public void saveCuarto1(View view) {
        SmartHouseDBHelper jj = new SmartHouseDBHelper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase = jj.getWritableDatabase();


        try{
            //Se intenta meter el arreglo de datos a la base de datos
            sqLiteDatabase.insertOrThrow(SmartConstract.CuartoEntry.TABLE_NAME, null, toContentValues(0));
            mensaje = "Cuarto guardado con exito";
        }catch (SQLException e){
            //Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();


    }

    public void saveCuarto2(View view) {
        SmartHouseDBHelper jj = new SmartHouseDBHelper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase = jj.getWritableDatabase();


        try{
            //Se intenta meter el arreglo de datos a la base de datos
            sqLiteDatabase.insertOrThrow(SmartConstract.CuartoDispEntry.TABLE_NAME, null, toContentValues(1));
            mensaje = "Dispositivos guardados con exito";
        }catch (SQLException e){
            //Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();


    }


    public ContentValues toContentValues(int i) {
        ContentValues values = new ContentValues();
    if(i == 0){
        values.put(SmartConstract.CuartoEntry.ID_CUARTO, agCu.getIdCuarto());
        values.put(SmartConstract.CuartoEntry.ID_CASA, idCasa);
        values.put(SmartConstract.CuartoEntry.NOMBRE_CUARTO, agCu.getNombreCuarto());
        values.put(SmartConstract.CuartoEntry.NUMERO_PISO, agCu.getNumero_Piso());
        values.put(SmartConstract.CuartoEntry.OBSERVACION, "");
    }else if(i==1){
        values.put(SmartConstract.CuartoDispEntry.ID_CUARTO_DISP, agCu.getIdCuartoDisp());
        values.put(SmartConstract.CuartoDispEntry.ID_CUARTO, agCu.getIdCuarto());
        values.put(SmartConstract.CuartoDispEntry.ID_TIPO_DISP, agCu.getTipoDesp());
    }

        return values;
    }

    public void mostrar(View view){
        String fina = "Nombre: "+xnombre+"\n" +
                      "Celular: "+xcel+"\n" +
                      "Correo: "+xmail+"\n" +
                      "Contraseña: "+xpass+"\n" +
                      "Coordenadas: "+xcoorde+"\n" +
                      "Número Interior: "+xNumInt+"";
        Toast.makeText(getApplicationContext(), fina, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), Inicio.class);
        finish();
        startActivity(intent);
    }


}
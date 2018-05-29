package ipn.cecyt9.instalador_pa;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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
    Cursor cursor;
    SQLiteDatabase sqLiteDatabase;
    SmartHouseDBHelper jj;


    int idUsr , idCasa, idCuarto, idCuartoDisp;
    String xnombre, xaPat, xaMat, xcel, xmail, xpass;
    String xcoorde ,xEstado, xMuni, xCodigoP, xCol, xCalle, xNumInt;
    EditText numeroPiso, nombreCuarto;
    ImageButton foco, puerta, camara, clima, focoC, puertaC, camaraC, climaC;
    Button cambiarC, agregaC;

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

        cambiarC = (Button)findViewById(R.id.habilitarNombre);
        agregaC = (Button)findViewById(R.id.agregaCu);

        focoC = (ImageButton)findViewById(R.id.focoC);
        puertaC = (ImageButton)findViewById(R.id.puertaC);
        camaraC = (ImageButton)findViewById(R.id.camaraC);
        climaC = (ImageButton)findViewById(R.id.climaC);

    }

    public void visibleC(View view){
        np = numeroPiso.getText().toString().trim();nn = nombreCuarto.getText().toString().trim();
        if( np.isEmpty() || nn.isEmpty() ){
            Toast.makeText(getApplicationContext(), "Campo(s) vacíos", Toast.LENGTH_SHORT).show();
        }else{
            numPi = agCu.setNumero_Piso(Integer.parseInt(np));namaC = agCu.setNombreCuarto(nn);
            if(!numPi || !namaC){
                Toast.makeText(getApplicationContext(), "Campo(s) incorrectos", Toast.LENGTH_SHORT).show();
                numeroPiso.setText("");
                nombreCuarto.setText("");
            }else{
                buscaID1();
                //cerrar.setVisibility(View.VISIBLE);
                saveCuarto1(view);
                agregaC.setVisibility(View.INVISIBLE);

                foco.setVisibility(View.VISIBLE);
                puerta.setVisibility(View.VISIBLE);
                camara.setVisibility(View.VISIBLE);
                clima.setVisibility(View.VISIBLE);

                focoC.setVisibility(View.VISIBLE);
                puertaC.setVisibility(View.VISIBLE);
                camaraC.setVisibility(View.VISIBLE);
                climaC.setVisibility(View.VISIBLE);

                numeroPiso.setEnabled(false);
                nombreCuarto.setEnabled(false);
                cambiarC.setVisibility(View.VISIBLE);


            }


        }


    }

    public void agregaFoco(View view){
        tipoD = agCu.agregaDisp(1);
        Toast.makeText(getApplicationContext(), agCu.despliegueDatos(), Toast.LENGTH_SHORT).show();
    }

    public void agregaPuerta(View view){
        tipoD = agCu.agregaDisp(2);
        Toast.makeText(getApplicationContext(), agCu.despliegueDatos(), Toast.LENGTH_SHORT).show();
    }

    public void agregaCamara(View view){
        tipoD = agCu.agregaDisp(3);
        Toast.makeText(getApplicationContext(), agCu.despliegueDatos(), Toast.LENGTH_SHORT).show();
    }

    public void agregaClima(View view){
        tipoD = agCu.agregaDisp(4);
        Toast.makeText(getApplicationContext(), agCu.despliegueDatos(), Toast.LENGTH_SHORT).show();
    }

    public void buscaID1(){
        jj = new SmartHouseDBHelper(getApplicationContext());
        sqLiteDatabase = jj.getWritableDatabase();
        String query = "select max("+SmartConstract.CuartoEntry.ID_CUARTO+") from "+SmartConstract.CuartoEntry.TABLE_NAME+"";
        cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            idCuarto = Integer.parseInt(cursor.getString(0)) + 1;
            //Toast.makeText(getApplicationContext(), "ID Cuarto: "+idCuarto, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "No existen registros", Toast.LENGTH_SHORT).show();
        }


    }

    public void buscaID2(){
        jj = new SmartHouseDBHelper(getApplicationContext());
        sqLiteDatabase = jj.getWritableDatabase();
        String query = "select max("+SmartConstract.CuartoDispEntry.ID_CUARTO_DISP+") from "+SmartConstract.CuartoDispEntry.TABLE_NAME+"";
        cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            idCuartoDisp = Integer.parseInt(cursor.getString(0)) + 1;
            //Toast.makeText(getApplicationContext(), "ID CuartoDisp: "+idCuartoDisp, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "No existen registros", Toast.LENGTH_SHORT).show();
        }


    }

    public void cambiarC(View view){
        SmartHouseDBHelper jj = new SmartHouseDBHelper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase = jj.getWritableDatabase();
        agregaC.setVisibility(View.VISIBLE);

        numeroPiso.setText("");
        numeroPiso.setEnabled(true);
        nombreCuarto.setText("");
        nombreCuarto.setEnabled(true);
        

        foco.setVisibility(View.INVISIBLE);
        puerta.setVisibility(View.INVISIBLE);
        camara.setVisibility(View.INVISIBLE);
        clima.setVisibility(View.INVISIBLE);

        focoC.setVisibility(View.INVISIBLE);
        puertaC.setVisibility(View.INVISIBLE);
        camaraC.setVisibility(View.INVISIBLE);
        climaC.setVisibility(View.INVISIBLE);

        cambiarC.setVisibility(View.INVISIBLE);

        int noFocos = agCu.getFocos();
        int noPuertas = agCu.getPuertas();
        int noCamaras = agCu.getCamaras();
        int noClimas = agCu.getClimas();
        if(noFocos!= 0){
            do{
                buscaID2();
                saveCuarto2(1);
                noFocos--;
            }while(noFocos!=0);
        }if(noPuertas!=0){
            do{
                buscaID2();
                saveCuarto2(2);
                noPuertas--;
            }while(noPuertas!=0);
        }if(noCamaras!=0){
            do{
                buscaID2();
                saveCuarto2(3);
                noCamaras--;
            }while(noCamaras!=0);
        }if(noClimas!=0){
            do{
                buscaID2();
                saveCuarto2(4);
                noClimas--;
            }while(noClimas!=0);
        }

        jj.close();

        agCu.setPuertas(0);
        agCu.setFocos(0);
        agCu.setClimas(0);
        agCu.setCamaras(0);

        mostrar(view);
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
            sqLiteDatabase.insertOrThrow(SmartConstract.CuartoEntry.TABLE_NAME, null, toContentValues());
            mensaje = "Cuarto guardado con exito";
        }catch (SQLException e){
            //Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();


    }
    int j = 0;
    public void saveCuarto2(int tipo) {
        SmartHouseDBHelper jj = new SmartHouseDBHelper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase = jj.getWritableDatabase();
        j++;
        try{
            //Se intenta meter el arreglo de datos a la base de datos
            sqLiteDatabase.insertOrThrow(SmartConstract.CuartoDispEntry.TABLE_NAME, null, dispos(tipo));
            mensaje = "Dispositivos guardados con exito";
        }catch (SQLException e){
            //Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }
        if(j==1){


            Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
        }else{

        }


    }


    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();

        values.put(SmartConstract.CuartoEntry.ID_CUARTO, idCuarto);
        values.put(SmartConstract.CuartoEntry.ID_CASA, idCasa);
        values.put(SmartConstract.CuartoEntry.NOMBRE_CUARTO, agCu.getNombreCuarto());
        values.put(SmartConstract.CuartoEntry.NUMERO_PISO, agCu.getNumero_Piso());
        values.put(SmartConstract.CuartoEntry.OBSERVACION, "");

        return values;
    }

    public ContentValues dispos(int tipo){
        ContentValues values = new ContentValues();
        values.put(SmartConstract.CuartoDispEntry.ID_CUARTO_DISP, idCuartoDisp);
        values.put(SmartConstract.CuartoDispEntry.ID_CUARTO, idCuarto);
        values.put(SmartConstract.CuartoDispEntry.ID_TIPO_DISP, tipo);

        return values;
    }

    public void mostrar(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(Cuarto.this);
        builder.setCancelable(false);
        builder.setTitle("AÑADIR CUARTO");
        builder.setMessage("Desea agregar otro cuarto?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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
                });

        // Create the AlertDialog object and return it
        builder.create().show();


    }


}
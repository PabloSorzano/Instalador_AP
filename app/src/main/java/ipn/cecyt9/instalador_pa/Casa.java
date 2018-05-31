package ipn.cecyt9.instalador_pa;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.*;

import ipn.cecyt9.instalador_pa.data.SmartConstract;
import ipn.cecyt9.instalador_pa.data.SmartHouseDBHelper;

public class Casa extends AppCompatActivity{
    agregaCasa agCasa = new agregaCasa();
    Cursor cursor;
    SQLiteDatabase sqLiteDatabase;
    SmartHouseDBHelper jj;

    EditText latitud, longitud, estado, municipio, codigoP, colonia, calle, numInt;
    Button agrega;

    String xnombre, xaPat, xaMat, xcel, xmail, xpass;

    int idUsr, idCasa, minLat=0, minLong=0;
    float LAT, LOG;
    String xEstado, xMuni, xCodigoP, xCol, xCalle, xNumInt, mensaje;
    String estDef = "Ingrese el estado",
            munDef = "Ingrese el municipio",
            codDef = "Ingrese el codigo postal",
            colDef = "Ingrese la colonia",
            calDef = "Ingrese la calle",
            intDef = "Ingrese el numero interior",
            latDef = "Ingrese latitud de la casa (numeros positivos y negativos con solo un punto)",
            longDef = "Ingrese longitud de la casa (numeros positivos y negativos con solo un punto)";
    boolean coord=false, state, mun, coP, col, cal, numI;
    boolean conD = true, finity = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        idUsr = getIntent().getExtras().getInt("idUsr");
        xnombre = getIntent().getExtras().getString("xnombre");
        xaPat = getIntent().getExtras().getString("xaPat");
        xaMat = getIntent().getExtras().getString("xaMat");
        xcel = getIntent().getExtras().getString("xcel");
        xmail = getIntent().getExtras().getString("xmail");
        xpass = getIntent().getExtras().getString("xpass");
        mensaje = "";
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_casa);
        latitud = (EditText)findViewById(R.id.latitud);
        longitud = (EditText)findViewById(R.id.longitud);
        estado = (EditText)findViewById(R.id.estado);
        municipio = (EditText)findViewById(R.id.municipio);
        codigoP = (EditText)findViewById(R.id.codigoP);
        colonia = (EditText)findViewById(R.id.colonia);
        calle = (EditText)findViewById(R.id.calle);
        numInt = (EditText)findViewById(R.id.numInt);

        agrega = (Button)findViewById(R.id.baja);

        jj = new SmartHouseDBHelper(getApplicationContext());
        sqLiteDatabase = jj.getWritableDatabase();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(!finity){
            sqLiteDatabase.delete(SmartConstract.UsrEntry.TABLE_NAME, SmartConstract.UsrEntry.ID_USUARIO+" = ?", new String[]{String.valueOf(idUsr)});
            //Toast.makeText(this, "Usuario eliminado", Toast.LENGTH_SHORT).show();
        }else{
        }
    }

    public void agregaCasa(View view){

        try{
            LAT = Float.parseFloat(latitud.getText().toString().trim());
            LOG = Float.parseFloat(longitud.getText().toString().trim());

            agCasa.setLAT(String.valueOf(LAT));
            agCasa.setLONG(String.valueOf(LOG));

            coord = agCasa.setxCoorde("(lat: " + LAT + ", long: " + LOG + ")");
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }


        //Toast.makeText(getApplicationContext(), agCasa.getxCoorde(), Toast.LENGTH_LONG).show();
        state = agCasa.setxEstado(estado.getText().toString().trim());
        mun = agCasa.setxMuni(municipio.getText().toString().trim());
        coP = agCasa.setxCodigoP(codigoP.getText().toString().trim());
        col = agCasa.setxCol(colonia.getText().toString().trim());
        cal = agCasa.setxCalle(calle.getText().toString().trim());
        numI = agCasa.setxNumInt(numInt.getText().toString().trim());


        xEstado = agCasa.getxEstado();
        xMuni = agCasa.getxMuni();
        xCodigoP = agCasa.getxCodigoP();
        xCol = agCasa.getxCol();
        xCalle = agCasa.getxCalle();
        xNumInt = agCasa.getxNumInt();


        if (xEstado.equals(estDef) || state == false) {
            Toast.makeText(getApplicationContext(), "Estado incorrecto", Toast.LENGTH_SHORT).show();
            estado.setText("");
            conD = false;
        }else if (xMuni.equals(munDef) || mun == false) {
            Toast.makeText(getApplicationContext(), "Municipio incorrecto", Toast.LENGTH_SHORT).show();
            municipio.setText("");
            conD = false;
        }else if (xCodigoP.equals(codDef) || coP == false || xCodigoP.length() != 5) {
            Toast.makeText(getApplicationContext(), "Codigo Postal incorrecto", Toast.LENGTH_SHORT).show();
            codigoP.setText("");
            conD = false;
        }else if (xCol.equals(colDef) || col == false) {
            Toast.makeText(getApplicationContext(), "Colonia incorrecta", Toast.LENGTH_SHORT).show();
            colonia.setText("");
            conD = false;
        }else if (xCalle.equals(calDef) || cal == false) {
            Toast.makeText(getApplicationContext(), "Calle incorrecta", Toast.LENGTH_SHORT).show();
            calle.setText("");
            conD = false;
        }else if (xNumInt.equals(intDef) || numI == false || xNumInt.length() > 10) {
            Toast.makeText(getApplicationContext(), "Numero Interior incorrecto", Toast.LENGTH_SHORT).show();
            numInt.setText("");
            conD = false;

        }else if (coord == false) {
            Toast.makeText(getApplicationContext(), "Coordenadas incorrectas", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "Recuerda que las coordenadas son dadas por números", Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "Además de llevar +  - y debe llevar un punto", Toast.LENGTH_LONG).show();
            minLat = 0;
            minLong = 0;
            latitud.setText("");
            longitud.setText("");
            conD = false;

        } else {
            conD = true;
            if (conD) {

                agCasa.setIdUsr(idUsr);
                //Toast.makeText(getApplicationContext(), agCasa.agregaHouse(), Toast.LENGTH_LONG).show();
                buscarID();

                Intent casa = new Intent(getApplicationContext(), Cuarto.class);

                casa.putExtra("idCasa", idCasa);
                casa.putExtra("xcoorde", agCasa.getxCoorde() );
                casa.putExtra("xEstado", xEstado);
                casa.putExtra("xMuni", xMuni);
                casa.putExtra("xCodigoP", xCodigoP);
                casa.putExtra("xCol", xCol);
                casa.putExtra("xCalle", xCalle);
                casa.putExtra("xNumInt", xNumInt);

                casa.putExtra("idUsr", idUsr);
                casa.putExtra("xnombre", xnombre);
                casa.putExtra("xaPat", xaPat);
                casa.putExtra("xaMat", xaMat);
                casa.putExtra("xcel", xcel);
                casa.putExtra("xmail", xmail);
                casa.putExtra("xpass", xpass);


                saveCasa(view);
                jj.close();


                finish();
                startActivity(casa);
            }
        }
    }

    public void buscarID(){

        String query = "select max("+SmartConstract.CasaEntry.ID_CASA+") from "+SmartConstract.CasaEntry.TABLE_NAME+"";
        cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            idCasa = Integer.parseInt(cursor.getString(0)) + 1;
            //Toast.makeText(getApplicationContext(), "ID Casa: "+idCasa, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "No existen registros", Toast.LENGTH_SHORT).show();
        }


    }

    public void saveCasa(View view) {
        SmartHouseDBHelper jj = new SmartHouseDBHelper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase = jj.getWritableDatabase();

        try{
            //Se intenta meter el arreglo de datos a la base de datos
            sqLiteDatabase.insertOrThrow(SmartConstract.CasaEntry.TABLE_NAME, null, toContentValues(view));
            mensaje = "Casa guardada con exito";
            finity = true;
        }catch (SQLException e){
            //Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }
        //Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();



    }
    public ContentValues toContentValues(View view) {
        ContentValues values = new ContentValues();

        values.put(SmartConstract.CasaEntry.ID_CASA, idCasa);
        values.put(SmartConstract.CasaEntry.ID_USUARIO, idUsr);
        values.put(SmartConstract.CasaEntry.COORDENADAS, agCasa.getxCoorde());
        values.put(SmartConstract.CasaEntry.ESTADO, xEstado);
        values.put(SmartConstract.CasaEntry.MUNICIPIO, xMuni);
        values.put(SmartConstract.CasaEntry.CODIGO_POSTAL, xCodigoP);
        values.put(SmartConstract.CasaEntry.COLONIA, xCol);
        values.put(SmartConstract.CasaEntry.CALLE, xCalle);
        values.put(SmartConstract.CasaEntry.NUMERO_INTERIOR, xNumInt);
        return values;
    }

    public void minLat(View view){
        minLat++;latitud.setEnabled(false);
        latitud.setInputType(InputType.TYPE_CLASS_TEXT);
        latitud.setText("");
        latitud.setEnabled(true);
        if(minLat%2==0){
            //Toast.makeText(getApplicationContext(), "click uno", Toast.LENGTH_SHORT).show();
            latitud.append("+");
            latitud.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            latitud.performClick();
        }else if(minLat%2!=0){
            //Toast.makeText(getApplicationContext(), "click dos", Toast.LENGTH_SHORT).show();
            latitud.append("-");
            latitud.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            latitud.performClick();
        }
    }

    public void minLong(View view){
        minLong++;longitud.setEnabled(false);
        longitud.setInputType(InputType.TYPE_CLASS_TEXT);
        longitud.setText("");
        longitud.setEnabled(true);

        if(minLong%2==0){
            //Toast.makeText(getApplicationContext(), "click uno", Toast.LENGTH_SHORT).show();
            longitud.append("+");
            longitud.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            longitud.performClick();
        }else if(minLong%2!=0){
            //Toast.makeText(getApplicationContext(), "click dos", Toast.LENGTH_SHORT).show();
            longitud.append("-");
            longitud.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            longitud.performClick();
        }
    }
}

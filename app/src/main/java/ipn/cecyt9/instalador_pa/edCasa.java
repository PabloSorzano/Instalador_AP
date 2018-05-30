package ipn.cecyt9.instalador_pa;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ipn.cecyt9.instalador_pa.data.SmartConstract;
import ipn.cecyt9.instalador_pa.data.SmartHouseDBHelper;

public class edCasa extends AppCompatActivity {
    SmartHouseDBHelper jj ;
    SQLiteDatabase sqLiteDatabase ;
    Cursor cursor;
    agregaUsuario agUsr = new agregaUsuario();
    agregaCasa agCasa = new agregaCasa();
    Intent edUsr;

    EditText Latitud, Longitud, Estado, Municipio, CodigoP, Colonia, Calle, NumInt;
    Button edita;

    int idCasa, idUsr, minLat=0, minLong=0;
    float LAT, LOG;
    String coorde, estado, muni, codP, col, call, numInt;
    String nombre, aPat, aMat, telefono, email, pass;

    String table, columns[], selection, selectionArgs[], groupBy, having, orderBy, limit, msj;
    String whereClause, whereArgs[];

    boolean coord , state, mun, coP, colo, cal, numI;
    boolean conD=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ed_casa);

        edUsr = new Intent(getApplicationContext(), edCuarto.class);

        idCasa = getIntent().getExtras().getInt("idCasa");
        coorde = getIntent().getExtras().getString("coorde");
        estado = getIntent().getExtras().getString("estado");
        muni = getIntent().getExtras().getString("muni");
        codP = getIntent().getExtras().getString("codP");
        col = getIntent().getExtras().getString("col");
        call = getIntent().getExtras().getString("call");
        numInt = getIntent().getExtras().getString("numInt");

        idUsr = getIntent().getExtras().getInt("idUsr");
        nombre = getIntent().getExtras().getString("nombre");
        aPat = getIntent().getExtras().getString("aPat");
        aMat = getIntent().getExtras().getString("aMat");
        telefono = getIntent().getExtras().getString("telefono");
        email = getIntent().getExtras().getString("email");
        pass = getIntent().getExtras().getString("pass");

        Latitud = (EditText)findViewById(R.id.Latitud);
        Longitud = (EditText)findViewById(R.id.Longitud);
        Estado = (EditText)findViewById(R.id.Estado);
        Municipio = (EditText)findViewById(R.id.Municipio);
        CodigoP = (EditText)findViewById(R.id.CodigoP);
        Colonia = (EditText)findViewById(R.id.Colonia);
        Calle = (EditText)findViewById(R.id.Calle);
        NumInt = (EditText)findViewById(R.id.NumInt);

        edita = (Button)findViewById(R.id.Edita);

        jj = new SmartHouseDBHelper(getApplicationContext());
        sqLiteDatabase = jj.getWritableDatabase();
    }

    public void editaCasa(View view){
        SmartHouseDBHelper jj = new SmartHouseDBHelper(getApplicationContext());

        try{
            LAT = Float.parseFloat(Latitud.getText().toString().trim());
            LOG = Float.parseFloat(Longitud.getText().toString().trim());

            agCasa.setLAT(String.valueOf(LAT));
            agCasa.setLONG(String.valueOf(LOG));
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        coord = agCasa.setxCoorde("(lat: "+LAT+", long: "+LOG+")");
        //Toast.makeText(getApplicationContext(), agCasa.getxCoorde(), Toast.LENGTH_LONG).show();
        state = agCasa.setxEstado(Estado.getText().toString().trim());
        mun = agCasa.setxMuni(Municipio.getText().toString().trim());
        coP = agCasa.setxCodigoP(CodigoP.getText().toString().trim());
        colo = agCasa.setxCol(Colonia.getText().toString().trim());
        cal = agCasa.setxCalle(Calle.getText().toString().trim());
        numI = agCasa.setxNumInt(NumInt.getText().toString().trim());


        estado = agCasa.getxEstado();
        muni = agCasa.getxMuni();
        codP = agCasa.getxCodigoP();
        col = agCasa.getxCol();
        call = agCasa.getxCalle();
        numInt = agCasa.getxNumInt();


        if (state == false) {
            Toast.makeText(getApplicationContext(), "Estado incorrecto", Toast.LENGTH_SHORT).show();
            Estado.setText("");
        }else if (mun == false) {
            Toast.makeText(getApplicationContext(), "Municipio incorrecto", Toast.LENGTH_SHORT).show();
            Municipio.setText("");
        }else if (coP == false || codP.length() != 5) {
            Toast.makeText(getApplicationContext(), "Codigo Postal incorrecto", Toast.LENGTH_SHORT).show();
            CodigoP.setText("");
        }else if (colo == false) {
            Toast.makeText(getApplicationContext(), "Colonia incorrecta", Toast.LENGTH_SHORT).show();
            Colonia.setText("");
        }else if (cal == false) {
            Toast.makeText(getApplicationContext(), "Calle incorrecta", Toast.LENGTH_SHORT).show();
            Calle.setText("");
        }else if (numI == false || numInt.length() > 10) {
            Toast.makeText(getApplicationContext(), "Numero Interior incorrecto", Toast.LENGTH_SHORT).show();
            NumInt.setText("");
        }else if (coord == false) {
            Toast.makeText(getApplicationContext(), "Coordenadas incorrectas", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "Recuerda que puede llevar +  - y debe llevar un punto", Toast.LENGTH_LONG).show();
            minLat = 0;
            minLong = 0;
            Latitud.setText("");
            Longitud.setText("");

        } else  {
            conD = true;
            if (conD) {
                //Toast.makeText(getApplicationContext(), agUsr.agregaUsuario(), Toast.LENGTH_LONG).show();
                table = SmartConstract.CasaEntry.TABLE_NAME;
                whereClause = SmartConstract.CasaEntry.ID_USUARIO + "=?";
                whereArgs = new String[]{String.valueOf(idUsr)};
                sqLiteDatabase.update(table, toContentValues(), whereClause, whereArgs);
                Toast.makeText(getApplicationContext(), "Casa actualizada", Toast.LENGTH_SHORT).show();
                jj.close();


                edUsr.putExtra("idCasa", idCasa);
                edUsr.putExtra("coorde", coorde);
                edUsr.putExtra("estado", estado);
                edUsr.putExtra("muni", muni);
                edUsr.putExtra("codP", codP);
                edUsr.putExtra("col", col);
                edUsr.putExtra("call", call);
                edUsr.putExtra("numInt", numInt);

                edUsr.putExtra("idUsr", idUsr);
                edUsr.putExtra("nombre", nombre);
                edUsr.putExtra("aPat", aPat);
                edUsr.putExtra("aMat", aMat);
                edUsr.putExtra("telefono", telefono);
                edUsr.putExtra("email", email);
                edUsr.putExtra("pass", pass);


                finish();
                startActivity(edUsr);
            }else{
            }

        }



    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();

        values.put(SmartConstract.CasaEntry.ID_CASA, idCasa);
        values.put(SmartConstract.CasaEntry.ID_USUARIO, idUsr);
        values.put(SmartConstract.CasaEntry.COORDENADAS, agCasa.getxCoorde());
        values.put(SmartConstract.CasaEntry.ESTADO, estado);
        values.put(SmartConstract.CasaEntry.MUNICIPIO, muni);
        values.put(SmartConstract.CasaEntry.CODIGO_POSTAL, codP);
        values.put(SmartConstract.CasaEntry.COLONIA, col);
        values.put(SmartConstract.CasaEntry.CALLE, call);
        values.put(SmartConstract.CasaEntry.NUMERO_INTERIOR, numInt);
        return values;
    }

    public void MinLat(View view){
        minLat++;Latitud.setEnabled(false);
        Latitud.setInputType(InputType.TYPE_CLASS_TEXT);
        Latitud.setText("");
        Latitud.setEnabled(true);
        if(minLat%2==0){
            //Toast.makeText(getApplicationContext(), "click uno", Toast.LENGTH_SHORT).show();
            Latitud.append("+");
            Latitud.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            Latitud.performClick();
        }else if(minLat%2!=0){
            //Toast.makeText(getApplicationContext(), "click dos", Toast.LENGTH_SHORT).show();
            Latitud.append("-");
            Latitud.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            Latitud.performClick();
        }
    }

    public void MinLong(View view){
        minLong++;Longitud.setEnabled(false);
        Longitud.setInputType(InputType.TYPE_CLASS_TEXT);
        Longitud.setText("");
        Longitud.setEnabled(true);

        if(minLong%2==0){
            //Toast.makeText(getApplicationContext(), "click uno", Toast.LENGTH_SHORT).show();
            Longitud.append("+");
            Longitud.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            Longitud.performClick();
        }else if(minLong%2!=0){
            //Toast.makeText(getApplicationContext(), "click dos", Toast.LENGTH_SHORT).show();
            Longitud.append("-");
            Longitud.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            Longitud.performClick();
        }
    }
}

package ipn.cecyt9.instalador_pa;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import ipn.cecyt9.instalador_pa.data.SmartConstract;
import ipn.cecyt9.instalador_pa.data.SmartHouseDBHelper;
import ipn.cecyt9.instalador_pa.data.UsrEntity;

public class Usuario extends AppCompatActivity {
    SmartHouseDBHelper jj ;
    SQLiteDatabase sqLiteDatabase ;
    Cursor cursor;

    EditText nombre, aPat, aMat, cel, email, password;
    Button agrega;

    agregaUsuario agUsr = new agregaUsuario();
    agregaCasa agCasa = new agregaCasa();
    boolean nama, pata, mata, celu, mai, pass, conD = true, agregarUsr;
    String namDef = "Anote el nombre",
    patDef = "Anote el apellido paterno",
    matDef = "Anote el apellido materno",
    celDef = "Anote el número móvil (10 dígitos)",
    mailDef = "Anote el correo electrónico",
    passDef = "Anote la contraseña",
    xnombre, xaPat, xaMat, xcel, xmail, xpass, mensaje;
    int idUsr = 666, contador=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(getApplicationContext(), "Ingrese los datos del propietario", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        nombre = (EditText) findViewById(R.id.nombre);
        aPat = (EditText) findViewById(R.id.aPat);
        aMat = (EditText) findViewById(R.id.aMat);
        cel = (EditText) findViewById(R.id.cel);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pass);

        agrega = (Button) findViewById(R.id.agreg);
        mensaje ="";

        jj = new SmartHouseDBHelper(getApplicationContext());
        sqLiteDatabase = jj.getWritableDatabase();
    }



    public void agregaUsuario(View view) {
        SmartHouseDBHelper jj = new SmartHouseDBHelper(getApplicationContext());
        if(contador==0){

        }else{

        }
        nama = agUsr.setXnombre(nombre.getText().toString().trim());
        pata = agUsr.setXaPat(aPat.getText().toString().trim());
        mata = agUsr.setXaMat(aMat.getText().toString().trim());
        celu = agUsr.setXcel(cel.getText().toString().trim());
        mai = agUsr.setXmail(email.getText().toString().trim());
        pass = agUsr.setXpass(password.getText().toString().trim());

        xnombre = agUsr.getXnombre();
        xaPat = agUsr.getXaPat();
        xaMat = agUsr.getXaMat();
        xcel = agUsr.getXcel();
        xmail = agUsr.getXmail();
        xpass = agUsr.getXpass();

        if (xnombre.equals(namDef) || nama == false) {
            Toast.makeText(getApplicationContext(), "Nombre incorrecto", Toast.LENGTH_SHORT).show();
            nombre.setText("");
            conD = false;
        } else if (xaPat.equals(patDef) || pata == false) {
            Toast.makeText(getApplicationContext(), "Apellido Paterno incorrecto", Toast.LENGTH_SHORT).show();
            aPat.setText("");
            conD = false;
        } else if (xaMat.equals(matDef) || mata == false) {
            Toast.makeText(getApplicationContext(), "Apellido Materno incorrecto", Toast.LENGTH_SHORT).show();
            aMat.setText("");
            conD = false;
        } else if (xcel.equals(celDef) || xcel.length() != 10 || celu == false) {
            Toast.makeText(getApplicationContext(), "Celular incorrecto", Toast.LENGTH_SHORT).show();
            cel.setText("");
            conD = false;
        } else if (xmail.equals(mailDef) || mai == false) {
            Toast.makeText(getApplicationContext(), "Correo incorrecto", Toast.LENGTH_SHORT).show();
            email.setText("");
            conD = false;
        } else if (xpass.equals(passDef) || pass == false) {
            Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
            password.setText("");
            conD = false;
        } else{
            conD = true;
            if (conD) {
                //Toast.makeText(getApplicationContext(), agUsr.agregaUsuario(), Toast.LENGTH_LONG).show();
                if(!existe()){
                    buscarID();

                    Intent casa = new Intent(getApplicationContext(), Casa.class);
                    casa.putExtra("idUsr", idUsr);
                    casa.putExtra("xnombre", xnombre);
                    casa.putExtra("xaPat", xaPat);
                    casa.putExtra("xaMat", xaMat);
                    casa.putExtra("xcel", xcel);
                    casa.putExtra("xmail", xmail);
                    casa.putExtra("xpass", xpass);


                    saveUsr(view);
                    jj.close();


                    finish();
                    startActivity(casa);
                }else{//si existe
                }


            }
        }

    }



    public void saveUsr(View view) {
        SmartHouseDBHelper jj = new SmartHouseDBHelper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase = jj.getWritableDatabase();

        try{
            //Se intenta meter el arreglo de datos a la base de datos
            sqLiteDatabase.insertOrThrow(SmartConstract.UsrEntry.TABLE_NAME, null, toContentValues());
            mensaje = "Usuario guardado con exito";
        }catch (SQLException e){
            //Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();


    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();

        values.put(SmartConstract.UsrEntry.ID_USUARIO, idUsr);
        values.put(SmartConstract.UsrEntry.NAME_USUARIO, xnombre);
        values.put(SmartConstract.UsrEntry.APELLIDO_PATERNO, xaPat);
        values.put(SmartConstract.UsrEntry.APELLIDO_MATERNO, xaMat);
        values.put(SmartConstract.UsrEntry.TELEFONO_MOVIL, xcel);
        values.put(SmartConstract.UsrEntry.EMAIL, xmail);
        values.put(SmartConstract.UsrEntry.CONTRASEÑA, xpass);

        return values;
    }

    public boolean existe(){
        String query = "select * from "+SmartConstract.UsrEntry.TABLE_NAME+" where "+SmartConstract.UsrEntry.EMAIL+" =?";
        cursor = sqLiteDatabase.rawQuery(query, new String[]{xmail});

        if (cursor.moveToFirst()) {
            Toast.makeText(getApplicationContext(), "Ya existe un usuario con ese correo electrónico", Toast.LENGTH_SHORT).show();
            email.setText("");
            return true;

        }else{
            return false;

        }
    }

    public void buscarID(){
        String query = "select max("+SmartConstract.UsrEntry.ID_USUARIO+") from "+SmartConstract.UsrEntry.TABLE_NAME+"";
        cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            idUsr = Integer.parseInt(cursor.getString(0)) + 1;
            //Toast.makeText(getApplicationContext(), "ID Usuario: "+idUsr, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "No existen registros", Toast.LENGTH_SHORT).show();
        }


    }

}

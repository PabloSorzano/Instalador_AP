package ipn.cecyt9.instalador_pa;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import android.widget.Toast;

import ipn.cecyt9.instalador_pa.data.SmartConstract;
import ipn.cecyt9.instalador_pa.data.SmartHouseDBHelper;

public class edUsuario extends AppCompatActivity {
    SmartHouseDBHelper jj ;
    SQLiteDatabase sqLiteDatabase ;
    Cursor cursor;
    agregaUsuario agUsr = new agregaUsuario();
    Intent edUsr;

    String nomCuarto, numPiso, obser;
    String table, columns[], selection, selectionArgs[], groupBy, having, orderBy, limit, msj;
    int idCuartoDisp, idTipoDisp;

    int idCasa, idUsr, idCuarto;
    String coorde, estado, muni, codP, col, call, numInt;
    String nombre, aPat, aMat, telefono, email, pass;

    EditText xnombre, xaPat, xaMat, xtelefono, xemail, xpass;

    boolean nama, pata, mata, celu, mai, contra, conD=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ed_usuario);

        edUsr = new Intent(getApplicationContext(), edCasa.class);

        idCasa = getIntent().getExtras().getInt("idCasa");
        idUsr = getIntent().getExtras().getInt("idUsr");
        coorde = getIntent().getExtras().getString("coorde");
        estado = getIntent().getExtras().getString("estado");
        muni = getIntent().getExtras().getString("muni");
        codP = getIntent().getExtras().getString("codP");
        col = getIntent().getExtras().getString("col");
        call = getIntent().getExtras().getString("call");
        numInt = getIntent().getExtras().getString("numInt");
        nombre = getIntent().getExtras().getString("nombre");
        aPat = getIntent().getExtras().getString("aPat");
        aMat = getIntent().getExtras().getString("aMat");
        telefono = getIntent().getExtras().getString("telefono");
        email = getIntent().getExtras().getString("email");
        pass = getIntent().getExtras().getString("pass");

        xnombre = (EditText)findViewById(R.id.xNombre);
        xaPat = (EditText)findViewById(R.id.xaPat);
        xaMat = (EditText)findViewById(R.id.xaMat);
        xtelefono = (EditText)findViewById(R.id.xCel);
        xemail = (EditText)findViewById(R.id.xEmail);
        xpass = (EditText)findViewById(R.id.xPass);

        xnombre.setText(nombre);
        xaPat.setText(aPat);
        xaMat.setText(aMat);
        xtelefono.setText(telefono);
        xemail.setText(email);
        xemail.setEnabled(false);
        xpass.setText(pass);

        jj = new SmartHouseDBHelper(getApplicationContext());
        sqLiteDatabase = jj.getWritableDatabase();
    }
    String whereClause, whereArgs[];
    public void editaUsuario(View view){
        SmartHouseDBHelper jj = new SmartHouseDBHelper(getApplicationContext());

        nama = agUsr.setXnombre(xnombre.getText().toString().trim());
        pata = agUsr.setXaPat(xaPat.getText().toString().trim());
        mata = agUsr.setXaMat(xaMat.getText().toString().trim());
        celu = agUsr.setXcel(xtelefono.getText().toString().trim());
        mai = agUsr.setXmail(xemail.getText().toString().trim());
        contra = agUsr.setXpass(xpass.getText().toString().trim());


        nombre = agUsr.getXnombre();
        aPat = agUsr.getXaPat();
        aMat = agUsr.getXaMat();
        telefono = agUsr.getXcel();
        email = agUsr.getXmail();
        pass = agUsr.getXpass();

        if (nama == false) {
            Toast.makeText(getApplicationContext(), "Nombre incorrecto", Toast.LENGTH_SHORT).show();
            xnombre.setText("");
        } else if (pata == false) {
            Toast.makeText(getApplicationContext(), "Apellido Paterno incorrecto", Toast.LENGTH_SHORT).show();
            xaPat.setText("");
        } else if(mata == false) {
            Toast.makeText(getApplicationContext(), "Apellido Materno incorrecto", Toast.LENGTH_SHORT).show();
            xaMat.setText("");
        } else if (telefono.length() != 10 || celu == false) {
            Toast.makeText(getApplicationContext(), "Celular incorrecto", Toast.LENGTH_SHORT).show();
            xtelefono.setText("");
        } else if (mai == false) {
            Toast.makeText(getApplicationContext(), "Correo incorrecto", Toast.LENGTH_SHORT).show();
            xemail.setText("");
        } else if (contra == false) {
            Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
            xpass.setText("");
        } else{
            conD = true;
            if (conD) {
                //Toast.makeText(getApplicationContext(), agUsr.agregaUsuario(), Toast.LENGTH_LONG).show();
                table = SmartConstract.UsrEntry.TABLE_NAME;
                whereClause = SmartConstract.UsrEntry.EMAIL+"=?";
                whereArgs = new String[]{email};
                sqLiteDatabase.update(table, toContentValues(), whereClause, whereArgs);
                //Toast.makeText(getApplicationContext(), "Usuario actualizado", Toast.LENGTH_SHORT).show();
                jj.close();


                edUsr.putExtra("idCasa", idCasa);
                edUsr.putExtra("coorde", coorde );
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
                }else{//si existe
                }


            }

    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();

        values.put(SmartConstract.UsrEntry.ID_USUARIO, idUsr);
        values.put(SmartConstract.UsrEntry.NAME_USUARIO, nombre);
        values.put(SmartConstract.UsrEntry.APELLIDO_PATERNO, aPat);
        values.put(SmartConstract.UsrEntry.APELLIDO_MATERNO, aMat);
        values.put(SmartConstract.UsrEntry.TELEFONO_MOVIL, telefono);
        values.put(SmartConstract.UsrEntry.EMAIL, email);
        values.put(SmartConstract.UsrEntry.CONTRASEÑA, pass);

        return values;
    }


}

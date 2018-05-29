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

    int c, idCuartoC[], i;
    String nomCuarto[], numPiso[];

    int d, idCuartoD[], idCuartoDisp[], idTipoDisp[], j;

    int idCasa, idUsr;
    String coorde, estado, muni, codP, col, call, numInt;
    String nombre, aPat, aMat, telefono, email, pass;

    EditText xnombre, xaPat, xaMat, xtelefono, xemail, xpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ed_usuario);

        c = getIntent().getExtras().getInt("indicadorC");
        idCuartoC = new int[c];
        nomCuarto = new String[c];
        numPiso = new String[c];
        i=c;
        do{
            idCuartoC[i] = getIntent().getExtras().getInt("idCuarto"+c);
            nomCuarto[i] = getIntent().getExtras().getString("nomCuarto"+c);
            numPiso[i] = getIntent().getExtras().getString("numPiso"+c);
            i--;
        }while(i>=0);

        d = getIntent().getExtras().getInt("indicadorD");
        idCuartoD = new int[d];
        idCuartoDisp = new int[d];
        idTipoDisp = new int[d];
        j=d;
        do{
            idCuartoDisp[j] = getIntent().getExtras().getInt("idCuartoDisp"+d);
            idCuartoD[j] = getIntent().getExtras().getInt("idCuarto"+d);
            idTipoDisp[j] = getIntent().getExtras().getInt("idTipoDisp"+d);
            j--;
        }while(j>=0);

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
    String table, whereClause, whereArgs[];
    public void editaUsuario(View view){
        table = SmartConstract.UsrEntry.TABLE_NAME;
        whereClause = "where "+SmartConstract.UsrEntry.EMAIL+"=?";
        whereArgs = new String[]{email};

        sqLiteDatabase.update(table, toContentValues(), whereClause, whereArgs);
        Toast.makeText(getApplicationContext(), "Hecho", Toast.LENGTH_SHORT).show();
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

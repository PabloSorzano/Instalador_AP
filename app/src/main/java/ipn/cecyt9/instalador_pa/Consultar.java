package ipn.cecyt9.instalador_pa;

import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.*;
import android.widget.Toast;

import ipn.cecyt9.instalador_pa.data.SmartConstract;
import ipn.cecyt9.instalador_pa.data.SmartHouseDBHelper;

public class Consultar extends AppCompatActivity {
    TextView text;
    Button cons;
    EditText mai;

    SmartHouseDBHelper jj ;
    SQLiteDatabase sqLiteDatabase ;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(getApplicationContext(),"Ingrese un correo para buscar al usuario", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        cons = (Button)findViewById(R.id.cons);
        text = (TextView)findViewById(R.id.text);
        mai = (EditText)findViewById(R.id.mai) ;

        cons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mai.getText().toString().trim().isEmpty()){
                    consult();
                }else{
                    Toast.makeText(getApplicationContext(), "Ingrese un correo a buscar por favor", Toast.LENGTH_SHORT).show();
                }
            }
        });

        jj = new SmartHouseDBHelper(getApplicationContext());
        sqLiteDatabase = jj.getWritableDatabase();





    }
    String table, columns[], selection, selectionArgs[], groupBy, having, orderBy, limit, msj;
    String coorde, estado, muni, codP, col, call, numInt;
    String nombre, email, pass, aPat, aMat, telefono;
    int idCasa, idUsr;
    public void consult(){
        table = SmartConstract.UsrEntry.TABLE_NAME;
        columns = new String[]{SmartConstract.UsrEntry.ID_USUARIO,
                SmartConstract.UsrEntry.NAME_USUARIO,
                SmartConstract.UsrEntry.APELLIDO_PATERNO,
                SmartConstract.UsrEntry.APELLIDO_MATERNO,
                SmartConstract.UsrEntry.TELEFONO_MOVIL,
                SmartConstract.UsrEntry.EMAIL,
                SmartConstract.UsrEntry.CONTRASEÑA};
        selection = ""+SmartConstract.UsrEntry.EMAIL+" = ?";
        selectionArgs = new String[]{mai.getText().toString().trim()};
        groupBy = null;
        having = null;
        orderBy = null;
        limit = null;
        msj="";
        //table: the name of the table you want to query
        //columns: the column names that you want returned. Don't return data that you don't need.
        //selection: the row data that you want returned from the columns (This is the WHERE clause.)
        //selectionArgs: This is substituted for the ? in the selection String above.
        //groupBy and having: This groups duplicate data in a column with data having certain conditions. Any unneeded parameters can be set to null.
        //        orderBy: sort the data
        //limit: limit the number of results to return

        cursor = sqLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
        //Nos aseguramos de que existe al menos un registro
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                idUsr = Integer.parseInt(cursor.getString(0));
                nombre = cursor.getString(1);
                aPat = cursor.getString(2);
                aMat = cursor.getString(3);
                telefono = cursor.getString(4);
                email = cursor.getString(5);
                pass = cursor.getString(6);
                msj = "-------------USUARIO-------------\n"+
                        "ID: "+idUsr+"\n" +
                        "Nombre: "+nombre+" "+aPat+" "+aMat+"\n" +
                        "Teléfono Móvil: "+telefono+"\n" +
                        "E-mail: "+email+"\n" +
                        "Contraseña: "+pass;
                text.setText("");
                text.append(msj);
            } while(cursor.moveToNext());
        }else{
            Toast.makeText(getApplicationContext(), "No existen registros", Toast.LENGTH_SHORT).show();
        }

        table = SmartConstract.CasaEntry.TABLE_NAME;
        columns = new String[]{SmartConstract.CasaEntry.ID_CASA,
                SmartConstract.CasaEntry.ID_USUARIO,
                SmartConstract.CasaEntry.COORDENADAS,
                SmartConstract.CasaEntry.ESTADO,
                SmartConstract.CasaEntry.MUNICIPIO,
                SmartConstract.CasaEntry.CODIGO_POSTAL,
                SmartConstract.CasaEntry.COLONIA,
                SmartConstract.CasaEntry.CALLE,
                SmartConstract.CasaEntry.NUMERO_INTERIOR};
        selection = "*";
        selectionArgs = null;
        groupBy = null;
        having = null;
        orderBy = null;
        limit = null;
        msj="";
        //table: the name of the table you want to query
        //columns: the column names that you want returned. Don't return data that you don't need.
        //selection: the row data that you want returned from the columns (This is the WHERE clause.)
        //selectionArgs: This is substituted for the ? in the selection String above.
        //groupBy and having: This groups duplicate data in a column with data having certain conditions. Any unneeded parameters can be set to null.
        //        orderBy: sort the data
        //limit: limit the number of results to return

        cursor = sqLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
        //Nos aseguramos de que existe al menos un registro
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                idCasa = Integer.parseInt(cursor.getString(0));
                coorde = cursor.getString(2);
                estado = cursor.getString(3);
                muni = cursor.getString(4);
                codP = cursor.getString(5);
                col = cursor.getString(6);
                call = cursor.getString(7);
                numInt = cursor.getString(8);
                msj = "-------------CASA-------------\n"+
                        "ID_Casa: "+idCasa+"\n" +
                        "ID_Usuario: "+cursor.getString(1) +
                        "Coordenadas: "+coorde+"\n" +
                        "Estado: "+estado+"\n" +
                        "Municipio: "+muni+"\n" +
                        "Codigo Postal: "+codP+"\n" +
                        "Colonia: "+col+"\n" +
                        "Calle: "+call+"\n" +
                        "Numero Interior: "+numInt;

                text.append(msj);
            } while(cursor.moveToNext());
        }else{
            Toast.makeText(getApplicationContext(), "No existen registros", Toast.LENGTH_SHORT).show();
        }
    }
}

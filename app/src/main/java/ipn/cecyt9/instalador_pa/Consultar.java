package ipn.cecyt9.instalador_pa;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import ipn.cecyt9.instalador_pa.data.SmartConstract;
import ipn.cecyt9.instalador_pa.data.SmartHouseDBHelper;

public class Consultar extends AppCompatActivity {
    SmartHouseDBHelper jj ;
    SQLiteDatabase sqLiteDatabase ;
    Cursor cursor;

    int idUsr;
    String nombre, email, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        jj = new SmartHouseDBHelper(getApplicationContext());
        sqLiteDatabase = jj.getWritableDatabase();


        String table = SmartConstract.UsrEntry.TABLE_NAME;
        String[] columns = {SmartConstract.UsrEntry.ID_USUARIO,
                SmartConstract.UsrEntry.NAME_USUARIO,
                SmartConstract.UsrEntry.EMAIL,
                SmartConstract.UsrEntry.CONTRASEÑA};
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;
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
                email = cursor.getString(2);
                pass = cursor.getString(3);
                TextView dato = new TextView(getApplicationContext());
                dato.append(""+idUsr+"");
            } while(cursor.moveToNext());
        }else{}
    }
}

package ipn.cecyt9.instalador_pa;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import ipn.cecyt9.instalador_pa.data.SmartConstract;
import ipn.cecyt9.instalador_pa.data.SmartHouseDBHelper;

public class edCuarto extends AppCompatActivity {
    SmartHouseDBHelper jj ;
    SQLiteDatabase sqLiteDatabase ;
    Cursor cursor;
    agregaUsuario agUsr = new agregaUsuario();
    Intent edUsr;

    String nomCuarto, numPiso, obser;
    String table, columns[], selection, selectionArgs[], groupBy, having, orderBy, limit, msj;
    int idCuartoDisp, idTipoDisp;

    int idCuarto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ed_cuarto);
        jj = new SmartHouseDBHelper(getApplicationContext());
        sqLiteDatabase = jj.getWritableDatabase();
    }

    public void obtenerCuartoDisp(int idCasa){
        //cuarto
        table = SmartConstract.CuartoEntry.TABLE_NAME;
        columns = new String[]{SmartConstract.CuartoEntry.ID_CUARTO,
                SmartConstract.CuartoEntry.ID_CASA,
                SmartConstract.CuartoEntry.NOMBRE_CUARTO,
                SmartConstract.CuartoEntry.NUMERO_PISO,
                SmartConstract.CuartoEntry.OBSERVACION};
        selection = SmartConstract.CuartoEntry.ID_CASA+"=?";
        selectionArgs = new String[]{String.valueOf(idCasa)};
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
                idCuarto = Integer.parseInt(cursor.getString(0));
                nomCuarto = cursor.getString(2);
                numPiso = cursor.getString(3);
                obser = cursor.getString(4);
                msj = "-------------CUARTO-------------\n"+
                        "ID_Cuarto:\n  "+idCuarto+"\n" +
                        "ID_Casa:\n  "+cursor.getString(1)+"\n" +
                        "Nombre de Cuarto:\n  "+nomCuarto+"\n" +
                        "Numero de Piso:\n  "+numPiso+"\n" ;



                //dispositivos
                table = SmartConstract.CuartoDispEntry.TABLE_NAME;
                columns = new String[]{SmartConstract.CuartoDispEntry.ID_CUARTO_DISP,
                        SmartConstract.CuartoDispEntry.ID_CUARTO,
                        SmartConstract.CuartoDispEntry.ID_TIPO_DISP};
                selection = SmartConstract.CuartoDispEntry.ID_CUARTO+"=?";
                selectionArgs = new String[]{String.valueOf(idCuarto)};
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
                Cursor cursor1;
                cursor1 = sqLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
                //Nos aseguramos de que existe al menos un registro
                if (cursor1.moveToFirst()) {
                    //Recorremos el cursor hasta que no haya más registros
                    do {
                        idCuartoDisp = Integer.parseInt(cursor1.getString(0));
                        idTipoDisp = Integer.parseInt(cursor1.getString(2));
                        msj = "---------CUARTO/DISP---------\n"+
                                "ID_CuartoDisp:\n  "+idCuartoDisp+"\n" +
                                "ID_Cuarto:\n  "+cursor1.getString(1)+"\n" +
                                "ID_TipoDisp:\n  "+idTipoDisp+"\n" ;




                    } while(cursor1.moveToNext());
                }else{
                }

            } while(cursor.moveToNext());
        }else{
        }
    }
}

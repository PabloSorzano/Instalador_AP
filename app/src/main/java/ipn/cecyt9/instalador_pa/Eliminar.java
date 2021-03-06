package ipn.cecyt9.instalador_pa;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.*;

import ipn.cecyt9.instalador_pa.data.SmartConstract;
import ipn.cecyt9.instalador_pa.data.SmartHouseDBHelper;

public class Eliminar extends AppCompatActivity {
    SmartHouseDBHelper jj ;
    SQLiteDatabase sqLiteDatabase ;
    Cursor cursor;

    EditText mai;
    Button del;
    String cuartos="";
    int bucle=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        Toast.makeText(getApplicationContext(), "Eliminar al usuario", Toast.LENGTH_SHORT).show();

        mai = (EditText)findViewById(R.id.mail);
        del = (Button)findViewById(R.id.delete);

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mai.getText().toString().trim().isEmpty()){
                    consult();
                }else{
                    Toast.makeText(getApplicationContext(), "Ingrese un correo a buscar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        jj = new SmartHouseDBHelper(getApplicationContext());
        sqLiteDatabase = jj.getWritableDatabase();
    }

    String table, columns[], selection, selectionArgs[], groupBy, having, orderBy, limit, msj;
    String coorde, estado, muni, codP, col, call, numInt;
    String nombre, email, pass, aPat, aMat, telefono;
    String nomCuarto, numPiso, obser;
    int idCasa, idUsr, idCuarto, idCuartoDisp, idTipoDisp;

    public void consult(){

        table = SmartConstract.UsrEntry.TABLE_NAME;
        columns = new String[]{SmartConstract.UsrEntry.ID_USUARIO,
                SmartConstract.UsrEntry.NAME_USUARIO,
                SmartConstract.UsrEntry.APELLIDO_PATERNO,
                SmartConstract.UsrEntry.APELLIDO_MATERNO,
                SmartConstract.UsrEntry.TELEFONO_MOVIL,
                SmartConstract.UsrEntry.EMAIL,
                SmartConstract.UsrEntry.CONTRASEÑA};
        selection = SmartConstract.UsrEntry.EMAIL+" = ?";
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
                        "ID_Usuario:\n  "+idUsr+"\n" +
                        "Nombre:\n  "+nombre+" "+aPat+" "+aMat+"\n" +
                        "Teléfono Móvil:\n  "+telefono+"\n" +
                        "E-mail:\n  "+email+"\n" +
                        "Contraseña:\n  "+pass+"\n";
            } while(cursor.moveToNext());

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
            selection = SmartConstract.CasaEntry.ID_USUARIO+"=?";
            selectionArgs = new String[]{String.valueOf(idUsr)};
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
                            "ID_Casa:\n  "+idCasa+"\n" +
                            "ID_Usuario:\n  "+cursor.getString(1)+"\n" +
                            "Coordenadas:\n  "+coorde+"\n" +
                            "Estado:\n  "+estado+"\n" +
                            "Municipio:\n  "+muni+"\n" +
                            "Codigo Postal:\n  "+codP+"\n" +
                            "Colonia:\n  "+col+"\n" +
                            "Calle:\n  "+call+"\n" +
                            "Numero Interior:\n  "+numInt+ "\n";

                } while(cursor.moveToNext());
            }else{
            }
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
                    bucle++;
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
                            cuartos += idCuartoDisp+",";
                        } while(cursor1.moveToNext());
                    }else{
                    }



                } while(cursor.moveToNext());
            }else{
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(Eliminar.this);
            builder.setCancelable(false);
            builder.setTitle("ELIMINAR USUARIO");
            builder.setMessage("Desea eliminar a "+nombre+"?");
            builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    eliminar();
                }
            })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            // Create the AlertDialog object and return it
            builder.create().show();
        }else{
            Toast.makeText(getApplicationContext(), "No existen registros de usuarios", Toast.LENGTH_SHORT).show();
        }





    }

    String[] rooms;
    int i;
    public void eliminar(){

        sqLiteDatabase.delete(SmartConstract.UsrEntry.TABLE_NAME, SmartConstract.UsrEntry.ID_USUARIO+" = ?", new String[]{String.valueOf(idUsr)});
        //Toast.makeText(getApplicationContext(), "Usuario dado de baja", Toast.LENGTH_SHORT).show();
        sqLiteDatabase.delete(SmartConstract.CasaEntry.TABLE_NAME, SmartConstract.CasaEntry.ID_CASA+" = ?", new String[]{String.valueOf(idCasa)});
        //Toast.makeText(getApplicationContext(), "Casa dada de baja", Toast.LENGTH_SHORT).show();
        sqLiteDatabase.delete(SmartConstract.CuartoEntry.TABLE_NAME, SmartConstract.CuartoEntry.ID_CASA+" = ?", new String[]{String.valueOf(idCasa)});
        //Toast.makeText(getApplicationContext(), "Cuarto dado de baja", Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), cuartos, Toast.LENGTH_LONG).show();
        rooms = cuartos.split(",");
        i=rooms.length -1 ;
        do{
            sqLiteDatabase.delete(SmartConstract.CuartoDispEntry.TABLE_NAME, SmartConstract.CuartoDispEntry.ID_CUARTO_DISP+" = ?",new String[]{rooms[i]});
            i--;
        }while(i>=0);

        //Toast.makeText(getApplicationContext(), "Dispositivos dados de baja", Toast.LENGTH_SHORT).show();
        mai.setText("");
        email="";
        jj.close();
        Toast.makeText(getApplicationContext(), nombre+" ha sido eliminad@", Toast.LENGTH_SHORT).show();
    }

    public String cuartos(){
        for(int i=0; i<bucle; i++){
            cuartos += (idCuarto-i)+",";
        }
        return cuartos;
    }
}

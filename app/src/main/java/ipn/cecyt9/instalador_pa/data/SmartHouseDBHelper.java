package ipn.cecyt9.instalador_pa.data;

import android.content.*;
import android.content.Context;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteOpenHelper;

public class SmartHouseDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SMARTHOUSE";

    // se guarda en /data/data/<paquete>/databases/<nombre-de-la-bd>.db
    public SmartHouseDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //comandos sql
        db.execSQL("CREATE TABLE " + SmartConstract.UsrEntry.TABLE_NAME + " ("
                + SmartConstract.UsrEntry.ID_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SmartConstract.UsrEntry.NAME_USUARIO + " TEXT NOT NULL,"
                + SmartConstract.UsrEntry.APELLIDO_PATERNO + " TEXT NOT NULL,"
                + SmartConstract.UsrEntry.APELLIDO_MATERNO + " TEXT NOT NULL,"
                + SmartConstract.UsrEntry.TELEFONO_MOVIL + " TEXT NOT NULL,"
                + SmartConstract.UsrEntry.EMAIL + " TEXT NOT NULL,"
                + SmartConstract.UsrEntry.CONTRASEÑA + " TEXT NOT NULL,"
                + "UNIQUE (" + SmartConstract.UsrEntry.ID_USUARIO + "))");

        db.execSQL("CREATE TABLE " + SmartConstract.CasaEntry.TABLE_NAME + " ("
                + SmartConstract.CasaEntry.ID_CASA + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SmartConstract.CasaEntry.ID_USUARIO + " INTEGER NOT NULL,"
                + SmartConstract.CasaEntry.COORDENADAS + " TEXT NOT NULL,"
                + SmartConstract.CasaEntry.ESTADO + " TEXT NOT NULL,"
                + SmartConstract.CasaEntry.MUNICIPIO + " TEXT NOT NULL,"
                + SmartConstract.CasaEntry.CODIGO_POSTAL + " TEXT NOT NULL,"
                + SmartConstract.CasaEntry.COLONIA + " TEXT NOT NULL,"
                + SmartConstract.CasaEntry.CALLE + " TEXT NOT NULL,"
                + SmartConstract.CasaEntry.NUMERO_INTERIOR + " TEXT NOT NULL,"
                + "UNIQUE (" + SmartConstract.CasaEntry.ID_CASA + "),"
                + "FOREIGN KEY (" + SmartConstract.CasaEntry.ID_USUARIO + ") REFERENCES USUARIO("+ SmartConstract.UsrEntry.ID_USUARIO +") ON DELETE CASCADE ON UPDATE CASCADE)");

        db.execSQL("CREATE TABLE " + SmartConstract.CuartoEntry.TABLE_NAME + " ("
                + SmartConstract.CuartoEntry.ID_CUARTO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SmartConstract.CuartoEntry.ID_CASA + " INTEGER NOT NULL,"
                + SmartConstract.CuartoEntry.NOMBRE_CUARTO + " TEXT NOT NULL,"
                + SmartConstract.CuartoEntry.NUMERO_PISO + " TEXT NOT NULL,"
                + SmartConstract.CuartoEntry.OBSERVACION + " TEXT NOT NULL,"
                + "UNIQUE (" + SmartConstract.CuartoEntry.ID_CUARTO + "),"
                + "FOREIGN KEY (" + SmartConstract.CuartoEntry.ID_CASA + ") REFERENCES CASA("+ SmartConstract.CasaEntry.ID_CASA +") ON DELETE CASCADE ON UPDATE CASCADE)");

        db.execSQL("CREATE TABLE " + SmartConstract.CatDispEntry.TABLE_NAME + " ("
                + SmartConstract.CatDispEntry.ID_TIPO_DISP + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SmartConstract.CatDispEntry.NOMBRE_DISP + " TEXT NOT NULL,"
                + SmartConstract.CatDispEntry.DESCRIPCION + " TEXT NOT NULL,"
                + "UNIQUE (" + SmartConstract.CatDispEntry.ID_TIPO_DISP + "))");

        db.execSQL("CREATE TABLE " + SmartConstract.CuartoDispEntry.TABLE_NAME + " ("
                + SmartConstract.CuartoDispEntry.ID_CUARTO_DISP + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SmartConstract.CuartoDispEntry.ID_CUARTO + " INTEGER NOT NULL,"
                + SmartConstract.CuartoDispEntry.ID_TIPO_DISP + " INTEGER NOT NULL,"
                + "UNIQUE (" + SmartConstract.CuartoDispEntry.ID_CUARTO_DISP + "),"
                + "FOREIGN KEY (" + SmartConstract.CuartoDispEntry.ID_CUARTO + ") REFERENCES CUARTO("+ SmartConstract.CuartoEntry.ID_CUARTO +")  ON DELETE CASCADE ON UPDATE CASCADE,"
                + "FOREIGN KEY (" + SmartConstract.CuartoDispEntry.ID_TIPO_DISP + ") REFERENCES CATALOGO_DISPOSITIVOS("+ SmartConstract.CatDispEntry.ID_TIPO_DISP +") ON DELETE CASCADE ON UPDATE CASCADE)");

        db.execSQL("CREATE TABLE " + SmartConstract.UsoDispEntry.TABLE_NAME + " ("
                + SmartConstract.UsoDispEntry.ID_USO_DISP + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SmartConstract.UsoDispEntry.ID_CUARTO_DISP + " INTEGER NOT NULL,"
                + SmartConstract.UsoDispEntry.STATUS + " BOOLEAN NOT NULL,"
                + SmartConstract.UsoDispEntry.INICIO_USO + " TIME NOT NULL,"
                + SmartConstract.UsoDispEntry.FIN_USO + " TIME NOT NULL,"
                + "UNIQUE (" + SmartConstract.UsoDispEntry.ID_USO_DISP + "),"
                + "FOREIGN KEY (" + SmartConstract.UsoDispEntry.ID_CUARTO_DISP + ") REFERENCES CUARTO_DISPOSITIVOS("+ SmartConstract.CuartoDispEntry.ID_CUARTO_DISP +") ON DELETE CASCADE ON UPDATE CASCADE)");

    }

    public long saveUsr(UsrEntity usrEntity) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                SmartConstract.UsrEntry.TABLE_NAME,
                null,
                toContentValues(usrEntity));

    }
    public ContentValues toContentValues(UsrEntity usrEntity) {
        ContentValues values = new ContentValues();

            values.put(SmartConstract.UsrEntry.ID_USUARIO, usrEntity.getIdUsr());
            values.put(SmartConstract.UsrEntry.NAME_USUARIO, usrEntity.getXnombre());
            values.put(SmartConstract.UsrEntry.APELLIDO_PATERNO, usrEntity.getXaPat());
            values.put(SmartConstract.UsrEntry.APELLIDO_MATERNO, usrEntity.getXaMat());
            values.put(SmartConstract.UsrEntry.TELEFONO_MOVIL, usrEntity.getXcel());
            values.put(SmartConstract.UsrEntry.EMAIL, usrEntity.getXmail());
            values.put(SmartConstract.UsrEntry.CONTRASEÑA, usrEntity.getXpass());
        return values;
    }

    public long saveCasa(CasaEntity casaEntity) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                SmartConstract.CasaEntry.TABLE_NAME,
                null,
                toContentValues(casaEntity));

    }
    public ContentValues toContentValues(CasaEntity casaEntity) {
        ContentValues values = new ContentValues();

        values.put(SmartConstract.CasaEntry.ID_CASA, casaEntity.getIdCasa());
        values.put(SmartConstract.CasaEntry.ID_USUARIO, casaEntity.getIdUsr());
        values.put(SmartConstract.CasaEntry.COORDENADAS, casaEntity.getxCoorde());
        values.put(SmartConstract.CasaEntry.ESTADO, casaEntity.getxEstado());
        values.put(SmartConstract.CasaEntry.MUNICIPIO, casaEntity.getxMuni());
        values.put(SmartConstract.CasaEntry.CODIGO_POSTAL, casaEntity.getxCodigoP());
        values.put(SmartConstract.CasaEntry.COLONIA, casaEntity.getxCol());
        values.put(SmartConstract.CasaEntry.CALLE, casaEntity.getxCalle());
        values.put(SmartConstract.CasaEntry.NUMERO_INTERIOR, casaEntity.getxNumInt());
        return values;
    }

    public long saveCuarto1(CuartoEntity cuartoEntity) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                SmartConstract.CuartoEntry.TABLE_NAME,
                null,
                toContentValues(cuartoEntity));

    }
    public ContentValues toContentValues(CuartoEntity cuartoEntity) {
        ContentValues values = new ContentValues();

        values.put(SmartConstract.CuartoEntry.ID_CUARTO, cuartoEntity.getIdCuarto());
        values.put(SmartConstract.CuartoEntry.ID_CASA, cuartoEntity.getIdCasa());
        values.put(SmartConstract.CuartoEntry.NOMBRE_CUARTO, cuartoEntity.getNombreCuarto());
        values.put(SmartConstract.CuartoEntry.NUMERO_PISO, cuartoEntity.getNumeroPiso());
        values.put(SmartConstract.CuartoEntry.OBSERVACION, cuartoEntity.getObservacion());
        return values;
    }

    public long saveCuarto2(Cuarto_DispositivosEntity cuarto_DispositivosEntity) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                SmartConstract.CuartoEntry.TABLE_NAME,
                null,
                toContentValues(cuarto_DispositivosEntity));

    }
    public ContentValues toContentValues(Cuarto_DispositivosEntity cuarto_DispositivosEntity) {
        ContentValues values = new ContentValues();

        values.put(SmartConstract.CuartoDispEntry.ID_CUARTO_DISP, cuarto_DispositivosEntity.getIdCuarto_Disp());
        values.put(SmartConstract.CuartoDispEntry.ID_CUARTO, cuarto_DispositivosEntity.getIdCuarto());
        values.put(SmartConstract.CuartoDispEntry.ID_TIPO_DISP, cuarto_DispositivosEntity.getIdTipo_Disp());
        return values;
    }


    public long saveCatalogo() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                SmartConstract.CatDispEntry.TABLE_NAME,
                null,
                toContentValues());

    }
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();

        values.put(SmartConstract.CatDispEntry.ID_TIPO_DISP, 1);
        values.put(SmartConstract.CatDispEntry.NOMBRE_DISP, "Foco");
        values.put(SmartConstract.CatDispEntry.DESCRIPCION, "Fuente de luz con switch");
        values.put(SmartConstract.CatDispEntry.ID_TIPO_DISP, 2);
        values.put(SmartConstract.CatDispEntry.NOMBRE_DISP, "Puerta");
        values.put(SmartConstract.CatDispEntry.DESCRIPCION, "Lugar de acceso");
        values.put(SmartConstract.CatDispEntry.ID_TIPO_DISP, 3);
        values.put(SmartConstract.CatDispEntry.NOMBRE_DISP, "Camara");
        values.put(SmartConstract.CatDispEntry.DESCRIPCION, "Objeto de vigilancia");
        values.put(SmartConstract.CatDispEntry.ID_TIPO_DISP, 4);
        values.put(SmartConstract.CatDispEntry.NOMBRE_DISP, "Clima");
        values.put(SmartConstract.CatDispEntry.DESCRIPCION, "Objeto capz de aclimatizar");
        return values;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //sin operaciones
    }
}

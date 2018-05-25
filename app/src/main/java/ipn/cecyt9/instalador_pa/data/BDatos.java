package com.example.jose.proyecto;

/*
    Delgado Mar José Manuel
    Sepulveda Vazquez Ernesto
    -Integradora-
*/

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.*;
import android.database.Cursor;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class BDatos extends SQLiteOpenHelper{

	//Se inicializan las variables
    private int _clave = 0;
    private String _nombre;
    private String _apodo;
    private int _estatura;
    private int _edad;
    private int _peso;
    private String _gen;
    private String _tip;
    private String _imc;
    private String arroz, cFlakes, crema, qPor, huevo, salch, ref;
    private String kkhuate, lVaca, ygNa, fruta, carne, cerv;
    private byte[] baos;
    private ArrayList category = new ArrayList();

	//Se inicializa variable final con la tabla que se creara y los datos que almacenara, así como el tipo
    private static final String TABLA_DIETAS = "CREATE TABLE dietas (clave INTEGER primary key, " +
            "nombre TEXT, " +   //1
            "apodo TEXT, " +    //2
            "estatura TEXT, " + //3
            "edad TEXT, " +     //4
            "peso TEXT, " +     //5
            "gen TEXT, " +      //6
            "tipo TEXT," +      //7
            "imc TEXT," +       //8
            "calorias TEXT," +  //9
            "img BLOB);";       //10


	//Metodo para invocar la base de datos
    public BDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "Integradora", factory, 1);
    }

	//Metodo para invocar la creación de la tabla de usuarios
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(TABLA_DIETAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

	//Metodo para guardar usuarios en la base de datos
    public String guardar (){
        String mensaje = "";
		//Invocación de metodo para escribir en la base
        SQLiteDatabase base = this.getWritableDatabase();
		//Declaración de variable que almacenara los datos de usuario
        ContentValues valores = new ContentValues();
		//Invocación del metodo que generara el ID del usuario
        _clave = maxID();
		//Se meten valores a la variable ContentValues 
        valores.put("clave", _clave);
        valores.put("nombre", _nombre);
        valores.put("apodo", _apodo);
        valores.put("estatura", _estatura);
        valores.put("edad", _edad);
        valores.put("peso", _peso);
        valores.put("gen", _gen);
        valores.put("tipo", _tip);
        valores.put("img", baos);
        valores.put("imc",_imc);

        try{
			//Se intenta meter el arreglo de datos a la base de datos
            base.insertOrThrow("dietas", null, valores);
            mensaje = "guardado con exito y tu IMC es: " + _imc;
        }catch (SQLException e){
			//Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }

        return mensaje;
    }
	
	//Metodo para actualizar los datos de un usuario registrado
    public String actualizar(int aidi) {
        String mensaje = "";
		//Invocación de metodo para escribir en la base
        SQLiteDatabase base = this.getWritableDatabase();
		//Declaración de variable que almacenara los datos de usuario
        ContentValues valores = new ContentValues();
		//Se meten valores a la variable ContentValues
        valores.put("nombre", _nombre);
        valores.put("apodo", _apodo);
        valores.put("estatura", _estatura);
        valores.put("edad", _edad);
        valores.put("peso", _peso);
        valores.put("gen", _gen);
        valores.put("tipo", _tip);
        valores.put("img", baos);
        valores.put("imc",_imc);

        try{
            //Se intenta meter el arreglo de datos a actualizar del usuario en la base de datos
			base.update("dietas", valores, "clave = " + aidi, null);
            mensaje = "guardado con exito";
        }catch (SQLException e){
			//Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }

        return mensaje;
    }
	//Metodo para eliminar un usuario de la base
    public String elim(int aidi) {
        String mensaje = "";
		//Invocación de metodo para escribir en la base
        SQLiteDatabase base = this.getWritableDatabase();

        try{
			//Se intenta eliminar el usuario que coincida con el id dado
            base.delete("dietas","clave = " + aidi,null);
            mensaje = "Se ha eliminado el sujeto";
        }catch (SQLException e){
			//Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }

        return mensaje;
    }

	//Metodo para buscar un usuario en la base de datos
    public String buscar (int busqueda, int iD){
		//Invocación de metodo para escribir en la base
        SQLiteDatabase base = this.getWritableDatabase();
        String q = "";
        switch(busqueda){
            //Se busca el la clave de usuario en base a la clave
			case 0:
                q = "SELECT clave FROM dietas WHERE clave = " + iD;
                break;
			//Se busca el nombre de usuario en base a la clave
            case 1:
                q = "SELECT nombre FROM dietas WHERE clave = " + iD;
                break;
            //Se busca el apodo de usuario en base a la clave
			case 2:
                q = "SELECT apodo FROM dietas WHERE clave = " + iD;
                break;
            //Se busca la estatura de usuario en base a la clave
			case 3:
                q = "SELECT estatura FROM dietas WHERE clave = " + iD;
                break;
            //Se busca la edad de usuario en base a la clave
			case 4:
                q = "SELECT edad FROM dietas WHERE clave = " + iD;
                break;
            //Se busca el peso de usuario en base a la clave
			case 5:
                q = "SELECT peso FROM dietas WHERE clave = " + iD;
                break;
            //Se busca el genero de usuario en base a la clave
			case 6:
                q = "SELECT gen FROM dietas WHERE clave = " + iD;
                break;
			//Se busca el tipo de usuario en base a la clave
            case 7:
                q = "SELECT tipo FROM dietas WHERE clave = " + iD;
                break;
			//Se busca el imc de usuario en base a la clave
            case 8:
                q = "SELECT imc FROM dietas WHERE clave = " + iD;
                break;
			//Se busca las calorias de usuario en base a la clave
            case 9:
                q = "SELECT calorias FROM dietas WHERE clave = " + iD;
                break;
			//Se busca la dieta de usuario en base a la clave
            default:
                q = "SELECT * FROM dietas WHERE clave = " + iD;
                break;
        }
        String datos;
		//Declaración de variable que almacenara la consulta de la base de datos
        Cursor fila = base.rawQuery(q,null);
		//Se tratara de encontrar usuarios en base a la consulta hecha
        if(fila.moveToFirst()){
			//Se almacenara el dato de usuario obtenido en una variable String
            datos = fila.getString(0);
        }else{
			//Si la consulta no trae resultado se obtendra un error
            datos = "Error vato";
        }
        return datos;
    }
	
	//Metodo para buscar imagen del usuario
    public void bucarI(int aidi){
		//Invocación de metodo para escribir en la base
        SQLiteDatabase base = this.getWritableDatabase();
		//Declaración de String con la consulta que se hara a la base de datos
        String q = "SELECT img FROM dietas WHERE clave = " + aidi;
		//Declaración de variable que almacenara la consulta de la base de datos
        Cursor fila = base.rawQuery(q,null);
		//Se tratara de encontrar usuarios en base a la consulta hecha
        if(fila.moveToFirst()){
            baos = fila.getBlob(0);
        }
    }
	//Metodo para checar si la base de datos sigue funcionando
    public boolean checarBase(){
		//Invocación de metodo para escribir en la base
        SQLiteDatabase base = this.getWritableDatabase();
		//Declaración de String con la consulta que se hara a la base de datos
        String q = "SELECT * FROM dietas";
        boolean valuar = false;
		//Declaración de variable que almacenara la consulta de la base de datos
        Cursor fila = base.rawQuery(q,null);
		//Se tratara de encontrar usuarios en base a la consulta hecha
        if(fila.moveToFirst()){
            valuar = true;
        }

        return valuar;
    }

	//Metodo que contara los usuarios registrados
    public int darTotal(){
		//Invocación de metodo para escribir en la base
        SQLiteDatabase base = this.getWritableDatabase();
		//Declaración de String con la consulta que se hara a la base de datos
        String q = "SELECT COUNT(*) FROM dietas";
		
        int tot = 0;
        String gett = "";
		//Declaración de variable que almacenara la consulta de la base de datos
        Cursor fila = base.rawQuery(q,null);
		//Se tratara de encontrar usuarios en base a la consulta hecha
        if(fila.moveToLast()){
			//Variable que almacena cuantos usuarios registrados hay
            gett = fila.getString(0);
        }
        tot = Integer.parseInt(gett);
        return tot;
    }

	//Metodo para actualizar las dietas
    public String act(int i, int chete){
        String mensaje = " ";
		//Invocación de metodo para escribir en la base
        SQLiteDatabase base = this.getWritableDatabase();
		//Declaración de variable que almacenara los datos de usuario
        ContentValues registro = new ContentValues();
		//Meter registros a la variable consultora
        registro.put("calorias", chete);
        try{
			 //Se intenta meter el arreglo de datos a actualizar del usuario en la base de datos
            base.update("dietas", registro, "clave = " + i, null);
            mensaje = "guardado con exito";
        }catch (SQLException e){
			//Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }

        return mensaje;
    }
	
	//Metodo para registrar usuarios con su id segun la cantidad de usuarios registrados
    public int maxID(){
        //Invocación de metodo para escribir en la base
		SQLiteDatabase base = this.getWritableDatabase();
		//Declaración de String con la consulta que se hara a la base de datos
        String q = "SELECT * FROM dietas", r = "SELECT COUNT(*) FROM dietas";
		//Declaración de String con la consulta que se hara a la base de datos
        String h = "SELECT clave FROM dietas";
        int val = 0, cond = 0;
        String receptor = "", valu = "";
		//Declaración de variable que almacenara la consulta de la base de datos
        Cursor fila = base.rawQuery(r,null);
		//Se tratara de encontrar usuarios en base a la consulta hecha
        if(fila.moveToFirst()){
			//Variable que almacena cuantos usuarios registrados hay
            receptor = fila.getString(0);
        }
		//Conversion del valor obtenido a int
        val = Integer.parseInt(receptor);
		
        fila = base.rawQuery(h,null);
		//Se tratara de encontrar usuarios en base a la consulta hecha
        if(fila.moveToLast()) {
            valu = fila.getString(0);
            cond = Integer.parseInt(valu);
        }

        if(cond>=val){
            val = val + 1;
        }

        return val;
    }
	
	//Metodo para desplegar los usuarios registrados en forma de listView
    public ArrayList llenarDatos(Drawable drawable, Drawable des) {
		
        SQLiteDatabase base = this.getWritableDatabase();

        String q = "SELECT * FROM dietas";
        Cursor fila = base.rawQuery(q,null);

        if(fila.moveToFirst()){
            do {
                //Category cat = new Category(fila.getString(0),fila.getString(1), "Persona Saludable",drawable);
                //category.add(cat);
            }while(fila.moveToNext());
        }else{
            //Category cat = new Category("0", "Aún Sin Datos", "Presiona el botón para agregar datos", des);
            //category.add(cat);
        }
        return category;
    }
	//Metodos para enviar y recibir parametros de usuarios
    public int get_clave() {
        return _clave;
    }

    public void set_clave(int _clave) {
        this._clave = _clave;
    }

    public String get_nombre() { return _nombre; }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String get_apodo() {
        return _apodo;
    }

    public void set_apodo(String _apodo) {
        this._apodo = _apodo;
    }

    public int getEstatura() {
        return _estatura;
    }

    public void setEstatura(int _estatura) {
        this._estatura = _estatura;
    }

    public int get_edad() {
        return _edad;
    }

    public void set_edad(int _edad) {
        this._edad = _edad;
    }

    public int get_peso() {
        return _peso;
    }

    public void set_peso(int _peso) {
        this._peso = _peso;
    }

    public String get_gen() {
        return _gen;
    }

    public void set_gen(String _gen) {
        this._gen = _gen;
    }

    public String get_tip() {
        return _tip;
    }

    public void set_tip(String _tip) {
        this._tip = _tip;
    }

    public byte[] getBaos() {
        return baos;
    }

    public void setBaos(byte[] baos) {
        this.baos = baos;
    }

    public void set_imc(String _imc) {
        this._imc = _imc;
    }

}
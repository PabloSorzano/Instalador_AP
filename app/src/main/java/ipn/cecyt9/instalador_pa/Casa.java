package ipn.cecyt9.instalador_pa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import ipn.cecyt9.instalador_pa.data.CasaEntity;
import ipn.cecyt9.instalador_pa.data.SmartHouseDBHelper;

public class Casa extends AppCompatActivity{
    agregaCasa agCasa = new agregaCasa();

    EditText latitud, longitud, estado, municipio, codigoP, colonia, calle, numInt;
    Button agrega;

    String xnombre, xaPat, xaMat, xcel, xmail, xpass;

    int idUsr, idCasa, minLat=0, minLong=0;
    String LAT, LOG, xEstado, xMuni, xCodigoP, xCol, xCalle, xNumInt;
    String estDef = "Ingrese el estado",
            munDef = "Ingrese el municipio",
            codDef = "Ingrese el codigo postal",
            colDef = "Ingrese la colonia",
            calDef = "Ingrese la calle",
            intDef = "Ingrese el numero interior",
            latDef = "Ingrese latitud de la casa (numeros positivos y negativos con solo un punto)",
            longDef = "Ingrese longitud de la casa (numeros positivos y negativos con solo un punto)";
    boolean coord , state, mun, coP, col, cal, numI;
    boolean conD = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        idUsr = getIntent().getExtras().getInt("idUsr");
        xnombre = getIntent().getExtras().getString("xnombre");
        xaPat = getIntent().getExtras().getString("xaPat");
        xaMat = getIntent().getExtras().getString("xaMat");
        xcel = getIntent().getExtras().getString("xcel");
        xmail = getIntent().getExtras().getString("xmail");
        xpass = getIntent().getExtras().getString("xpass");

        super.onCreate(savedInstanceState);
        Toast.makeText(getApplicationContext(), "Agregar Casa", Toast.LENGTH_SHORT).show();

        setContentView(R.layout.activity_casa);
        latitud = (EditText)findViewById(R.id.latitud);
        longitud = (EditText)findViewById(R.id.longitud);
        estado = (EditText)findViewById(R.id.estado);
        municipio = (EditText)findViewById(R.id.municipio);
        codigoP = (EditText)findViewById(R.id.codigoP);
        colonia = (EditText)findViewById(R.id.colonia);
        calle = (EditText)findViewById(R.id.calle);
        numInt = (EditText)findViewById(R.id.numInt);

        agrega = (Button)findViewById(R.id.agrega);

    }

    public void agregaCasa(View view){
        agCasa.setLAT(latitud.getText().toString().trim());
        agCasa.setLONG(longitud.getText().toString().trim());
        coord = agCasa.setxCoorde("(lat: "+agCasa.getLAT()+", long: "+agCasa.getLONG()+")");
        state = agCasa.setxEstado(estado.getText().toString().trim());
        mun = agCasa.setxMuni(municipio.getText().toString().trim());
        coP = agCasa.setxCodigoP(codigoP.getText().toString().trim());
        col = agCasa.setxCol(colonia.getText().toString().trim());
        cal = agCasa.setxCalle(calle.getText().toString().trim());
        numI = agCasa.setxNumInt(numInt.getText().toString().trim());

        LAT = agCasa.getLAT();
        LOG = agCasa.getLONG();
        xEstado = agCasa.getxEstado();
        xMuni = agCasa.getxMuni();
        xCodigoP = agCasa.getxCodigoP();
        xCol = agCasa.getxCol();
        xCalle = agCasa.getxCalle();
        xNumInt = agCasa.getxNumInt();


        if (xEstado.equals(estDef) || state == false) {
            Toast.makeText(getApplicationContext(), "Estado incorrecto", Toast.LENGTH_SHORT).show();
            estado.setText("");
            conD = false;
        }else if (xMuni.equals(munDef) || mun == false) {
            Toast.makeText(getApplicationContext(), "Municipio incorrecto", Toast.LENGTH_SHORT).show();
            municipio.setText("");
            conD = false;
        }else if (xCodigoP.equals(codDef) || coP == false || xCodigoP.length() != 5) {
            Toast.makeText(getApplicationContext(), "Codigo Postal incorrecto", Toast.LENGTH_SHORT).show();
            codigoP.setText("");
            conD = false;
        }else if (xCol.equals(colDef) || col == false) {
            Toast.makeText(getApplicationContext(), "Colonia incorrecta", Toast.LENGTH_SHORT).show();
            colonia.setText("");
            conD = false;
        }else if (xCalle.equals(calDef) || cal == false) {
            Toast.makeText(getApplicationContext(), "Calle incorrecta", Toast.LENGTH_SHORT).show();
            calle.setText("");
            conD = false;
        }else if (xNumInt.equals(intDef) || numI == false || xNumInt.length() > 10) {
            Toast.makeText(getApplicationContext(), "Numero Interior incorrecto", Toast.LENGTH_SHORT).show();
            numInt.setText("");
            conD = false;

        }else if (coord == false || LAT.equals(latDef) || LOG.equals(longDef)) {
            Toast.makeText(getApplicationContext(), "Coordenadas incorrectas", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "Recuerda que puede llevar +  - y debe llevar un punto", Toast.LENGTH_LONG).show();
            latitud.setText("");
            longitud.setText("");
            conD = false;

        } else {
            conD = true;
            if (conD) {

                agCasa.setIdUsr(idUsr);
                Toast.makeText(getApplicationContext(), agCasa.agregaHouse(), Toast.LENGTH_LONG).show();
                idCasa = agCasa.getIdCasa();

                Intent casa = new Intent(getApplicationContext(), Cuarto.class);

                casa.putExtra("idCasa", idCasa);
                casa.putExtra("xcoorde", agCasa.getxCoorde() );
                casa.putExtra("xEstado", xEstado);
                casa.putExtra("xMuni", xMuni);
                casa.putExtra("xCodigoP", xCodigoP);
                casa.putExtra("xCol", xCol);
                casa.putExtra("xCalle", xCalle);
                casa.putExtra("xNumInt", xNumInt);

                casa.putExtra("idUsr", idUsr);
                casa.putExtra("xnombre", xnombre);
                casa.putExtra("xaPat", xaPat);
                casa.putExtra("xaMat", xaMat);
                casa.putExtra("xcel", xcel);
                casa.putExtra("xmail", xmail);
                casa.putExtra("xpass", xpass);
                finish();
                startActivity(casa);

                SmartHouseDBHelper jj = new SmartHouseDBHelper(getApplicationContext());
                jj.saveCasa(new CasaEntity(idCasa,idUsr,agCasa.getxCoorde(),xEstado,xMuni,xCodigoP,xCol,xCalle,xNumInt));
            }
        }
    }

    public void minLat(View view){

        //if(minLat!=0&&minLat<=1){
            latitud.append("-");minLat++;
        //}else{

        //}
    }

    public void minLong(View view){

        ///if(minLong!=0&&minLong<=1){
            longitud.append("-");minLong++;
        //}else{

        //}
    }
}

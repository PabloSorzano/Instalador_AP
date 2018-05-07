package ipn.cecyt9.instalador_pa;

import java.util.*;

public class agregaCasa {
    agregaUsuario agUsr = new agregaUsuario();
    validacionesJT vd = new validacionesJT();

    int idCasa = 0, idUsr = agUsr.getIdUsr();

    String LAT,LONG,xCoorde,xEstado,xMuni,xCodigoP,xCol,xCalle,xNumInt;
    public ArrayList<String> casas = new ArrayList<String>();
    String conjunto[];
    String datos = "";

    public int getIdCasa() {
        return idCasa;
    }

    public void setIdCasa(int idCasa) {
        this.idCasa = idCasa;
    }

    public int getIdUsr() {
        return idUsr;
    }

    public void setIdUsr(int idUsr) {
        this.idUsr = idUsr;
    }

    public String getLAT() {
        return LAT;
    }

    public void setLAT(String LAT) {
        this.LAT = LAT;
    }

    public String getLONG() {
        return LONG;
    }

    public void setLONG(String LONG) {
        this.LONG = LONG;
    }

    public String getxCoorde() {
        return xCoorde;
    }

    public boolean setxCoorde(String xCoorde) {
        this.xCoorde = xCoorde;
        return vd.evaluaCoord(this.LAT, this.LONG);
    }

    public String getxEstado() {
        return xEstado;
    }

    public boolean setxEstado(String xEstado) {
        this.xEstado = xEstado;
        return vd.soloLetras(getxEstado());
    }

    public String getxMuni() {
        return xMuni;
    }

    public boolean setxMuni(String xMuni) {
        this.xMuni = xMuni;
        return vd.soloLetras(getxMuni());
    }

    public String getxCodigoP() {
        return xCodigoP;
    }

    public boolean setxCodigoP(String xCodigoP) {
        this.xCodigoP = xCodigoP;
        return vd.soloNumeros(getxCodigoP());
    }

    public String getxCol() {
        return xCol;
    }

    public boolean setxCol(String xCol) {
        this.xCol = xCol;
        return vd.soloLetras(getxCol());
    }

    public String getxCalle() {
        return xCalle;
    }

    public boolean setxCalle(String xCalle) {
        this.xCalle = xCalle;
        return vd.soloLetras(getxCalle());
    }

    public String getxNumInt() {
        return xNumInt;
    }

    public boolean setxNumInt(String xNumInt) {
        this.xNumInt = xNumInt;
        return vd.sinEspecial(getxNumInt());
    }

    public String agregaHouse(){

        conjunto =  new String[idCasa+1];
        this.idCasa = idCasa +1;
        conjunto[conjunto.length-1] = "Casa: "+getIdCasa()+", \n ID_Usuario: "+getIdUsr()+", \n Coordenadas: "+getxCoorde()+", \n Estado: "+getxEstado()+", \n Municipio: "+getxMuni()+", \n Codigo Postal:"+getxCodigoP()+", \n Colonia: "+getxCol()+", \n Calle: "+getxCalle()+", \n Numero interior: "+getxNumInt()+"";
        casas.add(conjunto[conjunto.length-1]);

        for(int i=0; i<casas.size(); i++){
            datos += casas.get(i);
        }
        return datos;
    }
}

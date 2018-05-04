package ipn.cecyt9.instalador_pa;

import java.util.*;

public class agregaUsuario {
    validacionesJT vd = new validacionesJT();

    int idUsr=0;
    String xnombre, xaPat, xaMat, xcel, xmail, xpass;
    public ArrayList<String> usuarios = new ArrayList<String>();
    String conjunto[];
    String datos = "";

    public int getIdUsr() {
        return idUsr;
    }

    public String getXnombre() {
        return xnombre;
    }

    public boolean setXnombre(String xnombre) {
        this.xnombre = xnombre;
        return vd.soloLetras(getXnombre());//solo letras
    }

    public String getXaPat() {
        return xaPat;
    }

    public boolean setXaPat(String xaPat) {
        this.xaPat = xaPat;
        return vd.soloLetras(getXaPat());//solo letras
    }

    public String getXaMat() {
        return xaMat;
    }

    public boolean setXaMat(String xaMat) {
        this.xaMat = xaMat;
        return vd.soloLetras(getXaMat());//solo letras
    }

    public String getXcel() {
        return xcel;
    }

    public boolean setXcel(String xcel) {
        this.xcel = xcel;
        return vd.soloNumeros(getXcel());//solo letras
    }

    public String getXmail() {
        return xmail;
    }

    public boolean setXmail(String xmail) {
        this.xmail = xmail;
        return vd.soloMail(getXmail());//solo mail
    }

    public String getXpass() {
        return xpass;
    }

    public boolean setXpass(String xpass) {
        this.xpass = xpass;
        return vd.sinEspecial(getXpass());//sin especial
    }

    public String agregaUsuario(){
        conjunto =  new String[idUsr+1];
        this.idUsr = idUsr +1;
        conjunto[conjunto.length-1] = "Usuario: "+getIdUsr()+", A.Paterno: "+getXaPat()+", A.Materno: "+getXaMat()+", Celular: "+getXcel()+", E-mail: "+getXmail()+", Password: "+getXpass()+"";
        usuarios.add(conjunto[conjunto.length-1]);
        for(int i=0; i<usuarios.size(); i++){
            datos += usuarios.get(i);
        }
        return datos;
    }
}

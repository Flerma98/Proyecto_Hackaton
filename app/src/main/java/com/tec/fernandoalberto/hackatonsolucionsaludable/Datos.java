package com.tec.fernandoalberto.hackatonsolucionsaludable;

public class Datos {
    private int PH;
    private int CE;
    private int Sal;
    private String Fecha;
    private String Hora;

    public Datos() {
    }

    public Datos(int PH, int CE, int Sal, String fecha, String hora) {
        this.PH = PH;
        this.CE = CE;
        this.Sal = Sal;
        Fecha = fecha;
        Hora = hora;
    }

    public int getPH() {
        return PH;
    }

    public void setPH(int PH) {
        this.PH = PH;
    }

    public int getSal() {
        return Sal;
    }

    public void setSal(int sal) {
        Sal = sal;
    }

    public int getCE() {
        return CE;
    }

    public void setCE(int CE) {
        this.CE = CE;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }
}

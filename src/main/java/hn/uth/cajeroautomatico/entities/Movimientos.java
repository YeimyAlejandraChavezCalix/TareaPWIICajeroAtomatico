package hn.uth.cajeroautomatico.entities;

public class Movimientos {
    private int transaccionid;
    private String tipo;
    private double monto;

    public Movimientos() {

    }
    public Movimientos(int transaccionid, String tipo, double monto) {
        this.transaccionid = transaccionid;
        this.tipo = tipo;
        this.monto = monto;
    }

    public int getTransaccionid() {
        return transaccionid;
    }

    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }
    public void setTransaccionid(int transaccionid) {
        this.transaccionid = transaccionid;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }

}

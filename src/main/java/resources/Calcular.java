package resources;

public class Calcular {
    private int tazaInteres;
    private int num1;

    public Calcular(int tazaInteres, int num1) {
        this.tazaInteres = tazaInteres;
        this.num1 = num1;
    }

    public int CalcularInteres() {
        return tazaInteres * num1;
    }

    public int getTazaInteres() {
        return tazaInteres;
    }

    public void setTazaInteres(int tazaInteres) {
        this.tazaInteres = tazaInteres;
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }
}

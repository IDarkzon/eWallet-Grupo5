package resources;

public class Evento {
    private String nombre;
    private String fechaProgramada;
    private String hora;

    public Evento(String nombre, String fechaProgramada, String hora) {
        this.nombre = nombre;
        this.fechaProgramada = fechaProgramada;
        this.hora = hora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(String fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}

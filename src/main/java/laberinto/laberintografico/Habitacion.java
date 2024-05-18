package laberinto.laberintografico;

/**
 *
 * @author Antonio
 */
public class Habitacion{

    private final String descripcion;
    private Habitacion sNorte;
    private Habitacion sSur;    
    private Habitacion sEste;
    private Habitacion sOeste;

    public Habitacion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setSalida(String direccion, Habitacion h) {
        switch (direccion.toLowerCase()) {
            case "norte" -> this.sNorte = h;
            case "sur" -> this.sSur = h;
            case "este" -> this.sEste = h;
            case "oeste" -> this.sOeste = h;
            default -> System.out.println("Dirección no válida");
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Habitacion getSalida(String direccion) {

        switch (direccion.toLowerCase()) {
            case "norte" -> {return sNorte;}
            case "sur" -> {return sSur;}
            case "este" -> {return sEste;}
            case "oeste" -> {return sOeste;}
            default -> {
                return null;
            }
        }
    }

}

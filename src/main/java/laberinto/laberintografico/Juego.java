package laberinto.laberintografico;


/**
 *
 * @author Diurno
 */
public final class Juego {

    boolean objetoClave = false;

    private Habitacion habitacionActual;
    private Habitacion habitacionInicial;
    private String item;

    public Juego() {
        crearHabitaciones();
        habitacionActual = habitacionInicial;
    }

    public void crearHabitaciones() {
        habitacionInicial = new Habitacion("1");
        Habitacion habitacionA = new Habitacion("2");
        Habitacion habitacionB = new Habitacion("3");
        Habitacion habitacionEspecial = new Habitacion("4");
        Habitacion habitacionD = new Habitacion("5");
        Habitacion habitacionTrampa = new Habitacion("6");
        Habitacion habitacionF = new Habitacion("7");
        Habitacion habitacionOscura = new Habitacion("8");
        Habitacion habitacionBoss = new Habitacion("9");

        habitacionInicial.setSalida("sur", habitacionA);

        habitacionA.setSalida("sur", habitacionEspecial);
        habitacionA.setSalida("este", habitacionB);
        
        habitacionB.setSalida("oeste", habitacionA);
        habitacionB.setSalida("este", habitacionTrampa);
        
        habitacionEspecial.setSalida("sur", habitacionOscura);
        habitacionEspecial.setSalida("este", habitacionD);
        
        habitacionD.setSalida("norte", habitacionB);
        habitacionD.setSalida("oeste", habitacionEspecial);
        habitacionD.setSalida("este", habitacionF);
        
        habitacionF.setSalida("sur", habitacionBoss);
        habitacionF.setSalida("oeste", habitacionD);
        
        habitacionOscura.setSalida("este", habitacionBoss);
        habitacionOscura.setSalida("norte", habitacionEspecial);
    }

    public boolean direccionValida(String direccion) {
        if (direccion.equals("CONTROL")) {
            return true;
        } else if (direccion.equals("B")) {
            return true;
        }
 
        switch (direccion.toUpperCase()) {
            case "W" -> direccion = "norte";
            case "A" -> direccion = "oeste";
            case "S" -> direccion = "sur";
            case "D" -> direccion = "este";
            default -> {return false;}
        }

        return habitacionActual.getSalida(direccion) != null;
    }

    public void irA(String direccion) {
        switch (direccion.toUpperCase()) {
            case "W" -> direccion = "norte";
            case "A" -> direccion = "oeste";
            case "S" -> direccion = "sur";
            case "D" -> direccion = "este";
        }
        
        Habitacion nuevaHabitacion = habitacionActual.getSalida(direccion);
        
        if (nuevaHabitacion != null) {
            habitacionActual = nuevaHabitacion;
        } else {
            System.out.println("No puedes ir en esa dirección.");
        }
    }

    public void buscarEnHabitacionEspecial() {
        if (habitacionActual.getDescripcion().equals("4") && !objetoClave) {
            item = "Xerneas";
            objetoClave = true;
        } else {
            System.out.println("No hay nada especial en esta habitación.");
        }
    }

    public String getDescripcion() {
        return habitacionActual.getDescripcion();
    }
}

package laberinto.laberintografico;

public class Ayudante {
    private int nivel, defensa, ataque;
    
    public Ayudante(int nivel, int ataque, int defensa) {
        this.nivel = nivel;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    public double calcularDanio(int poderBase) {
        double danioFisico = ((2.0 * nivel + 10) / 250) * (ataque + poderBase) / defensa + 2;
        return danioFisico;
    }
}

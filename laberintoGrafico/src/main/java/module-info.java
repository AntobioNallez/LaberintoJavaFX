module laberinto.laberintografico {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens laberinto.laberintografico to javafx.fxml;
    exports laberinto.laberintografico;
}

module org.stones.skystonesjavaed {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;

    opens org.stones.skystonesjavaed to javafx.fxml;
    exports org.stones.skystonesjavaed;
}
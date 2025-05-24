module org.stones.skystonesjavaed {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;

    opens org.stones.skystonesjavaed to javafx.fxml;
//    exports org.stones.skystonesjavaed;
    exports org.stones.skystonesjavaed.controller;
    opens org.stones.skystonesjavaed.controller to javafx.fxml;
    exports org.stones.skystonesjavaed.view;
    opens org.stones.skystonesjavaed.view to javafx.fxml;
    exports org.stones.skystonesjavaed.model;
    opens org.stones.skystonesjavaed.model to javafx.fxml;
    exports org.stones.skystonesjavaed.config;
    opens org.stones.skystonesjavaed.config to javafx.fxml;
}
module rbk.rbktimes {
    requires javafx.controls;
    requires javafx.fxml;


    opens rbk.rbktimes to javafx.fxml;
    exports rbk.rbktimes;
    exports rbk.rbktimes.controllers;
    opens rbk.rbktimes.controllers to javafx.fxml;
}

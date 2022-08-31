module Network{
	requires javafx.controls;
	requires javafx.fxml;
	requires commons.lang3;
	requires java.base;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml,commons.lang3;
	opens server.pac to commons.lang3;

}

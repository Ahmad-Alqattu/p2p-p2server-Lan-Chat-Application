module Network{
	requires javafx.controls;
	requires javafx.fxml;
	requires java.base;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;

}

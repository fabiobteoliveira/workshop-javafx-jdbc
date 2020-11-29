package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {

	
	//Acessa o stage onde o meu controller está
	public static Stage currentStage(ActionEvent event) {
		
		return (Stage)((Node)event.getSource()).getScene().getWindow();
	}
}

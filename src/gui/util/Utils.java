package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {

	
	//Acessa o stage onde o meu controller est�
	public static Stage currentStage(ActionEvent event) {
		
		return (Stage)((Node)event.getSource()).getScene().getWindow();
	}
}

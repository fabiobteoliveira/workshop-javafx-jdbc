package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;
import model.services.SellerService;

public class MainViewController implements Initializable {

	// Controle de tela

	@FXML
	private MenuItem menuItemSeller;

	@FXML
	private MenuItem menuItemDepartment;

	@FXML
	private MenuItem menuItemAbout;

	/* Métodos para tratar os eventos do menu */
	@FXML
	public void onMenuItemSellerAction() {

		loadView("/gui/SellerList.fxml", (SellerListController controller) -> {

			controller.setSellerService(new SellerService());
			controller.updateTableView();
		});
	}

	@FXML
	public void onMenuItemDepartmentAction() {

		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) -> {

			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		}); // 2º parametro -> inicia o controlador
	}

	@FXML
	public void onMenuItemAboutAction() {

		loadView("/gui/About.fxml", x -> {
		});
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {

	}

	/* Funçao para abrir outra tela || Funçao generica tipo <T> */
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) { // synchronized ->
																									// impede
																									// interrupção
																									// durante
																									// multithreading

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();

			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent(); // pega 1º elemento da view
																					// principal (ScrollPane)

			Node mainMenu = mainVBox.getChildren().get(0);// 1º filho da vbox menu principal;

			mainVBox.getChildren().clear(); // Limpa todos os filhos do mainVBox
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());

			// Ativar a func initializingAction
			T controller = loader.getController(); // Executam a açao que passar como parametro
			initializingAction.accept(controller); // Executam a açao que passar como parametro

		} catch (IOException e) {
			Alerts.showAlert("IOException", "Error Loading View", e.getMessage(), AlertType.ERROR);
		}
	}

}

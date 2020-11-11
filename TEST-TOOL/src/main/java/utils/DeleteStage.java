package utils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import utils.ToolManager;;

public class DeleteStage extends Stage{
	Button ok = new Button("Brisanje po ID-u");
	Button ok1 = new Button("Brisanje po parametrima");
	TextField tfId = new TextField();
	TextField tfNaziv = new TextField();
	TextField tfKljuc = new TextField();
	TextField tfVrednost = new TextField();
	
	public DeleteStage(StartStage ss, MainStage ms) {
		
		GridPane gp = new GridPane();
		gp.add(tfId, 0, 0);
		gp.add(ok, 1, 0);
		gp.add(ok1, 3, 0);
		gp.add(tfNaziv, 0, 1);
		gp.add(tfKljuc, 1, 1);
		gp.add(tfVrednost, 2, 1);
		gp.setPadding(new Insets(10,10,10,10));
		tfId.setPromptText("ID za brisanje");
		tfNaziv.setPromptText("Naziv entiteta");
		tfKljuc.setPromptText("Kljuc");
		tfVrednost.setPromptText("Vrednost kljuca");
		gp.setAlignment(Pos.CENTER);
		gp.setPadding(new Insets(10,10,10,10));
		
		Scene sc = new Scene(gp, 800, 600);
		setScene(sc);
		
		ok.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ToolManager.getManager().brisiNaOsnovuIDa(tfId.getText(), ss.tf1.getText());
				ms.tv.getItems().clear();
				ms.tv.getItems().addAll(ToolManager.getManager().entities);
			}
		});
		ok1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ToolManager.getManager().brisiKljucVrednost(tfNaziv.getText(), tfKljuc.getText(), tfVrednost.getText(), ss.tf1.getText());
				ms.tv.getItems().clear();
				ms.tv.getItems().addAll(ToolManager.getManager().entities);
			}
		});
	}
	
	
	
}

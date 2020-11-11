package utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Entity;
import utils.ToolManager;
import utils.API;

public class MainStage extends Stage {
	StartStage ss;

	TableView<Entity> tv = new TableView<Entity>();
	Button btnDodajEntitet = new Button("Dodaj entitet");
	Button btnSacuvajUFajl = new Button("Sacuvaj u fajl"); /// Da se sacuva iz TableView znaci ono sto je dodavano i
															/// brisano da se sacuva fajl
	Button btnSortirajIdRastuce = new Button("Sortiraj po ID rastuce");
	Button btnSortirajIdOpadajuce = new Button("Sortiraj po ID opadajuce");
	Button btnSortirajPoImenu = new Button("Sortiraj po imenu");

	Button btnCreate = new Button("Crate");
	Button btnAdd = new Button("Add");
	Button btnDelete = new Button("Delete");

	TextArea ta = new TextArea();
	TextField tfSacuvaj = new TextField("naziv fajla");

	// vrati sve entitete sa nazivom ______ koji sadrze kljuc ______ i ime im
	// pocinje sa _______

	Label label1 = new Label("Vrati sve entitete sa nazivom ");
	TextField textF1 = new TextField();
	Label label2 = new Label(" koji sadrze kljuc ");
	TextField textF2 = new TextField();
	Label label3 = new Label(" i ime im pocinje sa ");
	TextField textF3 = new TextField();
	Button btnOK = new Button("OK");

	// selektovati sve studente koji za ključ “adresa” imaju entitet koji kao
	// vrednost ključa “grad” sadrži vrednost “Beograd”);

	Label label11 = new Label("Selektovati sve entitete koji za kljuc ");
	TextField textF11 = new TextField();
	Label label22 = new Label("imaju entitet koji kao vrednost kljuca ");
	TextField textF22 = new TextField();
	Label label33 = new Label("sadrzi vrednost ");
	TextField textF33 = new TextField();
	Button btnOK1 = new Button("OK");
	ToggleButton tb1 = new ToggleButton("ID se automatski generise");
	ToggleButton tb2 = new ToggleButton("Sami biramo ID");

	ToggleGroup group = new ToggleGroup();
	public MainStage(StartStage ss) {
		
		
		
		 tb1.setToggleGroup(group);
		 tb2.setToggleGroup(group);
	
		
		
		HBox h3 = new HBox(10);
		h3.setAlignment(Pos.BASELINE_LEFT);
		h3.setPadding(new Insets(10, 10, 10, 10));
		h3.getChildren().addAll(label1, textF1, label2, textF2, label3, textF3, btnOK);

		HBox h4 = new HBox(10);
		h4.setAlignment(Pos.CENTER);
		h4.setPadding(new Insets(10, 10, 10, 10));
		h4.getChildren().addAll(label11, textF11, label22, textF22, label33, textF33, btnOK1);

		this.ss = ss;
		HBox root = new HBox(10);
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setAlignment(Pos.CENTER);
		VBox vLevi = new VBox(10);

		vLevi.setAlignment(Pos.CENTER);

		VBox v1 = new VBox(10);
		VBox v2 = new VBox(10);
		VBox v3 = new VBox(10);
		VBox v4 = new VBox(10);
		v1.getChildren().addAll(tb1, tb2);
//		Label id = new Label("id");
//		Label naziv = new Label("naziv");
//		TextField tfId = new TextField();
//		TextField tfNaziv = new TextField();
//		TextField tfEntitet = new TextField("Ime entiteta");
//		TextField tfAtribut = new TextField("Ime atributa");
//		TextField tfEntitet1 = new TextField("Vrednost za entiet");
//		TextField tfAtribut1 = new TextField("Vrednost za atribut");
//		v1.getChildren().addAll(id, tfId);
//		v2.getChildren().addAll(naziv, tfNaziv);
//		v3.getChildren().addAll(tfEntitet, tfEntitet1);
//		v4.getChildren().addAll(tfAtribut, tfAtribut1);

//		HBox h1 = new HBox(10);
//		h1.getChildren().addAll(v1, v2, v3, v4);
//		h1.setAlignment(Pos.CENTER);

		HBox h2 = new HBox(10);
		h2.getChildren().addAll(tfSacuvaj, btnSacuvajUFajl, btnDodajEntitet, btnDelete, btnSortirajIdRastuce,
				btnSortirajIdOpadajuce, btnSortirajPoImenu);
		vLevi.getChildren().addAll(tv, h2, h3, h4, ta, v1);

		GridPane gp = new GridPane();
		TableColumn<Entity, String> tcId = new TableColumn<Entity, String>("ID");
		TableColumn<Entity, String> tcNaziv = new TableColumn<Entity, String>("IME");
		TableColumn<Entity, Map<String, Object>> tcOstalo = new TableColumn<Entity, Map<String, Object>>(
				"Ostali atributi");
		TableColumn<Entity, Map<String, Entity>> tcUgnjezdeni = new TableColumn<Entity, Map<String, Entity>>(
				"Ugnjezdeni entitet");

		tcId.setCellValueFactory(new PropertyValueFactory<Entity, String>("id"));
		tcNaziv.setCellValueFactory(new PropertyValueFactory<Entity, String>("name"));
		tcOstalo.setCellValueFactory(new PropertyValueFactory<Entity, Map<String, Object>>("properties"));
		tcUgnjezdeni.setCellValueFactory(new PropertyValueFactory<Entity, Map<String, Entity>>("entities"));
		root.getChildren().addAll(vLevi);

		tv.getColumns().addAll(tcId, tcNaziv, tcOstalo, tcUgnjezdeni);

		Scene sc = new Scene(root, 1300, 1000);
		setScene(sc);

		List<Entity> lista = ToolManager.getManager().openDir(ss.getTf1().getText());
		tv.getItems().addAll(lista);

		btnDodajEntitet.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String tekst = ta.getText();

			}
		});

		btnSacuvajUFajl.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ToolManager.getManager().save(ToolManager.getManager().entities, tfSacuvaj.getText());
			}
		});

		MainStage mss = this;
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				DeleteStage ds = new DeleteStage(ss, mss);
				ds.show();
			}

		});

		btnSortirajPoImenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Comparator<Entity> comparator = Comparator.comparing(Entity::getName);
				ToolManager.getManager().entities.sort(comparator);
				tv.getItems().clear();
				tv.getItems().addAll(ToolManager.getManager().entities);
				// tv.sort();
			}
		});

		btnSortirajIdOpadajuce.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ToolManager.getManager().entities.sort(null);
				tv.getItems().clear();
				tv.getItems().addAll(ToolManager.getManager().entities);

			}
		});

		btnSortirajIdRastuce.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Comparator<Entity> comparator = Comparator.comparing(Entity::getId);
				ToolManager.getManager().entities.sort(comparator);
				tv.getItems().clear();
				tv.getItems().addAll(ToolManager.getManager().entities);
			}
		});

		btnOK.setOnAction(new EventHandler<ActionEvent>() {
//			Label label1 = new Label("Vrati sve entitete sa nazivom ");
//			TextField textF1 = new TextField();
//			Label label2 = new Label(" koji sadrze kljuc ");
//			TextField textF2 = new TextField();
//			Label label3 = new Label(" i ime im pocinje sa ");
//			TextField textF3 = new TextField();
//			Button btnOK = new Button("OK");
			@Override
			public void handle(ActionEvent event) {
				tv.getItems().clear();
				List<Entity> entitys = new ArrayList<Entity>();
				boolean sadrziKljuc = false, imePocinje = false;
//				for (Entity e : ToolManager.getManager().entities) {
//					for (Map.Entry<String, Object> entry : e.getProperties().entrySet()) {
//						System.out.println(entry.getKey());
//					}
//				}
				for (Entity e : ToolManager.getManager().entities) {
					if (!textF1.getText().isEmpty() && !textF2.getText().isEmpty() && !textF3.getText().isEmpty()) {
						if (e.getName().equalsIgnoreCase(textF1.getText())) {
							for (Map.Entry<String, Object> entry : e.getProperties().entrySet()) {
								if (entry.getKey().equalsIgnoreCase(textF2.getText())) {
									System.out.println(entry.getKey());
									sadrziKljuc = true;
									if (imePocinje) {
										entitys.add(e);
										sadrziKljuc = false;
										imePocinje = false;
										break;
									}
								}
								
								if (!(e.getName().isEmpty())
										&& e.getName().startsWith(textF3.getText())) {
									imePocinje = true;
									if (sadrziKljuc) {
										entitys.add(e);
										sadrziKljuc = false;
										imePocinje = false;
										break;
									}
								}
							}
							sadrziKljuc = false;
							imePocinje = false;

						}
					}
				}
				tv.getItems().addAll(entitys);
			}

		});
//		Label label11 = new Label("Selektovati sve entitete koji za kljuc ");
//		TextField textF11 = new TextField(); adresa
//		Label label22= new Label("imaju entitet koji kao vrednost kljuca ");
//		TextField textF22 = new TextField(); grad
//		Label label33= new Label("sadrzi vrednost ");
//		TextField textF33 = new TextField();
		btnOK1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				tv.getItems().clear();
				List<Entity> entitys = new ArrayList<Entity>();
				if (!textF11.getText().isEmpty() && !textF22.getText().isEmpty() && !textF33.getText().isEmpty()) {
					for (Entity e : ToolManager.getManager().entities) {
						for (Map.Entry<String, Entity> entry : e.getEntities().entrySet()) {
							if (entry.getKey().equalsIgnoreCase(textF11.getText())) {
								for (Map.Entry<String, Object> entry2 : entry.getValue().getProperties().entrySet()) {
									if (entry2.getKey().equalsIgnoreCase(textF22.getText())) {
										if (((String) entry2.getValue()).equalsIgnoreCase(textF33.getText())) {
											entitys.add(e);
										}
									}
								}
							}
						}
					}

				}
				tv.getItems().addAll(entitys);
			}

		});
		
		btnDodajEntitet.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ToggleButton selectedToggleButton =
				        (ToggleButton) group.getSelectedToggle();
				
			}
			
		});
	}

}

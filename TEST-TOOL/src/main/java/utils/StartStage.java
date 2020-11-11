package utils;

import java.io.File;
import java.nio.file.Files;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class StartStage extends Stage {
	TextField tf = new TextField();
	Label l = new Label("Unesi eksentziju");

	TextField tf1 = new TextField();
	Label l1 = new Label("Unesi putaju do direktorijuma");

	TextField tf2 = new TextField("Koliko najvise entiteta u jedan fajl zelite:");

	GridPane gp = new GridPane();

	Button ok = new Button("ok");
	private int broj;

	public StartStage() {
		gp.setPadding(new Insets(10, 10, 10, 10));
		gp.add(l, 0, 0);
		gp.add(tf, 0, 1);
		gp.add(l1, 1, 0);
		gp.add(tf1, 1, 1);
		gp.add(ok, 0, 2);
		gp.add(tf2, 1, 2);

		gp.setAlignment(Pos.CENTER);
		Scene sc = new Scene(gp, 800, 600);
		setScene(sc);

		ok.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (tf.getText().equalsIgnoreCase(".json")) {
					try {
						Class.forName("utils.JSONImplementation");
						File directory = new File(tf1.getText());

						File[] contents = directory.listFiles();
						if (contents == null) {
							directory.mkdir();
							File f = new File(directory.getPath() + File.separator + "newFile.json");
							f.createNewFile();
							API api = ToolManager.getManager(f.getAbsolutePath()); // jer otvaramo za .json
						} else {
							API api = ToolManager.getManager(contents[0].getAbsolutePath()); /// sluzi samo za proveru i
																								/// nista vise
						}

						otvoriStage();

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (tf.getText().equalsIgnoreCase(".yaml")) {
					try {
						Class.forName("utils.YAMLImplementation");
						File directory = new File(tf1.getText());

						File[] contents = directory.listFiles();
						if (contents == null) {
							directory.mkdir();
							File f = new File(directory.getPath() + File.separator + "newFile.yaml");
							f.createNewFile();
							API api = ToolManager.getManager(f.getAbsolutePath()); // jer otvaramo za .json
						} else {
							API api = ToolManager.getManager(contents[0].getAbsolutePath()); /// sluzi samo za proveru i
																								/// nista vise
						}

						otvoriStage();

					} catch (Exception e) {
						e.printStackTrace();

					}
				} else {
					System.out.println("Pogresan unos");
				}
			}
		});

	}

	public void otvoriStage() {
		MainStage ms = new MainStage(this);
		ms.show();
		hide();
	}

	public TextField getTf() {
		return tf;
	}

	public void setTf(TextField tf) {
		this.tf = tf;
	}

	public Label getL() {
		return l;
	}

	public void setL(Label l) {
		this.l = l;
	}

	public TextField getTf1() {
		return tf1;
	}

	public void setTf1(TextField tf1) {
		this.tf1 = tf1;
	}

	public Label getL1() {
		return l1;
	}

	public void setL1(Label l1) {
		this.l1 = l1;
	}

	public GridPane getGp() {
		return gp;
	}

	public void setGp(GridPane gp) {
		this.gp = gp;
	}

	public Button getOk() {
		return ok;
	}

	public void setOk(Button ok) {
		this.ok = ok;
	}

	public TextField getTf2() {
		return tf2;
	}

	public int getBroj() {
		return broj;
	}
	
	public void setBroj(int broj) {
		this.broj = broj;
	}

}

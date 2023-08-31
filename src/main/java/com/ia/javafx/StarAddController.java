package com.ia.javafx;

import com.ia.Degree;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class StarAddController {
	@FXML
	private ChoiceBox<String> starDropdown;
	@FXML
	private RadioButton indexCorrOn;
	@FXML
	private Button continueButton;
	@FXML
	private TextField angularHeightDegrees;
	@FXML
	private TextField angularHeightMinutes;
	@FXML
	private TextField indexCorr;
	private MainScreenController main;


	public void loadStars() {
		String[] stars = {"Alpheratz", "Ankaa", "Schedar", "Diphda", "Achernar", "Hamal", "Polaris", "Acamar", "Menkar", "Mirfak", "Aldebaran", "Rigel", "Capella", "Bellatrix", "Elnath", "Alnilam", "Betelgeuse", "Canopus", "Sirius", "Adhara", "Procyon", "Pollux", "Avior", "Suhail", "Miaplacidus", "Alphard", "Regulus", "Dubhe", "Denebola", "Gienah", "Acrux", "Gacrux", "Alioth", "Spica", "Alkaid", "Hadar", "Menkent", "Arcturus", "Rigil Kent.", "Kochab", "Zuben'ubi", "Alphecca", "Antares", "Atria", "Sabik", "Shaula", "Rasalhague", "Eltanin", "Kaus Aust.", "Vega", "Nunki", "Altair", "Peacock", "Deneb", "Enif", "Al Na’ir", "Fomalhaut", "Scheat", "Markab"};
		starDropdown.setItems(FXCollections.observableList(List.of(stars)));
	}

	public void getData() {
		String star = starDropdown.getValue();
		Degree angularHeight =
				new Degree(angularHeightDegrees.getText() + " " + angularHeightMinutes.getText());
		Degree indexCorrection = new Degree(Double.parseDouble(indexCorr.getText().replace("°", "")));
		boolean indexCorrectionOn = indexCorrOn.isPressed();

		main.addStarDisplay(star);
		Stage temp = (Stage) continueButton.getScene().getWindow();
		temp.close();
	}

	public void setMain(MainScreenController main) {
		this.main = main;
	}
}
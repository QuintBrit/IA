package com.ia.javafx;

import com.ia.*;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;

import static java.lang.Math.ceil;

public class FinalFixDisplayController {
	@FXML
	private Pane pane;
	@FXML
	private Button saveFixButton;
	@FXML
	private Button loadFixButton;
	@FXML
	private Button plotButton;

	public void loadFixes() {
		FileChooser fileChooser = new FileChooser();
		File f = fileChooser.showOpenDialog(loadFixButton.getScene().getWindow());
		ArrayList<Plot> fixes = FileHandler.loadPlot(f.getAbsolutePath());
		String[] s = new String[fixes.size()];
		AValue[] a = new AValue[fixes.size()];
		Degree[] az = new Degree[fixes.size()];
		Latitude[] alat = new Latitude[fixes.size()];
		Longitude[] alon = new Longitude[fixes.size()];

		for (int i = 0; i < fixes.size(); i++) {
			s[i] = fixes.get(i).getStar();
			a[i] = fixes.get(i).getAValue();
			az[i] = fixes.get(i).getAzimuth();
			alat[i] = fixes.get(i).getAssumedLatitude();
			alon[i] = fixes.get(i).getAssumedLongitude();
		}

		addStarDisplay(fixes.size(), s, a, az, alat, alon);
	}

	public void addStarDisplay(int numStars, String[] stars, AValue[] aValues, Degree[] azimuths,
	                           Latitude[] assumedLatitudes, Longitude[] assumedLongitudes) {
		// use double int arr for each position, with all the magic numbers contained within
		int[][] xPositions;
		int[][] yPositions;

		if (numStars == 2) {
			xPositions = new int[][]{{40, 60, 50}, {290, 310, 300}};
			yPositions = new int[][]{{120, 170, 210, 250, 290}, {120, 170, 210, 250, 290}};
		} else {
			xPositions = new int[][]{{40, 60, 50}, {290, 310, 300}, {136, 160, 150}};
			yPositions = new int[][]{{20, 70, 110, 150, 190}, {20, 70, 110, 150, 190}, {297, 240, 280, 320, 360}};
		}

		for (int i = 0; i < numStars; i++) {
			Label starLabel = new Label(stars[i]);
			starLabel.setFont(new Font("System", 22));
			starLabel.setStyle("-fx-border-color: black");
			starLabel.setLayoutX(xPositions[i][0]);
			starLabel.setLayoutY(yPositions[i][0]);

//			FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
//			System.out.println(fontLoader.computeStringWidth(label.getText(), label.getFont()));

			final Text text = new Text(stars[i]);
			text.setFont(new Font("System", 22));
			text.setStyle("-fx-border-color: black");
			new Scene(new Group(text));
			text.applyCss();
			// 1 is a fudge to account for the border
			final double width = ceil(text.getLayoutBounds().getWidth()) + 1;
			System.out.println(width);

			pane.getChildren().add(starLabel);

			Label aValueLabel = new Label(aValues[i].toString());
			aValueLabel.setFont(new Font("System", 18));
			aValueLabel.setStyle("-fx-border-color: black");
			aValueLabel.setLayoutX(xPositions[i][1]);
			aValueLabel.setLayoutY(yPositions[i][1]);

			pane.getChildren().add(aValueLabel);

			Label azimuthLabel = new Label(azimuths[i].toString());
			azimuthLabel.setFont(new Font("System", 18));
			azimuthLabel.setStyle("-fx-border-color: black");
			azimuthLabel.setLayoutX(xPositions[i][1]);
			azimuthLabel.setLayoutY(yPositions[i][2]);

			pane.getChildren().add(azimuthLabel);

			Label assumedLatitudeLabel = new Label(assumedLatitudes[i].toString());
			assumedLatitudeLabel.setFont(new Font("System", 18));
			assumedLatitudeLabel.setStyle("-fx-border-color: black");
			assumedLatitudeLabel.setLayoutX(xPositions[i][1]);
			assumedLatitudeLabel.setLayoutY(yPositions[i][3]);

			pane.getChildren().add(assumedLatitudeLabel);

			Label assumedLongitudeLabel = new Label(assumedLongitudes[i].toString());
			assumedLongitudeLabel.setFont(new Font("System", 18));
			assumedLongitudeLabel.setStyle("-fx-border-color: black");
			assumedLongitudeLabel.setLayoutX(xPositions[i][1]);
			assumedLongitudeLabel.setLayoutY(yPositions[i][4]);

			pane.getChildren().add(assumedLongitudeLabel);

			// use vertical and horizontal lines

			Line mainVerticalLine;
			mainVerticalLine = new Line(xPositions[i][2], starLabel.getLayoutY()+34, xPositions[i][2],
				assumedLongitudeLabel.getLayoutY()+14);

			pane.getChildren().add(mainVerticalLine);

			Line smallHorizontalLine1 = new Line(xPositions[i][2], aValueLabel.getLayoutY()+14,
					aValueLabel.getLayoutX(), aValueLabel.getLayoutY()+14);
			pane.getChildren().add(smallHorizontalLine1);

			Line smallHorizontalLine2 = new Line(xPositions[i][2], azimuthLabel.getLayoutY()+14,
					azimuthLabel.getLayoutX(), azimuthLabel.getLayoutY()+14);
			pane.getChildren().add(smallHorizontalLine2);

			Line smallHorizontalLine3 = new Line(xPositions[i][2], assumedLatitudeLabel.getLayoutY()+14,
					assumedLatitudeLabel.getLayoutX(), assumedLatitudeLabel.getLayoutY()+14);
			pane.getChildren().add(smallHorizontalLine3);

			Line smallHorizontalLine4 = new Line(xPositions[i][2], assumedLongitudeLabel.getLayoutY()+14,
					assumedLongitudeLabel.getLayoutX(), assumedLongitudeLabel.getLayoutY()+14);
			pane.getChildren().add(smallHorizontalLine4);

			if (i == 2) {
				starLabel.setLayoutX(starLabel.getLayoutX()-width);
				mainVerticalLine.setStartY(aValueLabel.getLayoutY()+14);

				saveFixButton.setLayoutX(plotButton.getLayoutX());
				loadFixButton.setLayoutX(plotButton.getLayoutX());

				saveFixButton.setLayoutY(plotButton.getLayoutY()-100);
				loadFixButton.setLayoutY(plotButton.getLayoutY()-50);

				Line smallHorizontalLine5 = new Line(xPositions[i][0], starLabel.getLayoutY()+17,
						xPositions[i][2], starLabel.getLayoutY()+17);
				pane.getChildren().add(smallHorizontalLine5);
			}
		}
	}
}
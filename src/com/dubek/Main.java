package com.dubek;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application{

	Time theTime = new Time(-3600000);
	Label timeString = new Label(theTime.toString());

	int interval = 0;

    public static void main(String[] args){
		launch(args);
	}

	public void start(Stage primaryStage) throws InterruptedException {

    	timeString.setFont(Font.font(40));

		Button countDown = new Button("Count Down");
		countDown.setOnAction(value -> interval = -1000);
		countDown.setPrefSize(90, 40);

		Button countUp = new Button("Count");
		countUp.setOnAction(value -> interval = 1000);
		countUp.setPrefSize(90, 40);

		Button pause = new Button("Pause");
		pause.setOnAction(value -> interval = 0);
		pause.setPrefSize(90, 40);

		Button reset = new Button("Reset");
		reset.setOnAction(value -> {
			interval = 0;
			theTime = new Time(-3600000);
		});
		reset.setPrefSize(90, 40);


		HBox countButtons = new HBox(countDown, countUp, pause, reset);
		countButtons.setSpacing(5);

		//HBox editButtons = new HBox(pause, reset);
		//editButtons.setSpacing(5);

		VBox root = new VBox(timeString, countButtons);
		root.setAlignment(Pos.CENTER);
		root.setSpacing(5);
		root.setPadding(new Insets(8, 8, 8, 8));

		Scene scene = new Scene(root);

    	primaryStage.setTitle("Time Tracker v1.0");
    	primaryStage.setScene(scene);
    	primaryStage.show();

		Timer myTimer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				Platform.runLater(() -> {
					theTime.setTime(theTime.getTime() + interval);
					timeString.setText(theTime.toString());
				});
			}
		};

    	myTimer.scheduleAtFixedRate(task, 1000, 1000);

	}

}

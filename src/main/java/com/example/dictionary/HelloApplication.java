package com.example.dictionary;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("My Dictionary");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    int yLine=10;
    TextField wordTextField;
    DictionaryUsingHashMap dictionaryUsingHashMap;
    Label meaningLabel;
    Button searchButton;
    ListView<String> suggestedWordList;
    Pane createContent() {
        Pane root=new Pane();
        root.setPrefSize(300,300);

        wordTextField=new TextField();
        wordTextField.setPromptText("Please enter a word");
        wordTextField.setTranslateY(yLine);
        wordTextField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                //meaningLabel.setText(wordTextField.getText());
                String word=wordTextField.getText();
                if(word.isBlank()==false && word.length()>2){
                    ArrayList<String> suggestions = dictionaryUsingHashMap.getSuggestions(word);
                    suggestedWordList.getItems().clear();
                    suggestedWordList.getItems().addAll(suggestions);
                }
            }
        });

        dictionaryUsingHashMap=new DictionaryUsingHashMap();

        meaningLabel=new Label("I am the meaning");
        meaningLabel.setTranslateY(yLine+30);
        meaningLabel.setTranslateX(10);

        searchButton=new Button("Search");
        searchButton.setTranslateY(yLine);
        searchButton.setTranslateX(200);
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String word = wordTextField.getText();
                if(word.isBlank()){
                    meaningLabel.setText("Please enter a word");
                    meaningLabel.setTextFill(Color.RED);
                }
                else{
                    meaningLabel.setText(dictionaryUsingHashMap.findMeaning(word));
                    meaningLabel.setTextFill(Color.BLACK);

                }

            }
        });

        suggestedWordList=new ListView<>();
        suggestedWordList.setTranslateY(yLine+65);
        String[] suggestedList={"Sampat","Eksdee"};
        suggestedWordList.getItems().addAll(suggestedList);
        suggestedWordList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String selectedWord=suggestedWordList.getSelectionModel().getSelectedItem();
                ;
                meaningLabel.setText(dictionaryUsingHashMap.findMeaning(selectedWord));
            }
        });



        root.getChildren().addAll(wordTextField,searchButton,meaningLabel,suggestedWordList);
        return root;
    }
}
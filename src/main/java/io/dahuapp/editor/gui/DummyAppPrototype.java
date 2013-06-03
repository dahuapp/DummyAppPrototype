package io.dahuapp.editor.gui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class DummyAppPrototype extends Application {

    @Override
    public void start(Stage primaryStage) {
        WebView wv = new WebView();
        wv.getEngine().load(getClass().getResource("dahuapp.html").toString());

        StackPane root = new StackPane();
        root.getChildren().add(wv);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Mon navigateur à moi");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

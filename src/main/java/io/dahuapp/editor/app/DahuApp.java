package io.dahuapp.editor.app;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;


public class DahuApp extends Application {

    private WebView webview;
    private JSObject windowJSObject;
    private JSObject dahuappJSObject;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        
        // init dahuapp
        initDahuApp();
        
        // pin it to stackpane
        root.getChildren().add(webview);

        // create the sceen
        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("DahuApp Editor");
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
    
    private void initDahuApp() {
        webview = new WebView();
        webview.getEngine().load(getClass().getResource("dahuapp.html").toExternalForm());

        // get main window JSObject and add our oun object
        windowJSObject = (JSObject) webview.getEngine().executeScript("window");
        windowJSObject.setMember("dahuapp", new DahuAppJS());
        //dahuappJSObject = (JSObject) webview.getEngine().executeScript("dahuapp");
        
        // driver should be added to dahuapp
        // window.dahuapp.driver.keyboard.doSomeThing(...)
    }
    
    /**
     * DahuApp JavaScript interface to DahuApp main application
     */
    public class DahuAppJS {
        public void exit() {
            Platform.exit();
        }
    }
}

package io.dahuapp.editor.app;

import java.util.HashMap;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;

import io.dahuapp.editor.drivers.Driver;
import io.dahuapp.editor.drivers.DummyDriver;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;

public class DahuApp extends Application {

    private WebView webview;
    private DahuAppJS dahuappJS;
    
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
        // create webview
        webview = new WebView();
        
        // load main app
        webview.getEngine().load(getClass().getResource("dahuapp.html").toExternalForm());

        // extend the webview js context
        webview.getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(final ObservableValue<? extends Worker.State> observableValue, final State oldState, final State newState) {
                if (newState == State.SUCCEEDED) {
                    JSObject win = (JSObject) webview.getEngine().executeScript("window");
                    dahuappJS = new DahuAppJS();
                    win.setMember("dahuapp", dahuappJS);
                    
                    // Java side test
                    dahuappJS.printHello("petter");
                    webview.getEngine().executeScript("dahuapp.printHello('mary')");
                    webview.getEngine().executeScript("window.dahuapp.printHello('mary2')");
                }
            }
        });
    }
    
    /**
     * DahuApp JavaScript interface to DahuApp main application
     */
    public class DahuAppJS {
        HashMap<String, Driver> drivers = new HashMap<String, Driver>();
        
        public DahuAppJS() {
            drivers.put("dummy", new DummyDriver());
        }
        
        public String sayHello(String name) {
            return "Hello "+name;
        }
        
        public void printHello(String name) {
            System.out.println(sayHello(name));
        }
        
        public void exit() {
            Platform.exit();
        }
        
        public DummyDriver dummy() {
            return (DummyDriver) drivers.get("dummy");
        }
    }
}

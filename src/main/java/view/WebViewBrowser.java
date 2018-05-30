package view;

import javafx.application.Application;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class WebViewBrowser extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        WebView webview = new WebView();
        final WebEngine webengine = webview.getEngine();
    }
}

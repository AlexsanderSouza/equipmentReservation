/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.alertaInformacao;
import view.LOGIN.viewlogin;

/**
 *
 * @author WIGOR
 */
public class startView extends Application{
    
    private static Stage stage;
    alertaInformacao vAlerta = new alertaInformacao();
    @Override
    public void start(Stage primaryStage) throws IOException {    
            stage = primaryStage;
            stage.setTitle("Sistema de Aloca��o");
            
            primaryStage.show();
            viewlogin vLoginView = new viewlogin();
            vLoginView.start();        
    }
    
    public static void setScene(Parent p){
        Scene scene = new Scene(p);
        stage.setScene(scene);
    }
    
    public static void sair(){
        stage.close();
    }
    
}

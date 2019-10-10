/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botecofx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.TilePane;

/**
 * FXML Controller class
 *
 * @author Silvio
 */
public class FXMLPainelComandaController implements Initializable {

    @FXML
    private TilePane painel;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            Parent root = FXMLLoader.load(getClass().getResource("FXMLComanda.fxml"));
//            painel.setId("1");
//            painel.getChildren().add(root);
//            
//        } catch (IOException ex) {
//            System.out.println(ex);
//        }
        try {
            for (int i = 0; i < 20; i++) 
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLComanda.fxml"));
                Parent root = (Parent) loader.load();
                FXMLComandaController ctr = loader.getController();
                ctr.setNumeroComanda(i);
                painel.getChildren().add(root);    
                
            }
            

            //this.controller = (Tela1Controller) loader.getController(); 
        } catch (Exception e) { }
    }    
    
}

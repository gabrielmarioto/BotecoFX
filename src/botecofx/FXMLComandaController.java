/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botecofx;

import static botecofx.FXMLPrincipalController.spnprincipal;
import db.entidades.Comanda;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Silvio
 */
public class FXMLComandaController implements Initializable {

    private Comanda comanda;
    @FXML
    private Label lbcomanda;
    @FXML
    private AnchorPane painel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
     public void setComanda(Comanda c)
    {
        comanda = c;
        lbcomanda.setText(lbcomanda.getText().replace("#", ""+comanda.getNum()));
    }
    public void setCor(String cor)
    {
        painel.setStyle("-fx-background-color: " + cor + ";");
    }
    @FXML
    private void clkAcaoExemplo(ActionEvent event) 
    {
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLComandaPopUp.fxml"));
        Parent root = (Parent) loader.load();
        FXMLComandaPopUpController ger = loader.getController();
        ger.setComanda(comanda);
        spnprincipal.setCenter(root);
            
            
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    private void efeito(ActionEvent event, boolean on)
    {
        double opi=1, opf=0.2;
        if(!on){opi=0.2; opf=1;}
        Node node = ((Node)event.getSource()).getParent().getParent(); // referencia o painel das comandas
        FadeTransition ft = new FadeTransition(Duration.millis(500), node);
        ft.setFromValue(opi);
        ft.setToValue(opf);
        ft.play(); 
    }

}

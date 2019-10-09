/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botecofx;

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

    @FXML
    private Label lbcomanda;
    private int comanda;
    @FXML
    private AnchorPane painel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setNumeroComanda(int num) {
        comanda = num;
        lbcomanda.setText("comanda #" + comanda);
        if (comanda % 3 == 0) //troca a cor do painel
        {
            painel.setStyle("-fx-background-color: #FFFF00;");
        }
    }

    @FXML
    private void clkAcaoExemplo(ActionEvent event) {
//        Alert alert;
//        alert  =new Alert(Alert.AlertType.INFORMATION);
//        alert.setContentText("Comanda número "+comanda);
//        alert.showAndWait();
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLComandaPopUp.fxml"));
            Parent root = (Parent) loader.load();
            FXMLComandaPopUpController ctr = loader.getController();
            ctr.setNumcomanda(comanda);
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            efeito(event, true);
            stage.showAndWait();
            efeito(event, false);
            
            
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

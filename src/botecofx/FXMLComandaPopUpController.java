/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botecofx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Silvio
 */
public class FXMLComandaPopUpController implements Initializable {

    private int numcomanda;
    @FXML
    private Label label;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void clkBotaoFechar(ActionEvent event) 
    {
        ((Button)event.getSource()).getScene().getWindow().hide();
    }

    public void setNumcomanda(int numcomanda) {
        this.numcomanda = numcomanda;
        label.setText(label.getText()+numcomanda);
    }
    
    
}

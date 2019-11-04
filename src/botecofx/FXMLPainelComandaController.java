/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botecofx;

import db.dal.DALComanda;
import db.entidades.Comanda;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
           DALComanda dal = new DALComanda();
        List <Comanda> lista = dal.get("com_status = 'A'");
        try
        {
            for(Comanda c : lista)
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLComanda.fxml"));
                Parent root = (Parent) loader.load();
                FXMLComandaController ctl = loader.getController();
                ctl.setComanda(c);
                if(c.getValor() > 0)
                    ctl.setCor("#00FF00");
                if(c.getValor() == 0)
                    ctl.setCor("#FF0000"); 
                painel.getChildren().add(root);
            } 
        }
        catch(IOException e )
        {
            System.out.println(e.getMessage());
        }
    }    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botecofx;

import botecofx.db.util.Banco;
import botecofx.util.ConsultaAPI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Aluno
 */
public class BotecoFX extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLPrincipal.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        stage.setMaximized(true);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
//        String cep="19053694";
//        String jsonresult = ConsultaAPI.consultaCep(cep);
//        System.out.println(jsonresult);
        
        if(Banco.conectar())
           launch(args);
        else
        {  JOptionPane.showMessageDialog(null, "Erro: "+Banco.getCon().getMensagemErro());
           System.exit(0);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botecofx;

import db.util.Banco;
import util.ConsultaAPI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Aluno
 */
public class BotecoFX extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLPrincipal.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.setMaximized(true);
        stage.setTitle("Boteco FX");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/hamburger.png"))); // SETAR ICONE NA JANELA
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {

//        String cep="19053694";
//        String jsonresult = ConsultaAPI.consultaCep(cep);
//        System.out.println(jsonresult);
        if(!Banco.conectar())
        {
            JOptionPane.showMessageDialog(null, "Erro: " + Banco.getCon().getMensagemErro());
            if(JOptionPane.showConfirmDialog(null, "Deseja criar uma base de dados?") == JOptionPane.YES_OPTION)
            {
                if(!Banco.criarBD("botecodb"))
                {                    
                    JOptionPane.showMessageDialog(null, "Erro ao criar banco: " + Banco.getCon().getMensagemErro());
                    System.exit(-1);
                }
                else
                {
                    Banco.realizaRestaure("bdutil\\restore.bat");
                    if(!Banco.conectar())
                    {
                        JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco");                    
                        System.exit(-1);
                    }
//                    else                        
//                        if(!Banco.criarTabelas("script/script.sql", "botecodb"))
//                        {
//                            JOptionPane.showMessageDialog(null, "Erro ao criar tabelas: " + Banco.getCon().getMensagemErro());
//                            System.exit(-1);
//                        }
                }
            }    
            else
                System.exit(-1);
        }
        launch(args);
    }

}

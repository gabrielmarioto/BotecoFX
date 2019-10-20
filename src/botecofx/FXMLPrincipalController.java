/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botecofx;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

/**
 *
 * @author Aluno
 */
public class FXMLPrincipalController implements Initializable
{

    //dados estáticos
    public static BorderPane spnprincipal = null;

    @FXML
    private BorderPane pnprincipal;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        spnprincipal = pnprincipal;
      //spnprincipal.setStyle("-fx-background-position: center;");
    }

    @FXML
    private void clkCadProduto(ActionEvent event)
    {

        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLCadProduto2.fxml"));
            efeito(true);
            pnprincipal.setCenter(root);

        } catch (IOException ex)
        {
            System.out.println(ex);
        }

    }

    @FXML
    private void clkLink(ActionEvent event)
    {
        try
        {
            Desktop.getDesktop().browse(new URI("http://unoeste.br"));
        } catch (Exception ex)
        {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clkMostrarComandas(ActionEvent event)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLPainelComanda.fxml"));
            pnprincipal.setCenter(root);

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    @FXML
    private void clkNovaComanda(ActionEvent event)
    {

    }

    public static void efeito(boolean on)
    {
        if (on)
        {
//            FadeTransition ft = new FadeTransition(Duration.millis(500), spnprincipal);
//            ft.setFromValue(1.0);
//            ft.setToValue(0.5);
//            ft.play(); 
            spnprincipal.setStyle("-fx-background-image: url('icons/textura.png');");

        } else
        {
            spnprincipal.setStyle("-fx-background-image: url('icons/textura.png');");
        }

    }

    @FXML
    private void clkGoToHome(ActionEvent event)
    {
        FXMLPrincipalController.spnprincipal.setCenter(null);
        FXMLPrincipalController.efeito(false);
    }

    @FXML
    private void clkCadGarcom(ActionEvent event)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLCadGarcom.fxml"));
            efeito(true);
            pnprincipal.setCenter(root);

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    @FXML
    private void clkFechar(ActionEvent event) 
    {
        System.exit(0);
    }

    @FXML
    private void clkCadCategoria(ActionEvent event)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLCadCategoria.fxml"));
            efeito(true);
            pnprincipal.setCenter(root);

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    @FXML
    private void clkCadUnidade(ActionEvent event)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLCadUnidade.fxml"));
            efeito(true);
            pnprincipal.setCenter(root);

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

}

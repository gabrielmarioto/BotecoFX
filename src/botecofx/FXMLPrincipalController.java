/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botecofx;

import db.util.Banco;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.HashMap;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;

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
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLAbrirComanda.fxml"));
            pnprincipal.setCenter(root);

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
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
            // spnprincipal.setStyle("css/jfoenix-components");    

        } else
        {
            spnprincipal.setStyle("-fx-background-image: url('icons/textura.png');");
        }

    }

    @FXML
    private void clkGoToHome(ActionEvent event)
    {
        spnprincipal.setCenter(null);

    }

    @FXML
    private void clkCadGarcom(ActionEvent event)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLCadGarcom.fxml"));

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

            pnprincipal.setCenter(root);

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    @FXML
    private void clkCadTipoPgto(ActionEvent event)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLCadTipoPgto.fxml"));

            pnprincipal.setCenter(root);

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    @FXML
    private void clkRelatorioProd(ActionEvent event) {
        String sql = "select prod_id, prod_nome, prod_preco, prod_descr, uni_nome, cat_nome from produto p, categoria c, unidade u where p.cat_id = c.cat_id and p.uni_id = u.uni_id order by prod_id";
        gerarRelatorio(sql,"src/relatorios/rel_produtos.jasper", "Relatório", null);
    }
    
    private void gerarRelatorio(String sql, String relat, String titulotela, HashMap parametros) 
    {
        try 
        {  //sql para obter os dados para o relatorio
            ResultSet rs = Banco.getCon().consultar(sql);
            //implementação da interface JRDataSource para DataSource ResultSet
            JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
            //preenchendo e chamando o relatório
            String jasperPrint = JasperFillManager.fillReportToFile(relat, parametros, jrRS);
            JasperViewer viewer = new JasperViewer(jasperPrint, false, false);

            viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);//maximizado
            viewer.setTitle(titulotela);
            viewer.setVisible(true);
        } catch (JRException erro) 
        {
            System.out.println(erro);
        }
    }

    @FXML
    private void clkRelatorioProdCat(ActionEvent event)
    {
        String sql = "SELECT * FROM produto p JOIN categoria c ON  p.cat_id = c.cat_id ORDER BY  c.cat_nome, p.prod_nome";
//        HashMap parametros = new HashMap();
//        parametros.put("mensagem", "Relatorio de Preços de 2019");
        gerarRelatorio(sql, "src/relatorios/rel_prod_agp2.jasper", null, null);
    }

    @FXML
    private void clkRelatorioGarcom(ActionEvent event)
    {
        String sql = "select gar_cidade, gar_nome from garcon order by gar_cidade, gar_nome";
        gerarRelatorio(sql, "src/relatorios/rel_garcon.jasper", null, null);
    }

    @FXML
    private void clkRelatorioComanda(ActionEvent event)
    {
    }

}

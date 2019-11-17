/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botecofx;

import static botecofx.FXMLPrincipalController.gerarRelatorioIntegrado;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class FXMLRelatorioComandaController implements Initializable
{

    @FXML
    private JFXDatePicker dtpInicial;
    @FXML
    private JFXDatePicker dtpFinal;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        dtpFinal.setValue(LocalDate.now());
        dtpInicial.setValue(LocalDate.now());
    }    

    @FXML
    private void clkGerar(ActionEvent event)
    {
        if(dtpInicial.getValue() != null  && dtpFinal.getValue() != null)
        {
            String sql = "SELECT com_data, com_id, com_numero, com_nome, com_desc, gar_nome, com_valor, com_status FROM comanda c INNER JOIN garcon g ON c.gar_id = g.gar_id WHERE c.com_data between '#1' AND '#2' ORDER BY c.com_data";
            sql=sql.replaceAll("#1", dtpInicial.getValue().toString());
            sql=sql.replaceAll("#2", dtpFinal.getValue().toString());
            gerarRelatorioIntegrado(sql, "src/relatorios/rel_comanda_data.jasper", dtpInicial.getValue().format(DateTimeFormatter.ofPattern("dd/MM/uuuu")) + " à " + dtpFinal.getValue().format(DateTimeFormatter.ofPattern("dd/MM/uuuu")), "periodo");
            System.out.println(sql);
        }
    }

    @FXML
    private void clkCancelar(ActionEvent event)
    {
        FXMLPrincipalController.efeito(false);
        FXMLPrincipalController.spnprincipal.setCenter(null);
    }
    
}

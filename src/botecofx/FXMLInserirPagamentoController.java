/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botecofx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.dal.DALTipoPgto;
import db.entidades.Comanda;
import db.entidades.TipoPgto;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.MaskFieldUtil;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class FXMLInserirPagamentoController implements Initializable
{

    @FXML
    private SplitPane painel;
    @FXML
    private JFXButton btconfirmar;
    @FXML
    private JFXButton btcancelar;
    @FXML
    private AnchorPane pndados;
    @FXML
    private JFXComboBox<TipoPgto> cbbTipo;
    @FXML
    private JFXTextField txvalor;
    private Comanda c = new Comanda();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        MaskFieldUtil.monetaryField(txvalor);
        
        DALTipoPgto dal = new DALTipoPgto();
        List<TipoPgto> tp = dal.get("");
        ObservableList<TipoPgto> ob = FXCollections.observableList(tp);
        cbbTipo.setItems(ob);
    }    

    @FXML
    private void clkBtConfirmar(ActionEvent event)
    {
         Double valor;
        try
        {
            valor = Double.parseDouble(txvalor.getText().replace(".", "").replace(',', '.'));
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            valor = 0.0;
        }
        
        c.addPagamento(valor, cbbTipo.getSelectionModel().getSelectedItem());
        ((Stage) btconfirmar.getScene().getWindow()).close();
    }

    @FXML
    private void clkbtcancelar(ActionEvent event)
    {
        ((Stage) txvalor.getScene().getWindow()).close();
    }
     public Comanda.Pagamento getPgto()
    {
        return c.getPagamentos().get(0);
    }
    
}

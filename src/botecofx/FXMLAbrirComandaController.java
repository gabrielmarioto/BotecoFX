/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botecofx;

import static botecofx.FXMLPrincipalController.spnprincipal;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import db.dal.DALComanda;
import db.dal.DALGarcon;
import db.entidades.Comanda;
import db.entidades.Garcon;
import db.util.Banco;
import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;
import util.MaskFieldUtil;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class FXMLAbrirComandaController implements Initializable
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
    private JFXTextField txmesa;
    @FXML
    private JFXTextField txnome;
    @FXML
    private JFXTextField txdescricao;
    @FXML
    private JFXDatePicker dtpdata;
    @FXML
    private JFXComboBox<Garcon> cbbGarcom;
    boolean flag = false;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        dtpdata.setValue(LocalDate.now());
        DALGarcon dal = new DALGarcon();
        
        
        txmesa.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)   
                {
                    try
                    {
                        flag = false;
                        int mesa = Integer.parseInt(txmesa.getText());
                        ResultSet rs = Banco.getCon().consultar("select com_numero from comanda where com_status = 'A'");

                        while(rs.next())
                            if(rs.getInt("com_numero") == mesa)
                                flag = true;

                        if(flag)
                            JOptionPane.showMessageDialog(null, "Mesa em uso");
                    }
                    catch(Exception e)
                    {

                    } 
                }
            }
        });
                
        List <Garcon> dados = dal.get("");
        ObservableList <Garcon> obsList= FXCollections.observableList(dados);
        cbbGarcom.setItems(obsList);
    }    

    @FXML
    private void clkBtConfirmar(ActionEvent event)
    {
        if(flag)
             JOptionPane.showMessageDialog(null, "Mesa em uso");
        else
            if(isOk())
            {
                System.out.println(cbbGarcom.getValue());
               Comanda c = new Comanda(cbbGarcom.getValue(), Integer.parseInt(txmesa.getText()), txnome.getText(), dtpdata.getValue(), txdescricao.getText(), 0, 'A');

                DALComanda dal = new DALComanda();

                if(dal.gravar(c))
                {
                    spnprincipal.setCenter(null);
                }
                else
                {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Erro ao abrir comanda. Erro: " + Banco.getCon().getMensagemErro());
                    System.out.println(Banco.getCon().getMensagemErro());
                    a.showAndWait();
                }
            }
    }

    @FXML
    private void clkbtcancelar(ActionEvent event)
    {
        spnprincipal.setCenter(null);
    }
    private boolean isOk()
    {
        ObservableList<Node> componentes = pndados.getChildren();
        for (Node n : componentes) {
            if (n instanceof TextInputControl &&  ((TextInputControl) n).getText().isEmpty())
            {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("O campo " + n.getId().replace("tx", "") + " não foi preenchido");
                if(!n.getId().equals("txCodigo"))
                {
                    a.show();
                    return false;
                }
            }
            if (n instanceof ComboBox && ((ComboBox) n).getSelectionModel().getSelectedItem()== null)
            {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("O campo " + n.getId().replace("cb", "") + " não foi selecionado");
                a.show();
                return false;
            }
        }
        return true;
    }

    
}

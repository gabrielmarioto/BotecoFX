/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botecofx;

import static botecofx.FXMLPrincipalController.spnprincipal;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.dal.DALCategoria;
import db.dal.DALUnidade;
import db.entidades.Categoria;
import db.entidades.Unidade;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class FXMLCadUnidadeController implements Initializable
{

    @FXML
    private JFXButton btnovo;
    @FXML
    private JFXButton btalterar;
    @FXML
    private JFXButton btapagar;
    @FXML
    private JFXButton btconfirmar;
    @FXML
    private JFXButton btcancelar;
    @FXML
    private JFXTextField txcod;
    @FXML
    private JFXTextField txnome;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private JFXTextField txpesquisa;
    @FXML
    private JFXButton btpesquisar;
    @FXML
    private TableView<Unidade> tabela;
    @FXML
    private TableColumn<Unidade, Integer> colcod;
    @FXML
    private TableColumn<Unidade, String> colnome;
    @FXML
    private SplitPane painel;
    @FXML
    private AnchorPane pndados;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        colcod.setCellValueFactory(new PropertyValueFactory("cod"));
        colnome.setCellValueFactory(new PropertyValueFactory("nome"));
        estadoOriginal();
        carregaTabela("");
    }
    private void fadeout()
    {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), painel);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    private void estadoOriginal()
    {
        pnpesquisa.setDisable(false);
        pndados.setDisable(true);
        btconfirmar.setDisable(true);
        btcancelar.setDisable(false);
        btapagar.setDisable(true);
        btalterar.setDisable(true);
        btnovo.setDisable(false);

        ObservableList<Node> componentes = pndados.getChildren(); //”limpa” os componentes
        for (Node n : componentes)
        {
            if (n instanceof TextInputControl) // textfield, textarea e htmleditor
            {
                ((TextInputControl) n).setText("");
            }
            if (n instanceof ComboBox)
            {
                ((ComboBox) n).getItems().clear();
            }
        }

        carregaTabela("");
    }

    private void estadoEdicao()
    {     // carregar os componentes da tela (listbox, combobox, ...)
        // p.e. : carregaEstados();
        pnpesquisa.setDisable(true);
        pndados.setDisable(false);
        btconfirmar.setDisable(false);
        btapagar.setDisable(true);
        btalterar.setDisable(true);
        txnome.requestFocus();
    }

    private void carregaTabela(String filtro)
    {
        DALUnidade dal = new DALUnidade();
        List<Unidade> res = dal.get(filtro);
        ObservableList<Unidade> modelo;
        modelo = FXCollections.observableArrayList(res);
        tabela.setItems(modelo);
    }

    @FXML
    private void clkBtNovo(ActionEvent event)
    {
        estadoEdicao();
    }

    @FXML
    private void clkbtalterar(ActionEvent event)
    {
        if (tabela.getSelectionModel().getSelectedItem() != null)
        {
            Unidade u = (Unidade) tabela.getSelectionModel().getSelectedItem();
            txcod.setText("" + u.getCod());
            txnome.setText(u.getNome());
            estadoEdicao();
        }
    }

    @FXML
    private void clkBtApagar(ActionEvent event)
    {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Confirma a exclusão?");
        if (a.showAndWait().get() == ButtonType.OK)
        {
            DALUnidade dal = new DALUnidade();
            Unidade u = tabela.getSelectionModel().getSelectedItem();           
            if(!dal.apagar(u))
            {
                a.setContentText("Erro ao excluir!");
                a.showAndWait();
            }
            carregaTabela("");
        }
        estadoOriginal();
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event)
    {
         int cod;
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        try
        {
            cod = Integer.parseInt(txcod.getText());
        } catch (Exception e)
        {
            cod = 0;
        }
        if (txnome.getText().length() > 0)
        {
            Unidade u = new Unidade(cod, txnome.getText());
            DALUnidade dal = new DALUnidade();
            if (u.getCod() == 0)
            {
                if (!dal.gravar(u))
                {
                    a.setContentText("Problemas ao Gravar");
                }
            } else
            {
                if (!dal.alterar(u))
                {
                    a.setContentText("Problemas ao Alterar");
                    a.showAndWait();
                }
            }
            estadoOriginal();
        } else
        {
            a.setContentText("Informe o nome!");
            a.showAndWait();
        }
        
        carregaTabela("");
    }

    @FXML
    private void clkbtcancelar(ActionEvent event)
    {
         if (!pndados.isDisabled()) // encontra em estado de edição
        {
            estadoOriginal();
        } else
        {
            spnprincipal.setCenter(null);
          
        }
    }

    @FXML
    private void clkTxPesquisa(KeyEvent event)
    {
         carregaTabela("upper(uni_nome) like '%" + txpesquisa.getText().toUpperCase() + "%'");
    }

    @FXML
    private void clkBtPesquisar(ActionEvent event)
    {
         carregaTabela("upper(uni_nome) like '%" + txpesquisa.getText().toUpperCase() + "%'");
    }

    @FXML
    private void clkTabela(MouseEvent event)
    {
        if (event.getClickCount() == 2 && tabela.getSelectionModel().getSelectedIndex() >= 0)
        {
            pndados.setDisable(true);
            btalterar.setDisable(false);
            btnovo.setDisable(true);
            btapagar.setDisable(false);

            txcod.setText("" + tabela.getSelectionModel().getSelectedItem().getCod());
            txnome.setText(tabela.getSelectionModel().getSelectedItem().getNome());
        }
    }

}

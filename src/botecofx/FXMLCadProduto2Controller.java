/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botecofx;

import static botecofx.FXMLPrincipalController.spnprincipal;
import db.dal.DALCategoria;
import db.dal.DALProduto;
import db.dal.DALUnidade;
import db.entidades.Categoria;
import db.entidades.Produto;
import db.entidades.Unidade;
import util.ConsultaAPI;
import util.MaskFieldUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class FXMLCadProduto2Controller implements Initializable
{

    @FXML
    private AnchorPane pndados;
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
    private JFXTextField txdescr;
    @FXML
    private JFXTextField txpreco;
    @FXML
    private JFXComboBox<Unidade> cbunidade;
    @FXML
    private JFXComboBox<Categoria> cbcategoria;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private JFXTextField txpesquisa;
    @FXML
    private JFXButton btpesquisar;
    @FXML
    private TableView<Produto> tabela;
    @FXML
    private TableColumn<Produto, String> colcod;
    @FXML
    private TableColumn<Produto, String> colnome;
    @FXML
    private TableColumn<Produto, String> colpreco;
    @FXML
    private SplitPane painel;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // transição para abrir o painel
        fadeout();
        //preparar as colunas
        colcod.setCellValueFactory(new PropertyValueFactory("cod"));
        colnome.setCellValueFactory(new PropertyValueFactory("nome"));
        colpreco.setCellValueFactory(new PropertyValueFactory("preco"));
        MaskFieldUtil.monetaryField(txpreco);
        estadoOriginal();
    }

    private void fadeout()
    {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), painel);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    private void Original()
    {
        pnpesquisa.setDisable(true);

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
        DALProduto dal = new DALProduto();
        List<Produto> res = dal.get(filtro);
        ObservableList<Produto> modelo;
        modelo = FXCollections.observableArrayList(res);
        tabela.setItems(modelo);
        List<Categoria> categorias = new DALCategoria().get("");
        cbcategoria.setItems(FXCollections.observableArrayList(categorias));
        cbunidade.setItems(FXCollections.observableArrayList(new DALUnidade().get("")));

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
            Produto p = (Produto) tabela.getSelectionModel().getSelectedItem();
            txcod.setText("" + p.getCod());
            txnome.setText(p.getNome());
            txdescr.setText(p.getDesc());
            txpreco.setText(String.format("%10.2f", p.getPreco()));
            estadoEdicao();
            cbcategoria.getSelectionModel().select(0);// gambis
            cbunidade.getSelectionModel().select(0);// gambis
            cbcategoria.getSelectionModel().select(p.getCodc());
            cbunidade.getSelectionModel().select(p.getCodu());
        }

    }

    @FXML
    private void clkBtApagar(ActionEvent event)
    {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Confirma a exclusão?");
        if (a.showAndWait().get() == ButtonType.OK)
        {
            DALProduto dal = new DALProduto();
            Produto p;
            p = tabela.getSelectionModel().getSelectedItem();
            dal.apagar(p);
            carregaTabela("");
        }
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event)
    {
        int cod;
        try
        {
            cod = Integer.parseInt(txcod.getText());
        } catch (Exception e)
        {
            cod = 0;
        }
        Produto p = new Produto(cod, cbcategoria.getValue(), cbunidade.getValue(),
                txnome.getText(),
                Double.parseDouble(txpreco.getText().replace(".", "").replace(",", ".")),
                txdescr.getText());
        DALProduto dal = new DALProduto();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        if (p.getCod() == 0) // novo cadastro
        {
            if (!dal.gravar(p))
            {
                a.setContentText("Problemas ao Gravar");
            } else
            {

            }
        } else //alteração de cadastro
        if (!dal.alterar(p))
        {
            a.setContentText("Problemas ao Alterar");
        } else
        {

        }
        a.showAndWait();
        estadoOriginal();
    }

    @FXML
    private void clkbtcancelar(ActionEvent event)
    {
        if (!pndados.isDisabled()) // encontra em estado de edição
        {
            estadoOriginal();
        } else
        {
            FXMLPrincipalController.spnprincipal.setCenter(null);
            FXMLPrincipalController.efeito(false);
        }
        //ou TelaMenuController.painel.getChildren().clear();

    }

    @FXML
    private void clkTxPesquisa(KeyEvent event)
    {
        carregaTabela("upper(prod_nome) like '%" + txpesquisa.getText().toUpperCase() + "%'");
    }

    @FXML
    private void clkBtPesquisar(ActionEvent event)
    {
        carregaTabela("upper(prod_nome) like '%" + txpesquisa.getText().toUpperCase() + "%'");
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
            txdescr.setText(tabela.getSelectionModel().getSelectedItem().getDesc());
            txpreco.setText("" + tabela.getSelectionModel().getSelectedItem().getPreco());

            //FAZER COMBOBOX (GAMBIS COPIADA DO PROFESSOR)
            cbcategoria.getSelectionModel().select(0);// gambis
            cbunidade.getSelectionModel().select(0);// gambis
            cbcategoria.getSelectionModel().select(tabela.getSelectionModel().getSelectedItem().getCodc());
            cbunidade.getSelectionModel().select(tabela.getSelectionModel().getSelectedItem().getCodu());

        }

    }

}

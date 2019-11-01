/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botecofx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.dal.DALProduto;
import db.entidades.Comanda;
import db.entidades.Produto;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.MaskFieldUtil;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class FXMLInserirProdutoController implements Initializable
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
    private JFXTextField txproduto;
    @FXML
    private JFXTextField txquantidade;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private JFXTextField txpesquisa;
    @FXML
    private JFXButton btpesquisar;
    @FXML
    private TableView<Produto> tabela;
    @FXML
    private TableColumn<Produto, Integer> colcod;
    @FXML
    private TableColumn<Produto, String> colnome;
    @FXML
    private TableColumn<Produto, Double> colpreco;

    private Comanda c = new Comanda();
    private Produto p;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
       colnome.setCellValueFactory(new PropertyValueFactory("nome"));
       colpreco.setCellValueFactory(new PropertyValueFactory("preco"));
       colcod.setCellValueFactory(new PropertyValueFactory("cod"));
       
        MaskFieldUtil.numericField(txquantidade);
        carregaTabela("");
    }    

    @FXML
    private void clkBtConfirmar(ActionEvent event)
    {
        if(!txproduto.getText().isEmpty())
        {
            c.addProduto(p, Integer.parseInt(txquantidade.getText()), p.getPreco());
            ((Stage)btconfirmar.getScene().getWindow()).close();
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Nenhum produto selecionado");
            a.showAndWait();
        } 
    }

    @FXML
    private void clkbtcancelar(ActionEvent event)
    {
        p = null;
        ((Stage) btcancelar.getScene().getWindow()).close();
    }

    @FXML
    private void clkTxPesquisa(KeyEvent event)
    {
        carregaTabela("UPPER(prod_nome) like '%" + txpesquisa.getText().toUpperCase() + "%'");
    }
    private void carregaTabela(String filtro)
    {
        DALProduto dal = new DALProduto();
        List<Produto> l = dal.get(filtro);
        ObservableList<Produto> ob = FXCollections.observableList(l);
        tabela.setItems(ob);
    }
    @FXML
    private void clkBtPesquisar(ActionEvent event)
    {
        carregaTabela("UPPER(prod_nome) like '%" + txpesquisa.getText().toUpperCase() + "%'");
    }

    @FXML
    private void clkTabela(MouseEvent event)
    {
        if(tabela.getSelectionModel().getSelectedIndex() >= 0)
        {
            btconfirmar.setDisable(false);
            txproduto.setDisable(true);
            p = (Produto) tabela.getSelectionModel().getSelectedItem();
            txproduto.setText(p.getNome());
        }
    }
    public Comanda.Item getProduto()
    {
        return c.getItens().get(0);
    }
}

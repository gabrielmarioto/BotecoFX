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
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbarLayout;
import com.jfoenix.controls.JFXTextField;
import db.dal.DALComanda;
import db.dal.DALGarcon;
import db.entidades.Comanda;
import db.entidades.Garcon;
import db.util.Banco;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
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
 * @author Silvio
 */
public class FXMLComandaPopUpController implements Initializable
{
    private Label label;
    @FXML
    private SplitPane painel;
    @FXML
    private JFXButton btalterar;
    @FXML
    private JFXButton btfechar;
    @FXML
    private JFXButton btconfirmar;
    @FXML
    private JFXButton btcancelar;
    @FXML
    private JFXTextField txmesa;
    @FXML
    private JFXTextField txnome;
    @FXML
    private JFXTextField txdescricao;
    @FXML
    private JFXTextField txvalor;
    @FXML
    private JFXComboBox<Garcon> cbbGarcom;
    @FXML
    private JFXDatePicker dtp_Data;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private TableView<Comanda.Item> tbvItems;
    @FXML
    private TableColumn<Comanda.Item, String> colnome;
    @FXML
    private TableColumn<Comanda.Item, Integer> colquantidade;
    @FXML
    private TableColumn<Comanda.Item, Double> colpreco;
    @FXML
    private TableView<Comanda.Pagamento> tbvPagamentos;
    @FXML
    private TableColumn<Comanda.Pagamento, String> coltipo;
    @FXML
    private TableColumn<Comanda.Pagamento, Double> colvalor;
    @FXML
    private AnchorPane pnDados2;
    @FXML
    private AnchorPane pnDados1;
    private Comanda c;
    private double valor;
    @FXML
    private JFXButton btnRemoverItem;
    @FXML
    private JFXButton btnRemoverPag;
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        colnome.setCellValueFactory(new PropertyValueFactory("p"));
        colpreco.setCellValueFactory(new PropertyValueFactory("valor"));
        colquantidade.setCellValueFactory(new PropertyValueFactory("quant"));
        
        coltipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        colvalor.setCellValueFactory(new PropertyValueFactory("valor"));
        
        MaskFieldUtil.monetaryField(txvalor);
       // carregaDados();
        estadoOriginal();
    }

    @FXML
    private void clkbtalterar(ActionEvent event)
    {
        estadoEdicao();
    }

    @FXML
    private void clkBtFechar(ActionEvent event) throws IOException
    {
        if (valor == 0) {
            c.setStatus('F');
            DALComanda dal = new DALComanda();
            if (dal.alterar(c)) {
                String sql = "select * from comanda c join item i on i.com_id = c.com_id join pagamento p on p.com_id = c.com_id join produto pr on pr.prod_id = i.prod_id join tipopgto tp on p.tpg_id = tp.tpg_id where c.com_id = " + c.getCod();
                //FXMLPrincipalController.gerarRelatorioIntegrado(sql, "rel/nota_fiscal.jasper", null, null);
                //clkBtnCancelar(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Erro ao fechar comanda");
                a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Ainda há valor a ser quitado");
            a.showAndWait();
        }
       
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event)
    {
        if(isOk())
        {
            DALComanda dal = new DALComanda();
            c = new Comanda(c.getCod(),cbbGarcom.getValue(), Integer.parseInt(txmesa.getText()), txnome.getText(), dtp_Data.getValue(), txdescricao.getText(), 0, 'A');
            if(dal.alterar(c))
            {
                snackBar("Comanda alterada com sucesso");
                estadoOriginal();
            }
            else
            {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Erro ao alterar comanda. " + Banco.getCon().getMensagemErro());
                a.showAndWait();
            } 
        }
    }
    private void snackBar(String texto)
    {
      JFXSnackbar snacbar = new JFXSnackbar(pnDados2);
        JFXSnackbarLayout layout = new JFXSnackbarLayout(texto);
        layout.setStyle("-fx-backgroundcolor:#FFFFF");
        snacbar.fireEvent(new JFXSnackbar.SnackbarEvent(layout));
    }
    @FXML
    private void clkbtcancelarX(ActionEvent event) throws IOException
    {
        if(!pnDados1.isDisable())
            estadoOriginal();
        else
        {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLPainelComanda.fxml"));
            spnprincipal.setCenter(root);
        }
    }

    @FXML
    private void ClkBtInserirItem(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLInsereProduto.fxml"));
        Parent root = (Parent) loader.load();
        FXMLInserirProdutoController p = loader.getController();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
        Comanda.Item ite = p.getProduto();
        c.addProduto(ite.getP(), ite.getQuant(), ite.getValor());
        alteraValor();
        carregaTabela();
    }

    @FXML
    private void ClkBtRemoverItem(ActionEvent event)
    {
    }


    @FXML
    private void clkBtInserirPag(ActionEvent event)
    {
    }

    @FXML
    private void clkBtRemoverPag(ActionEvent event)
    {
    }
    
    public Comanda getComanda()
    {
        return c;
    }
    
    public void setComanda(Comanda c)
    {
        this.c = c;
        carregaTabela();
        carregaDados();
        estadoOriginal();
    }
    private void carregaTabela()
    {
        ObservableList<Comanda.Item> obItem = FXCollections.observableList(c.getItens());
        tbvItems.setItems(obItem);
        ObservableList<Comanda.Pagamento> obPagamento = FXCollections.observableList(c.getPagamentos());
        tbvPagamentos.setItems(obPagamento);
    }
    
   private void estadoOriginal()
    {
        btnRemoverItem.setDisable(true);
        btnRemoverPag.setDisable(true);
        pnDados2.setDisable(true);
        pnDados1.setDisable(true);
        btconfirmar.setDisable(true);
        btalterar.setDisable(false);
        btfechar.setDisable(false);
        btfechar.setDisable(false);
    }
    
    private void estadoEdicao()
    {
        btnRemoverItem.setDisable(true);
        btnRemoverPag.setDisable(true);
        pnDados2.setDisable(false);
        pnDados1.setDisable(false);
        btconfirmar.setDisable(false);
        btalterar.setDisable(true);
        btfechar.setDisable(false);
    }
    
    private void carregaDados()
    {
        txdescricao.setText(c.getDesc());
        txmesa.setText("" + c.getNum());
        txnome.setText(c.getNome());
        DALGarcon dal = new DALGarcon();
        ObservableList<Garcon> ob = FXCollections.observableList(dal.get(""));
        cbbGarcom.getSelectionModel().select(0);// gambis        
        cbbGarcom.getSelectionModel().select(c.getGar());
        dtp_Data.setValue(c.getData());
        alteraValor();
    }
    
    private void alteraValor()
    {
        valor = 0;
        double val = 0;
        for(Comanda.Item ci : c.getItens())
            valor += ci.getValor() * ci.getQuant();
        c.setValor(valor);
        for(Comanda.Pagamento p : c.getPagamentos())
            val += p.getValor();
        
        valor = valor - val;
        txvalor.setText(String.format("%10.2f", valor));
    }
    private boolean isOk()
    {
        ObservableList<Node> componentes = pnDados2.getChildren();
        for (Node n : componentes) {
            if (n instanceof TextInputControl &&  ((TextInputControl) n).getText().isEmpty())
            {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("O campo " + n.getId().replace("tb", "") + " não foi preenchido");
                if(!n.getId().equals("tbCodigo"))
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

    @FXML
    private void clkTabelaItens(MouseEvent event)
    {
        if(tbvItems.getSelectionModel().getSelectedItem() != null)
        {
            btnRemoverItem.setDisable(false);
        }
    }

    @FXML
    private void clkTabelaPagamentos(MouseEvent event)
    {
        if(tbvPagamentos.getSelectionModel().getSelectedItem() != null)
        {
            btnRemoverPag.setDisable(false);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botecofx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.dal.DALCategoria;
import db.dal.DALGarcon;
import db.dal.DALProduto;
import db.dal.DALUnidade;
import db.entidades.Categoria;
import db.entidades.Garcon;
import db.entidades.Produto;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import util.MaskFieldUtil;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class FXMLCadGarcomController implements Initializable {

    @FXML
    private SplitPane painel;
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
    private AnchorPane pndados;
    @FXML
    private JFXTextField txcod;
    @FXML
    private JFXTextField txnome;
    @FXML
    private JFXTextField txcpf;
    @FXML
    private JFXTextField txcep;
    @FXML
    private JFXTextField txendereco;
    @FXML
    private JFXTextField txcidade;
    @FXML
    private JFXTextField txuf;
    @FXML
    private JFXTextField txfone;
    @FXML
    private ImageView imgview_foto;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private JFXTextField txpesquisa;
    @FXML
    private JFXButton btpesquisar;
    @FXML
    private TableView<Garcon> tabela;
    @FXML
    private TableColumn<Garcon, Integer> colcod;
    @FXML
    private TableColumn<Garcon, String> colnome;
    @FXML
    private TableColumn<Garcon, String> colcpf;
    @FXML
    private TableColumn<Garcon, String> colcep;
    @FXML
    private TableColumn<Garcon, String> colendereco;
    @FXML
    private TableColumn<Garcon, String> colcidade;
    @FXML
    private TableColumn<Garcon, String> coluf;
    @FXML
    private TableColumn<Garcon, String> colfone;
    @FXML
    private JFXButton btnFoto;

    static public File arq = null;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
         fadeout();
         colcod.setCellValueFactory(new PropertyValueFactory("cod"));
         colnome.setCellValueFactory(new PropertyValueFactory("nome"));
         colcpf.setCellValueFactory(new PropertyValueFactory("cpf"));
         colcep.setCellValueFactory(new PropertyValueFactory("cep"));
         colendereco.setCellValueFactory(new PropertyValueFactory("endereco"));
         colcidade.setCellValueFactory(new PropertyValueFactory("cidade"));
         coluf.setCellValueFactory(new PropertyValueFactory("uf"));
         colfone.setCellValueFactory(new PropertyValueFactory("fone"));
         MaskFieldUtil.cepField(txcep);
         MaskFieldUtil.foneField(txfone);
         MaskFieldUtil.cpfField(txcpf);
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
        for (Node n : componentes) {
            if (n instanceof TextInputControl) // textfield, textarea e htmleditor
            {
                ((TextInputControl) n).setText("");
            }
            if (n instanceof ComboBox) {
                ((ComboBox) n).getItems().clear();
            }
        }
        carregaTabela("");
    }

    private void estadoEdicao() {     // carregar os componentes da tela (listbox, combobox, ...)
        // p.e. : carregaEstados();
        pnpesquisa.setDisable(true);
        pndados.setDisable(false);
        btconfirmar.setDisable(false);
        btapagar.setDisable(true);
        btalterar.setDisable(true);
        txnome.requestFocus();
        txcep.setDisable(true);
        txcidade.setDisable(true);
        txcod.setDisable(true);
        txcpf.setDisable(true);
        txendereco.setDisable(true);
        txfone.setDisable(true);
        txnome.setDisable(true);
        txuf.setDisable(true);
    }

    private void carregaTabela(String filtro) 
    {
        DALGarcon dal = new DALGarcon();
        List<Garcon> res = dal.get(filtro);
        ObservableList<Garcon> modelo;
        modelo = FXCollections.observableArrayList(res);
        tabela.setItems(modelo);
    }
    @FXML
    private void clkBtNovo(ActionEvent event) {
    }

    @FXML
    private void clkbtalterar(ActionEvent event) {
    }

    @FXML
    private void clkBtApagar(ActionEvent event) {
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event) {
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
        if (tabela.getSelectionModel().getSelectedIndex() >= 0)
        {
            
            btalterar.setDisable(false);
            btapagar.setDisable(false);
            pndados.setDisable((false));
            
            txcod.setText(""+tabela.getSelectionModel().getSelectedItem().getCod());
            txnome.setText(tabela.getSelectionModel().getSelectedItem().getNome());
            txcep.setText(tabela.getSelectionModel().getSelectedItem().getCep());
            txcidade.setText(""+tabela.getSelectionModel().getSelectedItem().getCidade());
            txcpf.setText(""+tabela.getSelectionModel().getSelectedItem().getCpf());
            txendereco.setText(""+tabela.getSelectionModel().getSelectedItem().getEnderco());
            txfone.setText(""+tabela.getSelectionModel().getSelectedItem().getFone());
            txuf.setText(""+tabela.getSelectionModel().getSelectedItem().getUf());
            

        }
    }

    @FXML
    private void clkCarregarImagem(ActionEvent event) 
    {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JPEG & JPG images ", "*.jpeg","*.jpg");
        fc.getExtensionFilters().add(extFilter);
        fc.setSelectedExtensionFilter(extFilter);
        arq = fc.showOpenDialog(null);
        Image img;
        if (arq != null) 
        {
            img = new Image(arq.toURI().toString());
            imgview_foto.setImage(img);
        }
    }
    
    
}

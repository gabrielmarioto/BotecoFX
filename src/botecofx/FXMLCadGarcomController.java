/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botecofx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.dal.DALGarcon;
import db.entidades.Garcon;
import db.util.Banco;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.json.JSONObject;
import util.ConsultaAPI;
import util.MaskFieldUtil;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class FXMLCadGarcomController implements Initializable
{

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
     *
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
        colendereco.setCellValueFactory(new PropertyValueFactory("enderco"));
        colcidade.setCellValueFactory(new PropertyValueFactory("cidade"));
        coluf.setCellValueFactory(new PropertyValueFactory("uf"));
        colfone.setCellValueFactory(new PropertyValueFactory("fone"));
        MaskFieldUtil.cepField(txcep);
        MaskFieldUtil.foneField(txfone);
        MaskFieldUtil.cpfField(txcpf);
        carregaTabela("");
        estadoOriginal();
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
        imgview_foto.setImage(null);
        pnpesquisa.setDisable(false);
        pndados.setDisable(true);
        btconfirmar.setDisable(true);
        btcancelar.setDisable(false);
        btapagar.setDisable(true);
        btalterar.setDisable(true);
        btnovo.setDisable(false);
        txcep.setDisable(true);
        txcidade.setDisable(true);
        txcod.setDisable(true);
        txcpf.setDisable(true);
        txendereco.setDisable(true);
        txfone.setDisable(true);
        txnome.setDisable(true);
        txuf.setDisable(true);

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
        txcep.setDisable(false);
        txcidade.setDisable(false);
        txcod.setDisable(false);
        txcpf.setDisable(false);
        txendereco.setDisable(false);
        txfone.setDisable(false);
        txnome.setDisable(false);
        txuf.setDisable(false);
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
    private void clkBtNovo(ActionEvent event)
    {
        estadoEdicao();
    }

    @FXML
    private void clkbtalterar(ActionEvent event) throws IOException
    {
        DALGarcon dal = new DALGarcon();
        if (tabela.getSelectionModel().getSelectedItem() != null)
        {
            Garcon g = (Garcon) tabela.getSelectionModel().getSelectedItem();
            InputStream img = dal.getFoto(g);
            if (img != null)
            {
                BufferedImage bimg = ImageIO.read(img);
                SwingFXUtils.toFXImage(bimg, null);
                imgview_foto.setImage(SwingFXUtils.toFXImage(bimg, null));
            }
            txcod.setText("" + g.getCod());
            txnome.setText(g.getNome());
            txcep.setText(g.getCep());
            txcidade.setText(g.getCidade());
            txcpf.setText(g.getCpf());
            txendereco.setText(g.getEnderco());
            txfone.setText(g.getFone());

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
            DALGarcon dal = new DALGarcon();
            Garcon g;
            g = tabela.getSelectionModel().getSelectedItem();
            dal.apagar(g);
            carregaTabela("");
            estadoOriginal();
            a.setContentText("Exclusao com sucesso!");
        }
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event) throws IOException
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
            if (txcpf.getText().length() > 0)
            {
                if (txcep.getText().length() > 0)
                {
                    if (txendereco.getText().length() > 0)
                    {
                        if (txcidade.getText().length() > 0)
                        {
                            if (txuf.getText().length() > 0)
                            {
                                if (txfone.getText().length() > 0)
                                {
                                    Garcon g = new Garcon(cod, txnome.getText(), txcpf.getText(), txcep.getText(), txendereco.getText(), txcidade.getText(), txuf.getText(), txfone.getText());
                                    DALGarcon dal = new DALGarcon();
                                    
                                    if (g.getCod() == 0) // SIGNIFICA NOVO CADASTRO
                                    {
                                        if (dal.gravar(g))
                                        {
                                            if (imgview_foto.getImage() != null)
                                            {
                                                BufferedImage bimg = SwingFXUtils.fromFXImage(imgview_foto.getImage(), null);
                                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                                byte[] imageInByte;
                                                ImageIO.write(bimg, "jpg", baos);
                                                baos.flush();
                                                imageInByte = baos.toByteArray();
                                                baos.close();
                                                g.setCod(Banco.getCon().getMaxPK("garcon", "gar_id")); // PEGAR O ID PARA NAO DAR ERRO
                                                InputStream in = new ByteArrayInputStream(imageInByte);
                                                if (!dal.gravarFoto(g, in, baos.toByteArray().length))
                                                {
                                                    a.setContentText("Garçon gravado sem foto");
                                                    a.showAndWait();
                                                }
                                            } else
                                            {
                                                a.setContentText("Garçon gravado sem foto");
                                                a.showAndWait();
                                            }
                                        } else
                                        {
                                            a.setContentText("Problemas ao gravar!");
                                            a.showAndWait();
                                        }
                                    } else // SIGNIFICA QUE CODIGO NÃO É NOVO CADASTRO
                                    {
                                        if (dal.alterar(g))
                                        {
                                            if (imgview_foto.getImage() != null)
                                            {
                                                BufferedImage bimg = SwingFXUtils.fromFXImage(imgview_foto.getImage(), null);
                                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                                byte[] imageInByte;
                                                ImageIO.write(bimg, "jpg", baos);
                                                baos.flush();
                                                imageInByte = baos.toByteArray();
                                                baos.close();

                                                InputStream in = new ByteArrayInputStream(imageInByte);
                                                if (dal.gravarFoto(g, in, baos.toByteArray().length))
                                                {
                                                    a.setContentText("Garçon alterado com sucesso");
                                                } else
                                                {
                                                    a.setContentText("Garçon alterado sem foto");
                                                    a.showAndWait();
                                                }
                                            }
                                        } else
                                        {
                                            a.setContentText("Problemas ao alterar!");
                                            a.showAndWait();
                                        }
                                    }
                                    a.showAndWait();
                                    estadoOriginal();
                                }
                                else
                                {
                                    a.setContentText("Informe o telefone!");
                                }
                            }
                            else
                            {
                                a.setContentText("Informe o UF!");
                            }
                        }
                        else
                        {
                            a.setContentText("Informe a cidade!");
                        }
                    }
                    else
                    {
                        a.setContentText("Informe o endereço!");
                    }
                }
                else
                {
                    a.setContentText("Informe o cep!");
                }
            }
            else
            {
                a.setContentText("Informe o cpf!");
            }
        }
        else
        {
            a.setContentText("Informe o nome!");
        }
        a.showAndWait();
        carregaTabela("");
    }

    @FXML
    private void clkTxPesquisa(KeyEvent event)
    {
        carregaTabela("upper(gar_nome) like '%" + txpesquisa.getText().toUpperCase() + "%'");
    }

    @FXML
    private void clkBtPesquisar(ActionEvent event)
    {
        carregaTabela("upper(gar_nome) like '%" + txpesquisa.getText().toUpperCase() + "%'");
    }

    @FXML
    private void clkTabela(MouseEvent event) throws IOException
    {
        if (event.getClickCount() == 2 && tabela.getSelectionModel().getSelectedIndex() >= 0)
        {
            estadoEdicao();
            btalterar.setDisable(false);
            btapagar.setDisable(false);
            pndados.setDisable(true);
            btnovo.setDisable(true);
            btconfirmar.setDisable(true);
            txcod.setText("" + tabela.getSelectionModel().getSelectedItem().getCod());
            txnome.setText(tabela.getSelectionModel().getSelectedItem().getNome());
            txcep.setText(tabela.getSelectionModel().getSelectedItem().getCep());
            txcidade.setText(tabela.getSelectionModel().getSelectedItem().getCidade());
            txcpf.setText(tabela.getSelectionModel().getSelectedItem().getCpf());
            txendereco.setText(tabela.getSelectionModel().getSelectedItem().getEnderco());
            txfone.setText(tabela.getSelectionModel().getSelectedItem().getFone());
            txuf.setText(tabela.getSelectionModel().getSelectedItem().getUf());

            DALGarcon dal = new DALGarcon();
            Garcon g = (Garcon) tabela.getSelectionModel().getSelectedItem();
            InputStream img = dal.getFoto(g);
            if (img != null)
            {
                BufferedImage bimg = ImageIO.read(img);
                SwingFXUtils.toFXImage(bimg, null);
                imgview_foto.setImage(SwingFXUtils.toFXImage(bimg, null));
            }
        }

    }

    @FXML
    private void clkCarregarImagem(ActionEvent event)
    {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JPEG & JPG images ", "*.jpeg", "*.jpg");
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

    @FXML
    private void evtCep(KeyEvent event)
    {
        if (txcep.getText().length() >= 8)
        {
            Task task = new Task()
            {
                @Override
                protected Object call() throws Exception
                {
                    String sjson = ConsultaAPI.consultaCep(txcep.getText());
                    JSONObject obj = new JSONObject(sjson);
                    txcidade.setText(obj.getString("localidade"));
                    txuf.setText(obj.getString("uf"));
                    if (obj.getString("logradouro").length() >= 0)
                    {
                        txendereco.setText(obj.getString("logradouro"));
                    }
                    return null;
                }
            };
            new Thread(task).start();
        }
    }

    @FXML
    private void clkbtcancelarX(ActionEvent event)
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

}

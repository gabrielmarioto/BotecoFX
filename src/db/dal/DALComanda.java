package db.dal;

import db.util.Banco;
import db.entidades.Comanda;
import java.sql.SQLException;
import java.util.List;



public class DALComanda {
    
    public void gravar(Comanda c) throws SQLException
    {   
        boolean ok=true;
        try
        {
            Banco.getCon().getConnect().setAutoCommit(false);//COMMIT MANUAL
            //insert para tabela comanda
            //caso deu erro: ok=false;
            // recuperar o códido serial da comanda
            //laço para insert dos itens da comanda
            //caso deu erro: ok=false;
            //laço para insert dos pagamentos
            //caso deu erro: ok=false;
            //INSERIR COMANDA, PAGAMENTOS E ITEMS
        }
        catch(Exception e)
        {   ok=false; }
        if(ok)
          Banco.getCon().getConnect().commit();
        else
         Banco.getCon().getConnect().rollback();
    }   
    public void alterar(Comanda c)
    {   
//        boolean ok=true;
//        try
//        {
//            Banco.getCon().getConnect().setAutoCommit(false);//commit manual
//            //update para tabela comanda
//            //caso deu erro: ok=false;
//            // delete para os itens
//            //laço para insert dos itens da comanda
//            //caso deu erro: ok=false;
//            // delete para os pagamentos
//            //laço para insert dos pagamentos
//            //caso deu erro: ok=false;
//        }
//        catch(Exception e)
//        {   ok=false; }
//        if(ok)
//          Banco.getCon().getConnect().commit();
//        else
//         Banco.getCon().getConnect().rollback();
    }    
}

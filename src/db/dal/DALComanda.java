package db.dal;

import db.util.Banco;
import db.entidades.Comanda;
import db.entidades.Comanda.Item;
import db.entidades.Comanda.Pagamento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;



public class DALComanda {
    
    public boolean gravar(Comanda c)
    {
        boolean result = false;
        try
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            Banco.getCon().getConnect().setAutoCommit(false);
            
            String SQL = "INSERT INTO comanda (gar_id, com_numero, com_nome, com_data, com_desc, com_valor, com_status) VALUES (#1, #2, '#3', '#4', '#5', #6, '#7') ";
            SQL = SQL.replace("#1", "" + c.getGar().getCod());            
            SQL = SQL.replace("#2", "" + c.getNum());
            SQL = SQL.replace("#3", c.getNome());
            SQL = SQL.replace("#4", c.getData().toString());
            SQL = SQL.replace("#5", c.getDesc());
            SQL = SQL.replace("#6", "" + c.getValor());
            SQL = SQL.replace("#7", ""+c.getStatus());
            result = Banco.getCon().manipular(SQL);          
           
            if(result)
            {
                c.setCod(Banco.getCon().getMaxPK("comanda", "com_id"));

                //List <Comanda.Item> itens = c.getItens();

                for(Comanda.Item i: c.getItens())
                {
                    SQL = "INSERT INTO item (com_id, prod_id, it_quantidade) VALUES (#1, #2, #3)";
                    SQL = SQL.replace("#1", "" + c.getCod());
                    SQL = SQL.replace("#2", "" + i.getP().getCod());
                    SQL = SQL.replace("#3", "" + i.getQuant());
                    
                    result = result && Banco.getCon().manipular(SQL);
                }
                if(result)
                {
                    List <Comanda.Pagamento> pagamentos = c.getPagamentos();

                    for(Comanda.Pagamento p: pagamentos)
                    {
                        SQL = "INSERT INTO pagamento (com_id, pag_valor, tpg_id) VALUES (#1, #2, #3)";
                        SQL = SQL.replace("#1", "" + c.getCod());
                        SQL = SQL.replace("#2", "" + p.getValor());
                        SQL = SQL.replace("#3", "" + p.getTipo().getCod());

                        result = result && Banco.getCon().manipular(SQL);
                    }
                }
            }
        }
        catch(Exception e)
        {
            result = false;
        }
        
        try
        {
            if(result)
                Banco.getCon().getConnect().commit();
            else
                Banco.getCon().getConnect().rollback();
            
            Banco.getCon().getConnect().setAutoCommit(true);
        }
        catch(SQLException sql){}
        
        return result;
    }
    
    public boolean alterar(Comanda c)
    {
        boolean result = false;
        String SQL = "UPDATE comanda SET gar_id = #1, com_numero = #2, com_nome = '#3', com_data = '#4', com_desc = '#5', com_valor = #6, com_status = '#7' WHERE com_id = " + c.getCod();
        SQL = SQL.replace("#1", "" + c.getGar().getCod());
        SQL = SQL.replace("#2", "" + c.getNum());
        SQL = SQL.replace("#3", c.getNome());
        SQL = SQL.replace("#4", c.getData().toString());
        SQL = SQL.replace("#5", c.getDesc());
        SQL = SQL.replace("#6", "" + c.getValor());
        SQL = SQL.replace("#7", "" + c.getStatus());
        System.out.println("UPDATE "+SQL);
        try
        {
            Banco.getCon().getConnect().setAutoCommit(false);
        
              result = Banco.getCon().manipular(SQL);

            if(result)
            {
                SQL = "DELETE FROM item where com_id = " + c.getCod();
                Banco.getCon().manipular(SQL);
                List <Comanda.Item> itens = new ArrayList<>();
                System.out.println("DELETE "+SQL);

                for(Item i: c.getItens())
                {
                    SQL = "INSERT INTO item (com_id, prod_id, it_quantidade) VALUES (#1, #2, #3)";
                    SQL = SQL.replace("#1", "" + c.getCod());
                    SQL = SQL.replace("#2", "" + i.getP().getCod());
                    SQL = SQL.replace("#3", "" + i.getQuant());
                    System.out.println("INSERE "+SQL);
                    Banco.getCon().manipular(SQL);                    
                }

                if(result)
                {
                    SQL = "DELETE FROM pagamento where com_id = " + c.getCod();
                    Banco.getCon().manipular(SQL);
                    List <Comanda.Pagamento> pagamentos = c.getPagamentos();
                    System.out.println("DELETE PAGAMENTO"+SQL);
                    for(Pagamento p: pagamentos)
                    {
                        SQL = "INSERT INTO pagamento (com_id,pag_valor,tpg_id) VALUES (#1,"+p.getValor()+","+p.getTipo().getCod()+")";
                        SQL = SQL.replace("#1", "" + c.getCod());
             
                        System.out.println(SQL);
                        System.out.println("INSERER PGTO "+SQL);
                        Banco.getCon().manipular(SQL);
                    }
                }
            }
        }
        catch(SQLException sql)
        {
            result = false;
        }
        
        try
        {
            if(result)
                Banco.getCon().getConnect().commit();
            else
                Banco.getCon().getConnect().rollback();
            
            Banco.getCon().getConnect().setAutoCommit(true);
        }
        catch(Exception e){}
        
        return result;
    }
    
    public boolean deletar(Comanda c)
    {
        boolean result = false;
        
        try
        {
            Banco.getCon().getConnect().setAutoCommit(false);
        
            String SQL = "DELETE FROM pagamento where com_id = " + c.getCod();
            result = Banco.getCon().manipular(SQL); 

            SQL = "DELETE FROM item where com_id = " + c.getCod();
            result = result && Banco.getCon().manipular(SQL);  

            SQL = "DELETE FROM comanda where com_id = " + c.getCod();
            result = result && Banco.getCon().manipular(SQL);
            
        }
        catch(SQLException sql)
        {
            result = false;
        }
        
        try
        {
            if(result)
                Banco.getCon().getConnect().commit();
            else
                Banco.getCon().getConnect().rollback();
            
            Banco.getCon().getConnect().setAutoCommit(true);
        }
        catch(Exception e) { }

        return result;
    }
    
    public Comanda get(int cod)
    {
        String Sql = "SELECT * FROM comanda WHERE com_id = " + cod;
        Comanda c = null;
        DALGarcon g = new DALGarcon();
        DALProduto p = new DALProduto();
        DALTipoPgto tp = new DALTipoPgto();
        ResultSet rs = null;
        try
        {
            rs = Banco.getCon().consultar(Sql);
            if(rs.next())
            {
                //Le Dados da comanda
                c = new Comanda(rs.getInt("com_id"), g.get(rs.getInt("gar_id")), rs.getInt("com_numero"),
                        rs.getString("com_nome"), rs.getDate("com_data").toLocalDate(), rs.getString("com_desc"), 
                        rs.getDouble("com_valor"), rs.getString("com_status").charAt(0));
                
                //Le itens da comanda
                Sql = "SELECT * FROM item WHERE com_id = " + c.getCod();
                rs = Banco.getCon().consultar(Sql); 
                while(rs.next())
                    c.addProduto(p.get(rs.getInt("prod_id")) , rs.getInt("it_quantidade"));
                
                //Le pagamentos da comanda
                Sql = "SELECT * FROM pagamento WHERE com_id = " + c.getCod();
                rs = Banco.getCon().consultar(Sql); 
                while(rs.next())
                    c.addPagamento(rs.getDouble("pag_valor"), tp.get(rs.getInt("tpg_id")));
            }
        }
        catch(SQLException e) { }
        return c;
    }
    
    public List<Comanda> get(String filtro)
    {
        List <Comanda> c = new ArrayList<>();
        String Sql = "SELECT * FROM comanda";
        if(!filtro.isEmpty())
            Sql += " WHERE " + filtro;
        int i = 0;
        DALGarcon g = new DALGarcon();
        DALProduto p = new DALProduto();
        DALTipoPgto tp = new DALTipoPgto();
        ResultSet rs = null;
        ResultSet rs_ip = null;
        try
        {
            rs = Banco.getCon().consultar(Sql);
            while(rs.next())
            {
                //Le Dados da comanda
                c.add(new Comanda(rs.getInt("com_id"), g.get(rs.getInt("gar_id")), rs.getInt("com_numero"),
                        rs.getString("com_nome"), rs.getDate("com_data").toLocalDate(), rs.getString("com_desc"), 
                        rs.getDouble("com_valor"), rs.getString("com_status").charAt(0)));
                
                //Le itens da comanda
                Sql = "SELECT * FROM item WHERE com_id = " + c.get(i).getCod();
                rs_ip = Banco.getCon().consultar(Sql); 
                while(rs_ip.next())
                   c.get(i).addProduto(p.get(rs_ip.getInt("prod_id")) , rs_ip.getInt("it_quantidade"));
                
                //Le pagamentos da comanda
                Sql = "SELECT * FROM pagamento WHERE com_id = " + c.get(i).getCod();
                rs_ip = Banco.getCon().consultar(Sql); 
                while(rs_ip.next())
                    c.get(i).addPagamento(rs_ip.getInt("pag_id"),rs_ip.getDouble("pag_valor"), tp.get(rs_ip.getInt("tpg_id")));
                
                i++;
            }
        }
        catch(SQLException e) { }
        
        return c;
    }
}

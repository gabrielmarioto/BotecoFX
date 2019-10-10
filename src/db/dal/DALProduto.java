package db.dal;

import db.entidades.Produto;
import db.util.Banco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DALProduto {
    
    public boolean gravar(Produto p)
    {
        String sql = "insert into produto(cat_id,uni_id,prod_nome,prod_preco,prod_descr) values (#1,#2,'#3',#4,'#5')";
        sql = sql.replaceAll("#1", ""+p.getCodc().getCod());
        sql = sql.replaceAll("#2", ""+p.getCodu().getCod());
        sql = sql.replaceAll("#3", ""+p.getNome());
        sql = sql.replaceAll("#4", ""+p.getPreco());
        sql = sql.replaceAll("#5", ""+p.getDesc());
        return Banco.getCon().manipular(sql);
        
    }
    
    public boolean alterar(Produto p)
    {
        String sql = "update produto set cat_id=#1,uni_id=#2,prod_nome='#3',prod_preco=#4,prod_descr='#5' where prod_id="+p.getCod();
        sql = sql.replaceAll("#1", ""+p.getCodc().getCod());
        sql = sql.replaceAll("#2", ""+p.getCodu().getCod());
        sql = sql.replaceAll("#3", ""+p.getNome());
        sql = sql.replaceAll("#4", ""+p.getPreco());
        sql = sql.replaceAll("#5", ""+p.getDesc());
        return Banco.getCon().manipular(sql);
        
    }
    
    public boolean apagar(Produto p)
    {
        return Banco.getCon().manipular("delete from produto where prod_id="+p.getCod());
    }
    
    public Produto get(int cod)
    {
        Produto aux = null;
        ResultSet rs = Banco.getCon().consultar("select * from produto where prod_id="+cod);
        try 
        {
            if(rs.next())
            {
                aux = new Produto(rs.getInt("prod_id"),new DALCategoria().get(rs.getInt("cat_id")),new DALUnidade().get(rs.getInt("uni_id")),rs.getString("prod_nome"),rs.getDouble("prod_preco"),rs.getString("prod_descr"));
            }
        } 
        catch (SQLException ex) 
        {
            
        }
        return aux;
    }
    
    public List<Produto> get(String filtro)
    {
        String sql="select * from produto";
        if(!filtro.isEmpty())
            sql+=" where "+filtro;
        List <Produto> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        try 
        {
            while(rs.next())
            {
                aux.add(new Produto(rs.getInt("prod_id"),new DALCategoria().get(rs.getInt("cat_id")),new DALUnidade().get(rs.getInt("uni_id")),rs.getString("prod_nome"),rs.getDouble("prod_preco"),rs.getString("prod_descr")));
            }
        } 
        catch (SQLException ex) 
        {
            
        }
        
        return aux;
    }
    
}

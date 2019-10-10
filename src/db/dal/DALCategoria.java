package db.dal;

import db.util.Banco;
import db.entidades.Categoria;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DALCategoria {
    
    public boolean gravar(Categoria c)
    {
        String sql = "insert into categoria(cat_nome) values ('#1')";
        sql = sql.replaceAll("#1",c.getNome());
        return Banco.getCon().manipular(sql);
        
    }
    
    public boolean alterar(Categoria c)
    {
        String sql = "update categoria set cat_nome='#1'where cat_id="+c.getCod();
        sql = sql.replaceAll("#1",c.getNome());
        return Banco.getCon().manipular(sql);
        
    }
    
    public boolean apagar(Categoria c)
    {
        return Banco.getCon().manipular("delete from categoria where cat_id="+c.getCod());
    }
    
    public Categoria get(int cod)
    {
        Categoria aux = null;
        ResultSet rs = Banco.getCon().consultar("select * from categoria where cat_id="+cod);
        try 
        {
            if(rs.next())
            {
                aux = new Categoria(rs.getInt("cat_id"),rs.getString("cat_nome"));
            }
        } 
        catch (SQLException ex) 
        {
            
        }
        return aux;
    }
    
    public List<Categoria> get(String filtro)
    {
        String sql="select *from categoria";
        if(!filtro.isEmpty())
            sql+=" where "+filtro;
        List <Categoria> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        try 
        {
            while(rs.next())
            {
                aux.add(new Categoria(rs.getInt("cat_id"),rs.getString("cat_nome")));
            }
        } 
        catch (SQLException ex) 
        {
            
        }
        
        return aux;
    }
}

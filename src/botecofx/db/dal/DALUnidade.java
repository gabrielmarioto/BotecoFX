package botecofx.db.dal;

import botecofx.db.util.Banco;
import botecofx.db.entidades.Unidade;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DALUnidade {
    
    public boolean gravar(Unidade u)
    {
        String sql = "insert into unidade(uni_nome) values ('#1')";
        sql = sql.replaceAll("#1",""+u.getNome());
        return Banco.getCon().manipular(sql);
        
    }
    
    public boolean alterar(Unidade u)
    {
        String sql = "update unidade set uni_nome='#1'where uni_id="+u.getCod();
        sql = sql.replaceAll("#1",""+u.getNome());
        return Banco.getCon().manipular(sql);
        
    }
    
    public boolean apagar(Unidade u)
    {
        return Banco.getCon().manipular("delete from unidade where uni_id="+u.getCod());
    }
    
    public Unidade get(int cod)
    {
        Unidade aux = null;
        ResultSet rs = Banco.getCon().consultar("select * from unidade where uni_id="+cod);
        try 
        {
            if(rs.next())
            {
                aux = new Unidade(rs.getInt("uni_id"),rs.getString("uni_nome"));
            }
        } 
        catch (SQLException ex) 
        {
            
        }
        return aux;
    }
    
    public List<Unidade> get(String filtro)
    {
        String sql="select *from unidade";
        if(!filtro.isEmpty())
            sql+=" where "+filtro;
        List <Unidade> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        try 
        {
            while(rs.next())
            {
                aux.add(new Unidade(rs.getInt("uni_id"),rs.getString("uni_nome")));
            }
        } 
        catch (SQLException ex) 
        {
            
        }
        
        return aux;
    }
    
}

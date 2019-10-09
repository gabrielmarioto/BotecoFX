package botecofx.db.dal;

import botecofx.db.util.Banco;
import botecofx.db.entidades.TipoPgto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DALTipoPgto {
    
    public boolean gravar(TipoPgto t)
    {
        String sql = "insert into tipopgto(tpg_nome) values ('#1')";
        sql = sql.replaceAll("#1",t.getNome());
        return Banco.getCon().manipular(sql);
        
    }
    
    public boolean alterar(TipoPgto t)
    {
        String sql = "update tipopgto set tpg_nome='#1'where tpg_id="+t.getCod();
        sql = sql.replaceAll("#1",t.getNome());
        return Banco.getCon().manipular(sql);
        
    }
    
    public boolean apagar(TipoPgto t)
    {
        return Banco.getCon().manipular("delete from tipopgto where tpg_id="+t.getCod());
    }
    
    public TipoPgto get(int cod)
    {
        TipoPgto aux = null;
        ResultSet rs = Banco.getCon().consultar("select * from tipopgto where tpg_id="+cod);
        try 
        {
            if(rs.next())
            {
                aux = new TipoPgto(rs.getInt("tpg_id"),rs.getString("tpg_nome"));
            }
        } 
        catch (SQLException ex) 
        {
            
        }
        return aux;
    }
    
    public List<TipoPgto> get(String filtro)
    {
        String sql="select *from tipopgto";
        if(!filtro.isEmpty())
            sql+=" where "+filtro;
        List <TipoPgto> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        try 
        {
            while(rs.next())
            {
                aux.add(new TipoPgto(rs.getInt("tpg_id"),rs.getString("tpg_nome")));
            }
        } 
        catch (SQLException ex) 
        {
            
        }
        
        return aux;
    }
    
}

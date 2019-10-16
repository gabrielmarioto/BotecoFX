package db.dal;

import db.util.Banco;
import db.entidades.Comanda;
import db.entidades.Garcon;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DALGarcon
{

    public boolean gravar(Garcon g)
    {
        String sql = "insert into garcon(gar_nome,gar_cpf,gar_cep,gar_endereco,gar_cidade,gar_uf,gar_fone) values ('#1','#2','#3','#4','#5','#6','#7')";
        sql = sql.replaceAll("#1", "" + g.getNome());
        sql = sql.replaceAll("#2", "" + g.getCpf());
        sql = sql.replaceAll("#3", "" + g.getCep());
        sql = sql.replaceAll("#4", "" + g.getEnderco());
        sql = sql.replaceAll("#5", "" + g.getCidade());
        sql = sql.replaceAll("#6", "" + g.getUf());
        sql = sql.replaceAll("#7", "" + g.getFone());
        return Banco.getCon().manipular(sql);

    }

    public boolean alterar(Garcon g)
    {
        String sql = "update garcon set gar_nome='#1',gar_cpf='#2',gar_cep='#3',gar_endereco='#4',gar_cidade='#5',gar_uf='#6',gar_fone='#7' where gar_id=" + g.getCod();
        sql = sql.replaceAll("#1", "" + g.getNome());
        sql = sql.replaceAll("#2", "" + g.getCpf());
        sql = sql.replaceAll("#3", "" + g.getCep());
        sql = sql.replaceAll("#4", "" + g.getEnderco());
        sql = sql.replaceAll("#5", "" + g.getCidade());
        sql = sql.replaceAll("#6", "" + g.getUf());
        sql = sql.replaceAll("#7", "" + g.getFone());
        return Banco.getCon().manipular(sql);

    }

    public boolean apagar(Garcon g)
    {
        return Banco.getCon().manipular("delete from garcon where gar_id=" + g.getCod());
    }

    public Garcon get(int cod)
    {
        Garcon aux = null;
        ResultSet rs = Banco.getCon().consultar("select * from garcon where gar_id=" + cod);
        try
        {
            if (rs.next())
            {
                aux = new Garcon(rs.getInt("gar_id"), rs.getString("gar_nome"), rs.getString("gar_cpf"), rs.getString("gar_cep"), rs.getString("gar_endereco"), rs.getString("gar_cidade"), rs.getString("gar_uf"), rs.getString("gar_fone"));
            }
        } catch (SQLException ex)
        {

        }
        return aux;
    }

    public List<Garcon> get(String filtro)
    {
        String sql = "select *from garcon";
        if (!filtro.isEmpty())
        {
            sql += " where " + filtro;
        }
        List<Garcon> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while (rs.next())
            {
                aux.add(new Garcon(rs.getInt("gar_id"), rs.getString("gar_nome"), rs.getString("gar_cpf"), rs.getString("gar_cep"), rs.getString("gar_endereco"), rs.getString("gar_cidade"), rs.getString("gar_uf"), rs.getString("gar_fone")));
            }
        } catch (SQLException ex)
        {

        }

        return aux;
    }

    public boolean gravarFoto(Garcon g, InputStream pic, int length)
    {
        try
        {
            PreparedStatement ps = Banco.getCon().getConnect().prepareStatement("UPDATE garcon set gar_foto = ? where gar_id = " + g.getCod());
            ps.setBinaryStream(1, pic, length);
            ps.executeUpdate();
            ps.close();
            pic.close();
        } catch (Exception e)
        {
            return false;
        }

        return true;
    }

    public InputStream getFoto(Garcon g)
    {
        InputStream pic = null;
        try
        {
            PreparedStatement ps = Banco.getCon().getConnect().prepareStatement("SELECT gar_foto FROM garcon where gar_id = " + g.getCod());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                byte[] imgBytes = rs.getBytes("gar_foto");
                pic = new ByteArrayInputStream(imgBytes);
            }

            ps.close();
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }

        return pic;
    }
}

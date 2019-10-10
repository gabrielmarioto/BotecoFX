package db.util;

// design pattern Singleton
public class Banco 
{
    static private Conexao con = null;
    
    static public Conexao getCon()
    {
        return con;
    }

    private Banco() {} // construtor privado, ou seja, nao pode dar um new em banco, da uma segurança maior no projeto
    
    static public boolean conectar()
    {
        con = new Conexao(); 
        return con.conectar("jdbc:postgresql://localhost/","botecodb" ,"postgres", "postgres123");
            
    }
}

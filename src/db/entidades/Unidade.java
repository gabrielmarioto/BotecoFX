package db.entidades;

public class Unidade {
    private int cod;
    private String nome;

    public Unidade() {
        this(0,"");
    }

    public Unidade(int cod, String nome) {
        this.cod = cod;
        this.nome = nome;
    }

    public Unidade(String nome) {
        this.nome = nome;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
    
}

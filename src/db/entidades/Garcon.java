package db.entidades;

public class Garcon {
    
    private int cod;
    private String nome;
    private String cpf;
    private String cep;
    private String enderco;
    private String cidade;
    private String uf;
    private String fone;

    public Garcon() {
    }

    public Garcon(int cod, String nome, String cpf, String cep, String enderco, String cidade, String uf, String fone) {
        this.cod = cod;
        this.nome = nome;
        this.cpf = cpf;
        this.cep = cep;
        this.enderco = enderco;
        this.cidade = cidade;
        this.uf = uf;
        this.fone = fone;
    }

    public Garcon(String nome, String cpf, String cep, String enderco, String cidade, String uf, String fone) {
        this.nome = nome;
        this.cpf = cpf;
        this.cep = cep;
        this.enderco = enderco;
        this.cidade = cidade;
        this.uf = uf;
        this.fone = fone;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEnderco() {
        return enderco;
    }

    public void setEnderco(String enderco) {
        this.enderco = enderco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    @Override
    public String toString() {
        return nome;
    }

    
    
    
    
    
}

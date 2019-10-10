package db.entidades;

public class Produto {
    
    private int cod;
    private Categoria codc;
    private Unidade codu;
    private String nome;
    private double preco;
    private String desc;

    public Produto() {
        this(0,null,null,"",0,"");
    }

    public Produto(int cod, Categoria codc, Unidade codu, String nome, double preco, String desc) {
        this.cod = cod;
        this.codc = codc;
        this.codu = codu;
        this.nome = nome;
        this.preco = preco;
        this.desc = desc;
    }

    public Produto(Categoria codc, Unidade codu, String nome, double preco, String desc) {
        this.codc = codc;
        this.codu = codu;
        this.nome = nome;
        this.preco = preco;
        this.desc = desc;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Categoria getCodc() {
        return codc;
    }

    public void setCodc(Categoria codc) {
        this.codc = codc;
    }

    public Unidade getCodu() {
        return codu;
    }

    public void setCodu(Unidade codu) {
        this.codu = codu;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    
}

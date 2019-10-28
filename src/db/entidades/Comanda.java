package db.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Comanda 
{
   //inner class
    public class Item
    {
       private Produto p;
       private int quant;
       private double valor; //valor unitario;

        public Item(Produto p, int quant, double valor) {
            this.p = p;
            this.quant = quant;
            this.valor = valor;
        }

        public Produto getP() {
            return p;
        }

        public void setP(Produto p) {
            this.p = p;
        }

        public int getQuant() {
            return quant;
        }

        public void setQuant(int quant) {
            this.quant = quant;
        }

        public double getValor() {
            return valor;
        }

        public void setValor(double valor) {
            this.valor = valor;
        }
       
    }
    public class Pagamento
    {
        private double valor;
        private int cod;
        private TipoPgto tipo;

        public Pagamento(double valor, TipoPgto tipo) {
            this.valor = valor;
            this.tipo = tipo;
        }
         public Pagamento(int pag_id, double pag_valor, TipoPgto tipo) {
            this.cod= pag_id;
            this.valor = pag_valor;
            this.tipo = tipo;
        }
        public double getValor() {
            return valor;
        }

        public void setValor(double valor) {
            this.valor = valor;
        }

        public TipoPgto getTipo() {
            return tipo;
        }

        public void setTipo(TipoPgto tipo) {
            this.tipo = tipo;
        }
        
    }
    private int cod;
    private Garcon gar;
    private int num;
    private String nome;
    private LocalDate data;
    private String desc;
    private double valor;
    private char status;
    private List <Item> itens;
    private List <Pagamento> pagamentos;

    public List<Item> getItens() {
        return itens;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }
    
    

    public Comanda() {
        this(0,null,0,"",null,"",0,' ');
    }

    public Comanda(int cod, Garcon gar, int num, String nome, LocalDate data, String desc, double valor, char status) {
        this.cod = cod;
        this.gar = gar;
        this.num = num;
        this.nome = nome;
        this.data = data;
        this.desc = desc;
        this.valor = valor;
        this.status = status;
        itens = new ArrayList();
        pagamentos = new ArrayList();
        
    }

    public Comanda(Garcon gar, int num, String nome, LocalDate data, String desc, double valor, char status) {
        this(0, gar, num, nome, data, desc, valor, status);
    }
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Garcon getGar() {
        return gar;
    }

    public void setGar(Garcon gar) {
        this.gar = gar;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return nome;
    }
    public void addProduto(Produto p, int q)
    {
        itens.add(new Item(p, q, p.getPreco()));
    }
    public void addProduto(Produto prod, int quantidade, double valor) {
        itens.add(new Item(prod, quantidade, valor));
    }
    public void addPagamento(double valor, TipoPgto tp)
    {
        pagamentos.add(new Pagamento(valor, tp));
    }
    public void addPagamento(int id, double valor, TipoPgto tp) {
        pagamentos.add(new Pagamento(id, valor, tp));
    }
    
}

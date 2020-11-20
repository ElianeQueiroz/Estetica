package br.edu.up.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "agendamentos")
public class Agendamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAgendamento;
	private Date dataIni;
	private Date dataFim;
    @ManyToOne(optional=false)
    @JoinColumn(name = "idCliente")
	private Cliente cliente = new Cliente();
    @OneToMany(targetEntity=AgendamentoServico.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "idAgendamento", referencedColumnName = "idAgendamento", nullable = true)
    private List<AgendamentoServico> servicos = new ArrayList<>();
    @OneToMany(targetEntity=AgendamentoProduto.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "idAgendamento", referencedColumnName = "idAgendamento", nullable = true)
    private List<AgendamentoProduto> produtos = new ArrayList<>();
    private int situacao;
	
	public Agendamento() {
		super();
	}

	public int getIdAgendamento() {
		return idAgendamento;
	}

	public void setIdAgendamento(int idAgendamento) {
		this.idAgendamento = idAgendamento;
	}

	public Date getDataIni() {
		return dataIni;
	}

	public void setDataIni(Date dataIni) {
		this.dataIni = dataIni;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}

	public List<AgendamentoServico> getServicos() {
		return servicos;
	}

	public void setServicos(List<AgendamentoServico> servicos) {
		this.servicos = servicos;
	}

	public List<AgendamentoProduto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<AgendamentoProduto> produtos) {
		this.produtos = produtos;
	}
	
	@Override
	public String toString() {
		return "Código: " + idAgendamento + " Cliente: " + cliente.getNome() + " Data Hora Início: " + dataIni;
	}
	
	public int getProximaSeqServico() {
		int seq = 0;
		if ( ( this.servicos != null ) && (this.servicos.size() > 0 ) ) {
			seq = ( this.servicos.get(this.servicos.size()-1).getSeq() + 1 ); 
		} else {
            seq = 1;			
		}
  		
		return seq;
	}
	
	public int getProximaSeqProduto() {
		int seq = 0;
		if ( ( this.produtos != null ) && (this.produtos.size() > 0 ) ) {
			seq = ( this.produtos.get(this.produtos.size()-1).getSeq() + 1 );
		} else {
			seq = 1;			
		}
		
		return seq;
	}	
}

package br.com.CVCCorp.transferencias.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.CVCCorp.transferencias.entities.Transferencia;

public class TransferenciaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String originAccount;
	private String destinationAccount;
	private BigDecimal transferAmount;
	private Double rate;
	private LocalDate transferDate;
	private LocalDate schedulingDate;
	
	public TransferenciaDTO() {
		
	}

	public TransferenciaDTO(Long id, String originAccount, String destinationAccount, BigDecimal transferAmount,
			Double rate, LocalDate transferDate) {
		this.id = id;
		this.originAccount = originAccount;
		this.destinationAccount = destinationAccount;
		this.transferAmount = transferAmount;
		this.rate = rate;
		this.transferDate = transferDate;
	}
	
	public TransferenciaDTO(Transferencia entity) {
		id = entity.getId();
		originAccount = entity.getOriginAccount();
		destinationAccount = entity.getDestinationAccount();
		transferAmount = entity.getTransferAmount();
		rate = entity.getRate();
		transferDate = entity.getTransferDate();
		schedulingDate = entity.getSchedulingDate();				
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOriginAccount() {
		return originAccount;
	}

	public void setOriginAccount(String originAccount) {
		this.originAccount = originAccount;
	}

	public String getDestinationAccount() {
		return destinationAccount;
	}

	public void setDestinationAccount(String destinationAccount) {
		this.destinationAccount = destinationAccount;
	}

	public BigDecimal getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(BigDecimal transferAmount) {
		this.transferAmount = transferAmount;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public LocalDate getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(LocalDate transferDate) {
		this.transferDate = transferDate;
	}

	public LocalDate getSchedulingDate() {
		return schedulingDate;
	}
		
}

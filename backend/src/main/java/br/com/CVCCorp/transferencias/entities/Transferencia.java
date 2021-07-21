package br.com.CVCCorp.transferencias.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class Transferencia {

	private String originAccount;
	private String destinationAccount;
	private BigDecimal TransferAmount;
	private Double rate;
	private LocalDate transferDate;
	private LocalDate schedulingDate = LocalDate.now();

	public Transferencia(String originAccount, String destinationAccount, BigDecimal transferAmount, Double rate,
			LocalDate transferDate) {

		this.originAccount = originAccount;
		this.destinationAccount = destinationAccount;
		this.TransferAmount = transferAmount;
		this.rate = rate;
		this.transferDate = transferDate;
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
		return TransferAmount;
	}

	public void setTransferAmount(BigDecimal transferAmount) {
		TransferAmount = transferAmount;
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

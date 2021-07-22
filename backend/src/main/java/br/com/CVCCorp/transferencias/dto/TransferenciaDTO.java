package br.com.CVCCorp.transferencias.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.CVCCorp.transferencias.entities.Transferencia;

public class TransferenciaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "Conta de origem deve ser preenchida.")
	private String originAccount;
	@NotBlank(message = "Conta de destino deve ser preenchida.")
	private String destinationAccount;
	
	@NotNull(message = "Valor de transferência deve ser preenchido.")
	@DecimalMin(value = "1", message = "Valor da transferência precisa ser no mínimo R$ 1,00." )
	private BigDecimal transferAmount;
	private Double rate;
	@FutureOrPresent(message = "Data não pode ser anterior a hoje.")
	private LocalDate transferDate;
	private LocalDate schedulingDate;
	
	public TransferenciaDTO() {
		
	}

	public TransferenciaDTO(Long id, String originAccount, String destinationAccount, BigDecimal transferAmount,
			 LocalDate transferDate) {
		this.id = id;
		this.originAccount = originAccount;
		this.destinationAccount = destinationAccount;
		this.transferAmount = transferAmount;
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

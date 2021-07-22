package br.com.CVCCorp.transferencias.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.CVCCorp.transferencias.entities.Transferencia;

public class TransferenciaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "Conta de origem deve ser preenchida.")
	@Length(max = 6, message = "Conta não pode ter mais do que 6 caracteres.")
	private String originAccount;
	
	@NotBlank(message = "Conta de destino deve ser preenchida.")
	@Length(max = 6, message = "Conta não pode ter mais do que 6 caracteres.")
	private String destinationAccount;
	
	@NotNull(message = "Valor de transferência deve ser preenchido.")
	@DecimalMin(value = "1", message = "Valor da transferência precisa ser no mínimo R$ 1,00." )
	private Double transferAmount;
	
	private Double rate;
	
	@FutureOrPresent(message = "Data não pode ser anterior a hoje.")
	private LocalDate transferDate;
	
	private LocalDate schedulingDate;
	
	public TransferenciaDTO() {
		
	}

	public TransferenciaDTO(Long id, String originAccount, String destinationAccount, Double transferAmount,
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

	public Double getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(Double transferAmount) {
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

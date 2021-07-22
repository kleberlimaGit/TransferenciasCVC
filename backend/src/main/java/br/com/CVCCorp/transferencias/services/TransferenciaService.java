package br.com.CVCCorp.transferencias.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.CVCCorp.transferencias.dto.TransferenciaDTO;
import br.com.CVCCorp.transferencias.entities.Transferencia;
import br.com.CVCCorp.transferencias.repositories.TransferenciaRepository;
import br.com.CVCCorp.transferencias.services.exceptions.ResourceNotFoundException;
import br.com.CVCCorp.transferencias.services.exceptions.WrongArgumentException;

@Service
public class TransferenciaService {

	@Autowired
	private TransferenciaRepository repository;
	
	//Listar transferencias
	
	@Transactional(readOnly = true)
	public List<TransferenciaDTO> listTransfer(){
		List<Transferencia> transferencia = repository.findAll();
		List<TransferenciaDTO> list = transferencia.stream().map(transf -> new TransferenciaDTO(transf)).collect(Collectors.toList());
		return list;
	}
	
	// Fazer transferencia 
	@Transactional
	public TransferenciaDTO makeTransfer(TransferenciaDTO dto) {
		Transferencia transferencia = new Transferencia();
		copyToEntity(dto, transferencia);
		validateRate(dto,transferencia);
		
		transferencia = repository.save(transferencia);
		
		return new TransferenciaDTO(transferencia);			
	}
	
	@Transactional
	public TransferenciaDTO updateTransfer(Long id, TransferenciaDTO dto) {
		try {
			Transferencia transferencia = repository.getOne(id);
			copyToEntity(dto, transferencia);
			validateRate(dto,transferencia);
			transferencia = repository.save(transferencia);
			
			return new TransferenciaDTO(transferencia);	
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Transferencia não identificada " + id);
		}
	}
	
	@Transactional
	public void deleteTransfer(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Transferencia não identificada " + id);
		}
	}

	
	public void validateRate(TransferenciaDTO dto, Transferencia entity) {
		LocalDate today = LocalDate.now();
		LocalDate transferDate = dto.getTransferDate();
		long daysToTransfer = ChronoUnit.DAYS.between(today, transferDate);
		
		if(transferDate.isEqual(today)) {
			transferOnTheDay(dto,entity);
		}else if(daysToTransfer <= 10) {
			transferWithinTenDays(dto,entity,daysToTransfer);
		}else if(daysToTransfer > 10) {
			transferOverTenDays(dto,entity,daysToTransfer);
		}
	}
	
	
	// transferencia no dia da operação
	public void transferOnTheDay(TransferenciaDTO dto, Transferencia entity) {
		BigDecimal amount = dto.getTransferAmount();
		double tax = 3.0;
		double percentOnValue = 0.03;
		Double rateValue = amount.doubleValue()*percentOnValue + tax;
		
		entity.setRate(rateValue);
	}
	
	// transferencia com até 10 dias para a operação
	public void transferWithinTenDays(TransferenciaDTO dto, Transferencia entity, long numberOfDays) {
		double tax = 12.0*numberOfDays;
		double rateValue = tax;
	
		entity.setRate(rateValue);
	}
	// transferencia acima de 10 dias para a operação
	public void transferOverTenDays(TransferenciaDTO dto, Transferencia entity, long numberOfDays) {
		Double amount = dto.getTransferAmount().doubleValue();
		double businessValue = 100000.00;
		double rateValue = 0.0;
		
		
		if(numberOfDays > 10 && numberOfDays < 20) {
			rateValue = amount*0.08;
			entity.setRate(rateValue);
		}else if (numberOfDays <=30) {
			rateValue = amount*0.06;
			entity.setRate(rateValue);
		}else if(numberOfDays <= 40) {
			rateValue = amount*0.04;
			entity.setRate(rateValue);
		}else if(numberOfDays > 40 && amount.doubleValue() < businessValue) {
			throw new WrongArgumentException("Acima de 40 dias o valor da transferência não pode ser menor do que R$ 100.000,00 .");
		}else {
			rateValue = amount*0.02;
			entity.setRate(rateValue);
		}
			
	}
	
	// copiar dto para entitade
	public void copyToEntity(TransferenciaDTO dto, Transferencia entity ) {
		entity.setDestinationAccount(dto.getDestinationAccount());
		entity.setOriginAccount(dto.getOriginAccount());
		entity.setTransferDate(dto.getTransferDate());
		entity.setTransferAmount(dto.getTransferAmount());
	}
	
	
	
	
	
	
	
}

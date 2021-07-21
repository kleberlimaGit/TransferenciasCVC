package br.com.CVCCorp.transferencias.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.CVCCorp.transferencias.dto.TransferenciaDTO;
import br.com.CVCCorp.transferencias.entities.Transferencia;
import br.com.CVCCorp.transferencias.repositories.TransferenciaRepository;

@Service
public class TransferenciaService {

	@Autowired
	private TransferenciaRepository repository;
	
	//Listar Transferencias
	
	@Transactional(readOnly = true)
	public List<TransferenciaDTO> listTransfer(){
		List<Transferencia> transferencia = repository.findAll();
		List<TransferenciaDTO> list = transferencia.stream().map(transf -> new TransferenciaDTO(transf)).collect(Collectors.toList());
		return list;
	}
	
	@Transactional
	public TransferenciaDTO makeTransfer(TransferenciaDTO dto) {
		Transferencia transferencia = new Transferencia();
		
			
		
		
	}

	
	
}

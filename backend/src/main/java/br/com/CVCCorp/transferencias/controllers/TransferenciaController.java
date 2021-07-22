package br.com.CVCCorp.transferencias.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.CVCCorp.transferencias.dto.TransferenciaDTO;
import br.com.CVCCorp.transferencias.services.TransferenciaService;

@RestController
@RequestMapping("/transfer")
public class TransferenciaController {

	@Autowired
	private TransferenciaService service;

	@GetMapping
	public ResponseEntity<List<TransferenciaDTO>> getTransfers(TransferenciaDTO dto) {

		List<TransferenciaDTO> list = service.listTransfer();

		return ResponseEntity.ok().body(list);

	}

	@PostMapping
	public ResponseEntity<TransferenciaDTO> createTransfer(@Valid @RequestBody TransferenciaDTO dto) {
		dto = service.makeTransfer(dto);
		
		// este método irá responder a requisição com um status code 201 de objeto criado.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TransferenciaDTO> updateTransfer(@PathVariable Long id, @Valid @RequestBody TransferenciaDTO dto){
		dto = service.updateTransfer(id, dto);
		return ResponseEntity.ok().body(dto); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<TransferenciaDTO> deleteTransfer(@PathVariable Long id){
		service.deleteTransfer(id);
		
		 // retorna o status code 204 no Contente.
		return ResponseEntity.noContent().build(); 
	}
	
}

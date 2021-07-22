package br.com.CVCCorp.transferencias.tests.integration;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import br.com.CVCCorp.transferencias.dto.TransferenciaDTO;
import br.com.CVCCorp.transferencias.entities.Transferencia;
import br.com.CVCCorp.transferencias.services.TransferenciaService;
import br.com.CVCCorp.transferencias.services.exceptions.WrongArgumentException;
import br.com.CVCCorp.transferencias.tests.factory.TransferenciaFactory;

@SpringBootTest
@Transactional
public class TransferenciaServiceIT {

	@Autowired
	private TransferenciaService service;
	
	
	
	private Transferencia transferenciaNoDia;
	private Transferencia transferenciaComAte10Dias;
	private Transferencia transferenciaAte20Dias;
	private Transferencia transferenciaAte30Dias;
	private Transferencia transferenciaAte40Dias;
	private Transferencia transferenciaAcima40DiasEValorAbaixoDeCemMil;
	private Transferencia transferenciaAcima40DiasEValorAcimaDeCemMil;
	
	@BeforeEach
	void setUp() throws Exception {
		transferenciaNoDia = TransferenciaFactory.createTransfer(500.00, LocalDate.now());
		transferenciaComAte10Dias = TransferenciaFactory.createTransfer(500.00, LocalDate.now().plusDays(10));
		transferenciaAte20Dias = TransferenciaFactory.createTransfer(500.00, LocalDate.now().plusDays(20));
		transferenciaAte30Dias = TransferenciaFactory.createTransfer(500.00, LocalDate.now().plusDays(30));
		transferenciaAte40Dias = TransferenciaFactory.createTransfer(500.00, LocalDate.now().plusDays(40));
		transferenciaAcima40DiasEValorAbaixoDeCemMil = TransferenciaFactory.createTransfer(500.00, LocalDate.now().plusDays(41));
		transferenciaAcima40DiasEValorAcimaDeCemMil = TransferenciaFactory.createTransfer(100001.00, LocalDate.now().plusDays(41));		
	}
	
	
	@Test
	@DisplayName("Teste que lança uma exceção para caso não haja uma taxa para ser aplicada")
	public void TransferShouldThrowsWrongArgumentExceptionWhenTransferValueLessThanOrEqualToAHundredThousand() {
		Assertions.assertThrows(WrongArgumentException.class, () -> {
			TransferenciaDTO dto = new TransferenciaDTO(transferenciaAcima40DiasEValorAbaixoDeCemMil);
			 service.makeTransfer(dto);
		});
	}
	
	@Test
	@DisplayName("Teste calcula taxa para transferências quando o agendamento é hoje.")
	public void feeShouldBeThreePlusThreePercentOfTheTransferValueWhenTheDateIsToday() {
		TransferenciaDTO dto = new TransferenciaDTO(transferenciaNoDia);
		dto = service.makeTransfer(dto);
		Assertions.assertEquals(18.00, dto.getRate());
	}
	
	@Test
	@DisplayName("Teste calcula taxa para transferências com ate 10 dias de agendamento.")
	public void feeShouldBeTwelveTimesTheNumberOfDaysToScheduleTheTransfer() {
		TransferenciaDTO dto = new TransferenciaDTO(transferenciaComAte10Dias);
		dto = service.makeTransfer(dto);
		Assertions.assertEquals(120.00, dto.getRate());
	}
	
	@Test
	@DisplayName("Teste calcula taxa para transferências com ate 20 dias de agendamento.")
	public void testShouldCalculateTheFeeForTransferSchedulesLongerThanTenDaysAndUpToTwentyDays() {
		TransferenciaDTO dto = new TransferenciaDTO(transferenciaAte20Dias);
		dto = service.makeTransfer(dto);
		Assertions.assertEquals(40.00, dto.getRate());
	}
	
	@Test
	@DisplayName("Teste calcula taxa para transferências com ate 30 dias de agendamento.")
	public void testShouldCalculateTheFeeForTransferSchedulesLongerThanTwentyDaysAndUpToThirtyDays() {
		TransferenciaDTO dto = new TransferenciaDTO(transferenciaAte30Dias);
		dto = service.makeTransfer(dto);
		Assertions.assertEquals(30.00, dto.getRate());
	}
	
	@Test
	@DisplayName("Teste calcula taxa para transferências com ate 40 dias de agendamento.")
	public void testShouldCalculateTheFeeForTransferSchedulesLongerThanThirtyDaysAndUpToFortyDays() {
		TransferenciaDTO dto = new TransferenciaDTO(transferenciaAte40Dias);
		dto = service.makeTransfer(dto);
		Assertions.assertEquals(20.00, dto.getRate());
	}
	
	@Test
	@DisplayName("Teste calcula taxa para transferências com acima de 40 dias de agendamento e valor acima de R$ 100.000,00 .")
	public void testShouldCalculateTheFeeForTransferSchedulesOverFortyDays() {
		TransferenciaDTO dto = new TransferenciaDTO(transferenciaAcima40DiasEValorAcimaDeCemMil);
		dto = service.makeTransfer(dto);
		Assertions.assertEquals(2000.02, dto.getRate());
	}
}

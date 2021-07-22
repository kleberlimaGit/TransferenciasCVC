package br.com.CVCCorp.transferencias.tests.factory;

import java.time.LocalDate;

import br.com.CVCCorp.transferencias.entities.Transferencia;

public class TransferenciaFactory {

	public static Transferencia createTransfer(Double transferValue, LocalDate dateOfTransfer) {
		return new Transferencia(null,"333333","222222",transferValue,dateOfTransfer);
	}
}

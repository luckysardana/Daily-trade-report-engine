package com.assignment.jpmc.tradereporting.model;

import java.time.LocalDate;
import java.util.Currency;

public class Instruction implements Comparable<Instruction> {

	private final Entity entity;
	private final LocalDate instructionDate;
	private final LocalDate settlementDate;
	private final Enum<TransactionType> transactionType;
	private final double agreedFx;
	private final int units;
	private final double unitPrice;
	private final Currency currency;

	public Instruction(final Entity entity, final LocalDate instructionDate, final LocalDate settlementDate, final Enum<TransactionType> transactionType,
					   final double agreedFx, final int units, final double unitPrice, final Currency currency) {
		this.entity = entity;
		this.instructionDate = instructionDate;
		this.settlementDate = settlementDate;
		this.transactionType = transactionType;
		this.agreedFx = agreedFx;
		this.units = units;
		this.unitPrice = unitPrice;
		this.currency = currency;
	}

	public Entity getEntity() {
		return entity;
	}

	public LocalDate getInstructionDate() {
		return instructionDate;
	}

	public LocalDate getSettlementDate() {
		return settlementDate;
	}

	public Enum<TransactionType> getTransactionType() {
		return transactionType;
	}

	public double getAgreedFx() {
		return agreedFx;
	}

	public int getUnits() {
		return units;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public Currency getCurrency() {
		return currency;
	}

	@Override
	public String toString() {
		return "Instruction [entity=" + entity + ", instructionDate=" + instructionDate + ", settlementDate="
				+ settlementDate + ", transactionType=" + transactionType + ", agreedFx=" + agreedFx + ", units="
				+ units + ", unitPrice=" + unitPrice + ", currency=" + currency + "]";
	}

	@Override
	public int compareTo(Instruction instruction) {
		if(this.settlementDate.isAfter(instruction.settlementDate)) {
			return 1;
		}else if(this.settlementDate.isBefore(instruction.settlementDate)){
			return -1;
		}
		return 0;
	}
}

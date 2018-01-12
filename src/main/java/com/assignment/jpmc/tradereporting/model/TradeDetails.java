package com.assignment.jpmc.tradereporting.model;

import java.time.LocalDate;

public class TradeDetails implements Comparable<TradeDetails>{

	private final double tradeValue;
	private final Entity entity;
	private final Enum<TransactionType> transactionType;
	private final LocalDate settledDate;

	public TradeDetails(final double tradeValue, final Entity entity, final Enum<TransactionType> transactionType, final LocalDate settledDate) {
		this.tradeValue = tradeValue;
		this.entity = entity;
		this.transactionType = transactionType;
		this.settledDate = settledDate;
	}

	public double getTradeValue() {
		return tradeValue;
	}
	public Entity getEntity() {
		return entity;
	}
	public Enum<TransactionType> getTransactionType() {
		return transactionType;
	}

	public LocalDate getSettledDate() {
		return settledDate;
	}

	@Override
	public String toString() {
		return "TradeDetails{" +
				"tradeValue=" + tradeValue +
				", entity=" + entity +
				", transactionType=" + transactionType +
				", settledDate=" + settledDate +
				'}';
	}

	@Override
	public int compareTo(TradeDetails tradeDetails) {
		if(this.getTradeValue() > tradeDetails.tradeValue) {
			return 1;
		}else if(this.getTradeValue() < tradeDetails.tradeValue) {
			return -1;
		}
		return 0;
	}
	
}

package com.assignment.jpmc.tradereporting;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import com.assignment.jpmc.tradereporting.model.Instruction;
import com.assignment.jpmc.tradereporting.model.TradeDetails;
import com.google.common.base.Preconditions;

public class InstructionExecutor {

	private final SortedSet<TradeDetails> tradeDetails = new TreeSet<>();


	/*
	This method takes list of instructions, iterate over that create TradeDetail out of it and add it to Set<TradeDetail>
	which represent the set of settled trades.
	 */
    public Set<TradeDetails> execute(List<Instruction> instructions) {

        Preconditions.checkArgument(instructions != null && !instructions.isEmpty(), "Provided Data is Insufficient");

        instructions.forEach(instruction -> {
            LocalDate updatedSettledDate = instruction.getSettlementDate();
            // this is for checking if date is the weekend date, if it is it will update the settledDate to nearestWorkingDate
            if (isWeekendDate(instruction.getCurrency().toString(), instruction.getSettlementDate()))
                updatedSettledDate = getNearestWorkingDay(instruction.getCurrency().toString(), instruction.getSettlementDate());

            double tradeValue = instruction.getUnitPrice() * instruction.getUnits() * instruction.getAgreedFx();
            getTradeDetails().add(new TradeDetails(tradeValue, instruction.getEntity(), instruction.getTransactionType(), updatedSettledDate));
        });
        return getTradeDetails();
    }

    /*
    This method return if a date is weekend day or not
     */

	private boolean isWeekendDate(String currency, LocalDate givenSettlementDate) {
        boolean isWeekendDay = false;
        int dayNo = givenSettlementDate.getDayOfWeek().getValue();
        if (currency.equals("AED") || currency.equals("SAR")) {
            if(dayNo == 5 || dayNo == 6)
                isWeekendDay = true;
        } else {
            if(dayNo == 6 || dayNo == 7)
                isWeekendDay = true;
        }
        return isWeekendDay;
    }

    /*
    This method evaluates nearest working day of the weekend date
     */
	private LocalDate getNearestWorkingDay(String currency, LocalDate givenSettlementDate) {
	    LocalDate nearestWorkingDate = givenSettlementDate;
        int dayNo = givenSettlementDate.getDayOfWeek().getValue();
        if (currency.equals("AED") || currency.equals("SAR")) {
            if(dayNo == 5)
                nearestWorkingDate = nearestWorkingDate.plusDays(2);
            if(dayNo == 6)
                nearestWorkingDate = nearestWorkingDate.plusDays(1);
        } else {
            if(dayNo == 6)
                nearestWorkingDate = nearestWorkingDate.plusDays(2);
            if(dayNo == 7)
                nearestWorkingDate = nearestWorkingDate.plusDays(1);
        }
        return nearestWorkingDate;
    }

   	public SortedSet<TradeDetails> getTradeDetails() {
		return tradeDetails;
	}

}

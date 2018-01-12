package com.assignment.jpmc.tradereporting;

import com.assignment.jpmc.tradereporting.model.TradeDetails;
import com.assignment.jpmc.tradereporting.model.TransactionType;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class ReportGenerator {


    /*
    This method generate and print reports for a particular date, another way we can use a scheduler that runs every night instead of passing date
     */
	public void generateReport(Set<TradeDetails> tradeDetails, LocalDate settledDate) {

	    //grouping of trades based on transactionType
		Map<Enum<TransactionType>, Double> txnTypeAndTotalTradeValue = tradeDetails.stream()
                .filter(tradeDetail -> tradeDetail.getSettledDate().equals(settledDate))
                .collect(Collectors.groupingBy(TradeDetails::getTransactionType,
                        Collectors.summingDouble(TradeDetails::getTradeValue)));

        System.out.println("Report on " + settledDate);
        txnTypeAndTotalTradeValue.forEach((txnType, totalTradeValue) -> {

            if(txnType == TransactionType.S) {
                System.out.println();
                System.out.println(" Transaction Type    ||      Amount(USD)");
                System.out.println(" Outgoing            ||        " + totalTradeValue);
            } else {
                System.out.println();
                System.out.println(" Transaction Type    ||      Amount(USD)");
                System.out.println(" Incoming            ||        " + totalTradeValue);
            }
        });
        printEntitiesRank(tradeDetails);
	}

	private void printEntitiesRank(Set<TradeDetails> tradeDetails) {

	    //filtering of trade for a particular trasactiontype and finding the one with max trade value
	    TradeDetails maxValuedOutgoingTrade = tradeDetails.stream()
                .filter(trade -> trade.getTransactionType() ==  TransactionType.S)
                .max(Comparator.comparing(TradeDetails::getTradeValue)).orElseThrow(NoSuchElementException::new);
        TradeDetails maxValuedIncomingTrade = tradeDetails.stream()
                .filter(trade -> trade.getTransactionType() ==  TransactionType.B)
                .max(Comparator.comparing(TradeDetails::getTradeValue)).orElseThrow(NoSuchElementException::new);

        System.out.println("===================================");
        System.out.println("===========Rank Report=============");
        System.out.println("Entity   " + maxValuedOutgoingTrade.getEntity().getName() + " is Rank 1 for Outgoing with value "+maxValuedOutgoingTrade.getTradeValue());
        System.out.println("Entity   " + maxValuedIncomingTrade.getEntity().getName() + " is Rank 1 for Incoming with value "+maxValuedIncomingTrade.getTradeValue());
    }

}

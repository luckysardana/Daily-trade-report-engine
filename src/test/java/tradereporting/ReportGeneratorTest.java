package tradereporting;

import com.assignment.jpmc.tradereporting.InstructionExecutor;
import com.assignment.jpmc.tradereporting.ReportGenerator;
import com.assignment.jpmc.tradereporting.model.Entity;
import com.assignment.jpmc.tradereporting.model.Instruction;
import com.assignment.jpmc.tradereporting.model.TradeDetails;
import com.assignment.jpmc.tradereporting.model.TransactionType;
import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class ReportGeneratorTest {

	private List<Instruction> instructions = new ArrayList<>();
	private InstructionExecutor instructionExecutor = new InstructionExecutor();
	private ReportGenerator reportGenerator = new ReportGenerator();
    @Rule
    public ExpectedException thrown = ExpectedException.none();




    /**
	 * Country code has to be valid one as per this article http://www.xe.com/iso4217.php 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
        // instruction1 and  instruction2 are the exactly same as mentioned in problem document

		Instruction instruction1 = new Instruction(new Entity("foo"), LocalDate.of(2016, Month.JANUARY, 1),
				LocalDate.of(2016, Month.JANUARY, 5), TransactionType.B, 0.50, 200, 100.25,
				Currency.getInstance("SGD"));

		Instruction instruction2 = new Instruction(new Entity("bar"), LocalDate.of(2016, Month.JANUARY, 5),
				LocalDate.of(2016, Month.JANUARY, 7), TransactionType.S, 0.22, 450, 150.5,
				Currency.getInstance("AED"));

		//instruction3-instruction6 added extra to validate some test scenarios
        Instruction instruction3 = new Instruction(new Entity("boo"), LocalDate.of(2016, Month.JANUARY, 1),
                LocalDate.of(2016, Month.JANUARY, 5), TransactionType.B, 0.50, 300, 100.25,
                Currency.getInstance("EUR"));

        Instruction instruction4 = new Instruction(new Entity("zar"), LocalDate.of(2016, Month.JANUARY, 5),
                LocalDate.of(2016, Month.JANUARY, 5), TransactionType.S, 0.22, 550, 150.5,
                Currency.getInstance("GBP"));

        Instruction instruction5 = new Instruction(new Entity("roo"), LocalDate.of(2016, Month.JANUARY, 1),
                LocalDate.of(2016, Month.JANUARY, 5), TransactionType.B, 0.50, 400, 100.25,
                Currency.getInstance("SGD"));

        Instruction instruction6 = new Instruction(new Entity("woo"), LocalDate.of(2016, Month.JANUARY, 5),
                LocalDate.of(2016, Month.JANUARY, 5), TransactionType.B, 0.22, 650, 150.5,
                Currency.getInstance("INR"));


        instructions.add(instruction1);
		instructions.add(instruction2);
        instructions.add(instruction3);
        instructions.add(instruction4);
        instructions.add(instruction5);
        instructions.add(instruction6);

	}



	@Test
    public void testWhenInputInstructionIsEmpty_shouldThrowIllegalArguementException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Provided Data is Insufficient");
        instructionExecutor.execute(ImmutableList.of());
    }



	@Test
	public void testWhenInputIsValid_shouldAssertActualOutput() {
		
		Set<TradeDetails> executedTradeDetails = instructionExecutor.execute(instructions);
		assertThat(executedTradeDetails.size(), is(not(0)));
        assertThat(executedTradeDetails.size(), is(6));
        reportGenerator.generateReport(executedTradeDetails, LocalDate.of(2016, Month.JANUARY, 5));
	}

}

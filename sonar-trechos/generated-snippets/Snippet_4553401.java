public class Snippet__4553401 {

    private Money handleInstallmentTransaction(final RecurringDepositScheduleInstallment currentInstallment,
                final Money transactionAmountUnprocessed, final LocalDate transactionDate) {

            Money transactionAmountRemaining = transactionAmountUnprocessed;
            Money depositAmountPortion = Money.zero(transactionAmountRemaining.getCurrency());

            depositAmountPortion = currentInstallment.payInstallment(transactionDate, transactionAmountRemaining);
            transactionAmountRemaining = transactionAmountRemaining.minus(depositAmountPortion);

            return transactionAmountRemaining;

        }

}

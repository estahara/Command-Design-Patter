package exercicio.concreteCommand;

import exercicio.command.Command;
import exercicio.receiver.BankAccount;

import java.math.BigDecimal;

public class WithdrawCommand implements Command {
    private final BankAccount account;
    private final BigDecimal amount;

    public WithdrawCommand(BankAccount account, BigDecimal amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public boolean execute() {
        return account.withdraw(amount);
    }

    @Override
    public void undo() {
        account.deposit(amount);
    }

    @Override
    public String getDescription() {
        return "Withdrawal of " + amount + " from " + account.getOwner();
    }
}

package exercicio.concreteCommand;

import exercicio.command.Command;
import exercicio.receiver.BankAccount;

import java.math.BigDecimal;

public class DepositCommand implements Command {
    private final BankAccount account;
    private final BigDecimal amount;

    public DepositCommand(BankAccount account, BigDecimal amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public boolean execute() {
        account.deposit(amount);
        return true;
    }

    @Override
    public void undo() {
        account.withdraw(amount);
    }

    @Override
    public String getDescription() {
        return "Deposit of " + amount + " into " + account.getOwner();
    }
}

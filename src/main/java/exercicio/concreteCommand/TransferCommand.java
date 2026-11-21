package exercicio.concreteCommand;

import exercicio.command.Command;
import exercicio.receiver.BankAccount;

import java.math.BigDecimal;

public class TransferCommand implements Command {
    private final BankAccount source;
    private final BankAccount destination;
    private final BigDecimal amount;

    public TransferCommand(BankAccount source, BankAccount destination, BigDecimal amount) {
        this.source = source;
        this.destination = destination;
        this.amount = amount;
    }

    @Override
    public boolean execute() {
        if (!source.withdraw(amount)) return false;
        destination.deposit(amount);
        return true;
    }

    @Override
    public void undo() {
        destination.withdraw(amount);
        source.deposit(amount);
    }

    @Override
    public String getDescription() {
        return "Transfer of " + amount + " from " + source.getOwner() + " to " + destination.getOwner();
    }
}

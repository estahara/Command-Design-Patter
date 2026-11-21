package exercicio.client;

import exercicio.concreteCommand.BatchCommand;
import exercicio.concreteCommand.DepositCommand;
import exercicio.concreteCommand.TransferCommand;
import exercicio.concreteCommand.WithdrawCommand;
import exercicio.invoker.TransactionInvoker;
import exercicio.receiver.BankAccount;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BankAccount alice = new BankAccount("Alice", new BigDecimal("100"));
        BankAccount bob = new BankAccount("Bob", new BigDecimal("0"));
        TransactionInvoker invoker = new TransactionInvoker();

        invoker.run(new DepositCommand(alice, new BigDecimal("50")));
        invoker.run(new WithdrawCommand(alice, new BigDecimal("120")));
        invoker.undo();
        System.out.println("Alice: " + alice.getBalance());

        boolean isTransferDone = invoker.run(new TransferCommand(alice, bob, new BigDecimal("200")));
        System.out.println("Transfer of 200 done? " + isTransferDone + " | Alice: " + alice.getBalance() + " | Bob: " + bob.getBalance());

        boolean isBatchDone = invoker.run(new BatchCommand(List.of(
                new TransferCommand(alice, bob, new BigDecimal("100")),
                new TransferCommand(alice, bob, new BigDecimal("100")))));
        System.out.println("Batch done? " + isBatchDone + " | Alice: " + alice.getBalance() + " | Bob: " + bob.getBalance());

        invoker.run(new TransferCommand(alice, bob, new BigDecimal("30")));
        System.out.println("Statement:");
        invoker.getStatement().forEach(line -> System.out.println("  " + line));
    }
}

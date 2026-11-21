package exercicio.receiver;

import java.math.BigDecimal;

public class BankAccount {
    private final String owner;
    private BigDecimal balance;

    public BankAccount(String owner, BigDecimal initialBalance) {
        this.owner = owner;
        this.balance = initialBalance;
    }

    public void deposit(BigDecimal amount) {
        validateAmount(amount);
        balance = balance.add(amount);
    }

    public boolean withdraw(BigDecimal amount) {
        validateAmount(amount);
        if (!isBalanceSufficient(amount)) return false;
        balance = balance.subtract(amount);
        return true;
    }

    public boolean isBalanceSufficient(BigDecimal amount) {
        return balance.compareTo(amount) >= 0;
    }

    private void validateAmount(BigDecimal amount) {
        if (amount.signum() <= 0) throw new IllegalArgumentException("Amount must be positive");
    }

    public String getOwner() { return owner; }

    public BigDecimal getBalance() { return balance; }
}

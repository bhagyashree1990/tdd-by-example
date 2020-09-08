package guru.springframework;

public class Money implements Expression {
    protected String currency;
    protected int amount;

    public Money(int amount,String currency){
        this.amount = amount;
        this.currency = currency;
    }

    protected String currency(){
        return this.currency;
    }

    public  static  Money dollar(int amount){
        return new Money(amount,"USD");
    }

    public  static  Money franc(int amount){
        return new Money(amount,"CHF");
    }

    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount &&
                currency.equals(money.currency);
    }

    @Override
    public String toString() {
        return "Money{" +
                "currency='" + currency + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public Expression plus(Expression addend){
        return new Sum( this,addend);
    }

    @Override
    public Expression times(int multiplier) {
        return new Money(amount*multiplier,this.currency);
    }

    @Override
    public Money reduce(Bank bank,String toCurrency){
       return new Money(amount/bank.rate(this.currency,toCurrency),toCurrency);
    }
}

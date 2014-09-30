import java.lang.IllegalArgumentException;
import java.util.Random;

public class FederalTax {
    private float grossIncome;
    private float withheld;
    private float[] bracket;
    private float[] percentage;
    private float standardDeductible;
    private float personalExemption;
    private float deductible;
    private float childCredit;
    private int headCount = 3;

    public FederalTax(int year, float income, float withheld) {
        this.grossIncome = income;
        this.withheld = withheld;
        switch (year) {
            case 2014:
                this.bracket = new float[]{0f, 18150f, 73800f, 148850f, 226850f, 405100f, 457600f, Float.MAX_VALUE};
                this.percentage = new float[]{0f, .1f, .15f, .25f, .28f, .33f, .35f, .396f};
                this.standardDeductible = 12400f;
                this.personalExemption = 3950f;
                this.childCredit = 1000f;
                break;
            case 2013:
                this.bracket = new float[]{0f, 17850f, 72500f, 146400f, 223050f, 398350f, 450000f, Float.MAX_VALUE};
                this.percentage = new float[]{0f, .1f, .15f, .25f, .28f, .33f, .35f, .396f};
                this.standardDeductible = 12200f;
                this.personalExemption = 3900f;
                this.childCredit = 1000f;
                break;
            default:
                throw new IllegalArgumentException("invalid year for tax bracket");
        }
        this.deductible = standardDeductible + headCount * personalExemption;
    }

    private float getTax(float taxable) {
        if (taxable <= 0f) throw new IllegalArgumentException("taxable amount must be positive");
        float taxDue = 0f;
        for (int i = 1; i < bracket.length; i++) {
            if (this.bracket[i] < taxable) taxDue += this.percentage[i] * (this.bracket[i] - this.bracket[i - 1]);
            else {
                taxDue += this.percentage[i] * (taxable - this.bracket[i - 1]);
                break;
            }
        }
        return taxDue;
    }

    public float addDeductible(float value) {
        this.deductible += value;
        return this.deductible;
    }

    public float run() {
        float tax = getTax(grossIncome - deductible);
        return withheld - (tax - childCredit);
    }

    public static void main(String[] args) {
        if(args.length < 1) throw new IllegalArgumentException("please input your 401k contribution");
        // =========== 2013 ===========
        float income = 91571f;
        float withheld = 16226.56f;
        FederalTax taxPayer = new FederalTax(2013, income, withheld);
        taxPayer.addDeductible(5500); // IRA deductible
        System.out.printf("2013 Gross Income: %.2f, Taxable Income: %.2f, Tax Return: %.2f\n", income, taxPayer.grossIncome - taxPayer.deductible, taxPayer.run());

        // =========== 2014 ===========
        income = 80867.01f;
        withheld = 14807.61f;

        // ws payment from Oct to till end of year
        // TODO
        float ws401k = Float.parseFloat(args[0]);
        float fsa = 28.58f;
        float taxablePerPay = 4916.66f - 110.37f - 31.95f - fsa - 37.5f - ws401k * 4916.66f;
        income += 6 * taxablePerPay;
        withheld += 6 * 0.166f * taxablePerPay;

        taxPayer = new FederalTax(2014, income, withheld);
        taxPayer.addDeductible(5500); // IRA deductible

        System.out.printf("2014 Gross Income: %.2f, Taxable Income: %.2f, Tax Return: %.2f\n", income, taxPayer.grossIncome - taxPayer.deductible, taxPayer.run());
    }
}
import java.math.BigInteger;
//go wild with the input
import java.util.*;
public class Privatekey 
{
    boolean printing;
    ArrayList<BigInteger>primeFactors=new ArrayList<BigInteger>();
    final BigInteger TWO=BigInteger.valueOf(2);
    BigInteger randomNum=BigInteger.ONE;
    boolean fail;
    public Privatekey(boolean printing) {this.printing=printing;}
    public void factorRho(BigInteger num)
    {
        if (num.compareTo(BigInteger.ONE)==0)
            return;
        if (num.isProbablePrime(300)) 
        {
            primeFactors.add(num);
            return; 
        }
        BigInteger divisor = rho(num);
        if(fail)
            assignRandomNum();
        factorRho(divisor);
        factorRho(num.divide(divisor));
    }
    public ArrayList<BigInteger> getPrimeFactors(BigInteger num)
    {
        primeFactors.clear();
        factorRho(num);
        if(printing)
            System.out.println("The Pollard Rho algorithim has the function x^2"+((randomNum.compareTo(BigInteger.ZERO)>0)?"+":"")+randomNum+"\nThe factors are:");
        return primeFactors;
    }
    public static void main(String[] args)
    {
        System.out.println("This Program will return the prime factors of any number using the Pollard Rho Algorithm\n"); 
       Privatekey k=new Privatekey(true);
  Scanner input=new Scanner(System.in);
  System.out.println(k.getPrimeFactors(new BigInteger (input.next())));
    }
    private BigInteger rho(BigInteger num) 
    {
        BigInteger x1 = TWO, x2 = TWO, divisor=BigInteger.ONE; 
        if (num.mod(TWO).compareTo(BigInteger.ZERO)==0)
            return TWO;
 while(divisor.compareTo(BigInteger.ONE)==0) 
        {
            x1 = f(x1).mod(num);
            x2 = f(f(x2)).mod(num);
            divisor =x1.subtract(x2).gcd(num);
        } 
        if(divisor.equals(num))
        {
            if(printing)
                System.out.println("The Pollard Rho algorithim has failed for the function x^2"+((randomNum.compareTo(BigInteger.ZERO)>0)?"+":"")+randomNum);
            fail=true;
        }
        return divisor;
    }
    private void assignRandomNum()
    {
        randomNum=BigInteger.valueOf((long)(100*Math.random()-50));
        fail=false;
     }
    private BigInteger f(BigInteger x) 
    {
        return x.multiply(x).add(randomNum);
    }
}
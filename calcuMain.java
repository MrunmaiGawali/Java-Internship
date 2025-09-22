import java.util.*;
public class calcuMain {

        
    public static double add(double a,double b)
    {
        return a+b;
    }
    public static double sub(double a,double b)
    {
        return a-b;
    }
    public static double mul(double a,double b)
    {
        return a*b;
    }
    public static double div(double a,double b)
    {
        if(b==0)
        {
            System.out.println("divide by 0 is not allowed");
        }
        return a/b;
    }


    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        boolean continueCalculation=true;

        while(continueCalculation)
        {
            System.out.println("Enter whats you choice \n 1.Addition \n 2.Substraction \n 3.Multiplication \n 4.Division \n 5.Exit ");
            int myChoice=sc.nextInt();
             
            switch(myChoice)
            {
                case 1:System.out.println("Enter first number");
                 double num1=sc.nextDouble();
                System.out.println("Enter second number");
                 double num2=sc.nextDouble();
                 System.out.println("RESULT ="+ add(num1,num2));
                 break;

                case 2:
                System.out.println("Enter first number ");
                 double num3=sc.nextDouble();
                System.out.println("Enter second number");
                 double num4=sc.nextDouble();
                System.out.println("RESULT ="+ sub(num3,num4));
                break;

                case 3:
                 System.out.println("Enter first number ");
                 double num5=sc.nextDouble();
                System.out.println("Enter second number");
                 double num6=sc.nextDouble();
                  System.out.println("RESULT ="+ mul(num5,num6));
                break;

                case 4:
                 System.out.println("Enter first number ");
                 double num7=sc.nextDouble();
                System.out.println("Enter second number");
                 double num8=sc.nextDouble();
                System.out.println("RESULT ="+ div(num7,num8));
                break;


                case 5:
                    continueCalculation=false;
                    System.err.println("Thank you");
                    break;
                default :
                    System.err.println("Invalid choice! please enter a valid choice");

            }
            System.out.println();
        }
        sc.close();
    }
    
}

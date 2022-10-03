import java.io.File;
import java.io.IOException;

public class Driver {
    public static void main(String [] args) throws IOException {
        double [] c1 = {6,4,1,5};
        int [] e1 = {2,7,4,3};
        Polynomial p1 = new Polynomial(c1, e1);
        int max_num = p1.get_degree();
        System.out.println(max_num);

        Polynomial p = new Polynomial(); 
        System.out.println(p.evaluate(3)); 
        double [] c1_1 = {6, 5}; 
        int [] e1_1 = {0, 3}; 
        Polynomial p1_1 = new Polynomial(c1_1, e1_1); 
        double [] c2_2 = {-2,3,1,-9}; 
        int [] e2_2 = {3,1,0,4}; 
        Polynomial p2_2 = new Polynomial(c2_2, e2_2); 
        Polynomial s_1 = p1_1.add(p2_2);
        for(int i = 0; i < s_1.exponents.length; i++){
            System.out.println("Coef: " + s_1.coefficients[i]);
            System.out.println("Exp: " + s_1.exponents[i]);
        }
        System.out.println("s(2) = " + s_1.evaluate(2));
        if(s_1.hasRoot(1)) 
            System.out.println("1 is a root of s"); 
        else 
            System.out.println("1 is not a root of s"); 

        System.out.println("-------------------------------------");
        Polynomial s_2 = p1_1.multiply(p2_2);
        for(int i = 0; i < s_2.exponents.length; i++){
            System.out.println("Coef: " + s_2.coefficients[i]);
            System.out.println("Exp: " + s_2.exponents[i]);
        }

        // Test file
        File file = new File("testing.txt");
        Polynomial p_3 = new Polynomial(file);
        for(int i = 0; i < p_3.exponents.length; i++){
            System.out.println("Coef: " + p_3.coefficients[i]);
            System.out.println("Exp: " + p_3.exponents[i]);
        }
        p_3.saveToFile("testong2.txt");
    }
}
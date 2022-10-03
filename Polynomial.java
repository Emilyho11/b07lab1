import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

public class Polynomial {
     double[] coefficients;
     int[] exponents;
   
     // no-argument
     public Polynomial() {
        // Set polynomial to zero
       coefficients = new double[]{0};
       exponents = new int[]{0};
     }
   
     // double array
     public Polynomial(double[] nums, int[] exp_num) {
          coefficients = new double[nums.length];
          exponents = new int[nums.length];
          for(int i = 0; i < coefficients.length; i++) {
               coefficients[i] = nums[i];
               exponents[i] = exp_num[i];
          }
     }
     public Polynomial(File f) throws IOException {
          Scanner file_reader = new Scanner(f);
          String text = "";
          while(file_reader.hasNextLine()){
               text = file_reader.nextLine();
          }
          file_reader.close();
          String[] term_texts = text.replace("-", "+-").split("\\+");
          coefficients = new double[term_texts.length];
          exponents = new int[term_texts.length];
          for(int i = 0; i < term_texts.length; i++){
               int find_x = term_texts[i].indexOf('x');
               if(find_x == -1){
                    // x doesn't exist, so it's a constant
                    coefficients[i] = Double.parseDouble(term_texts[i]);
                    exponents[i] = 0;
               }
               else{
                    // Split by x
                    String[] coef_and_exp = term_texts[i].split("x");
                    double coef = Double.parseDouble(coef_and_exp[0]);
                    int exp = Integer.parseInt(coef_and_exp[1]);
                    coefficients[i] = coef;
                    exponents[i] = exp;
               }
          }
     }
     
     public int get_degree() {
          int biggest = 0;
          for (int i = 0; i < this.exponents.length; i++)
          {
               if(biggest < exponents[i]) {
                    biggest = exponents[i];
               }
          }
          return biggest;
     }
     public Polynomial add(Polynomial p) {
          int bigger_exp = Math.max(this.get_degree(), p.get_degree());
          double[]new_coefficients = new double[bigger_exp+1];
          int[]new_exponents = new int[bigger_exp+1];

          // Set new_exponents to have numbers 0 to bigger_exp
          for(int i = 0; i < bigger_exp+1; i++){
               new_exponents[i] = i;
          }
          for(int i = 0; i < p.exponents.length; i++){
               // set coefficient of p to the new_coefficients index
               new_coefficients[p.exponents[i]] = p.coefficients[i];
          }
          for(int i = 0; i < this.exponents.length; i++){
               // set coefficient of this to the new_coefficients index
               new_coefficients[this.exponents[i]] += this.coefficients[i];
          }
          // Check if there's zero as the coefficients
          int num = bigger_exp+1;
          for(int i = 0; i < new_coefficients.length; i++){
               if(new_coefficients[i] == 0){
                    num--;
               }
          }
          // Create final coefficient and exponent array
          int coef_index = 0;
          double[]final_coef = new double[num];
          int[]final_exp = new int[num];
          for(int i = 0; i < new_coefficients.length; i++){
               if(new_coefficients[i] != 0){
                    final_coef[coef_index] = new_coefficients[i];
                    //System.out.println("Coef: "+final_coef[coef_index]);
                    final_exp[coef_index] = new_exponents[i];
                    //System.out.println("Exp: "+final_exp[coef_index]);
                    coef_index++;
               }   
          }
          Polynomial p1 = new Polynomial(final_coef, final_exp);
          return p1;
     }
     
     public double evaluate(double x) {
          double result = 0;
          for(int i = 0; i < this.coefficients.length; i++){
               result += this.coefficients[i]*Math.pow(x, this.exponents[i]);
          }
          return result;
     }
     
     public boolean hasRoot(double value) {
          return this.evaluate(value) == 0;
     }

     public Polynomial multiply(Polynomial p){
          int max_size = this.get_degree()+p.get_degree();
          double[]new_coefficients = new double[max_size+1];
          int[]new_exponents = new int[max_size+1];

          // Set new_exponents to have numbers 0 to bigger_exp
          for(int i = 0; i <= max_size; i++){
               new_exponents[i] = i;
          }

          for(int i = 0; i < this.coefficients.length; i++){
               for(int j = 0; j < p.coefficients.length; j++){
                    double coeff = this.coefficients[i] * p.coefficients[j];
                    int exp = this.exponents[i] + p.exponents[j];
                    new_coefficients[exp] += coeff;
               }
          }
          // Check if there's zero as the coefficients
          int num = max_size+1;
          for(int i = 0; i < new_coefficients.length; i++){
               if(new_coefficients[i] == 0){
                    num--;
               }
          }
          // Create final coefficient and exponent array
          int coef_index = 0;
          double[]final_coef = new double[num];
          int[]final_exp = new int[num];
          for(int i = 0; i < new_coefficients.length; i++){
               if(new_coefficients[i] != 0){
                    final_coef[coef_index] = new_coefficients[i];
                    //System.out.println("Coef: "+final_coef[coef_index]);
                    final_exp[coef_index] = new_exponents[i];
                    //System.out.println("Exp: "+final_exp[coef_index]);
                    coef_index++;
               }
          }
          Polynomial p1 = new Polynomial(final_coef, final_exp);
          return p1;
     }

     public void saveToFile(String file) throws IOException {
          String poly = "";
          for(int i = 0; i < coefficients.length; i++){
               if(i > 0 && coefficients[i] > 0){
                    poly += ("+");
               }
               if(exponents[i] == 0){
                    poly += Double.toString(coefficients[i]);
                    continue;
               }
               poly += coefficients[i]+"x"+exponents[i];
               
          }
          FileWriter write_file = new FileWriter(file);
          write_file.write(poly);
          write_file.flush();
          write_file.close();
     }
   }
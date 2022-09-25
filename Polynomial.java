public class Polynomial {
     private double[] coefficients;
   
     // no-argument
     public Polynomial() {
        // Set polynomial to zero
       coefficients = new double[]{0};
     }
   
     // double array
     public Polynomial(double[] nums) {
          coefficients = new double[nums.length];
          for(int i = 0; i < coefficients.length; i++) {
                 coefficients[i] = nums[i];
          }
     }
     
     public Polynomial add(Polynomial p) {
          int bigger_len = Math.max(this.coefficients.length, p.coefficients.length);
          double[]new_coefficients = new double[bigger_len];
          for(int i = 0; i < this.coefficients.length; i++){
               new_coefficients[i] += this.coefficients[i];
          }
          for(int i = 0; i < p.coefficients.length; i++){
               new_coefficients[i] += p.coefficients[i];
          }
          Polynomial p1 = new Polynomial(new_coefficients);
          return p1;
     }
     
     public double evaluate(double x) {
          double result = 0;
          for(int i = 0; i < this.coefficients.length; i++){
               result += this.coefficients[i]*Math.pow(x, i);
          }
          return result;
     }
     
     public boolean hasRoot(double value) {
          return this.evaluate(value) == 0;
     }
   }
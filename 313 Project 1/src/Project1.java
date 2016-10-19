/* Name:Shameer Khan
 * CS-313
 * Project 1.
 */

import java.io.*;
import java.util.*;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class Project1 {
	public static void main(String[] args) throws IOException {
		Polynomial p1= new Polynomial();
		Polynomial p2= new Polynomial();
		int i = 0;//This i will help me alternate assignment of 2 polynomials. using %2.(0 for p1 and 1 for p2)
		try{
			BufferedReader br= new BufferedReader(new FileReader("File"));
			String line=br.readLine();
			int pairCounter=1;//for printing pair no. 
			while(line!=null){//main loop keep reading file until i run out of line in file;
				if(!line.isEmpty()){//skipping empty lines
				String[] reference= line.split(",");
				for(String ele: reference)
				if(i%2==0)//assigning poly1
				p1.append(Integer.parseInt(ele));
				else if(i%2!=0)//assigning poly2
				p2.append(Integer.parseInt(ele));
				if(p1.getSize()!=0 && p2.getSize()!=0){
					System.out.println("Polynomials in involving in pair "+ pairCounter+ " is: ");
					System.out.println("Polynomial 1: ");
					p1.fancyPrint();
					System.out.println("\nPolynomial 2: ");
					p2.fancyPrint();
					System.out.println();
					System.out.println("Sum of the pair no. "+ pairCounter+ " : ");
					if(Polynomial.sum(p1,p2).linearSum()==0)
						System.out.print("f(x)= "+0);
					else if(Polynomial.sum(p1, p2).linearSum()!=0)
						Polynomial.sum(p1, p2).fancyPrint();
					System.out.println();
					System.out.println("Product of the pair no. "+pairCounter+" : " );
						Polynomial.Product(p1, p2).fancyPrint();
					System.out.println("\n");	
					pairCounter++;
					p1.clearALL();//emptying p1 for new assignment
					p2.clearALL();//emptying p2 for new assignment
					}
				}
				line=br.readLine();
				i++;
			}
		}
			catch (Exception e) {
			System.out.println(e); 
		}
	}



}



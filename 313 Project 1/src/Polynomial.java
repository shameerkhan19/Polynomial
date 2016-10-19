
public class Polynomial {

	private Node head;//pointer to the first Node
	protected int size;//to keep track of size of the list
	
	public Polynomial(){//default constructor
		head=null;
		size=0;
	}
	public Polynomial(int x){//keeping two design for flexibility. one take integer
		head=new Node(x,null);
		size++;
	}
	public Polynomial (Node n){//this one takes in a Node.
		head=n;
		size++;
	}
	public void append(int x){//adding to the list by this
		if(head==null){//if i have empty list
			head= new Node(x,null);
			size++;
			return;
		}
		Node current=head;//if not a empty list
		while(current.getNext()!=null)
			current=current.getNext();
		current.setNext(new Node(x,null));
		size++;
	}
	public void fancyPrint(){//just to print out those fancy look polynomial
		Node current=head;
		int degreeTracker=0;
		System.out.print("f(x)=");
		while(current!=null){
			if(current.getCoefficient()!=0){
				if(current==head){
					System.out.print(current.getCoefficient());
					degreeTracker++;//keep track of degree
					current=current.getNext();
				}
				if(current.getCoefficient()>0)
				System.out.print("+"+current.getCoefficient()+"x^"+degreeTracker);
				else if(current.getCoefficient()<0)
				System.out.print(current.getCoefficient()+"x^"+degreeTracker);
			}
			degreeTracker++;
			current=current.getNext();
		}
	}
	public void rightShift(int x){//to use for the product method
		if(head==null){//empty list
			head=new Node(x,null);
			size++;
			return;
		}
		Node n= new Node(x,null);//not empty list
		n.setNext(head);
		head=n;
		size++;
	}
	public int linearSum(){//to print out if polynomial result is 0;
		Node current=head;
		int sum=0;
		while(current!=null){
			sum+=current.getCoefficient();
			current=current.getNext();
		}
		return sum;
	}
public void clearALL(){//delete entire polynomial for reassignment
		size=0;
		head=null;
		 }
public int getSize(){//to compare and know sizes of polynomials
		return size;
		}

 static Polynomial sum(Polynomial k1, Polynomial k2){
	Polynomial p3= new Polynomial();//the polynomial that will hold the sum of p1 and p2.
	int sum=0;
	Node current=k1.head;
	Node runner=k2.head;
	/*For the next 2 if blocks I am basically equaling the size. So i don't miss out 
	coefficient of the bigger polynomial
	*/
	if(k1.getSize()<k2.getSize()){
		while(k1.getSize()!=k2.getSize()){
			k1.append(0);
		}
	}
	else if(k1.getSize()>k2.getSize()){
		while(k2.getSize()!=k1.getSize()){
			k2.append(0);
		}
	}
	/*here two pointers current for p1 and runner for p2. using them to traverse the list.
	 * read the data in both.add them up and use their sum to created a node for a 
	 * new polynomial p3. by p3.append(sum); then return p3 once complete;
	 */
	while(current!=null && runner!=null){
		//here taking taking values from p1 and p2 from corresponding nodes and adding them
		//then assigning them to sum which will get appended as coefficient in p3.
		sum=(current.getCoefficient()+runner.getCoefficient());
		p3.append(sum);
		current=current.getNext();
		runner=runner.getNext();
	}
	return p3;//returning a pointer p3 which has my sum result.
}
 
 
 static Polynomial Product(Polynomial k1, Polynomial k2){
	int i=0;//using this to allow me to assign two polynomials.
	Polynomial temp= new Polynomial();//will be one of the two poly that perform sum operation
	Polynomial tempHolder = new Polynomial();//same idea as above
	for(Node current=k1.head;current!=null;current=current.getNext()){
		for(Node runner=k2.head;runner!=null;runner=runner.getNext()){
			/*say i have k1= 1,1 and k2=1,2; here for first iteration temp will get appended 
			 * with 1*1 and then 1*2 thus temp will look something like this temp=1,2; then for
			 * next iteration temp will be appended with 1*1 and 1*2. Therefore temp will look
			 * something like this temp=1,2; and will keep going like this until i hit null on
			 * polynomial k1; */
			temp.append(runner.getCoefficient()*current.getCoefficient());
		}
		if(i==0){//holding temp before deleting;
			/*here we are saving the Polynomial temp into a tempHolder. So we can assign
			 * the next iteration into temp; We do this so we have two polynomials that we
			 * can perform sum operation on.*/
			Node pointer=temp.head;
			while(pointer!=null){
				tempHolder.append(pointer.getCoefficient());
				pointer=pointer.getNext();
			}
			temp.clearALL();//clearing temp once we assigned it to tempHolder
		}
		else if(i>0){//for this parts logic please check at the bottom of this class
			for(int k=0;k<i;k++)
			temp.rightShift(0);
			tempHolder=sum(tempHolder,temp);
			temp.clearALL();
		}
		i++;
	}

	Polynomial p3= tempHolder;//here tempHolder has the final result once loop terminates
	return p3;//returning p3 is pointing to tempHolder and returning a pointer. Which has my result.
}
}

/*for example lets say two polynomials k1 and k2. k1=1,1; and k2=1,1; I don't enter this 
 * block until i>0 so i can have 2 polynomials. Once I enter this block. my temp=1,1;
 * and tempHolder=1,1;Now at this point my i=1; loop will run one time will rightshift temp
 *one time. temp will now look like this temp=0,1,1. Now why rightshift instead of left like  
 * multiplication? because usually when we multiply 2 polynomial such (2x+1)(x+1) we follow the
 * usual multiplication rule. But they way our polynomial is set up we got it backwards. ours 
 * would look like this (1+x)(1+2x). since our head node carries 0 degree element. our k1=1,1
 * and k2=1,2. when we loop them we get temp =1,2 tempHolder =1,2. If i left shift temp like usual
 * temp will be =1,2,0 passing this to sum would yield a polynomial p3=(1+1)(2+2)(0+0)=2,4,0. Thats
 * 2+4x. but that's the wrong answer. But if rightshift instead of left our temp will look like this
 * temp=0,1,2. sending this information to sum we get (1+0),(2+1),(2+0) which looks like this
 * 1+3x+2x^2 and that's the right answer. 
 * Basically the whole idea of shifting right instead of left is simply because our polynomials
 * are assigned backwards since usually highest degrees are written first and lowest degrees are 
 * written last. here its the other way around.
 * as for for(int k=0;k<i;k++)
			temp.rightShift(0);
 * the whole idea here is rightshift as many times i loop. Same like a regular multiplication
 * where with each new step you left shift corresponding to step number.
 * 
 */


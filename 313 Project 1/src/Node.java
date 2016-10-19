
public class Node {

	private int coefficient;//changed the name from element to coefficient. Easier to see.
	private Node next;
	
	public Node(){
		coefficient=0;
		next=null;
	}
	public Node( int c, Node n){
		coefficient=c;
		next=n;
	}
	//here i used source/generate getters and setters. therefore it looks a bit different from class.
	//saves a bit time.
	public int getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	
}

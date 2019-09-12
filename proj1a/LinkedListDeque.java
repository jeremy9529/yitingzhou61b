
public class LinkedListDeque<PlaceholderType> {
	private class Dlist{
		private PlaceholderType item;
		private Dlist rest;
		private Dlist pre;
		private Dlist(PlaceholderType i, Dlist r,Dlist p){
			item = i;
			rest = r;
			pre = p;
		}
	}
	
	private int size;
	private Dlist sentinel;
	public LinkedListDeque() {
        sentinel = new Dlist(null,sentinel,sentinel);
        size = 0;
    }
	
	public void addFirst(PlaceholderType item) {
		sentinel.rest = new Dlist(item,sentinel, sentinel.rest);
        sentinel.rest.rest.pre = sentinel.rest;
        size += 1;
	}
	
	public void addLast(PlaceholderType item) {
		sentinel.pre = new Dlist(item,sentinel, sentinel.pre);
		sentinel.pre.pre.rest = sentinel.pre;
		size += 1;
	}
	
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public int size() {
		return size;
	}
	
	public void printDeque() {
		Dlist temp = sentinel;
		while(temp.rest.item != null) {
			System.out.print(temp.rest.item+" ");
			temp = temp.rest;
		}
	}
    
	public PlaceholderType removeFirst() {
		if(size == 0) {
			return null;
		}else {
			PlaceholderType firstitem = sentinel.rest.item;
			sentinel.rest.rest.pre = sentinel;
			sentinel.rest = sentinel.rest.rest;
	        size -= 1;
	        return firstitem;
		}
	}
	
	public PlaceholderType removeLast() {
		if(size == 0) {
			return null;
		}else {
			PlaceholderType lastitem = sentinel.pre.item;
			sentinel.pre.pre.rest = sentinel;
			sentinel.pre = sentinel.pre.pre;
	        size -= 1;
	        return lastitem;
		}
		
	}
	
	public PlaceholderType get(int index) {
		Dlist temp = sentinel;
		int i = 0;
		if(index > this.size()-1) {
			return null;
		}else {
			while(i <= index) {
				temp = temp.rest;
				i++;
			}
			return temp.item;
		}
		
	}
	
	public PlaceholderType getRecursive(int index) {
		if(index > this.size()-1) {
			return null;
		}else {
			return this.helper(sentinel.rest,index);
		}
	}
	
	public PlaceholderType helper(Dlist temp, int i) {
		if(i == 0) {
			return temp.item;
		}else {
			return helper(temp.rest,i-1);
		}
	}
	
	
	public static void main(String[] args) {
		LinkedListDeque<String> Dllist = new LinkedListDeque<>();
		Dllist.addFirst("a");
        Dllist.addLast("b");
        Dllist.addLast("c");
       // Dllist.removeFirst();
        //Dllist.removeLast();
        //Dllist.removeLast();
        Dllist.printDeque();
        System.out.println();
        System.out.println(Dllist.size());
        System.out.println(Dllist.isEmpty());
        System.out.println(Dllist.get(1));
        System.out.println(Dllist.getRecursive(1));
    }

}

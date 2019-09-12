
public class LinkedListDeque<T> {
	private class Dlist{
		private T item;
		private Dlist rest;
		private Dlist pre;
		private Dlist(T i, Dlist r,Dlist p){
			item = i;
			rest = r;
			pre = p;
		}
	}
	
	private int size;
	private Dlist sentinel;
	public LinkedListDeque() {
        sentinel = new Dlist(null,null,null);
        sentinel.pre = sentinel;
        sentinel.rest = sentinel;
        size = 0;
    }
	
	public void addFirst(T item) {
		sentinel.rest = new Dlist(item,sentinel.rest, sentinel);
        sentinel.rest.rest.pre = sentinel.rest;
        size += 1;
	}
	
	public void addLast(T item) {
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
    
	public T removeFirst() {
		if(size == 0) {
			return null;
		}else {
			T firstitem = sentinel.rest.item;
			sentinel.rest.rest.pre = sentinel;
			sentinel.rest = sentinel.rest.rest;
	        size -= 1;
	        return firstitem;
		}
	}
	
	public T removeLast() {
		if(size == 0) {
			return null;
		}else {
			T lastitem = sentinel.pre.item;
			sentinel.pre.pre.rest = sentinel;
			sentinel.pre = sentinel.pre.pre;
	        size -= 1;
	        return lastitem;
		}
		
	}
	
	public T get(int index) {
		Dlist temp = sentinel;
		int i = 0;
		if(index < 0 || index > this.size()-1) {
			return null;
		}else {
			while(i <= index) {
				temp = temp.rest;
				i++;
			}
			return temp.item;
		}
		
	}
	
	public T getRecursive(int index) {
		if(index < 0 || index > this.size()-1) {
			return null;
		}else {
			return this.helper(sentinel.rest,index);
		}
	}
	
	private T helper(Dlist temp, int i) {
		if(i == 0) {
			return temp.item;
		}else {
			return helper(temp.rest,i-1);
		}
	}
	
	

}

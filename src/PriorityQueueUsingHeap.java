import java.util.HashMap;
public class PriorityQueueUsingHeap {
    public Village[] arr;
    public int N;
    HashMap<Village, Integer> table;
   

    public PriorityQueueUsingHeap(){
       // arr = (Village[]) new Comparable[50];
    	arr = new Village[50];
        N = 0;
        table = new HashMap<Village, Integer>(50);
    }

    public synchronized void insert(Village t){
        //if (N == arr.length - 1) resize(2*N + 1);
        
        arr[N] = t;
        table.put(t, N);
        
        N++;
      
        swim(N-1);
    }
    
    public synchronized Village peek(){
    	if (isEmpty()) return null;
    	return arr[0];	
    }

    public synchronized Village poll(){
        if (isEmpty()) return null;
        Village t= arr[0];
        N--;	
        exch(0,N);
        arr[N+1] = null;
        sink(0);
        table.remove(t);
       
        return t;

        //resize this array
        //if (N == (arr.length -1)/4) resize((arr.length-1) / 2 + 1);
        
    }
    //helper methods
    public String toString(){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < N; i ++)
            sb.append(arr[i].toString()+" ");
        
        return sb.toString();
    }

    public boolean isEmpty(){
        return N == 0;
    }
    private void resize(int capacity){
        Village[] copy = (Village[]) new Comparable[capacity];
        for(int i = 0; i <= N; i ++ )
            copy[i] = arr[i];
        arr = copy;
    }

    private synchronized void swim(int k){
    	int p = (k-1)/2;
 
        while(k > 0 && (arr[k]).compareTo(arr[p])<0){
            exch(k,p);
            k = p;
            p=(k-1)/2;
        }
    }
    
    public synchronized void decreaseKey(Village t, int dist){
    	//System.out.println("t: " + t);
    	//System.out.println("table: " + table);
    	try{
    		int index = table.get(t);
        	arr[index].setMinDistance(dist);
        	swim(index);
    	}
    	catch (NullPointerException n){
    		//System.out.println("Village not in table");
    		
    	}
    	
    	//System.out.println("after get"); 
    	
    	//System.out.println(t.getName()+": "+index);

    }

    private synchronized void sink(int k){
    	int c = smallerChild(k,N);
    	
    	while(c<N && (arr[k]).compareTo(arr[c])>0){
    		exch(k,c);
    		k=c;
    		c=smallerChild(k,N);
    	}
    	
    }
    
    public synchronized int smallerChild(int k, int n) {
    	int c= 2*k + 2; // k’s right child
    	if (c >= n || (arr[c-1]).compareTo(arr[c]) < 0)
    	c= c-1;
    	return c;
    	}

    private synchronized void exch(int i, int j){
    	table.replace(arr[i], j);
    	table.replace(arr[j], i);
        Village temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        
    }
}
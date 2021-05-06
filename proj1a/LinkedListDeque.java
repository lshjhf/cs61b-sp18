public class LinkedListDeque <T>{
    public class LinkedNode{
        public T item;
        public LinkedNode prev;
        public LinkedNode next;
        public LinkedNode (T i,LinkedNode p,LinkedNode n){
            item =i ;
            prev=p;
            next=n;
        }
    }

    private LinkedNode sentinel;
    private LinkedNode last;
    int size;

    public LinkedListDeque(){
        sentinel = new LinkedNode(null,null,null);
        sentinel.prev=sentinel;
        sentinel.next=sentinel;
        last=sentinel;
        size=0;

    }
    public void addFirst(T item) {
    sentinel.next= new LinkedNode(item,sentinel,sentinel.next);
    sentinel.next.next.prev=sentinel.next;
    if (size==0)last=sentinel.next;
    size+=1;
    }

    public void addLast(T item){
        last.next= new LinkedNode(item,last,sentinel);

        last=last.next;
        if (size==0)sentinel.next=last;
        sentinel.prev=last;
        size+=1;
    }
    public boolean isEmpty(){
     return (size==0);
    }
    public int size(){
     return size;
    }
    public void printDeque(){
    LinkedNode b= sentinel.next;
    for(int y=0;y<size();y++){
        System.out.println(b.item);
        b=b.next;
    }
    }
    public T removeFirst(){
        if (size==0) return null;
        if (size==1) {T a= sentinel.next.item;sentinel.next= sentinel;sentinel.prev=sentinel;size-=1;return a;}
        T a=sentinel.next.item;
        sentinel.next=sentinel.next.next;
        sentinel.next.prev=sentinel;
    size-=1;
    return a;
    }
    public T removeLast(){
        if (size==0) {return null;}
        if (size==1) {T a= sentinel.next.item;sentinel.next= sentinel;sentinel.prev=sentinel;size-=1;return a;}
    T a= sentinel.prev.item;
        sentinel.prev=sentinel.prev.prev;
        sentinel.prev.next=sentinel;
        size-=1;
        return a;
    }
    public T get(int index){
    LinkedNode b= sentinel.next;
    for(int i=0;i<index;i++){
        b=b.next;
    }
    return b.item;
    }

    public T getRecursive(int index){
        int o=0;
        LinkedNode e= sentinel.next;
        if (size==0) return null;
       return getRecursivehelp(index,o,e);

    }
    public T getRecursivehelp(int index,int help,LinkedNode point){

        if(index==help) return point.item;
        return getRecursivehelp(index,help+1,point.next);

    }

}

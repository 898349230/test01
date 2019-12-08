package geettime.linkedList;

/**
 * @classname: SingleLinkedList
 * @description:
 * @author: sunxinbo
 * @time: 2019/12/7、13:45
 */
public class SingleLinkedList<T> {

    private SingleNode<T> head;
    private SingleNode<T> tail;
    // 链表大小
    private int size;
    // 返回当前链表长度
    public int add(T obj){
        if(head == null){
            head = new SingleNode<>();
            head.setObj(obj);
            tail = head;
            return ++size;
        }
        SingleNode node = new SingleNode(obj, null);
        tail.setNext(node);
        tail = node;
        return ++size;
    }

    public boolean remove(T obj){
        if(head == null){
            return false;
        }
        // 头结点就是要删除的节点
        if(obj.equals(head.getObj())){
            head = head.getNext();
            size--;
            return true;
        }
        SingleNode<T> cur = head;
        SingleNode<T> pre = new SingleNode<>();
//        判断当前 node 是否是查找的 node，不是的话指针向前移动
        while(!obj.equals(cur.getObj())){
            pre = cur;
            cur = cur.getNext();
        }
//        找到相同的节点，判断是否为 tail
        if(cur == tail){
            pre.setNext(null);
            tail = pre;
            size--;
            return true;
        }
//            设置前一个节点的next指向当前节点的下一个
        pre.setNext(cur.getNext().getNext());
        size--;
        return true;
    }

    public void print(){
        System.out.print("size：" + size + ": ");
        if(null == head){
            System.out.println("SingleLinkedList is empty");
        }
        SingleNode<T> cur = head;
        while(null != cur){
            System.out.print(cur.getObj() + " -> ");
            cur = cur.getNext();
        }
    }

    public void reverse(){
        if(head == null){
            return;
        }
        this.tail = head;
        SingleNode<T> cur = head;
        SingleNode<T> next;
        SingleNode<T> pre = null;
        while(cur != null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        this.head = pre;
    }

    public class SingleNode<T> {

        private SingleNode<T> next;
        private T obj;

        public SingleNode getNext() {
            return next;
        }

        public void setNext(SingleNode next) {
            this.next = next;
        }

        public T getObj() {
            return obj;
        }

        public void setObj(T obj) {
            this.obj = obj;
        }

        public SingleNode() {
        }

        public SingleNode(T obj, SingleNode next) {
            this.next = next;
            this.obj = obj;
        }
    }

}

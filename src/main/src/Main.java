public class Main {
    public static void main(String[] args) {
        SkipList<Integer>list=new SkipList<Integer>();
        for(int i=1;i<20;i++){
            list.addToList(new SkipNode(i,666));
        }
        list.printList();
        list.deleteElem(4);
        list.deleteElem(8);
        list.printList();
    }
}

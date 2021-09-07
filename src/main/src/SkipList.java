import java.util.Random;
import java.util.Stack;

public class SkipList<T>{
    SkipNode head;
    int highLvl;
    Random random;

    final int MAX_LEVEL_SIZE = 32;

    public SkipList(){
        this.head = new SkipNode(Integer.MIN_VALUE, null);
        this.highLvl = 0;
        this.random = new Random();
    }

    public SkipNode searchSkipList(int key){
        SkipNode curr = this.head;
        while(curr != null){
            if(curr.key == key){
                return curr;
            }
            else if(curr.right == null){
                curr = curr.down;
            }
            else if(curr.right.key > key){
                curr = curr.down;
            }
            else{
                curr = curr.right;
            }
        }
        return null;
    }

    public void addToList(SkipNode node){
        int key = node.key;
        SkipNode findNode = this.searchSkipList(key);
        if(findNode != null){
            findNode.value = node.value;
            return;
        }

        Stack<SkipNode> stack = new Stack<>();
        SkipNode curr= this.head;
        while(curr != null){
            if(curr.right == null || curr.right.key > key){
                stack.add(curr);
                curr = curr.down;
            }
            else{
                curr = curr.right;
            }
        }
        //now we found the node in the correct spot in the most bottom level

        int lvl = 1;
        SkipNode downNode = null;
        while(!stack.isEmpty()) {
            curr = stack.pop();
            SkipNode newNode = new SkipNode(node.key, node.value);
            newNode.down = downNode;
            downNode = newNode;
            if (curr.right == null) {
                curr.right = newNode;
            } else {
                newNode.right = curr.right;
                curr.right = newNode;
            }
            if (lvl > MAX_LEVEL_SIZE) {
                break;
            }
            double num = random.nextDouble();
            if (num > 0.5) {
                break;
            }
            lvl++;
            if (lvl > this.highLvl) {
                this.highLvl = lvl;
                SkipNode newHeadNode = new SkipNode(Integer.MIN_VALUE, null);
                newHeadNode.down = this.head;
                this.head = newHeadNode;
                stack.add(this.head);
            }
        }
    }

    public void deleteElem(int key){
        SkipNode curr = this.head;
        while(curr != null){
            if(curr.right == null){
                curr = curr.down;
            }
            else if(curr.right.key == key){
                curr.right = curr.right.right;
                curr = curr.down;
            }
            else if(curr.right.key > key){
                curr = curr.down;
            }
            else{
                curr = curr.right;
            }
        }
        //recompute height
        while(this.head.right == null){
            this.head = this.head.down;
            this.highLvl --;
        }
    }

    public void printList() {
        SkipNode teamNode=this.head;
        int index=1;
        SkipNode last=teamNode;
        while (last.down!=null){
            last=last.down;
        }
        while (teamNode!=null) {
            SkipNode enumNode=teamNode.right;
            SkipNode enumLast=last.right;
            System.out.printf("%-8s","head->");
            while (enumLast!=null&&enumNode!=null) {
                if(enumLast.key==enumNode.key)
                {
                    System.out.printf("%-5s",enumLast.key+"->");
                    enumLast=enumLast.right;
                    enumNode=enumNode.right;
                }
                else{
                    enumLast=enumLast.right;
                    System.out.printf("%-5s","");
                }

            }
            teamNode=teamNode.down;
            index++;
            System.out.println();
        }
    }
}

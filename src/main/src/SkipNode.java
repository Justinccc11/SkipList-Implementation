import java.util.Random;
import java.util.Stack;


public class SkipNode<T> {
    int key;
    T value;
    SkipNode right;
    SkipNode down;
    public SkipNode(int k, T val){
        this.key = k;
        this.value = val;
        this.right = null;
        this.down = null;
    }
}


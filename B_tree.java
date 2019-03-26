/******************************************************************************
 *  Copyright 2002-2018, Robert Sedgewick and Kevin Wayne.
 *   *  You should have received a copy of the GNU General Public License
 *  	along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 *  B_tree 구조 연습
 *  특성 1. root Node가 leaf node인 경우를 제외하고는 항상 최소 2개의 children을 가진다.
 *     2. root node와 leaf node를 제외한 모든 node들은 최소 [M/2], 최소 M개의 서브트리를 가진다.
 *     3. 모든 leaf node들은 같은 level에 있어야 한다.
 *     4. 새로운 key 값은 leaf node에 삽입된다.
 *     5. node 내의 key 값들은 오름차순이다.
 *     6. leaf node는 최소 [M/2]-1 개의 key를 가지고 있다.
 *  */
public class B_tree<Key extends Comparable<Key>, Value>{

	private static final int M = 4; // M=4 로 정함
	
	private Node root; 
	private int height; // 트리의 깊이
	private int n; // number의  key-value
	
	private static final class Node
	{
		private int m;
		private Entry [] children = new Entry[M];
		
		private Node(int k)
		{
			m = k;
		}
	}
	
	private static class Entry
	{
		private Comparable key;
		private final Object val;
		private Node next;
		public Entry(Comparable key, Object val, Node next)
		{
			this.val = val;
			this.key = key;
			this.next = next;
		}
	}
	
	public B_tree() // 루트 생성
	{
		root = new Node(0);
	}
	
	public boolean isEmpty()
	{
		return size()==0;
	}
	public int size()
	{
		return n;
	}
	public int height()
	{
		return height;
	}
	public Value get(Key key)
	{
		if(key==null)
		{
			throw new IllegalArgumentException("argument get() is NULL");
			
		}
		return search(root, key, height);
	}
	private Value search(Node x, Key key , int ht)
	{
		Entry[] children = x.children;
		if(ht==0)
		{
			for(int j=0; j<x.m;j++)
			{
				if(eq(key, children[j].key)) return (Value) children[j].val;
			}
		}
		else
		{
			for(int j=0;j<x.m;j++)
			{
				if(j+1==x.m || less(key, children[j+1].key))
				{
					return search(children[j].next, key, ht-1);
				}
			}
		}
		return null;
	}
	
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("argument key to put() is null");
        Node u = insert(root, key, val, height); 
        n++;
        if (u == null) return;

        Node t = new Node(2);
        t.children[0] = new Entry(root.children[0].key, null, root);
        t.children[1] = new Entry(u.children[0].key, null, u);
        root = t;
        height++;
    }

    private Node insert(Node h, Key key, Value val, int ht) {
        int j;
        Entry t = new Entry(key, val, null);

        if (ht == 0) {
            for (j = 0; j < h.m; j++) {
                if (less(key, h.children[j].key)) break;
            }
        }

        else {
            for (j = 0; j < h.m; j++) {
                if ((j+1 == h.m) || less(key, h.children[j+1].key)) {
                    Node u = insert(h.children[j++].next, key, val, ht-1);
                    if (u == null) return null;
                    t.key = u.children[0].key;
                    t.next = u;
                    break;
                }
            }
        }

        for (int i = h.m; i > j; i--)
            h.children[i] = h.children[i-1];
        h.children[j] = t;
        h.m++;
        if (h.m < M) return null;
        else         return split(h);
    }

    private Node split(Node h) {
        Node t = new Node(M/2);
        h.m = M/2;
        for (int j = 0; j < M/2; j++)
            t.children[j] = h.children[M/2+j]; 
        return t;    
    }

    public String toString() {
        return toString(root, height, "") + "\n";
    }

    private String toString(Node h, int ht, String indent) {
        StringBuilder s = new StringBuilder();
        Entry[] children = h.children;

        if (ht == 0) {
            for (int j = 0; j < h.m; j++) {
                s.append(indent + children[j].key + " " + children[j].val + "\n");
            }
        }
        else {
            for (int j = 0; j < h.m; j++) {
                if (j > 0) s.append(indent + "(" + children[j].key + ")\n");
                s.append(toString(children[j].next, ht-1, indent + "     "));
            }
        }
        return s.toString();
    }

    private boolean less(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }
    private boolean eq(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }
	
    public static void main(String[] args) throws IllegalAccessException {
    
    	B_tree<String, String> st = new B_tree<String, String>();

        st.put("www.cs.princeton.edu", "128.112.136.12");
        st.put("www.cs.princeton.edu", "128.112.136.11");
        st.put("www.princeton.edu",    "128.112.128.15");
        st.put("www.yale.edu",         "130.132.143.21");
        st.put("www.simpsons.com",     "209.052.165.60");
        st.put("www.apple.com",        "17.112.152.32");
        st.put("www.amazon.com",       "207.171.182.16");
        st.put("www.ebay.com",         "66.135.192.87");
        st.put("www.cnn.com",          "64.236.16.20");
        st.put("www.google.com",       "216.239.41.99");
        st.put("www.nytimes.com",      "199.239.136.200");
        st.put("www.microsoft.com",    "207.126.99.140");
        st.put("www.dell.com",         "143.166.224.230");
        st.put("www.slashdot.org",     "66.35.250.151");
        st.put("www.espn.com",         "199.181.135.201");
        st.put("www.weather.com",      "63.111.66.11");
        st.put("www.yahoo.com",        "216.109.118.65");


        System.out.println("cs.princeton.edu:  " + st.get("www.cs.princeton.edu"));
        System.out.println("hardvardsucks.com: " + st.get("www.harvardsucks.com"));
        System.out.println("simpsons.com:      " + st.get("www.simpsons.com"));
        System.out.println("apple.com:         " + st.get("www.apple.com"));
        System.out.println("ebay.com:          " + st.get("www.ebay.com"));
        System.out.println("dell.com:          " + st.get("www.dell.com"));
        System.out.println();

        System.out.println("size:    " + st.size());
        System.out.println("height:  " + st.height());
        System.out.println(st);
        System.out.println();
    }
}

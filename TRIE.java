import java.util.ArrayList;
import java.util.List;


public class TRIE {

	private Node root; // 루트 노드
	private int indexofSingleChild; // 단일 노드의 인덱스
	
	public TRIE() {
		this.root = new Node(""); // 루트 노드를 빈 문자로 초기화
	}
	//삽입 메서드
	public void insert(String key, int value){
		Node tempNode = root;
		
		// 입력한 key 와 길이  만큼 반복
		for(int i=0; i<key.length(); i++)
		{
			char c = key.charAt(i); // 알파벳 추출
			int asciiIndex = transformASCIIIndex(c);
			
			if(tempNode.getChild(asciiIndex) == null){
				Node node = new Node(String.valueOf(c)); // 새로운 노드 생성
				tempNode.setChild(asciiIndex, node, value);
				tempNode = node;
			}
			else {
				tempNode = tempNode.getChild(asciiIndex);
			}
		}
		
		tempNode.setLeaf(true); // 앏파벳 문자들의 삽입이 완료되고 마지막 노드를 leaf 노드로 설정
	}
	// ASCII 값을 배열에 인덱스에 맞게 변환
	private int transformASCIIIndex(char c)
	{
		return c - 'a';
	}
	
	// 탐색 : 해당 키가 존재하는 지 여부 반환
	public boolean search(String key){
		Node trieNode = root;
		
		for(int i=0; i< key.length(); i++)
		{
			int asciiIndex = transformASCIIIndex(key.charAt(i));
			
			if(trieNode.getChild(asciiIndex) == null){
				return false;
			}
			else
			{
				trieNode = trieNode.getChild(asciiIndex);
			}
		}
		
		return true;
	}
	
	// 탐색 : 해당 키 값에 해당하는 값 반환
	public Integer  searchAsMap(String key) {
		Node trieNode = root;
		
		// 입력한 key의 탐색 길이만큼 반복
		for (int i=0; i < key.length(); i++){
			int asciiIndex = transformASCIIIndex(key.charAt(i));
			
			if(trieNode.getChild(asciiIndex)!=null){
				trieNode = trieNode.getChild(asciiIndex); // 하위 노드로 이동
			}
			else{
				return null;
			}
		}
		return trieNode.getValue();
	}
	
	// 자동완성
	public List<String> allWordsWithPrefix(String prefix)
	{
		Node trieNode = root;
		
		List<String> allWords = new ArrayList<>();
		
		// 접두사 길이 만큼 반복 수행
		for(int i=0; i<prefix.length(); i++)
		{
			int asciiIndex = transformASCIIIndex(prefix.charAt(i));
			trieNode = trieNode.getChild(asciiIndex);
		}
		
		collect(trieNode, prefix, allWords);
		return allWords;
	}
	
	// 자동완성 단어 수집
	private void collect(Node node, String prefix, List<String> allwords)
	{
		if(node == null)
		{
			return;
		}
		
		// leaf 노드인 경우 allwords에 저장
		if(node.isLeaf()){
			allwords.add(prefix);
		}
		// 노드의 자식노드의 수 만큼 반복 수행
		for  (Node childNode : node.getChildren()){
			if(childNode == null)
			{
				continue;
			}
			// 자식노드의 알파벳
			String childCharacter = childNode.getCharacter();
			collect(childNode, prefix+childCharacter, allwords);
		}
				
	}
	// 정렬
	public void sort()
	{
		List<String> list = allWordsWithPrefix("");
		for(String s : list)
		{
			System.out.print(s + " ");
		}
		System.out.println();
	}
	
	// 가장 긴 접두사 반환
	public String longestCommonPrefix()
	{
		Node trieNode = root; // 루트 노드로 초기화
		String longestCommonPrefix = ""; // 빈 문자열로 초기화
		
		while(countNumofChildren(trieNode) == 1 && !trieNode.isLeaf())
		{
			trieNode = trieNode.getChild(indexofSingleChild);
			longestCommonPrefix = longestCommonPrefix + String.valueOf((char) (indexofSingleChild + 'a'));
		}
		
		return longestCommonPrefix;
	}
	
	private int countNumofChildren(Node trieNode)
	{
		int numOfChildren = 0; // 하위 노드 개수 0으로 초기화
		
		// 하위 노드의 개수 만큼 반복 수행
		for(int i = 0; i < trieNode.getChildren().length; i++)
		{
			// 하위 노드가 존재하면
			if(trieNode.getChild(i)!=null){
				numOfChildren++;
				indexofSingleChild = i;
			}
		}
		return numOfChildren;
	}
	
	public class Node
	{
		private String character;
		private int value;
		private Node[] children;
		private boolean leaf;
		
		// 생성자
		public Node(String c)
		{
			this.character  = c;
			this.children = new Node[Constants.ALPHABET_SIZE];
		}
		
		// getter, setter, toString
		public Node getChild(int index) {
			return children[index];
		}
		
		public void setChild(int index, Node node, int value){
			node.setValue(value);
			this.children[index] =node;
		}
		
		public int getValue()
		{
			return value;
		}
		public void setValue(int value)
		{
			this.value = value;
		}
		public String getCharacter()
		{
			return character;
		}
		public void setCharacter(String character)
		{
			this.character = character;
		}

		public Node[] getChildren() {
			return children;
		}

		public void setChildren(Node[] children) {
			this.children = children;
		}

		public boolean isLeaf() {
			return leaf;
		}

		public void setLeaf(boolean leaf) {
			this.leaf = leaf;
		}
		
		@Override
	    public String toString()
	    {
	    	return this.character;
	    }
	}
	
	// 상수 클래스
	public class Constants
	{
		// 알파벳의 숫자만큼 크기 설정
		public static final int ALPHABET_SIZE = 26;
	}
}



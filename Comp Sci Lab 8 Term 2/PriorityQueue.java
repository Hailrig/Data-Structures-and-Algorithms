public interface PriorityQueue<K, V> {
	public Entry<K, V> insert(K k, V v);
	public Entry<K, V> removeMin();
	public Entry<K, V> min();
	public int size();
	public boolean isEmpty();
}


public interface AdaptablePriorityQueue<K, V> extends PriorityQueue<K, V> {
	public Entry<K, V> remove (Entry<K, V> entry);
	public V replaceValue(Entry<K, V> e, V value);
	public K replaceKey(Entry<K, V> entry, K k);
}

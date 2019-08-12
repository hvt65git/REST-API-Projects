package ht_twit_7;

 class Param<K, V> {
	K key;
	V value;

	K getKey() {
		return this.key;
	}

	V getValue() {
		return this.value;
	}

	void put(K key, V value) {
		this.key = key;
		this.value = value;
	}

	Param(K key, V value) {
		this.key = key;
		this.value = value;
	}

}

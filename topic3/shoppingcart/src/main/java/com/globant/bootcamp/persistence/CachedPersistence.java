package com.globant.bootcamp.persistence;

import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class CachedPersistence<T extends Identifiable<PK>, PK> {
	private final Map<PK, T> dataPersistence = new HashMap<>();

	public T persist(T data) {
		dataPersistence.put(data.getId(), data);
		return data;
	}

	public T remove(PK id) {
		return dataPersistence.remove(id);
	}

	public T read(PK id) {
		return dataPersistence.get(id);
	}

	public boolean contains(PK id) {
		return dataPersistence.containsKey(id);
	}
}

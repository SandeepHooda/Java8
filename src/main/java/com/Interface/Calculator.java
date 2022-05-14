package com.Interface;

@FunctionalInterface // - optional
public interface Calculator<T> {

	public T add(T a, T b);
	default int subtract(int a, int b) {
		return a-b;
	}
}

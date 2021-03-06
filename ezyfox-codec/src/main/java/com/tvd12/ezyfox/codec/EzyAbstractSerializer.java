package com.tvd12.ezyfox.codec; 
 
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.tvd12.ezyfox.codec.EzyMessageSerializer;
import com.tvd12.ezyfox.function.EzyParser;
import com.tvd12.ezyfox.io.EzyMaps; 
 
public abstract class EzyAbstractSerializer<T> implements EzyMessageSerializer { 
 
	protected Map<Class<?>, EzyParser<Object, T>> parsers = defaultParsers();
	 
	protected T parseNotNull(Object value) {
		EzyParser<Object, T> parser = getParser(value.getClass());
		if(parser != null)
			return parseWithParser(parser, value);
		return parseWithNoParser(value);
		 
	} 
	 
	protected T parseWithParser(EzyParser<Object, T> parser, Object value) {
		return parser.parse(value);
	} 
	 
	protected T parseWithNoParser(Object value) {
		throw new IllegalArgumentException("has no parse for " + value.getClass());
	} 
	 
	protected abstract T parseNil(); 
	 
	protected EzyParser<Object, T> getParser(Class<?> type) {
		return EzyMaps.getValue(parsers, type);
	} 
	 
	protected Map<Class<?>, EzyParser<Object, T>> defaultParsers() {
		Map<Class<?>, EzyParser<Object, T>> parsers = new ConcurrentHashMap<>();
		addParsers(parsers);
		return parsers;
	} 
	 
	protected abstract void addParsers(Map<Class<?>, EzyParser<Object, T>> parsers);
	 
} 

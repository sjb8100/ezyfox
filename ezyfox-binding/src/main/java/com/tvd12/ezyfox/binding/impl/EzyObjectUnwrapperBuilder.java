package com.tvd12.ezyfox.binding.impl;

import java.util.concurrent.atomic.AtomicInteger;

import com.tvd12.ezyfox.asm.EzyInstruction;
import com.tvd12.ezyfox.binding.EzyUnmarshaller;
import com.tvd12.ezyfox.binding.EzyUnwrapper;
import com.tvd12.ezyfox.reflect.EzyClass;

@SuppressWarnings("rawtypes")
public class EzyObjectUnwrapperBuilder extends EzyObjectReaderBuilder {

	protected static AtomicInteger COUNT = new AtomicInteger(0);

	public EzyObjectUnwrapperBuilder(EzyClass clazz) {
		super(clazz);
	}
	
	@Override
	protected String getReadMethodName() {
		return "unwrap";
	}
	
	@Override
	protected Class<?> getReaderInterface() {
		return EzyUnwrapper.class;
	}
	
	@Override
	protected Class[] getReaderMethodParameterTypes() {
		return new Class[] {EzyUnmarshaller.class, Object.class, Object.class};
	}
	
	@Override
	protected EzyInstruction newOutputObjectInstruction() {
		return new EzyInstruction("", "", false)
				.cast(clazz.getClazz(), "arg2");
	}
	
	@Override
	protected String getImplClassName() {
		return clazz.getName() + "$EzyObjectUnwrapper$EzyAutoImpl$" + COUNT.incrementAndGet();
	}
}
package com.tvd12.ezyfox.hazelcast.testing.service;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.hazelcast.service.EzyMongoAccountService;
import com.tvd12.ezyfox.hazelcast.testing.HazelcastBaseTest;

public class EzyMongoAccountServiceTest extends HazelcastBaseTest {

	@Test
	public void test() {
		EzyMongoAccountService service = new EzyMongoAccountService();
		service = new EzyMongoAccountService(HZ_INSTANCE);
		service.clear();
	}
	
}

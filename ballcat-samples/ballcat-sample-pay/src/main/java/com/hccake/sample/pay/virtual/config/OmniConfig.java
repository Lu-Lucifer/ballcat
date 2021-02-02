package com.hccake.sample.pay.virtual.config;

import com.hccake.starter.pay.virtual.BitcoinProperties;
import java.util.function.Supplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import live.lingting.virtual.currency.properties.OmniProperties;

/**
 * @author lingting 2021/1/5 16:24
 */
@Configuration
public class OmniConfig {

	@Bean
	public OmniProperties bitcoinProperties(BitcoinProperties properties) {
		// omni 使用的接口限制请求频率(5-10s一次), 需要根据项目实现, 这里直接返回true
		Supplier<Boolean> lock = () -> true;
		Supplier<Boolean> unlock = () -> true;

		return new OmniProperties()
				// 节点
				.setOmniEndpoints(properties.getOmni().getEndpoints())
				// 比特节点
				.setBitcoinEndpoints(properties.getEndpoints())
				// 请求锁
				.setLock(lock).setUnlock(unlock);
	}

}

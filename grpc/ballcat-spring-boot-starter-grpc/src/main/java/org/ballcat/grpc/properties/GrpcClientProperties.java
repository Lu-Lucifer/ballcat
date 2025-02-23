/*
 * Copyright 2023-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ballcat.grpc.properties;

import java.util.concurrent.TimeUnit;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lingting 2023-04-06 15:18
 */
@Data
@ConfigurationProperties(GrpcClientProperties.PREFIX)
public class GrpcClientProperties {

	public static final String PREFIX = "ballcat.grpc.client";

	private String host;

	private Integer port = 80;

	private boolean usePlaintext = true;

	private boolean enableRetry = true;

	/**
	 * 单位: 毫秒
	 */
	private long keepAliveTime = TimeUnit.SECONDS.toMillis(10);

	/**
	 * 单位: 毫秒
	 */
	private long keepAliveTimeout = TimeUnit.MINUTES.toMillis(1);

}

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

package org.ballcat.desensitize.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Hccake 2021/1/23
 *
 */
@Getter
@RequiredArgsConstructor
public enum SlideDesensitizationTypeEnum {

	/**
	 * 自定义类型
	 */
	CUSTOM(0, 0, "*"),

	/**
	 * 全部字符替换为 *
	 */
	ALL_ASTERISK(0, 0, "*"),

	/**
	 * 【银行卡号】, 前6位和后4位不脱敏，中间脱敏 eg. 330150******1234
	 */
	BANK_CARD_NO(6, 4, "*"),

	/**
	 * 【身份证号】年月日脱敏，前6后4不脱敏 eg. 655356*******1234
	 */
	ID_CARD_NO(6, 4, "*"),

	/**
	 * 【手机号】，某些国家手机号位数短，所以不做前三后四，使用前三后二
	 */
	PHONE_NUMBER(3, 2, "*");

	/**
	 * 左边的明文数
	 */
	private final int leftPlainTextLen;

	/**
	 * 右边的明文数
	 */
	private final int rightPlainTextLen;

	/**
	 * 剩余部分字符逐个替换的字符串
	 */
	private final String maskString;

}

package com.hccake.sample.pay.wx;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.hccake.starte.pay.wx.WxPay;
import com.hccake.starte.pay.wx.response.WxPayCallback;
import com.hccake.starte.pay.wx.response.WxPayOrderQueryResponse;
import com.hccake.starte.pay.wx.utils.WxPayUtil;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lingting 2021/2/25 13:55
 */
@RestController
@RequestMapping("wx")
@RequiredArgsConstructor
public class WxController {

	private final WxPay wxPay;

	private static final Snowflake snowflake = IdUtil.createSnowflake(1, 1);

	@SneakyThrows
	@GetMapping
	public String notice(HttpServletRequest request) {
		String sn = snowflake.nextIdStr();
		BigDecimal val = new BigDecimal("0.01");
		String ip = "27.115.44.246";
		// WxPayResponse response = wxPay.nativePay(sn, val, ip, "商品");
		WxPayOrderQueryResponse queryResponse = wxPay.query("1364829305962557441", "");

		String data = "**";
		WxPayCallback callback = WxPayCallback.of(WxPayUtil.xmlToMap(data));
		System.out.println(callback.checkSign(wxPay));
		return "";
	}

}

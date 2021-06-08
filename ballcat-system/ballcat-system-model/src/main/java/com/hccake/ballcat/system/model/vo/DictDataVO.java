package com.hccake.ballcat.system.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Hccake
 * @version 1.0
 * @date 2020/4/9 15:48
 */
@Data
@ApiModel(value = "字典数据VO")
public class DictDataVO {

	/**
	 * 字典标识
	 */
	@ApiModelProperty(value = "字典标识")
	private String dictCode;

	@ApiModelProperty("字典值类型")
	private Integer valueType;

	/**
	 * 字典Hash值
	 */
	@ApiModelProperty(value = "字典Hash值")
	private String hashCode;

	/**
	 * 字典项列表
	 */
	@ApiModelProperty(value = "字典项列表")
	private List<DictItemVO> dictItems;

}

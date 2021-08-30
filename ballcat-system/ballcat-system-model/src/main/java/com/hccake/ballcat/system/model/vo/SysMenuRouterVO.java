package com.hccake.ballcat.system.model.vo;

import com.hccake.ballcat.common.i18n.I18nClass;
import com.hccake.ballcat.common.i18n.I18nField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 菜单权限视图对象
 *
 * @author hccake 2021-04-06 17:59:51
 */
@Data
@I18nClass
@ApiModel(value = "菜单权限视图对象")
public class SysMenuRouterVO {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单ID
	 */
	@ApiModelProperty(value = "菜单ID")
	private Integer id;

	/**
	 * 父级ID
	 */
	@ApiModelProperty(value = "父级ID")
	private Integer parentId;

	/**
	 * 菜单名称
	 */
	@I18nField(condition = "type != 2")
	@ApiModelProperty(value = "菜单名称")
	private String title;

	/**
	 * 菜单图标
	 */
	@ApiModelProperty(value = "菜单图标")
	private String icon;

	/**
	 * 路由地址
	 */
	@ApiModelProperty(value = "路由地址")
	private String path;

	/**
	 * 打开方式 (1组件 2内链 3外链)
	 */
	@ApiModelProperty(value = "打开方式 (1组件 2内链 3外链)")
	private Integer targetType;

	/**
	 * 定位标识 (打开方式为组件时其值为组件相对路径，其他为URL地址)
	 */
	@ApiModelProperty(value = "定位标识 (打开方式为组件时其值为组件相对路径，其他为URL地址)")
	private String uri;

	/**
	 * 组件缓存：0-开启，1-关闭
	 */
	@ApiModelProperty(value = "组件缓存：0-开启，1-关闭")
	private Integer keepAlive;

	/**
	 * 隐藏菜单: 0-否，1-是
	 */
	@ApiModelProperty(value = "隐藏菜单:  0-否，1-是")
	private Integer hidden;

	/**
	 * 菜单类型 （0目录，1菜单，2按钮）
	 */
	@ApiModelProperty(value = "菜单类型 （0目录，1菜单，2按钮）")
	private Integer type;

	/**
	 * 备注信息
	 */
	@ApiModelProperty(value = "备注信息")
	private String remarks;

}
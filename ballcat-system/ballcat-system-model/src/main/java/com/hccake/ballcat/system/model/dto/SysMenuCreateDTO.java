package com.hccake.ballcat.system.model.dto;

import com.hccake.ballcat.common.i18n.I18nMessage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 菜单权限新建的DTO
 *
 * @author hccake 2021-04-06 17:59:51
 */
@Data
@ApiModel(value = "菜单权限新建的DTO")
public class SysMenuCreateDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单ID
	 */
	@NotNull(message = "id：{}")
	@ApiModelProperty(value = "菜单ID")
	private Integer id;

	/**
	 * 父级ID
	 */
	@NotNull(message = "parentId：{}")
	@ApiModelProperty(value = "父级ID")
	private Integer parentId;

	/**
	 * 菜单名称
	 */
	@ApiModelProperty(value = "菜单名称")
	private String title;

	/**
	 * 菜单图标
	 */
	@ApiModelProperty(value = "菜单图标")
	private String icon;

	/**
	 * 授权标识
	 */
	@ApiModelProperty(value = "授权标识")
	private String permission;

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
	 * 显示排序
	 */
	@ApiModelProperty(value = "显示排序")
	private Integer sort;

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

	/**
	 * 菜单标题对应的国际化信息
	 */
	@Valid
	@ApiModelProperty(value = "菜单标题对应的国际化信息")
	private List<I18nMessage> i18nMessages;

}

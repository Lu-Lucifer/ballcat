package com.hccake.ballcat.admin.modules.notify.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.hccake.ballcat.admin.constants.AnnouncementStatusEnum;
import com.hccake.ballcat.admin.modules.notify.model.entity.Announcement;
import com.hccake.ballcat.admin.modules.notify.model.qo.AnnouncementQO;
import com.hccake.ballcat.admin.modules.notify.model.vo.AnnouncementVO;
import com.hccake.ballcat.common.core.domain.PageParam;
import com.hccake.ballcat.common.core.domain.PageResult;
import com.hccake.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.hccake.extend.mybatis.plus.mapper.ExtendMapper;
import com.hccake.extend.mybatis.plus.toolkit.WrappersX;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 公告信息
 *
 * @author hccake 2020-12-15 17:01:15
 */
public interface AnnouncementMapper extends ExtendMapper<Announcement> {

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param qo 查询对象
	 * @return 分页结果数据 PageResult
	 */
	default PageResult<AnnouncementVO> queryPage(PageParam pageParam, AnnouncementQO qo) {
		IPage<AnnouncementVO> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<Announcement> wrapperX = WrappersX.lambdaAliasQueryX(Announcement.class)
				.likeIfPresent(Announcement::getTitle, qo.getTitle())
				.inIfPresent(Announcement::getStatus, (Object[]) qo.getStatus())
				.eqIfPresent(Announcement::getRecipientFilterType, qo.getRecipientFilterType());
		this.selectByPage(page, wrapperX);
		return new PageResult<>(page.getRecords(), page.getTotal());
	}

	/**
	 * 更新公共（限制只能更新未发布的公共）
	 * @param announcement 公共信息
	 * @return 更新是否成功
	 */
	default boolean updateIfUnpublished(Announcement announcement) {
		int flag = this.update(announcement,
				Wrappers.<Announcement>lambdaUpdate().eq(Announcement::getId, announcement.getId())
						.eq(Announcement::getStatus, AnnouncementStatusEnum.UNPUBLISHED.getValue()));
		return SqlHelper.retBool(flag);
	}

	/**
	 * 根据参数获取当前用户拉取过，或者未拉取过的有效的公告信息
	 * @param userId 用户ID
	 * @param pulled 当前用户是否拉取过
	 * @return 公告信息列表
	 */
	List<Announcement> listUserAnnouncements(@Param("userId") Integer userId, @Param("pulled") boolean pulled);

}
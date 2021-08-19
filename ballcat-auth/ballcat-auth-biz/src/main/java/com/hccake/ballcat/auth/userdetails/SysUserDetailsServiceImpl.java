package com.hccake.ballcat.auth.userdetails;

import cn.hutool.core.collection.CollectionUtil;
import com.hccake.ballcat.common.security.constant.TokenAttributeNameConstants;
import com.hccake.ballcat.common.security.userdetails.User;
import com.hccake.ballcat.system.model.dto.UserInfoDTO;
import com.hccake.ballcat.system.model.entity.SysUser;
import com.hccake.ballcat.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

/**
 * @author Hccake
 * @version 1.0
 * @date 2019/9/25 20:44
 */
@Slf4j
@RequiredArgsConstructor
public class SysUserDetailsServiceImpl implements UserDetailsService {

	private final SysUserService sysUserService;

	private final UserInfoCoordinator userInfoCoordinator;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser sysUser = sysUserService.getByUsername(username);
		if (sysUser == null) {
			log.error("登陆：用户名错误，用户名：{}", username);
			throw new UsernameNotFoundException("username error!");
		}
		UserInfoDTO userInfoDTO = sysUserService.findUserInfo(sysUser);
		return getUserDetailsByUserInfo(userInfoDTO);
	}

	/**
	 * 根据UserInfo 获取 UserDetails
	 * @param userInfoDTO 用户信息DTO
	 * @return UserDetails
	 */
	public UserDetails getUserDetailsByUserInfo(UserInfoDTO userInfoDTO) {

		SysUser sysUser = userInfoDTO.getSysUser();
		List<String> roles = userInfoDTO.getRoles();
		List<String> permissions = userInfoDTO.getPermissions();

		Set<String> dbAuthsSet = new HashSet<>();
		if (CollectionUtil.isNotEmpty(roles)) {
			// 获取角色
			dbAuthsSet.addAll(roles);
			// 获取资源
			dbAuthsSet.addAll(permissions);

		}
		Collection<? extends GrantedAuthority> authorities = AuthorityUtils
				.createAuthorityList(dbAuthsSet.toArray(new String[0]));

		// 默认将角色和权限放入属性中
		HashMap<String, Object> attributes = new HashMap<>(8);
		attributes.put(TokenAttributeNameConstants.ROLES, new HashSet<>(roles));
		attributes.put(TokenAttributeNameConstants.PERMISSIONS, new HashSet<>(permissions));

		// 用户额外属性
		userInfoCoordinator.coordinateAttribute(sysUser, attributes);

		return new User(sysUser.getUserId(), sysUser.getUsername(), sysUser.getPassword(), sysUser.getNickname(),
				sysUser.getAvatar(), sysUser.getStatus(), sysUser.getOrganizationId(), sysUser.getType(), authorities,
				attributes);
	}

}

package com.hccake.ballcat.admin.modules.sys.controller;

import com.hccake.ballcat.admin.modules.sys.manager.SysDictManager;
import com.hccake.ballcat.admin.modules.sys.model.entity.SysDict;
import com.hccake.ballcat.admin.modules.sys.model.entity.SysDictItem;
import com.hccake.ballcat.admin.modules.sys.model.qo.SysDictQO;
import com.hccake.ballcat.admin.modules.sys.model.vo.DictDataVO;
import com.hccake.ballcat.admin.modules.sys.model.vo.SysDictItemVO;
import com.hccake.ballcat.admin.modules.sys.model.vo.SysDictVO;
import com.hccake.ballcat.commom.log.operation.annotation.CreateOperationLogging;
import com.hccake.ballcat.commom.log.operation.annotation.DeleteOperationLogging;
import com.hccake.ballcat.commom.log.operation.annotation.UpdateOperationLogging;
import com.hccake.ballcat.common.core.domain.PageParam;
import com.hccake.ballcat.common.core.domain.PageResult;
import com.hccake.ballcat.common.core.result.BaseResultCode;
import com.hccake.ballcat.common.core.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 字典表
 *
 * @author hccake
 * @date 2020-03-26 18:40:20
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/dict")
@Api(value = "/sys/dict", tags = "字典表管理")
public class SysDictController {

	private final SysDictManager sysDictManager;

	/**
	 * 通过字典标识查找对应字典项
	 * @param dictCodes 字典标识列表
	 * @return 同类型字典
	 */
	@GetMapping("/data")
	public R<List<DictDataVO>> getDictData(@RequestParam("dictCodes") String[] dictCodes) {
		return R.ok(sysDictManager.queryDictDataAndHashVO(dictCodes));
	}

	/**
	 * 通过字典标识查找对应字典项
	 * @param dictHashCode 字典标识
	 * @return 同类型字典
	 */
	@PostMapping("/invalid-hash")
	public R<List<String>> invalidDictHash(@RequestBody Map<String, String> dictHashCode) {
		return R.ok(sysDictManager.invalidDictHash(dictHashCode));
	}

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param sysDictQO 字典查询参数
	 * @return R<PageResult<SysDictVO>>
	 */
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@GetMapping("/page")
	@PreAuthorize("@per.hasPermission('sys:dict:read')")
	public R<PageResult<SysDictVO>> getSysDictPage(PageParam pageParam, SysDictQO sysDictQO) {
		return R.ok(sysDictManager.dictPage(pageParam, sysDictQO));
	}

	/**
	 * 新增字典表
	 * @param sysDict 字典表
	 * @return R
	 */
	@ApiOperation(value = "新增字典表", notes = "新增字典表")
	@CreateOperationLogging(msg = "新增字典表")
	@PostMapping
	@PreAuthorize("@per.hasPermission('sys:dict:add')")
	public R<?> save(@RequestBody SysDict sysDict) {
		return sysDictManager.dictSave(sysDict) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增字典表失败");
	}

	/**
	 * 修改字典表
	 * @param sysDict 字典表
	 * @return R
	 */
	@ApiOperation(value = "修改字典表", notes = "修改字典表")
	@UpdateOperationLogging(msg = "修改字典表")
	@PutMapping
	@PreAuthorize("@per.hasPermission('sys:dict:edit')")
	public R<?> updateById(@RequestBody SysDict sysDict) {
		return sysDictManager.updateDictById(sysDict) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改字典表失败");
	}

	/**
	 * 通过id删除字典表
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id删除字典表", notes = "通过id删除字典表")
	@DeleteOperationLogging(msg = "通过id删除字典表")
	@DeleteMapping("/{id}")
	@PreAuthorize("@per.hasPermission('sys:dict:del')")
	public R<?> removeById(@PathVariable Integer id) {
		return sysDictManager.removeDictById(id) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除字典表失败");
	}

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param dictCode 字典标识
	 * @return R
	 */
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@GetMapping("/item/page")
	@PreAuthorize("@per.hasPermission('sys:dict:read')")
	public R<PageResult<SysDictItemVO>> getSysDictItemPage(PageParam pageParam,
			@RequestParam("dictCode") String dictCode) {
		return R.ok(sysDictManager.dictItemPage(pageParam, dictCode));
	}

	/**
	 * 新增字典项
	 * @param sysDictItem 字典项
	 * @return R
	 */
	@ApiOperation(value = "新增字典项", notes = "新增字典项")
	@CreateOperationLogging(msg = "新增字典项")
	@PostMapping("item")
	@PreAuthorize("@per.hasPermission('sys:dict:add')")
	public R<?> saveItem(@RequestBody SysDictItem sysDictItem) {
		return sysDictManager.saveDictItem(sysDictItem) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增字典项失败");
	}

	/**
	 * 修改字典项
	 * @param sysDictItem 字典项
	 * @return R
	 */
	@ApiOperation(value = "修改字典项", notes = "修改字典项")
	@UpdateOperationLogging(msg = "修改字典项")
	@PutMapping("item")
	@PreAuthorize("@per.hasPermission('sys:dict:edit')")
	public R<?> updateItemById(@RequestBody SysDictItem sysDictItem) {
		return sysDictManager.updateDictItemById(sysDictItem) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改字典项失败");
	}

	/**
	 * 通过id删除字典项
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id删除字典项", notes = "通过id删除字典项")
	@DeleteOperationLogging(msg = "通过id删除字典项")
	@DeleteMapping("/item/{id}")
	@PreAuthorize("@per.hasPermission('sys:dict:del')")
	public R<?> removeItemById(@PathVariable Integer id) {
		return sysDictManager.removeDictItemById(id) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除字典项失败");
	}

}

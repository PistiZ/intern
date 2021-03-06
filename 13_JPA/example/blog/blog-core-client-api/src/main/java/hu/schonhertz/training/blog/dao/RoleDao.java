package hu.schonhertz.training.blog.dao;

import java.util.List;

import hu.schonhertz.training.blog.dto.RoleDto;

public interface RoleDao extends BaseDao<RoleDto> {

	List<RoleDto> findRolesByUserId(Long id) throws Exception;

	void addRoleToUser(Long roleId, Long userId) throws Exception;

	void removeRoleFromUser(Long roleId, Long userId) throws Exception;

	RoleDto findRoleByName(String name) throws Exception;
}

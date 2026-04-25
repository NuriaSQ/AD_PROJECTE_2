package com.ra34.projecte2.dto.user;

import java.util.List;

public class UserRolesRequestDTO {

    private List<Long> roleIds;

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }
}
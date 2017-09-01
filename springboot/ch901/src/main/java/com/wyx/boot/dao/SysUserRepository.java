package com.wyx.boot.dao;

import com.wyx.boot.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by WangYx on 2017/9/1.
 */
public interface SysUserRepository extends JpaRepository<SysUser,Long> {

    SysUser findByUsername(String username);

}

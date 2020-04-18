package com.blogmang.dao;

import com.blogmang.entity.SystemUpdate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SystemUpdateDao {
    List<SystemUpdate> queryAllSystemUpdate();
    boolean addSystemUpdate(SystemUpdate systemUpdate);
    String selectSystemVersion();
}

package com.blogmang.dao;

import com.blogmang.entity.AboutMe;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AboutMeDao {
    AboutMe queryAboutMe();
    boolean saveAboutMe(AboutMe aboutMe);
    boolean updateAboutMe(AboutMe aboutMe);
}

package com.blogmang.service;

import com.blogmang.entity.AboutMe;

import java.util.List;

public interface AboutMeService {
    boolean saveAboutMe(AboutMe aboutMe);
    AboutMe queryAboutMe();
    boolean updateAboutMe(AboutMe aboutMe);

}

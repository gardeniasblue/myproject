package com.blogmang.service.impl;

import com.blogmang.dao.AboutMeDao;
import com.blogmang.entity.AboutMe;
import com.blogmang.service.AboutMeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AboutMeServiceImpl implements AboutMeService {
    @Autowired
    private AboutMeDao aboutMeDao;
    @Override
    public boolean saveAboutMe(AboutMe aboutMe) {
        aboutMeDao.saveAboutMe(aboutMe);
        return true;
    }

    @Override
    public AboutMe queryAboutMe() {
        return aboutMeDao.queryAboutMe();
    }

    @Override
    public boolean updateAboutMe(AboutMe aboutMe) {
        aboutMeDao.updateAboutMe(aboutMe);
        return true;
    }


}

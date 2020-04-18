package com.blogmang.controller.admin;

import com.blogmang.constants.HttpStatusConstants;
import com.blogmang.dto.Result;
import com.blogmang.entity.BlogTag;
import com.blogmang.entity.SystemUpdate;
import com.blogmang.service.SystemUpdateService;
import com.blogmang.util.DateUtils;
import com.blogmang.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class SystemUpdateController {
    @Autowired
    private SystemUpdateService systemUpdateService;
    @RequestMapping("/v8/queryall")
    @ResponseBody
    public Result<SystemUpdate> systemupdate(){
        List<SystemUpdate> list = systemUpdateService.queryAllSystemUpdate();
        return ResultGenerator.getResultByHttp(HttpStatusConstants.OK,list);
    }
    @RequestMapping("/v8/addsystemupdate")
    @ResponseBody
    public Result<SystemUpdate> addsystemupdate(@Validated SystemUpdate systemUpdate){
        systemUpdate.setCreateTime(DateUtils.getLocalCurrentDate());
        boolean result = systemUpdateService.addSystemUpdate(systemUpdate);
        if(result){
            return ResultGenerator.getResultByHttp(HttpStatusConstants.OK,"成功");
        }else{
            return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR,"失败");
        }

    }

}

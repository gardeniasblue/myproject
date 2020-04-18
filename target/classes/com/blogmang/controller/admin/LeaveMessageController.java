package com.blogmang.controller.admin;

import com.blogmang.constants.HttpStatusConstants;
import com.blogmang.dto.Result;
import com.blogmang.entity.LeaveMessage;
import com.blogmang.entity.SystemUpdate;
import com.blogmang.service.LeaveMessageService;
import com.blogmang.service.SystemUpdateService;
import com.blogmang.util.DateUtils;
import com.blogmang.util.ResultGenerator;
import com.sun.jmx.snmp.internal.SnmpSubSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class LeaveMessageController {
    @Autowired
    private LeaveMessageService leaveMessageService;

    /**
     * 查询通过/未通过审核的留言
     * @return
     */
    @RequestMapping("/v10/querymessagebyyon")
    @ResponseBody
    public Result<LeaveMessage> queryByYN(){
        List<LeaveMessage> leaveMessageList = leaveMessageService.queryAllMessageByYN(0);
        return ResultGenerator.getResultByHttp(HttpStatusConstants.OK,leaveMessageList);
    }
    /**
     * 查询全部留言
     * @return
     */
    @RequestMapping("/v10/queryallmessage")
    @ResponseBody
    public Result<LeaveMessage> queryAll(){
        List<LeaveMessage> leaveMessageList = leaveMessageService.queryAllMessage();
        return ResultGenerator.getResultByHttp(HttpStatusConstants.OK,leaveMessageList);
    }
    @RequestMapping("/v10/tomessagelist")
    public String tomessageList(Model model){
        List<LeaveMessage> leaveMessageList = leaveMessageService.queryAllMessage();
        model.addAttribute("messagelist",leaveMessageList);
        return "admin/message-list";
    }
    @RequestMapping("/v10/delmessage/{id}")
    public String delMessage(@PathVariable("id") Integer id){
        leaveMessageService.delMessage(id);
        return "redirect:/admin/v10/tomessagelist";
    }
    @RequestMapping("/v10/updatemessage/{id}")
    public String updateMessage(@PathVariable("id") Integer id){
        LeaveMessage leaveMessage = new LeaveMessage();
        leaveMessage.setId(id);
        leaveMessage.setLeaveMessageStatus(1);
        leaveMessageService.updateMessage(leaveMessage);
        return "redirect:/admin/v10/tomessagelist";
    }
    @RequestMapping("/v10/reply/{id}")
    public String replyMessage(@PathVariable("id") Integer id,Model model){
        LeaveMessage message = leaveMessageService.queryMessageById(id);
        System.out.println(message);
        model.addAttribute("message",message);
        return "admin/message-reply";
    }

    /**
     * 回复留言
     * @param leaveMessage
     * @return
     */

    @RequestMapping("/v10/replybody")
    public String replyMessage(LeaveMessage leaveMessage){

        leaveMessage.setReplyTime(DateUtils.getLocalCurrentDate());
        leaveMessage.setLeaveMessageStatus(1);
        leaveMessageService.updateMessage(leaveMessage);
        leaveMessageService.replyMessage(leaveMessage);
        return "redirect:/admin/v10/tomessagelist";
    }


}

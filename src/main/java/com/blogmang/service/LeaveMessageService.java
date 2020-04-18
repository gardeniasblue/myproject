package com.blogmang.service;

import com.blogmang.entity.LeaveMessage;

import java.util.List;

public interface LeaveMessageService {
    List<LeaveMessage> queryAllMessageByYN(Integer leaveMessageStatus);
    List<LeaveMessage> queryAllMessage();
    boolean saveMessage(LeaveMessage leaveMessage);
    boolean delMessage(Integer id);
    boolean updateMessage(LeaveMessage leaveMessage);
    boolean replyMessage(LeaveMessage leaveMessage);
    Integer countMessageSy();
    Integer countMessageSn();
    LeaveMessage queryMessageById(Integer id);
}

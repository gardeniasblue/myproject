package com.blogmang.service.impl;

import com.blogmang.dao.LeaveMessageDao;
import com.blogmang.entity.LeaveMessage;
import com.blogmang.service.LeaveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LeaveMessageServiceImpl implements LeaveMessageService {
    @Autowired
    private LeaveMessageDao leaveMessageDao;
    @Override
    public List<LeaveMessage> queryAllMessageByYN(Integer leaveMessageStatus) {
        return leaveMessageDao.queryAllMessageByYN(leaveMessageStatus);
    }

    @Override
    public List<LeaveMessage> queryAllMessage() {
        return leaveMessageDao.queryAllMessage();
    }

    @Override
    public boolean saveMessage(LeaveMessage leaveMessage) {
        leaveMessageDao.saveMessage(leaveMessage);
        return true;
    }

    @Override
    public boolean delMessage(Integer id) {
        leaveMessageDao.delMessage(id);
        return true;
    }

    @Override
    public boolean updateMessage(LeaveMessage leaveMessage) {
        leaveMessageDao.updateMessage(leaveMessage);
        return true;
    }

    @Override
    public boolean replyMessage(LeaveMessage leaveMessage) {
        leaveMessageDao.replyMessage(leaveMessage);
        return true;
    }

    @Override
    public Integer countMessageSy() {
        return leaveMessageDao.countMessageSy();
    }

    @Override
    public Integer countMessageSn() {
        return leaveMessageDao.countMessageSn();
    }

    @Override
    public LeaveMessage queryMessageById(Integer id) {
        return leaveMessageDao.queryMessageById(id);
    }
}

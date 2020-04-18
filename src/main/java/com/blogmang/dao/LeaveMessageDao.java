package com.blogmang.dao;


import com.blogmang.entity.LeaveMessage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LeaveMessageDao {
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

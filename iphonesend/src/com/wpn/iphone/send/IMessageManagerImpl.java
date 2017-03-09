package com.chantsoft.app.message.impl;

import com.chantsoft.app.message.IMessageManager;
import com.chantsoft.app.tc.base.ListObject;
import com.chantsoft.core.base.ResultBean;
import com.chantsoft.core.framework.exception.ModuleException;
import com.chantsoft.service.message.api.email.EmailMessageService;
import com.chantsoft.service.message.api.instation.InstationMessageService;
import com.chantsoft.service.message.api.instation.MessageBean;
import com.chantsoft.service.message.api.instation.MessageObserverBean;
import com.chantsoft.service.message.api.sms.SMSMessageService;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IMessageManagerImpl
  implements IMessageManager
{
  Logger logger = LoggerFactory.getLogger(getClass());

  private EmailMessageService emailMessageService = null;
  private InstationMessageService instationMessageService = null;
  private SMSMessageService smsMessageService = null;
  
  
  
  public EmailMessageService getEmailMessageService() {
	return emailMessageService;
}

public InstationMessageService getInstationMessageService() {
	return instationMessageService;
}

public SMSMessageService getSmsMessageService() {
	return smsMessageService;
}

public ExecutorService getExecutorService() {
	return executorService;
}

private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);

  public ResultBean sendSms(String templateId, String mobies, String content)
  {
    return this.smsMessageService.sendSms(templateId, mobies, content);
  }

  public void sendSimpeMessage(String to, String subject, String text)
  {
    Runnable runnable = new IMessageManagerImpl.1(this, to, subject, text);

    this.executorService.submit(runnable);
  }

  public void sendSimpeMessage(String[] to, String subject, String text)
  {
    Runnable runnable = new IMessageManagerImpl.2(this, to, subject, text);

    this.executorService.submit(runnable);
  }

  public void sendSimpeMessage(String[] to, String subject, String text, String[] cc, String[] bcc)
  {
    Runnable runnable = new IMessageManagerImpl.3(this, to, subject, text, cc, bcc);

    this.executorService.submit(runnable);
  }

  public void sendMiniMessage(String to, String subject, String text, File file)
  {
    Runnable runnable = new IMessageManagerImpl.4(this, to, subject, text, file);

    this.executorService.submit(runnable);
  }

  public void sendMiniMessage(String[] to, String subject, String text, File[] file)
  {
    Runnable runnable = new IMessageManagerImpl.5(this, to, subject, text, file);

    this.executorService.submit(runnable);
  }

  public void sendMiniMessage(String[] to, String subject, String text, File[] file, String[] cc, String[] bcc)
  {
    Runnable runnable = new IMessageManagerImpl.6(this, to, subject, text, file, cc, bcc);

    this.executorService.submit(runnable);
  }

  public void sendTextMessage(Integer msgType, Long senderId, String msgContent, Long receiverId, Long orgCorporationId)
  {
    Runnable runnable = new IMessageManagerImpl.7(this, msgType, senderId, msgContent, receiverId, orgCorporationId);

    this.executorService.submit(runnable);
  }

  public void sendTextMessage(Integer msgType, Long senderId, String msgContent, Long receiverId, Long orgCorporationId, Integer action)
  {
    Runnable runnable = new IMessageManagerImpl.8(this, msgType, senderId, msgContent, receiverId, orgCorporationId, action);

    this.executorService.submit(runnable);
  }

  public void sendTextMessages(Integer msgType, Long senderId, String msgContent, Collection<Long> receiverId, Long orgCorporationId)
  {
    Runnable runnable = new IMessageManagerImpl.9(this, msgType, senderId, msgContent, receiverId, orgCorporationId);

    this.executorService.submit(runnable);
  }

  public void sendTextMessages(Integer msgType, Long senderId, String msgContent, Collection<Long> receiverIds, Long orgCorporationId, Integer action)
  {
    Runnable runnable = new IMessageManagerImpl.10(this, msgType, senderId, msgContent, receiverIds, orgCorporationId);

    this.executorService.submit(runnable);
  }

  public void sendBizMessage(Integer msgType, Long senderId, Integer resourceType, String resourceId, String msgContent, Long receiverId, Long orgCorporationId)
  {
    Runnable runnable = new IMessageManagerImpl.11(this, msgType, senderId, resourceType, resourceId, msgContent, receiverId, orgCorporationId);

    this.executorService.submit(runnable);
  }

  public void sendBizMessage(Integer msgType, Long senderId, Integer resourceType, String resourceId, String msgContent, Long receiverId, Long orgCorporationId, Integer action)
  {
    Runnable runnable = new IMessageManagerImpl.12(this, msgType, senderId, resourceType, resourceId, msgContent, receiverId, orgCorporationId, action);

    this.executorService.submit(runnable);
  }

  public void sendBizMessages(Integer msgType, Long senderId, Integer resourceType, String resourceId, String msgContent, Collection<Long> receiverId, Long orgCorporationId)
  {
    Runnable runnable = new IMessageManagerImpl.13(this, msgType, senderId, resourceType, resourceId, msgContent, receiverId, orgCorporationId);

    this.executorService.submit(runnable);
  }

  public void sendBizMessages(Integer msgType, Long senderId, Integer resourceType, String resourceId, String msgContent, Collection<Long> receiverIds, Long orgCorporationId, Integer action)
  {
    Runnable runnable = new IMessageManagerImpl.14(this, msgType, senderId, resourceType, resourceId, msgContent, receiverIds, orgCorporationId);

    this.executorService.submit(runnable);
  }

  public void sendExtranetMessage(Integer msgType, Long senderId, String url, String msgContent, Long receiverId, Long orgCorporationId)
  {
    Runnable runnable = new IMessageManagerImpl.15(this, msgType, senderId, url, msgContent, receiverId, orgCorporationId);

    this.executorService.submit(runnable);
  }

  public void sendExtranetMessage(Integer msgType, Long senderId, String url, String msgContent, Collection<Long> receiverIds, Long orgCorporationId)
  {
    Runnable runnable = new IMessageManagerImpl.16(this, msgType, senderId, url, msgContent, receiverIds, orgCorporationId);

    this.executorService.submit(runnable);
  }

  public void sendOnlineMessage(MessageBean messageBean)
  {
    Runnable runnable = new IMessageManagerImpl.17(this, messageBean);

    this.executorService.submit(runnable);
  }

  public void sendOnlineMessage2Corporation(MessageBean messageBean, Long orgCorporationId)
  {
    Runnable runnable = new IMessageManagerImpl.18(this, messageBean, orgCorporationId);

    this.executorService.submit(runnable);
  }

  public void sendOnlineMessage2Person(MessageBean messageBean, Long receiverId)
  {
    Runnable runnable = new IMessageManagerImpl.19(this, messageBean, receiverId);

    this.executorService.submit(runnable);
  }

  public void sendMessage(MessageBean messageBean)
  {
    Runnable runnable = new IMessageManagerImpl.20(this, messageBean);

    this.executorService.submit(runnable);
  }

  public List<MessageBean> findWaitSendMessagesButNotRead(Long[] pid)
  {
    List msgList = this.instationMessageService.findWaitSendMessagesButNotRead(pid);
    if (null == msgList) {
      msgList = new ArrayList();
    }
    return msgList;
  }

  public void registerMsgObserver(MessageObserverBean observerBean)
  {
    Runnable runnable = new IMessageManagerImpl.21(this, observerBean);

    this.executorService.submit(runnable);
  }

  public void setEmailMessageService(EmailMessageService emailMessageService)
  {
    this.emailMessageService = emailMessageService;
  }

  public void setInstationMessageService(InstationMessageService instationMessageService)
  {
    this.instationMessageService = instationMessageService;
  }

  public void setSmsMessageService(SMSMessageService smsMessageService) {
    this.smsMessageService = smsMessageService;
  }

  public List<MessageBean> findSendedMessages(long pid)
    throws ModuleException
  {
    List msgList = this.instationMessageService.findSendedMessages(pid);
    if (null == msgList) {
      msgList = new ArrayList();
    }
    return msgList;
  }

  public ListObject<MessageBean> findSendedMessages(Long perId, Integer start, Integer range)
    throws ModuleException
  {
    return this.instationMessageService.findSendedMessages(perId, start, range);
  }

  public List<MessageBean> findWaitSendMessages(Long[] pid)
  {
    List msgList = this.instationMessageService.findWaitSendMessages(pid);
    if (null == msgList) {
      msgList = new ArrayList();
    }
    return msgList;
  }

  public ListObject<MessageBean> findSendedMessagesByPerson(Long perId, Long perId2, Integer start, Integer range)
    throws ModuleException
  {
    return this.instationMessageService.findSendedMessagesByPerson(perId, perId2, start, range);
  }

  public Map<Long, ListObject<MessageBean>> getLastestMsgOfPerson(Long userId, Integer count)
    throws ModuleException
  {
    return this.instationMessageService.getLastestMsgOfPerson(userId, count);
  }

  public void sendMessages(List<MessageBean> messageList)
  {
    Runnable runnable = new IMessageManagerImpl.22(this, messageList);

    this.executorService.submit(runnable);
  }

  public void sendMessages(List<MessageBean> MessageBeans, List<String> fids)
  {
    Runnable runnable = new IMessageManagerImpl.23(this, MessageBeans, fids);

    this.executorService.submit(runnable);
  }

  public void sendWaitSendMessages(List<MessageBean> MessageBeans)
  {
    Runnable runnable = new IMessageManagerImpl.24(this, MessageBeans);

    this.executorService.submit(runnable);
  }

  public List<MessageBean> findWaitSendMessages(Long pid, String resourceId)
  {
    List msgList = this.instationMessageService.findWaitSendMessages(pid, resourceId);
    if (null == msgList) {
      msgList = new ArrayList();
    }
    return msgList;
  }

  public List<MessageBean> findWaitSendMessages(Long pid, Long pid1)
  {
    List msgList = this.instationMessageService.findWaitSendMessages(pid, pid1);
    if (null == msgList) {
      msgList = new ArrayList();
    }
    return msgList;
  }

  public List<MessageBean> findWaitSendMessagesButNotRead(Long pid, Long pid1)
  {
    List msgList = this.instationMessageService.findWaitSendMessagesButNotRead(pid, pid1);
    if (null == msgList) {
      msgList = new ArrayList();
    }
    return msgList;
  }

  public void clearHistoryMessage(long pid)
  {
    this.instationMessageService.clearHistoryMessage(pid);
  }

  public ListObject<MessageBean> getMsgsWithPerson(Long curUserId, Long relUserId, Integer start, Integer count, String calendar)
    throws ModuleException
  {
    return this.instationMessageService.getMsgsWithPerson(curUserId, relUserId, start, count, calendar);
  }

  public ListObject<MessageBean> getMsgsWithSystem(Long curUserId, Integer type, Integer start, Integer count)
    throws ModuleException
  {
    return this.instationMessageService.getMsgsWithSystem(curUserId, type, start, count);
  }

  public ListObject<MessageBean> getMsgsWithPerson(Long curUserId, Long relUserId, Integer start, Integer count, String startDate, String endDate)
    throws ModuleException
  {
    return this.instationMessageService.getMsgsWithPerson(curUserId, relUserId, start, count, startDate, endDate);
  }

  public void clearMessage(Long curUserId, Long relUserId, Long cid)
    throws ModuleException
  {
    this.instationMessageService.clearMessage(curUserId, relUserId, cid);
  }

  public List<MessageBean> getMsgsWithPersonByGtDate(Long sendId, Long receiverId, String lastDate)
    throws ModuleException
  {
    List msgList = this.instationMessageService.getMsgsWithPersonByGtDate(sendId, receiverId, lastDate);
    if (null == msgList) {
      msgList = new ArrayList();
    }
    return msgList;
  }

  public List<MessageBean> findDefaultMessageByType(Long[] pids, Long[] cids, Integer[] msgTypes)
    throws ModuleException
  {
    List msgList = this.instationMessageService.findDefaultMessageByType(pids, cids, msgTypes);
    if (null == msgList) {
      msgList = new ArrayList();
    }
    return msgList;
  }

  public void markMessageReaded(Long msgId)
  {
    this.instationMessageService.markMessageReaded(msgId);
  }

  public MessageBean findMessageById(Long msgId)
    throws ModuleException
  {
    try
    {
      return this.instationMessageService.findMessageById(msgId);
    }
    catch (Exception e)
    {
      throw new ModuleException(e);
    }
  }
}
package hu.neuron.junior.service.impl.chatroom;

import hu.neuron.junior.client.api.service.chatroom.ChatRoomService;
import hu.neuron.junior.client.api.vo.ChatRoomVo;
import hu.neuron.junior.core.dao.ChatRoomDao;
import hu.neuron.junior.core.entity.ChatRoom;
import hu.neuron.junior.service.mapper.chatroom.ChatRoomVoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("chatRoomService")
@Transactional
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomDao chatRoomDao;

    @Autowired
    public ChatRoomServiceImpl(ChatRoomDao chatRoomDao) {
        this.chatRoomDao = chatRoomDao;
    }

    @Override
    public ChatRoomVo findById(Long id) {
        ChatRoom chatRoom = chatRoomDao.findOne(id);
        return ChatRoomVoMapper.toVo(chatRoom);
    }

    @Override
    public List<ChatRoomVo> findAll() {
        return ChatRoomVoMapper.toVo(chatRoomDao.findAll());
    }

    @Override
    public void save(ChatRoomVo chatRoomVo) {
        chatRoomDao.save(ChatRoomVoMapper.toEntity(chatRoomVo));
    }
}

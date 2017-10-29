package hu.neuron.junior.client.api.service.chatroom;

import hu.neuron.junior.client.api.vo.ChatRoomVo;

import java.util.List;

public interface ChatRoomService {

    ChatRoomVo findById(Long id);

    List<ChatRoomVo> findAll();

    void save(ChatRoomVo chatRoomVo);
}

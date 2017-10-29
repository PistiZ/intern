package hu.neuron.junior.service.mapper.chatroom;

import hu.neuron.junior.client.api.vo.ChatRoomVo;
import hu.neuron.junior.core.entity.ChatRoom;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomVoMapper {

    private static Mapper mapper = new DozerBeanMapper();

    public static ChatRoomVo toVo(ChatRoom chatRoom) {
        if (chatRoom == null) {
            return null;
        }
        return mapper.map(chatRoom, ChatRoomVo.class);
    }

    public static ChatRoom toEntity(ChatRoomVo chatRoomVo) {
        if (chatRoomVo == null) {
            return null;
        }
        return mapper.map(chatRoomVo, ChatRoom.class);
    }

    public static List<ChatRoomVo> toVo(List<ChatRoom> chatRooms) {
        List<ChatRoomVo> result = new ArrayList<>();
        for (ChatRoom chatRoom : chatRooms) {
            result.add(toVo(chatRoom));
        }
        return result;
    }

    public static List<ChatRoom> toEntity(List<ChatRoomVo> chatRoomVos) {
        List<ChatRoom> result = new ArrayList<>();
        for (ChatRoomVo chatRoomVo : chatRoomVos) {
            result.add(toEntity(chatRoomVo));
        }
        return result;
    }
}

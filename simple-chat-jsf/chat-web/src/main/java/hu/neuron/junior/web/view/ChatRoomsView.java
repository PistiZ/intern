package hu.neuron.junior.web.view;

import hu.neuron.junior.client.api.service.chatroom.ChatRoomService;
import hu.neuron.junior.client.api.vo.ChatRoomVo;
import hu.neuron.junior.client.api.vo.UserVo;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

@ManagedBean(name = "chatRoomsView")
@ViewScoped
public class ChatRoomsView {

    private enum View {
        LIST, EDIT
    }

    private View view;

    private List<ChatRoomVo> chatRooms;

    private ChatRoomVo editedChatRoom;

    @ManagedProperty("#{chatRoomService}")
    private ChatRoomService chatRoomService;

    @PostConstruct
    public void init() {
        editedChatRoom = null;
        chatRooms = chatRoomService.findAll();
        view = View.LIST;
    }

    public void addChatRoom(ActionEvent actionEvent) {
        editedChatRoom = new ChatRoomVo();
        editedChatRoom.setUsers(new LinkedList<>());
        editedChatRoom.setName("");

        UserVo userVo = (UserVo) actionEvent.getComponent().getAttributes().get("user");
        editedChatRoom.getUsers().add(userVo);

        chatRooms.add(editedChatRoom);

        view = View.EDIT;
    }

    public void joinChatRoom(ActionEvent actionEvent) {
        UserVo userVo = (UserVo) actionEvent.getComponent().getAttributes().get("user");
        ChatRoomVo chatRoomToConnect = (ChatRoomVo) actionEvent.getComponent().getAttributes().get("chatRoom");

        for (ChatRoomVo chatRoom : chatRooms) {
            if (chatRoomToConnect.getName().equals(chatRoom.getName())) {
                for (UserVo user : chatRoomToConnect.getUsers()) {
                    if (user.getUsername().equals(userVo.getUsername())) {
                        return;
                    }
                }

                chatRoom.getUsers().add(userVo);

                break;
            }
        }

        chatRoomService.save(chatRoomToConnect);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", null));
    }

    public void saveChatRoom() {
        if (editedChatRoom != null) {
            chatRoomService.save(editedChatRoom);
        }

        view = View.LIST;
    }

    public List<ChatRoomVo> getChatRooms() {
        return chatRooms;
    }

    public void setChatRooms(List<ChatRoomVo> chatRooms) {
        this.chatRooms = chatRooms;
    }

    public ChatRoomVo getEditedChatRoom() {
        return editedChatRoom;
    }

    public void setEditedChatRoom(ChatRoomVo editedChatRoom) {
        this.editedChatRoom = editedChatRoom;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setChatRoomService(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }
}

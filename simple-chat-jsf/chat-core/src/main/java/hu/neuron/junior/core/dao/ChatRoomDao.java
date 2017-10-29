package hu.neuron.junior.core.dao;

import hu.neuron.junior.core.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface ChatRoomDao extends JpaRepository<ChatRoom, Long> {

}

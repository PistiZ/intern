package hu.neuron.junior.client.api.service.user;

import hu.neuron.junior.client.api.vo.UserVo;

import java.util.List;

public interface UserService {

    UserVo findById(Long id);

    UserVo findByUsername(String username);

    UserVo registerUser(UserVo userVo);

    List<UserVo> findAll();
}

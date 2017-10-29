package hu.neuron.junior.service.impl.user;

import hu.neuron.junior.client.api.service.user.UserService;
import hu.neuron.junior.client.api.vo.UserVo;
import hu.neuron.junior.core.dao.RoleDao;
import hu.neuron.junior.core.dao.UserDao;
import hu.neuron.junior.core.entity.Role;
import hu.neuron.junior.core.entity.User;
import hu.neuron.junior.service.mapper.user.UserVoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

    private static final String ROLE_USER = "ROLE_USER";

    private final UserDao userDao;

    private final RoleDao roleDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public UserVo findById(Long id) {
        User user = userDao.findOne(id);
        return UserVoMapper.toVo(user);
    }

    @Override
    public UserVo findByUsername(String username) {
        User user = userDao.findByUsername(username);
        return UserVoMapper.toVo(user);
    }

    @Override
    public UserVo registerUser(UserVo userVo) {
        User user = UserVoMapper.toEntity(userVo);

        List<Role> roles = new ArrayList<>();

        Role role = roleDao.findByName(ROLE_USER);
        roles.add(role);
        user.setRoles(roles);

        user = userDao.save(user);
        return UserVoMapper.toVo(user);
    }

    @Override
    public List<UserVo> findAll() {
        return UserVoMapper.toVo(userDao.findAll());
    }

}

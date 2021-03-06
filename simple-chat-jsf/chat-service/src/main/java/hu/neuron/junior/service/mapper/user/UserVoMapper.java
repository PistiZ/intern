package hu.neuron.junior.service.mapper.user;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.neuron.junior.client.api.vo.UserVo;
import hu.neuron.junior.core.entity.User;

public class UserVoMapper {

    private static Mapper mapper = new DozerBeanMapper();

    public static UserVo toVo(User user) {
        if (user == null) {
            return null;
        }
        return mapper.map(user, UserVo.class);
    }

    public static User toEntity(UserVo userVO) {
        if (userVO == null) {
            return null;
        }
        return mapper.map(userVO, User.class);
    }

    public static List<UserVo> toVo(List<User> user) {
        List<UserVo> rv = new ArrayList<>();
        for (User users : user) {
            rv.add(toVo(users));
        }
        return rv;
    }

    public static List<User> toEntity(List<UserVo> user) {
        List<User> rv = new ArrayList<>();
        for (UserVo users : user) {
            rv.add(toEntity(users));
        }
        return rv;
    }
}

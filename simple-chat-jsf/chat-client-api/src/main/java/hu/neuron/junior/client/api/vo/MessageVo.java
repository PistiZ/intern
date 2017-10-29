package hu.neuron.junior.client.api.vo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class MessageVo extends BaseVo implements Serializable {

    private static final long serialVersionUID = -5395968745834185014L;

    private static final long ONE_DAY_MILLIS = 24 * 60 * 60 * 1000;

    private static final long ONE_HOUR_MILLIS = 60 * 60 * 1000;

    private static final long ONE_MINUTE_MILLIS = 60 * 1000;

    private String content;

    private UserVo fromUser;

    private UserVo toUser;

    public String getFormattedElapsedTime() {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        if (this.getRecDate().before(now)) {
            long spentMillis = now.getTime() - this.getRecDate().getTime();

            if (spentMillis >= 0 && spentMillis <= ONE_HOUR_MILLIS) {
                return spentMillis / ONE_MINUTE_MILLIS + " perce";
            } else if (spentMillis <= ONE_DAY_MILLIS) {
                return spentMillis / ONE_HOUR_MILLIS + " órája";
            } else {
                return spentMillis / ONE_DAY_MILLIS + " napja";
            }
        }

        return null;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserVo getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserVo fromUser) {
        this.fromUser = fromUser;
    }

    public UserVo getToUser() {
        return toUser;
    }

    public void setToUser(UserVo toUser) {
        this.toUser = toUser;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

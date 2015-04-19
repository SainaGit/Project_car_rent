package sdeveloper.car.rent.user;

import java.io.Serializable;

/**
 * Created by Saina on 4/9/2015.
 */
public class User implements Serializable {
    public String userName;
    public String userPicture;

    public User() {

    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPicture() {
        return userPicture;
    }
}

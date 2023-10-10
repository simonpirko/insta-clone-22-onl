package by.tms.instaclone22onl.model;

import lombok.Getter;

public class Follower {
    Follower follower = new Follower();
    User user = new User();
    private int followerId;
    private int userId;
    public int getFollowerId(){return followerId;}

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }

    public int getIUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    @Override
    public String toString(){
        return "Follower id ="+ followerId + "User id =" +userId;
    }
}
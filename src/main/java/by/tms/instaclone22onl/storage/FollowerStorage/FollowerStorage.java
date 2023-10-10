package by.tms.instaclone22onl.storage.FollowerStorage;

import by.tms.instaclone22onl.model.Follower;
import by.tms.instaclone22onl.model.User;

import java.util.List;
import java.util.Optional;

public  interface FollowerStorage {

     void add( Follower follower);
     Optional<Follower>getById(int followerId);

}



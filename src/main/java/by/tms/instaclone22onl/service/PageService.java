package by.tms.instaclone22onl.service;

import by.tms.instaclone22onl.model.Page;
import by.tms.instaclone22onl.model.Post;

import java.util.List;

public class PageService {
    public int getOffset(Page page){
        int offset = page.getLimit() * (page.getPageNumber()-1);

        return offset;
    }

}

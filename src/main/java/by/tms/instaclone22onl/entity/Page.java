package by.tms.instaclone22onl.entity;

import java.util.List;


@lombok.Getter
@lombok.Setter
@lombok.Builder
public class Page <T> {
    private int limit;
    private int pageNumber;
   private int pageMin;
   private int pageMax;
    private int totalPages;
    private Iterable<T> postsForPageList;


    public int getOffset(Page page){
        int offset = page.getLimit() * (page.getPageNumber()-1);

        return offset;
    }


    public int getPageMin(pageNumber){
        pageMin = pageNumber - 5;
        return pageMin;
    }

public int getPageMax(pageNumber){
        pageMax = pageNumber - 5;
        return pageMax;
}


}

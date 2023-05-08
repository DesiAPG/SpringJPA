package com.example.springjpa.pages;


import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;


public class PageRender<T> {
    private String url;
    private Page<T> page;
    private int numberOfPages;
    private int elementsPerPage;
    private int actualPage;
    private List<PageItem> pageItems;


    public PageRender(String url, Page<T> page) {
        this.url = url;
        this.page = page;
        this.pageItems = new ArrayList<PageItem>();
        elementsPerPage = page.getSize();
        numberOfPages = page.getTotalPages();
        actualPage = page.getNumber() + 1;
        int from, to;
        if (numberOfPages <= elementsPerPage) {
            from = 1;
            to = numberOfPages;
        } else {
            if (actualPage <= elementsPerPage / 2) {
                from = 1;
                to = elementsPerPage;
            } else if (actualPage >= numberOfPages - elementsPerPage / 2) {
                from = numberOfPages - elementsPerPage + 1;
                to = elementsPerPage;
            } else {
                from = actualPage - elementsPerPage / 2;
                to = elementsPerPage;
            }
        }
        for (int i = 0; i < to; i++) {
            pageItems.add(new PageItem(from + 1, actualPage == from + i
            ));
        }
    }

    public boolean isLast() {
        return page.isLast();
    }

    public boolean isFirst() {
        return page.isFirst();
    }

    public boolean isHasNext() {
        return page.hasNext();
    }

    public boolean isHasPrevious() {
        return page.hasPrevious();
    }

    public String getUrl() {
        return url;
    }

    public Page<T> getPage() {
        return page;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public int getElementsPerPage() {
        return elementsPerPage;
    }

    public int getActualPage() {
        return actualPage;
    }

    public List<PageItem> getPageItems() {
        return pageItems;
    }
}

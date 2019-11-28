package com.kai.kaidong;

class ListBean {
    private int resource;
    private String title;

    public ListBean(int resource, String title) {
        this.resource = resource;
        this.title = title;
    }

    public int getResource() {

        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

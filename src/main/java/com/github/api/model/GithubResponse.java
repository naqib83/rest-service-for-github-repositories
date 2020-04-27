package com.github.api.model;

import lombok.Data;

import java.util.List;

@Data
public class GithubResponse<T> {
    private List<T> items;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}

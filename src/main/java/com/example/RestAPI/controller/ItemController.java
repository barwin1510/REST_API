package com.example.RestAPI.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RestAPI.model.Item;

@RestController
@RequestMapping("/items")
public class ItemController {
private List<Item> itemList = new ArrayList<>();
    

    public ItemController() {
        itemList.add(new Item(1L, "Item 1"));
        itemList.add(new Item(2L, "Item 2"));
    }

    @GetMapping
    public List<Item> getAllItems() {
        return itemList;
    }

    @PostMapping
    public Item addItem(@RequestBody Item newItem) {
        itemList.add(newItem);
        return newItem;
    }


    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item updatedItem) {
        for (Item item : itemList) {
            if (item.getId().equals(id)) {
                item.setName(updatedItem.getName());
                return item;
            }
        }
        return null;
    }


    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemList.removeIf(item -> item.getId().equals(id));
        return "Item with ID " + id + " has been deleted.";
    }
}

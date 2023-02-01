package se.jensen.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.jensen.backend.model.CartItem;
import se.jensen.backend.service.CartService;


import java.util.List;

@CrossOrigin(origins = "http://localhost:3010")
@RequestMapping("/api/v1")
@RestController
public class CartController {

    @Autowired
    CartService db;

    @GetMapping("/carts/{id}")
    public ResponseEntity<List<CartItem>> getItems(@PathVariable("id") int id) {

        return new ResponseEntity<>(db.getItems(id), HttpStatus.OK);
    }

    @PatchMapping("/carts/{id}")
    public ResponseEntity<Integer> addOrRemoveItem(@PathVariable("id") int id, @RequestParam String action, @RequestBody CartItem item) {

        if ("add".equals(action)) return new ResponseEntity<>(db.addItem(id, action, item), HttpStatus.OK);
        else if ("remove".equals(action)) return new ResponseEntity<>(db.removeItem(id, action, item), HttpStatus.OK);
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("carts/{id}")
    public void clearCartOrClearCartAndRestock(@PathVariable("id") int id, @RequestParam boolean buyout) {

        if (buyout) db.clearCart(true);
        else {
            db.clearCartAndRestock(id);
        }
    }
}
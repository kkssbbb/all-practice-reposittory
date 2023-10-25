package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
  /*  public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }*/// @RequiredArgsConstructor 로 생성자 자동생성

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    //변경 감지 기능 사용
    @Transactional
    public void updateItem(Long itemId, Book bookParam){
        Item findItem = itemRepository.findOne(itemId);
        findItem.setPrice(bookParam.getPrice());
        findItem.setName(bookParam.getName());
        findItem.setStockQuantity(bookParam.getStockQuantity());
    }


    public List<Item> finItem(){
        return  itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return  itemRepository.findOne(itemId);
    }
}

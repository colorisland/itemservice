package hello.itemservice.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        // given
        Item item = new Item("lip tint", 5000, 5);

        // when
        Item savedItem = itemRepository.save(item);

        // then
        Item findItem = itemRepository.findById(savedItem.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        // given
        Item item1 = new Item("cushion21", 5000, 5);
        Item item2 = new Item("cushion23", 5000, 5);

        // when
        itemRepository.save(item1);
        itemRepository.save(item2);

        // then
        List<Item> items = itemRepository.findAll();
        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(item1, item2);
    }

    @Test
    void updateItem(){
        // given
        Item item1 = new Item("cushion21", 5000, 5);

        Item savedItem = itemRepository.save(item1);
        Long itemId = savedItem.getId();

        // when
        Item item2 = new Item("cushion23", 5000, 5);
        itemRepository.update(itemId, item2);

        // then
        Item findItem = itemRepository.findById(itemId);
        assertThat(findItem.getItemName()).isEqualTo(item2.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(item2.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(item2.getQuantity());
    }
}

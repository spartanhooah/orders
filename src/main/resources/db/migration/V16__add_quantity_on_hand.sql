alter table product
    add column quantity_on_hand integer default 0;

update product
    set quantity_on_hand = 10 where quantity_on_hand is null;
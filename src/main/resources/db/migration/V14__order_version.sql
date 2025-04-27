alter table order_header
    add version integer;

alter table order_line
    add version integer;
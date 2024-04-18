insert into parent (id, name)
values (nextval('parent_seq'), 'parent 1'),
       (nextval('parent_seq'), 'parent 1'),
       (nextval('parent_seq'), 'parent 1');

--names should be "parent" plus the id
    update parent
set name = 'parent ' || id;
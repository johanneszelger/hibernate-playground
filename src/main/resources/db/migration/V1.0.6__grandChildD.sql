insert into grand_childd (id, name, child_id) values
        (nextval('grand_child_d_seq'), 'grandchild 1', 1),
        (nextval('grand_child_d_seq'), 'grandchild 1', 1),
        (nextval('grand_child_d_seq'), 'grandchild 1', 1),
        (nextval('grand_child_d_seq'), 'grandchild 1', 1),
        (nextval('grand_child_d_seq'), 'grandchild 1', 1);

-- insert into child for every child with parent 1 but copy to all other parents
insert into grand_childd (id, child_id, name)
select nextval('grand_child_d_seq'), c.id, c.name
from grand_childd g, childb c
where c.id <> 1;

--names should be "child" plus the id
update grand_childd
set name = 'grandchild ' || id;
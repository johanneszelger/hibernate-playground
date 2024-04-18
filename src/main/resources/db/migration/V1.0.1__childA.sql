insert into childa (id, name, parent_id)
values (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1),
       (nextval('child_a_seq'), 'child 1', 1);

-- insert into child for every child with parent 1 but copy to all other parents
insert into childa (id, parent_id, name)
select nextval('child_a_seq'), p.id, c.name
from childa c, parent p
where p.id <> 1;

--names should be "child" plus the id
update childa
set name = 'child ' || id;
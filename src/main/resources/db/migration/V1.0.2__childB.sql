insert into childb (id, name, parent_id)
values (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1),
       (nextval('child_b_seq'), 'child 1', 1);

-- insert into child for every child with parent 1 but copy to all other parents
insert into childb (id, parent_id, name)
select nextval('child_b_seq'), p.id, c.name
from childb c, parent p
where p.id <> 1;

--names should be "child" plus the id
update childb
set name = 'child ' || id;
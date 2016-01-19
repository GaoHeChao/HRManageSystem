select * from usertab t
INSERT INTO USERTAB(username,pswd,email,phone,birth)
values('www','qqqqqqqq','g@123.com','13342278520','1123')
delete from usertab where username='www';

select * from usertab
where username='sss'
and pswd='4bbde07660e5eff90873642cfae9a8dd'

grant connect,resource   --分配权限
to cc;

create sequence seq_emp_id;   --employee表

create trigger emp_trig before
insert on employees for each row 
begin

 select seq_emp_id.nextval into:new.employee_id from dual;

end;

create sequence seq_pic_id;    --photo表

create trigger pic_trig before
insert on photo for each row 
begin

 select seq_pic_id.nextval into:new.photo_id from dual;

end;

select * from photo;
select * from departments;

alter table jobs
add constraint ck_sal
check (min_salary <= Max_salary);

update departments 
set department_name='santi' 
where department_id='10';

select * from admin;

select * from jobs
select * from departments;
select * from employees;
update departments set department_id=20,department_name='餐饮部'
where location_name=90119;
insert into employees values(1001,'刘','g@163.com','900-900',sysdate,'FWY',1000,20)
SELECT * 
FROM  employees e,departments d,jobs j
where e.department_id=d.department_id
and e.job_id=j.job_id

select * from employees;
insert into employees values(1001,'刘','g@163.com','900-900',sysdate,'FWY',1000,20)

select * from departments;














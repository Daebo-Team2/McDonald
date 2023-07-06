create table Store(
    no NUMBER primary key,
    name VARCHAR2(60) not null unique,
    id VARCHAR2(60) not null,
    pwd VARCHAR2(60) not null,
    status NUMBER default 1
);

create table Menu(
     no NUMBER primary key,
     category VARCHAR2(60) not null,
     name VARCHAR2(60) not null,
     image VARCHAR2(100),
     price NUMBER not null,
     valid NUMBER default 1
);

create table Emp(
    no NUMBER primary key,
    storename VARCHAR2(60) not null,
    hiredate DATE not null,
    tel VARCHAR2(30),
    pay NUMBER not null,
    wtime NUMBER,
    intime DATE,

    CONSTRAINT emp_storename FOREIGN KEY(storename) references Store(name)
);

create table Food(
     no NUMBER primary key,
     name VARCHAR2(60) not null unique
);

create table Post(
     no NUMBER primary key,
     storename VARCHAR2(60) not null,
     title VARCHAR2(120) not null,
     content LONG not null,
     wtime DATE not null,
     status NUMBER not null,
     reno NUMBER,

     CONSTRAINT post_storename FOREIGN KEY (storename) references Store(name)
);

create table Orders(
    no NUMBER primary key,
    price NUMBER not null,
    ordertime DATE not null,
    storeno NUMBER not null,

    CONSTRAINT order_storeno FOREIGN KEY (storeno) references Store(no)
);

create table Stock(
    foodname VARCHAR2(60),
    quantity NUMBER not null,
    storeno NUMBER not null,

    CONSTRAINT stock_storeno FOREIGN KEY (storeno) references Store(no),
    CONSTRAINT stock_foodname FOREIGN KEY (foodname) references Food(name)
);

create table Empinout(
     eno NUMBER not null,
     time DATE not null,
     valid NUMBER not null,

     CONSTRAINT empinout_eno FOREIGN KEY (eno) references Emp(no) on delete cascade
);

create table Stockorder(
    storeno NUMBER not null,
    foodname VARCHAR2(60) not null,
    quantity NUMBER not null,
    time DATE not null,
    status NUMBER not null,

    CONSTRAINT stockorder_storeno FOREIGN KEY (storeno) references Store(no),
    CONSTRAINT stockorder_foodname FOREIGN KEY (foodname) references Food(name)
);

create table Orderlist(
    orderno NUMBER not null,
    menuno NUMBER not null,
    quantity NUMBER not null,

    CONSTRAINT orderlist_orderno FOREIGN KEY (orderno) references Orders(no),
    CONSTRAINT orderlist_menuno FOREIGN KEY (menuno) references Menu(no)
);

create table Recipe(
    menuno NUMBER not null,
    foodno NUMBER not null,

    CONSTRAINT recipe_menuno FOREIGN KEY (menuno) references Menu(no),
    CONSTRAINT recipe_foodno FOREIGN KEY (foodno) references Food(no)
);

select * from tabs;
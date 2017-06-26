/*==========================================
DROP EVERYTHING
==========================================*/

drop table if exists career_x_subject;
drop table if exists career_x_teacher;
drop table if exists enrollment;
drop table if exists section;

drop table if exists career;
drop table if exists subject;
drop table if exists teacher;
drop table if exists student;


/*==========================================
CREATING BASIC TABLES
==========================================*/

create table career(
	id_career int primary key auto_increment,
    name varchar(200)
);

create table subject(
	id_subject int primary key auto_increment,
    name varchar(200)
);

create table teacher(
	id_teacher int primary key auto_increment,
    firstname varchar(200),
    lastname varchar(200)
);

create table student(
	id_student int primary key auto_increment,
    name varchar(200),
    username varchar(200),
    password varchar(200),
    id_career int
);

/*==========================================
CREATING CROSS TABLES
==========================================*/

create table career_x_subject(
	id_career int, 
    id_subject int,
    foreign key (id_career) references career(id_career) on update cascade,
    foreign key (id_subject) references subject(id_subject) on update cascade
);

create table career_x_teacher(
	id_career int, 
    id_teacher int,
    foreign key (id_career) references career(id_career) on update cascade,
    foreign key (id_teacher) references teacher(id_teacher) on update cascade
);

create table section(
	id_section int primary key auto_increment,
    id_subject int, 
    id_teacher int,
    description char(1),
    foreign key (id_subject) references subject(id_subject) on update cascade,
    foreign key (id_teacher) references teacher(id_teacher) on update cascade
);

create table enrollment(
	id_student int, 
    id_section int,
    enrol_date datetime,
    foreign key (id_student) references student(id_student) on update cascade,
    foreign key (id_section) references section(id_section) on update cascade
);

/*==========================================
INSERT VALUES TO BASIC TABLES 
==========================================*/

#----------------------#
#        CAREER        #
#----------------------#

	insert into career(name) values('Computer Sience Engineer');
	insert into career(name) values('Business Administration');
	insert into career(name) values('Medicine');

#----------------------#
#       SUBJECT        #
#----------------------#

	# Programming Subjects
	insert into subject(name) values('Logic I');
	insert into subject(name) values('Programming I');

	# Business Administration Subjects
	insert into subject(name) values('Economy I');
	insert into subject(name) values('Statistics I');

	# Medicine Subjects
	insert into subject(name) values('Biology I');
	insert into subject(name) values('Chemistry I');

	# Common Subjects
	insert into subject(name) values('Philosophy');

#----------------------#
#        TEACHER       #
#----------------------#

	insert into teacher(firstname, lastname) values('John', 'Dee');
	insert into teacher(firstname, lastname) values('Mary', 'Bush');
	insert into teacher(firstname, lastname) values('Robin', 'Williams');
	insert into teacher(firstname, lastname) values('Phil', 'Dumphy');
	insert into teacher(firstname, lastname) values('Albert', 'Einstein');

#----------------------#
#        STUDENT       #
#----------------------#

	insert into student (name, username, password, id_career) 
	values('Fernando Romero', 'fromero', 'fromero', 1);

	insert into student (name, username, password, id_career) 
	values('Tatian Valencia', 'tvalencia', 'tvalencia', 3);

	insert into student (name, username, password, id_career) 
	values('Yesenia Romero', 'yromero', 'yromero', 2);

	insert into student (name, username, password, id_career) 
	values('Nathalia Flores', 'nflores', 'nflores',3);

	insert into student (name, username, password, id_career) 
	values('Carlos Romero', 'cromero', 'cromero', 1);


/*==========================================
INSERT VALUES TO CROSS TABLES
==========================================*/

#----------------------#
#   CAREER_X_SUBJECT   #
#----------------------#

	# Computer Science Engineer

		# Logic I 
		insert into career_x_subject(id_career, id_subject) values(1,1);
		# Programming I
		insert into career_x_subject(id_career, id_subject) values(1,2);
		# Philosophy 
		insert into career_x_subject(id_career, id_subject) values(1,7);

	/*========================================*/

	# Business Admon 

		# Economy I 
		insert into career_x_subject(id_career, id_subject) values(2,3);
		# Statistics
		insert into career_x_subject(id_career, id_subject) values(2,4);
		# Philosophy 
		insert into career_x_subject(id_career, id_subject) values(2,7);

	/*========================================*/

	# Medicine 

		# Biology I 
		insert into career_x_subject(id_career, id_subject) values(3,5);
		# Chemistry I 
		insert into career_x_subject(id_career, id_subject) values(3,6);
		# Philosophy 
		insert into career_x_subject(id_career, id_subject) values(3,7);

#----------------------#
#   CAREER_X_TEACHER   #
#----------------------#

	# Computer Science Engineer

		# Jhon Dee  
		insert into career_x_teacher(id_career, id_teacher) values(1,1);
		# Albert Einstein
		insert into career_x_teacher(id_career, id_teacher) values(1,5);
		# Robin Williams
		insert into career_x_teacher(id_career, id_teacher) values(1,3);
		
	/*========================================*/

	# Business Admon

		# Mary Bush 
		insert into career_x_teacher(id_career, id_teacher) values(2,2);
		# Robin Williams
		insert into career_x_teacher(id_career, id_teacher) values(2,3);
		
	/*========================================*/
    
    # Medicine

		# Phil Dumphy
		insert into career_x_teacher(id_career, id_teacher) values(3,4);
		# Robin Williams
		insert into career_x_teacher(id_career, id_teacher) values(3,3);

#----------------------#		
#        SECTION       #
#----------------------#

	# Computer Science Engineer

		# Logic I - John Dee - Section A
		insert into section(id_subject, id_teacher, description) values(1,1,'A');
		# Logic I - Albert Einstein - Section B
		insert into section(id_subject, id_teacher, description) values(1,5,'B');
		# Programming I - Albert Einstein - Section A
		insert into section(id_subject, id_teacher, description) values(2,5,'A');
		# Programming I - John Dee - Section B
		insert into section(id_subject, id_teacher, description) values(2,1,'B');
		
	/*========================================*/

	# Business Admon

		# Economy I - Mary Bush - Section A
		insert into section(id_subject, id_teacher, description) values(3,2,'A');
		# Statistics I - Mary Bush - Section A
		insert into section(id_subject, id_teacher, description) values(4,2,'A');
		
	/*========================================*/

	# Medicine

		# Biology I - Phil Dumphy - Section A
		insert into section(id_subject, id_teacher, description) values(5,4,'A');
		# Chemestry I - Phil Dumphy - Section A
		insert into section(id_subject, id_teacher, description) values(6,4,'A');
		
	/*========================================*/
    
    # Common Section
		
        # Philosophy - Robin Williams - Section A
		insert into section(id_subject, id_teacher, description) values(7,3,'A');
    
    /*========================================*/

#----------------------#		
#     ENROLLMENT       #
#----------------------#
	
    # Computer Science Engineer

		# Fernando - Logic I - Seccion A
		insert into enrollment(id_student, id_section, enrol_date) values(1,1,now());
		# Carlos - Logic I - Seccion B
		insert into enrollment(id_student, id_section, enrol_date) values(5,2,now());
		# Fernando - Programming I - Seccion A
		insert into enrollment(id_student, id_section, enrol_date) values(1,3,now());
        # Carlos - Programming I - Seccion B
		insert into enrollment(id_student, id_section, enrol_date) values(5,4,now());
        # Yesi - Economy I - Seccion A
		insert into enrollment(id_student, id_section, enrol_date) values(3,5,now());
        # Yesi - Statistics I - Seccion A
		insert into enrollment(id_student, id_section, enrol_date) values(3,6,now());
        # Tati - Biology I - Seccion A
		insert into enrollment(id_student, id_section, enrol_date) values(2,7,now());
        # Nathalia - Chemistry I - Seccion A
		insert into enrollment(id_student, id_section, enrol_date) values(4,8,now());
        # Nathalia - Philosopy I - Seccion A
		insert into enrollment(id_student, id_section, enrol_date) values(4,9,now());
        
        
	/*========================================*/


/*==========================================
COMMIT
==========================================*/

commit;

/*==========================================
SELECTS
==========================================*/


select * from career;
select * from student;


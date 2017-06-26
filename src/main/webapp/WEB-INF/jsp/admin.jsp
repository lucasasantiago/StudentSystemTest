<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>Admin</title>
		
		<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/resources/css/styles.css" rel="stylesheet" />
		
		<script type="text/javascript">
            window.setTimeout(function() {
            	  $(".flash").fadeTo(500, 0).slideUp(500, function(){
            	      $(this).remove();
            	  });
            	}, 3000);
        </script>
	</head>
	<body>
	
		<!-- ============================================================ -->
		<!--                         Bar Navigation                       -->
		<!-- ============================================================ -->
		
		<header>
			<div class="container">
				<div class="row">
					<div class="left-header-admin col-xs-12- col-sm-6 col-md-6 col-lg-6">
						Student Enrollment System 
						&nbsp;
					</div>
					<div class="right-header-admin col-xs-12- col-sm-6 col-md-6 col-lg-6 vam">
						Admin &nbsp;&nbsp;
						<a href="${pageContext.request.contextPath}/logout" class="btn btn-success">Logout</a>
					</div>
				</div>
			</div>
		</header>
    	
    	<!-- ============================================================ -->
		<!--                            Container                         -->
		<!-- ============================================================ -->
		
		<div class="container">
			<section class="row section-admin">
				<article class="col-xs-12- col-sm-12 col-md-2 col-lg-2 article1-admin">
					<div class="list-group">
						<a href="${pageContext.request.contextPath}/allCareers" class="list-group-item">Careers</a>
						<a href="${pageContext.request.contextPath}/allStudents" class="list-group-item">Students</a>
						<a href="${pageContext.request.contextPath}/allTeachers" class="list-group-item">Teachers</a>
						<a href="${pageContext.request.contextPath}/allSubjects" class="list-group-item">Subjects</a>
					</div>
				</article>
				<article class="col-xs-12 col-sm-12 col-md-10 col-lg-10 article2-admin">
					<h2>
						Administration Management
					</h2>
					<p>Choose any option from the Left Panel to Start!</p>
					<p> &#60;===</p>
				</article>
			</section>
		</div>
		
		<!-- ============================================================ -->
		<!--                              Footer                          -->
		<!-- ============================================================ -->
		
		<footer class="footer">
			<div class="container">
				<p>SES - 2016</p>
			</div>
		</footer>		
		
		<script src="${pageContext.request.contextPath}/resources/js/jquery-3.1.1.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	</body>
</html>
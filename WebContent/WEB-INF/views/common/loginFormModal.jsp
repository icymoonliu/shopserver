<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="modal fade" id="loginFormModal" role="dialog" aria-hidden="true" aria-labelledby="myModalLabel">
	<div class="modal-dialog">
		<div class="modal-content">
		<form class="form-signin" id="loginForm" method="post" >
			<div class="modal-header">
				<button class="close" aria-hidden="true" type="button"
					data-dismiss="modal">×</button>
				<h4 class="modal-title" id="myModalLabel">用户登录</h4>
			</div>
			<div class="modal-body">
				
					<h2 class="form-signin-heading">Please sign in</h2>
					<label for="userName" class="sr-only">Username</label> 
					<input type="text" name="userName" id="userName" class="form-control"
						placeholder="Email address" required autofocus> 
					<label for="userPass" class="sr-only">Password</label> 
					<input type="password" name="userPass" id="userPass" class="form-control"
						placeholder="Password" required>
					
					
				

			</div>
			<div class="modal-footer">
				<button class="btn btn-primary" type="button" onclick="login()" id="loginBtn">登录</button>
				<button class="btn btn-default" type="button"
					data-dismiss="modal" 
					>关闭窗口</button>
				
			</div>
		</form>
		</div>

	</div>

</div>
		

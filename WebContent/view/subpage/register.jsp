<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<div class="container register_wrap">
        <div>
        <div class="form_wrap">
        <h2>
        	회원가입
        </h2>
        	<div class="panel panel-default">
        		<div class="panel-heading">
			    		<h3 class="panel-title">필수정보를 입력해주세요. <small>환영합니다!</small></h3>
			 			</div>
			 			<div class="panel-body">
			    		<form role="form" method="post" action="register" name="registerForm" onsubmit="return frmCheck()">
			    		<!-- <input type="hidden" name="command" value="register" > -->
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			                <input type="text" name="clientId" class="form-control input-sm" placeholder="아이디">
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<input type="text" name="clientName" class="form-control input-sm" placeholder="성명">
			    					</div>
			    				</div>
			    			</div>

			    			<div class="form-group">
			    				<input type="email" name="clientEmail" class="form-control input-sm" placeholder="Email 주소">
			    			</div>

			    			<div class="form-group">
			    				<input type="password" name="clientPassword" class="form-control input-sm" placeholder="비밀번호">
			    			</div>
			    			
			    			<input type="submit" value="등록" class="btn btn-dark btn-block"">			    		
			    		</form>
			    	</div>
	    		</div>
    		</div>
    	</div>
    </div>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		content.jsp
	</div>
	<div class="card-body">
		1. ${5} <br/>
		2. ${"홍길동"} <br/>
		3. ${2 + 5} <br/>
		
		4. ${7 mod 3} <br/>
		5. ${7 % 3} <br/>
		
		6. ${true && true} <br/>
		7. ${true and true} <br/>
		8. ${true || false} <br/>
		9. ${true or false} <br/>
		
		10. ${!true} <br/>
		11. ${not true} <br/>
		
		12. ${2 < 5}, ${2 lt 5} <br/> 
		13. ${2 > 5}, ${2 gt 5} <br/> 
		14. ${2 <= 5}, ${2 le 5} <br/> 
		15. ${2 >= 5}, ${2 ge 5} <br/> 
		16. ${2 != 5 } <br/>
		
		17. ${empty null}<br/>
		18. ${empty ""}<br/>
		
		19. ${pageContext.request.contextPath} <br/>
 	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
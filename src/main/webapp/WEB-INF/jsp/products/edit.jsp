<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/css/form.css">
<title>Thêm sản phẩm</title>
</head>
<body>
	<c:import url="/WEB-INF/jsp/newheader.jsp"></c:import>
	<div class="title" style="width: 250px; margin: 90px auto 0px;">
		<h1>Sửa sản phẩm</h1>
	</div>
	<form:form class="form-add" action="#" method="post"
		modelAttribute="products">
		<form:input path="id" type="hidden" />
		<br />
		<form:label path="name">Tên sản phẩm:</form:label>
		<form:input path="name" placeholder="Tên sản phẩm..."></form:input>
		<form:errors path="name"
			cssStyle="color:red;margin-left: 10px;
    font-style: italic;"></form:errors>
		<br />
		<form:label path="price">Giá bán:</form:label>
		<form:input path="price" placeholder="Giá bán..."></form:input>
		<form:errors path="price"
			cssStyle="color:red;margin-left: 10px;
    font-style: italic;"></form:errors>
		<br />
		<form:label path="images">Link hình ảnh:</form:label>
		<form:input path="images" placeholder="Link hình ảnh..."></form:input>
		<form:errors path="images"
			cssStyle="color:red;margin-left: 10px;
    font-style: italic;"></form:errors>
		<br />
		<form:label path="category.categoryID">Danh mục sản phẩm:</form:label>
		<form:select class="selected-box" path="category.categoryID"
			itemValue="categoryID" itemLabel="name" items="${categories}" />
		<form:errors path="category" cssStyle="color:red"></form:errors>
		<br />
		<!-- upload image -->
		<div>
			<input style="width: 95%;" type="file" class="upload"
				id="${editProp.prop}_file" accept="image/*">
			<form:input path="pictures" placeholder="Hình ảnh" type="hidden"
				cssClass="form-control" id="picturesInput" />
			<form:errors path="pictures" cssClass="small text-danger" />
			<img src="${products.pictures}" style="width: 45%; margin: 0 100px;" />
		</div>
		<!-- upload image -->
		<input class="btn-add" style="width: 200px; margin: 0 auto;"
			type="submit" value="Sửa" />
	</form:form>
	<c:import url="/WEB-INF/jsp/footer.jsp"></c:import>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script>
	$('.upload').change(function() {
	    uploadFile(this);
	});

	uploadFile = async (fileInput) => {
	    let formData = new FormData()
	    formData.append('file', fileInput.files[0]);
	    let uploadOption = {
	        method: 'POST',
	        body: formData
	    };
	    try {
	        let response = await fetch('/upload', uploadOption);
	        if (response.ok) {
	            let uploadResult = await response.json();
	            if (uploadResult.uploaded) {
		            $(fileInput).next().val(uploadResult.url);
		            $(fileInput).prev().val(uploadResult.url);
		            let $img =  $(fileInput).next().next();
		            if ($img.length > 0) {
		                $img.attr('src', uploadResult.url);
		            }
		        } else {
		            alert(uploadResult.messsage);
		        }
	        } else {
	            let error = await response.json();
	            alert(error);
	        }
	    } catch(error) {
	        alert(error);
	    }
	}
	</script>
</body>
</html>
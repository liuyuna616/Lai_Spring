<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>


<%
//     ProService proSvc = new ProService();
// 	Integer productId = (Integer)pageContext.getAttribute("productId");
//     List<ProVO> list = proSvc.getOverviewPicture(productId);
pageContext.getAttribute("list");

pageContext.getAttribute("proVO");
%>


<!DOCTYPE html>
<html lang="zh-Hant">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>商品頁面</title>
<!-- Bootstrap 5 CDN -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<style>
.ad_product{
display:flex;
flex-wrap:no-wrap;
justify-content:center;
gap:50px;
}
.ad_product > div{
display:inline-block!important;
}
a {
	color: black;
	text-decoration: none;
}
/* /* 自訂樣式 */
*
img {
	display: inline-block;
}

.product-image img { */
	/*  width: 90%; */
	/*  height: 200px; */
	object-fit: cover;
	/* 保持圖片不變形，覆蓋整個框架 */
	background-color: #f5f5f5;
	border: 1px solid #ddd;
}

.product-details {
margin-top: 5px;
}

.product-price {
	font-size: 2rem;
	color: #000;
}

.other-products {
	/* 	gap: 20px;  */
	/* 圖片之間的空隙 */
	
}

.other-products .product {
	/* 	text-align: center;  */
	/* 	display: flex;  */
	/* 	flex-direction: column;  */
	/* 	align-items: center;  */
	/* 	/* 置中圖片 */
	
}

.other-products img {
	width: 250px;
	height: 250px;
	object-fit: cover;
	/* 保持圖片不變形，覆蓋整個框架 */
}

.other-products .product p {
	margin-top: 20px;
	/* 圖片和文字之間的間隙 */
}

.btn-custom {
	width: 130px;
	/* 調整按鈕的寬度 */
	margin: 10px;
	/* 減小按鈕間的間距 */
}
.productName{
font-size:25px;
font_weight:700;
color:dark-gray;
}
/* RWD 調整 */
@media ( max-width : 1200px) {
	.product-price {
		font-size: 1.8rem;
	}
}

/* 當螢幕寬度為768px到992px之間，使用上下結構排列 */
@media ( max-width : 992px) and (min-width: 768px) {
	/*  	.product-row { */
	/*  		flex-direction: column;  */
	/*  	}  */
}

@media ( min-width : 768px) and (max-width: 992px) {
	/*  	.product-row {  */
	/*  		flex-direction: row;  */
	/*  	}  */
	/*  	.btn-custom-group {  */
	/*  		justify-content: flex-start;  */
	/*  	}  */
}

@media ( max-width : 576px) {
	.product-price {
		font-size: 1.5rem;
	}
	.btn-custom {
		width: 100%;
		/* 手機模式下按鈕自適應寬度 */
		/* 當螢幕寬度為768px以上，橫排顯示按鈕 */ @media ( min-width : 768px) {
		.btn-custom-group {
		display: flex;
		justify-content: space-between;
	}
}
</style>
</head>


<body>
<body>
	<div class="container">
		<div class="row product-row my-5 d-flex">
			<!-- 商品圖片區 -->
			<div class="col-md-6 d-flex justify-content-center">
				<!-- Bootstrap 5 Carousel -->
				<div id="productCarousel" class="carousel slide w-100 "
					data-bs-ride="carousel">
					<div class="carousel-inner">
						<c:if test="${list.size() >=1}">

							<div class="carousel-item active">
								<img
									src="<%=request.getContextPath()%>/pro/MultiDBGifReader?productId=${proVO.productId}&count=0"
									width=400px height=400px alt="pic"
									class="product-img w-100 d-block">
							</div>
						</c:if>

						<!-- 						c:if 是 jsp中的判斷式，當等list=2 圖片也等於2時會呼叫圖片。 -->
						<c:if test="${list.size() >=2}">

							<div class="carousel-item ">
								<img
									src="<%=request.getContextPath()%>/pro/MultiDBGifReader?productId=${proVO.productId}&count=1"
									width=400px height=400px alt="pic"
									class="product-img w-100 d-block">
							</div>
						</c:if>


						<c:if test="${list.size() >=3}">

							<div class="carousel-item ">
								<img
									src="<%=request.getContextPath()%>/pro/MultiDBGifReader?productId=${proVO.productId}&count=2"
									width=400px height=400px alt="pic"
									class="product-img w-100 d-block">
							</div>
						</c:if>


						<c:if test="${list.size() >=4}">

							<div class="carousel-item">
								<img
									src="<%=request.getContextPath()%>/pro/MultiDBGifReader?productId=${proVO.productId}&count=3"
									width=400px height=400px alt="pic"
									class="product-img w-100 d-block">
							</div>
						</c:if>


						<c:if test="${list.size() >=5}">
							<div class="carousel-item">
								<img
									src="<%=request.getContextPath()%>/pro/MultiDBGifReader?productId=${proVO.productId}&count=4"
									width=400px height=400px alt="pic" class="product-img ">
							</div>
						</c:if>
					</div>


					<!-- 左右切換按鈕 -->
					<button class="carousel-control-prev" type="button"
						data-bs-target="#productCarousel" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"
							style="filter: invert(0%) sepia(100%) saturate(10000%) hue-rotate(0deg) brightness(0.5);"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next " type="button"
						data-bs-target="#productCarousel" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"
							style="filter: invert(0%) sepia(100%) saturate(10000%) hue-rotate(0deg) brightness(0.5);"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>
			</div>

			<!-- 商品資訊區 -->
			<div class="col-md-6">
				<h1 class="product-title">${proVO.productName}</h1>
				<p>${proVO.productContent}</p>

				<!-- 尺寸選擇 -->
				<div class="product-details">
					<label for="size" class="form-label">尺寸</label>
					<p>${proVO.productSpec}</p>
				</div>

				<!-- 數量選擇 -->
				<div class="product-details">
					<label for="quantity" class="form-label">數量</label> <select
						id="quantity" class="form-select w-25">
						<option selected>1</option>
						<option>2</option>
						<option>3</option>
					</select>
				</div>

				<!-- 價格顯示 -->
				<div class="product-price my-3">$${proVO.price}</div>

				<!-- 按鈕區，使用btn-custom-group包裝 -->
				<div class="btn-custom-group">
					<button class="btn btn-dark btn-custom">加入購物車</button>
					<button class="btn btn-dark btn-custom">直接購買</button>
				</div>
			</div>
		</div>
	</div>
		<!-- 其他商品區 -->
	<div class="container">

<a href='<%=request.getContextPath()%>/pro/shop_homepage.jsp' style="display:inline-block!important;width:50vw;">
			<h3>其他推薦商品</h3>
</a>
	<br/>
	<div class="ad_product mt-3">



		<c:forEach var="proVO" items="${proVO_ad}">
			<div class=" mb-3">

					<div class="d-flex flex-column align-items-center " style="width:fit-content;">
						<a 
							href='<%=request.getContextPath()%>/product/pro.do?action=productID&productId=${proVO.productId}'>
							<img
							src="<%=request.getContextPath()%>/pro/DBGifReader?productId=${proVO.productId}"
							width="200px" height="200px" alt="pic" class="product-img">
						</a>
						<div class="product-details">
							<a
								href='<%=request.getContextPath()%>/product/pro.do?action=productID&productId=${proVO.productId}'
								class="productName"> ${proVO.productName} </a>

						</div>
					</div>



			</div>
		</c:forEach>
	</div>

</div>
	<!-- Bootstrap 5 JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</body>

</html>

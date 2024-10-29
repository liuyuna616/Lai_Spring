<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>


<%
    ProService proSvc = new ProService();
    List<ProVO> list = proSvc.getAll();
    pageContext.setAttribute("list",list);    
    pageContext.getAttribute("item");
%>


<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <title>Bootstrap Example</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<style>
/* <!-- 內嵌 CSS --> */
/* 搜尋欄與篩選功能對齊 */
.form-select,
.form-control {
    height: calc(3.5rem + 2px);
/* 設置與篩選選單相等的高度 */
}

/* 圖片大小 */
.icon-size {
    width: 20px;
    height: 20px;
}

/* 商品圖片調整 */
.product-img {
    object-fit: cover;
    max-width: 100%;
}

/* 調整按鈕區塊 */
.icon-button {
    background-color: #f8f9fa;
    /* 灰白底色 */
    border: none;
    border-radius: 50%;
    /* 圓角 */
    padding: 5px;
    margin-top: 5px;
    /* 按鈕上下之間的間距 */
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    /* 添加陰影 */
}

/* 調整購物車和最愛按鈕的區塊 */
.action-container {
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-top: 10px;
}

.product-details {
    flex-grow: 1;
}

.button-group {
    display: flex;
    flex-direction: column;
    /* 垂直排列按鈕 */
    align-items: center;
}

/* 商品價格的樣式 */
.product-price {
    color: red;
    font-weight: bold;
    font-size: 1.5rem;
    /* 調整價格的大小 */
    margin-top: 10px;
    /* 向下移動價格 */
}
.productName{
	color: black;
}

a{
	text-decoration: none;
}


.buy-now {
    background-color: #28a745;
    color: white;
    text-align: center;
    display: inline-block;
    padding: 10px 20px;
    text-decoration: none;
}

/* 調整按鈕樣式 */
button {
    background-color: transparent;
    border: none;
    cursor: pointer;
}

/* 心型圖示樣式 */
.heart {
    font-size: 40px;
    color: black;
    transition: all 0.3s ease;
}

/* 空心心型 */
.heart.empty::before {
    content: '♡';
}

/* 實心心型 */
.heart.filled::before {
    content: '♥';
}

</style>
</head>

<body>
    <!-- 輪播功能 -->
    <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"
                aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
                aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
                aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="images/blackfriday.png" class="d-block w-100" alt="Slide 1">
            </div>
            <div class="carousel-item">
                <img src="images/join.png" class="d-block w-100" alt="Slide 2">
            </div>
            <div class="carousel-item">
                <img src="images/onsale.png" class="d-block w-100" alt="Slide 3">
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
            data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
            data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>

    <!-- 搜尋與篩選功能 -->
    <div class="container my-5">
        <div class="row align-items-center">
          <FORM METHOD="post" ACTION="pro.do" >
      
            <!-- 篩選功能的下拉式選單 -->
            <div class="col-auto">
                <select class="form-select" aria-label="篩選選單">
                     <option class = "aaa" value="0">請選擇</option>
                    <option class = "aaa" value="1">熱門商品</option>
                    <option class = "bbb" value="2">最新上架</option>
                    <option class = "ccc" value="3">價格高低</option>
                </select>
            </div>
            
            <script>
            document.querySelector(".form-select").addEventListener("click",function(e){
            	console.log(e.target.value);
            	if(e.target.value== "1"){
            		window.location='<%=request.getContextPath()%>/product/pro.do?action=choose_popular';
            	}
            	if(e.target.value== "2"){
            		window.location='<%=request.getContextPath()%>/product/pro.do?action=choose_new';
            	}
            	else if (e.target.value== "3"){
            		window.location='<%=request.getContextPath()%>/product/pro.do?action=choose_price';
            	}
            	
         
            })
                                   
            </script>
                    
		 </FORM>


            <!-- 搜尋欄 -->
            <div class="col">
                <form METHOD="post" ACTION="<%=request.getContextPath()%>/product/pro.do" class="d-flex" role="search" >
                    <input name ="keyin" class="form-control me-2" type="search" placeholder="搜尋商品" aria-label="Search">
                    <input type ="hidden" name="action"	value="searchnam">
                    <button class="btn btn-outline-success" type="submit">搜尋</button>
					<p >${errorMsgs.empty1}</p>
					<p >${errorMsgs.empty2}</p>

                </form>
            </div>
        </div>
    </div>

    <!-- 商品區 -->
    
    <div class="container border border-primary my-5">
        <div id="product-container" class="row row-cols-2 row-cols-sm-3 row-cols-md-4 row-cols-lg-5 g-2 g-lg-3 my-3">
         <c:forEach var="proVO" items="${list}" >
           
            <!-- 動態加載商品內容 -->
            <div data-product-id="1" class="col">
                <div class="p-3 border bg-light">
                    <div>
                        <a href='<%=request.getContextPath()%>/product/pro.do?action=productID&productId=${proVO.productId}'>
                            <img src="<%=request.getContextPath()%>/pro/DBGifReader?productId=${proVO.productId}" width=200px height=200px alt="pic" class="product-img">  <!-- 商品圖片取得 -->
                        </a>
                    </div>
                    <div class="action-container">
                        <div class="product-details">
                            <a href='<%=request.getContextPath()%>/product/pro.do?action=productID&productId=${proVO.productId}' class = "productName"> ${proVO.productName}  ${item}  </a>
                            <div class="product-price">$${proVO.price}</div>
                        </div>
                        <div class="button-group">
<!--                             <button id="favoriteBtn" class="favorite"> -->
<!--                                 <i class="heart empty"></i> -->
<!--                             </button> -->
                            <button data-product-id="${proVO.productId}" class="icon-button">
                            	<a href='<%=request.getContextPath()%>/pro/ecommerce_cart.jsp'>  
                            	<!-- 要重新寫一個reader嗎 -->
                               	 <img src="images/shopping-car.png" alt="購物車" class="icon-size">
                                </a>
                            </button>
                        </div>
                    </div>
                    <a href='<%=request.getContextPath()%>/product/pro.do?action=choose_popular' class="btn buy-now mt-3 w-100">直接購買</a>
                </div>
            </div>
             </c:forEach>
        </div>
       
    </div>

    <!-- 分頁功能 -->
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item disabled" id="prevPage">
                <a class="page-link" href="javascript:void(0);" onclick="loadPage(currentPage - 1)">上一頁</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="javascript:void(0);" onclick="loadPage(1)">1</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="javascript:void(0);" onclick="loadPage(2)">2</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="javascript:void(0);" onclick="loadPage(3)">3</a>
            </li>
            <li class="page-item" id="nextPage">
                <a class="page-link" href="javascript:void(0);" onclick="loadPage(currentPage + 1)">下一頁</a>
            </li>
        </ul>
    </nav>
    

    <script>

        // 當頁籤被點擊時，動態加載頁面內容
        function loadPage(pageNumber) {
            // 限制頁面範圍
            if (pageNumber < 1 || pageNumber > totalPages) return;

            // 使用模擬的商品數據
            document.getElementById("product-container").innerHTML = productPages[pageNumber];
            currentPage = pageNumber;  // 更新當前頁面
            updatePagination();  // 更新頁籤狀態
        }

        // 更新「上一頁」和「下一頁」按鈕狀態
        function updatePagination() {
            var prevButton = document.getElementById("prevPage");
            var nextButton = document.getElementById("nextPage");

            if (currentPage === 1) {
                prevButton.classList.add("disabled");
            } else {
                prevButton.classList.remove("disabled");
            }

            if (currentPage === totalPages) {
                nextButton.classList.add("disabled");
            } else {
                nextButton.classList.remove("disabled");
            }
        }

        // 初始化時加載第一頁商品
        window.onload = function () {
            loadPage(1);
        };

        
        <!-- 內嵌 JavaScript -->

           // 觸發加入購物車的功能
           function addToCart(productId) {
               alert("商品 " + ${proVO.productId} + " 已加入購物車！");
           }
   

    </script>
    </head>


</html>
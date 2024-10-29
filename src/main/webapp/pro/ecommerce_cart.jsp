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

  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>購物車</title>
  <!-- 引入 Bootstrap 5 -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

  <style>

    .cart-item {
      border: 1px solid #ddd;
      padding: 15px;
      margin-bottom: 15px;
      border-radius: 5px;
      gap: 0px; /* 調整圖片和名稱之間的間距 */
    }

    .cart-item img {
      width: 100px;
      height: 100px;
    }

    .cart-total {
      font-size: 1.5rem;
      font-weight: bold;
      text-align: right;
    }

    .schedule {
      font-weight: bold;
      color: black;
      text-decoration: underline;
    }

    .quantity-control {
      display: flex;
      justify-content: flex-start; /* 讓數量控制按鈕靠左 */
      align-items: center;
      flex-direction: row; /* 確保按鈕和 "移除" 同行 */
    }

    .quantity-control button {
      width: 30px; /* 調整按鈕寬度 */
      height: 30px; /* 調整按鈕高度 */
      font-size: 1rem; /* 調整按鈕文字大小 */
      display: flex; /* 使用 Flexbox 來對齊內容 */
      justify-content: center; /* 水平置中 */
      align-items: center; /* 垂直置中 */
    }

    .quantity-control .quantity {
      font-size: 1rem; /* 調整數量文字大小 */
      margin: 0 10px; /* 確保按鈕和數量之間有足夠的間距 */
    }

    .remove-btn {
      margin-left: 20px;
      font-size: 10px; /* 調整字體稍小 */
      color: black; /* 使用黑色文字 */
      text-decoration: none; /* 底線 */
      background: none; /* 移除按鈕背景 */
      border: none; /* 移除按鈕邊框 */
      cursor: pointer; /* 鼠標變成指標 */
      padding: 0; /* 移除額外的 padding */
      writing-mode: vertical-lr;
      line-height: 1.5;
    }

    .sidebar {
      background-color: #f8f9fa;
      /* padding: 15px; */
      position: sticky;
      top: 0; /* 讓側邊欄固定在頂部 */
      height: 100vh; /* 側邊欄佔滿整個視窗高度 */
    }

    .sidebar a {
      display: block;
      padding: 10px 10px;
      margin-top: 20px;
      margin-bottom: 10px;
      font-size:14px;
      color: #333;
      text-decoration: none;
    }

    .sidebar a:hover {
      background-color: #e9ecef;
      border-radius: 5px;
    }

    .select-all-btn {
      margin-bottom: 20px;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

    .row {
      display: flex;
      flex-wrap: nowrap;
    }

    .content-area {
      flex-grow: 1;
    }

    .checkout-container {
      display: flex;
      justify-content: flex-end;
      align-items: center;
      gap: 15px; /* 在金額和按鈕之間加入一些間距 */
    }
/* 小於992px時隱藏側邊欄 */
@media (max-width: 992px) {
  .sidebar {
    display: none;
  }
  
  .content-area {
    margin-left: 0;
    flex-grow: 1;
  }
}

/* 介於768px到576px之間時的調整 */
@media (min-width: 576px) and (max-width: 768px) {
  .content-area {
    width: 100%;
  }

  .cart-item img {
    width: 80px;
    height: 80px;
  }

  .quantity-control button {
    width: 25px;
    height: 25px;
    font-size: 0.8rem;
    padding: 0;
    line-height: 25px;
  }

  .quantity-control .quantity {
    font-size: 0.9rem;
    margin: 0 5px;
  }

  .remove-btn {
    font-size: 8px;
    margin-left: 10px;
  }
}

/* 小於576px時進一步調整顯示 */
@media (max-width: 576px) {
  .sidebar {
    display: none;
  }

  .content-area {
    margin-left: 0;
  }

  .quantity-control button {
    width: 20px;
    height: 20px;
    font-size: 0.7rem;
    padding: 0;
    line-height: 20px;
  }

  .quantity-control .quantity {
    font-size: 0.8rem;
    margin: 0 5px;
  }

  .remove-btn {
    font-size: 7px;
    margin-left: 5px;
  }
}

  </style>
</head>
<body>
  <div class="container-fluid border border-primary">
    <div class="row">
      <!-- 側邊欄 -->
      <div class="col-md-1 sidebar">
        <a href="#">購物商城</a>
        <a href="#">訂單紀錄</a>
        <a href="#">我的最愛</a>
        <a href="#">購物車</a>
      </div>

      <!-- 主要內容 -->
      <div class="col-md-10 my-5">
        <h2 class="mb-4">購物車</h2>

        <!-- "全選結帳"按鈕 -->
        <div class="select-all-btn">
          <div>
            <input type="checkbox" id="select-all" onclick="toggleSelectAll()">
            <label for="select-all">全選結帳</label>
          </div>
        </div>
        

        <!-- 商店 A -->
        <div class="schedule">
          <input type="checkbox" id="store-a-checkbox" onclick="toggleStoreItems('store-a', this)">
          <label for="store-a-checkbox" class="mall-label">商城A</label> 預計取貨時間：2024/11/25
        </div>

        <div class="cart-item row store-a">
          <div class="col-3 col-sm-1">
            <input type="checkbox" class="store-a-checkbox item-checkbox" onclick="calculateTotal()">
          </div>
          <div class="col-3 col-md-2">
            <img src="/images/basketball.png" alt="商品1" class="img-fluid">
          </div>
          <div class="col-6 col-md-5">
            <h5>5號初階橡膠籃球</h5>
            <h6>尺寸：</h6>
          </div>
          <div class="col-6 col-md-2" data-price="2500">$2,500</div>
          <div class="col-6 col-md-2 quantity-control">
            <button class="btn btn-outline-secondary" onclick="decreaseQuantity(this)">-</button>
            <span class="quantity">1</span>
            <button class="btn btn-outline-secondary" onclick="increaseQuantity(this)">+</button>
            <button class="remove-btn" onclick="removeItem(this)">移除</button>
          </div>
        </div>

        <div class="cart-item row store-a">
          <div class="col-md-1">
            <input type="checkbox" class="store-a-checkbox item-checkbox" onclick="calculateTotal()">
          </div>
          <div class="col-md-2">
            <img src="/images/racket.png" alt="商品2" class="img-fluid">
          </div>
          <div class="col-md-5">
            <h5>商品2</h5>
            <h6>尺寸：</h6>
          </div>
          <div class="col-md-2" data-price="2000">$2,000</div>
          <div class="col-md-2 quantity-control">
            <button class="btn btn-outline-secondary" onclick="decreaseQuantity(this)">-</button>
            <span class="quantity">2</span>
            <button class="btn btn-outline-secondary" onclick="increaseQuantity(this)">+</button>
            <button class="remove-btn" onclick="removeItem(this)">移除</button>
          </div>
        </div>

        <div class="cart-item row store-a">
          <div class="col-md-1">
            <input type="checkbox" class="store-a-checkbox item-checkbox" onclick="calculateTotal()">
          </div>
          <div class="col-md-2">
            <img src="/images/Resistance bands.png" alt="商品3" class="img-fluid">
          </div>
          <div class="col-md-5">
            <h5>商品3</h5>
            <h6>尺寸：</h6>
          </div>
          <div class="col-md-2" data-price="667">$667</div>
          <div class="col-md-2 quantity-control">
            <button class="btn btn-outline-secondary" onclick="decreaseQuantity(this)">-</button>
            <span class="quantity">3</span>
            <button class="btn btn-outline-secondary" onclick="increaseQuantity(this)">+</button>
            <button class="remove-btn" onclick="removeItem(this)">移除</button>
          </div>
        </div>

        <!-- 商店 B -->
        <div class="schedule">
          <input type="checkbox" id="store-b-checkbox" onclick="toggleStoreItems('store-b', this)">
          <label for="store-b-checkbox" class="mall-label">商城B</label> 預計取貨時間：2024/11/21
        </div>

        <div class="cart-item row store-b">
          <div class="col-md-1">
            <input type="checkbox" class="store-b-checkbox item-checkbox" onclick="calculateTotal()">
          </div>
          <div class="col-md-2">
            <img src="https://via.placeholder.com/100" alt="商品100" class="img-fluid">
          </div>
          <div class="col-md-5">
            <h5>商品4</h5>
            <h6>尺寸：</h6>
          </div>
          <div class="col-md-2" data-price="499">$499</div>
          <div class="col-md-2 quantity-control">
            <button class="btn btn-outline-secondary" onclick="decreaseQuantity(this)">-</button>
            <span class="quantity">1</span>
            <button class="btn btn-outline-secondary" onclick="increaseQuantity(this)">+</button>
            <button class="remove-btn" onclick="removeItem(this)">移除</button>
          </div>
        </div>

        <div class="cart-item row store-b">
          <div class="col-md-1">
            <input type="checkbox" class="store-b-checkbox item-checkbox" onclick="calculateTotal()">
          </div>
          <div class="col-md-2">
            <img src="https://via.placeholder.com/100" alt="商品4" class="img-fluid">
          </div>
          <div class="col-md-5">
            <h5>商品5</h5>
            <h6>尺寸：</h6>
          </div>
          <div class="col-md-2" data-price="100">$100</div>
          <div class="col-md-2 quantity-control">
            <button class="btn btn-outline-secondary" onclick="decreaseQuantity(this)">-</button>
            <span class="quantity">2</span>
            <button class="btn btn-outline-secondary" onclick="increaseQuantity(this)">+</button>
            <button class="remove-btn" onclick="removeItem(this)">移除</button>
          </div>
        </div>

        <!-- 總計金額與前往結帳 -->
        <div class="checkout-container">
          <div class="cart-total">總計金額：$<span id="total-amount">0</span></div>
          <a href="checkout.html" class="btn btn-primary">前往結帳</a>
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
      </div>
    </div>

  <!-- 引入 Bootstrap 5 JS -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

 <!-- 全選功能的 JavaScript -->
  <script>
    const selectAllCheckbox = document.getElementById('select-all');
    const checkboxes = document.querySelectorAll('input[type="checkbox"]');

    selectAllCheckbox.addEventListener('change', function() {
      checkboxes.forEach(checkbox => {
        checkbox.checked = selectAllCheckbox.checked;
      });
      calculateTotal();  // 全選時重新計算總額
    });

    // 增加數量的功能
    function increaseQuantity(button) {
      const quantityElement = button.previousElementSibling;
      let quantity = parseInt(quantityElement.textContent);
      quantity++;
      quantityElement.textContent = quantity;
      calculateTotal();  // 每次數量變更時重新計算總額
    }

    // 減少數量的功能
    function decreaseQuantity(button) {
      const quantityElement = button.nextElementSibling;
      let quantity = parseInt(quantityElement.textContent);
      if (quantity > 1) {
        quantity--;
        quantityElement.textContent = quantity;
        calculateTotal();  // 每次數量變更時重新計算總額
      }
    }

    // 移除商品功能
    function removeItem(button) {
      const cartItem = button.closest('.cart-item');
      cartItem.remove();
      calculateTotal();  // 移除商品時重新計算總額
    }

    // 切換商城商品的選擇狀態
    function toggleStoreItems(storeClass, storeCheckbox) {
      const storeItems = document.querySelectorAll(`.${storeClass} .${storeClass}-checkbox`);
      storeItems.forEach(item => {
        item.checked = storeCheckbox.checked;
      });
      calculateTotal();  // 切換商店勾選時重新計算總額
    }

    // 計算總計金額
    function calculateTotal() {
      let total = 0;
      const selectedItems = document.querySelectorAll('.item-checkbox:checked');
      selectedItems.forEach(item => {
        const cartItem = item.closest('.cart-item');
        const price = parseFloat(cartItem.querySelector('[data-price]').dataset.price);
        const quantity = parseInt(cartItem.querySelector('.quantity').textContent);
        total += price * quantity;
      });
      document.getElementById('total-amount').textContent = total.toFixed(2);
    }
  </script>

</body>
</html>

</html>
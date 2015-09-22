/**
 * home.js
 * 
 * DOM (Document Object Model) Tre
 * 객체화 되어있는 DOM을 조작하고 변동시켜야 화면이 변한다.
 * 
 * Selecter 표현식 : DOM에서 객체(Element, Tag)를 찾는 표현식
 * 
 * 1. 태그이름	ex) <button></button>					==> button
 * 2. class		ex) <button class="btn"></button>		==> .btn
 * 3. id		ex) <button id="slideDown"></button>	==> #slideDown
 * 
 * jQuery 함수 : jQuery()
 * jQuery() 함수가 리턴하는 Type	==> jQuery Wrapper 객체
 * 								==> jQuery 객체
 * 								==> jQuery 집합 객체
 * jQuery() as $()
 * 
 */

// 이벤트 객체를 넘겨줄수 있다.
function xxx(event) {
//	alert("button click...");
	console.log("button click... event = " + event);
//	$('img').slideToggle(1000); //1초
	$('img').fadeToggle(1000);
}

$('span').click(xxx).draggable(); // Method Chain

$('img').draggable();




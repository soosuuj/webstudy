/**
 * 본문을 모두 읽은 뒤 JavaScript를 수행할 수 있도록 load 이벤트 처리한다.
 * 방법 1. window.onload = function(){}
 * 방법 2. $(documnet).ready(function(){})
 * 방법 3. $(function(){})
 */
 
 /*
  * /jsp 이거 변수처리... 안된다!!! jsp가 아니기 때문에 java xx
  *
  */
 
 
 
 $(function(){
  
   $('.gnb a').mouseover(function() {
    $(this).css('background-color', 'silver');
  })
  $('.gnb a').mouseout(function(){
    $(this).css('background-color', '');
  })
  var img = new Image();
 $(img).attr('src', getContextPath() + '/resource/image/naver.png');
 $(img).css('width', '150px');
 $('.logo').html(img); 
 
})
 
 // 컨텍스트패스를 반환하는 함수
 function getContextPath(){
  
  // location.href == http://localhost:8080/jsp/ex03_layout/main.jsp
  // 0/j 이 사이 인덱스 구한거임  var begin
  // jsp/ex03 이 사이 인덱스 end , begin+1부터 찾기 시작해라~
  
  // location.href == localhost:8080
  
  var begin = location.href.indexOf(location.host) + location.host.length;
  var end = location.href.indexOf('/', begin + 1);
  return location.href.substring(begin, end);
}

 
 
 
 
 
 
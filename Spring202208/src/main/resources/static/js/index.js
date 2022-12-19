/**
 * 
 */
 
 $('.btnBoard').on('click',function(){
	console.log('btnBoard 클릭');
	$('#section').load('/board/board_select');
	//경로명이 아니고 mapping 
})

$('.btnGuestBook').on('click',function(){
	console.log('btnGuest 클릭');
	$('#section').load('/guestbook/guestbook_select');
})

/*방명록 최근 10개 표시  */
$('#section>.guestbook').load("/guestbook/guestbook10");

/*게시판 최근 10개 표시 */
$('#section>.board').load("/board/board10");